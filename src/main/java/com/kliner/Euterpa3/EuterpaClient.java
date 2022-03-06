package com.kliner.Euterpa3;

import java.io.*;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.mpatric.mp3agic.*;

import java.time.LocalTime; 
import static java.time.temporal.ChronoUnit.SECONDS;

public class EuterpaClient {
	
	//String MP3folder = "D:\\Music\\";
	
	public String removeChars = "", trimChars = "";
	
	public String ostLength = "00:00:00";
	
	public static String timestampsDirectory = "Timestamps\\";
	public String timeStampFile = "TimestampsTest.txt";
	public ArrayList<Timestamp> timestamps;
	
	public static void run(String ost, String directory, ArrayList<String> tstamps, String art, 
			String series, String artist, String director, String medium, String company, String year, String sortCode,
			String removeChars, String trimChars, boolean removeNums)
	{
		//read folder for mp3 files
		//get a list of timestamps
		//cut mp3 up based on timestamps
		//return cut up mp3 files
		try
		{
			String ostLength = GetOSTLength(ost, Globals.MUSIC_LIBRARY_PATH);
			
			String tempDir = Globals.MUSIC_LIBRARY_PATH + "temp\\";
			String albumDir = StringFormatter.SurroundWithQuotes(Globals.MUSIC_LIBRARY_PATH + ost.replace(".mp3", ""));

			CommandPrompt.RunCommand("mkdir " + tempDir, Globals.MUSIC_LIBRARY_PATH);
			CommandPrompt.RunCommand("mkdir " + albumDir, Globals.MUSIC_LIBRARY_PATH);
			ArrayList<Timestamp> timestamps = LoadTimestamps(tstamps, removeChars, trimChars, removeNums);
			sliceOST(ost, ostLength, directory, timestamps, artist, director, series, year, company, medium, art, sortCode);
			CommandPrompt.RunCommand("rmdir " + tempDir, StringFormatter.SurroundWithQuotes(Globals.MUSIC_LIBRARY_PATH));
			System.out.print("Process complete!");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
			return;
		}
	}
	
	//LATEST
	public static void edit(String directory, String omitString, 
			String artist, String director, String company, String year, boolean trimLeadingNums, boolean addTrackNums, String sortCode)
	{
		//read folder for mp3 files
		//get a list of timestamps
		//cut mp3 up based on timestamps
		//return cut up mp3 files
		try
		{
			editPlaylist(directory, omitString, artist, director, company, year, trimLeadingNums, addTrackNums, sortCode);
			
			//loop through every file in the directory and run setProperties on each mp3 file

			System.out.print("Process complete!");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
			return;
		}
	}
	
	static String GetOSTLength(String ost)
	{
		try
		{
			String command = "ffmpeg -i " + StringFormatter.SurroundWithQuotes(ost) + " 2>&1";
	//		CommandPrompt.RunCommand(command);
	//		System.out.println("command: " + command);
			ProcessBuilder builder = new ProcessBuilder(
		            "cmd.exe", "/c", "cd " + Globals.MUSIC_LIBRARY_PATH + " && " + command);

		        builder.redirectErrorStream(true);
		        Process p = builder.start();
		        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
		        String line;
		        while (true) {
		            line = r.readLine();
		     //       System.out.println(line);
		            if (line == null) { break; }
		            if(line.contains("Duration"))
		            {
		            	String duration = RegexService.ExtractTime(line);
		            	if(duration != null)
		            	{
		            	//	System.out.print("OST Length: " + duration);
		            		return duration;
		            	}
		            }
		        }
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "";
		} 
		return "";
	}
	
	 static String GetOSTLength(String ost, String dir) {
		try
		{
			//String command = "ffmpeg -i " + StringFormatter.SurroundWithQuotes(ost) + " 2>&1";
			String command = "ffmpeg -i " + StringFormatter.SurroundWithQuotes(dir + ost);
		//	System.out.print("command: " + command);
			//CommandPrompt.RunCommand(command);
			ProcessBuilder builder = new ProcessBuilder(
		            "cmd.exe", "/c", "cd " + dir + " && " + command);

		        builder.redirectErrorStream(true);
		        Process p = builder.start();
		        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
		        String line;
		        while (true) {
		            line = r.readLine();
		            System.out.print("Result from GetOSTLength: " + line);
		            if (line == null) { break; }
		            if(line.contains("Duration"))
		            {
		            	String duration = RegexService.ExtractTime(line);
		            	if(duration != null)
		            	{
		            	//	System.out.println("OST Length: " + duration);
		            		return duration;
		            	}
		            }
		            
		        }
		}
		catch(Exception e)
		{
			
			e.printStackTrace();
			return "";
		}
		return "";
	}
	
	
	//LATEST ???
	static ArrayList<Timestamp> LoadTimestamps(ArrayList<String> input, String removeChars, String trimChars, boolean removeNums)
	{
		
		ArrayList<Timestamp> ts = new ArrayList<Timestamp>();
		
		try
		{
			for(String str : input)
			{		        
		        String beginTime = RegexService.ExtractTime(str);
		   //     System.out.println("HEY! Its the latest and greatest LoadTimestamps() !!!");
		        if(beginTime == null)
		        {
		        	break;
		        }
		        
		        String title = RegexService.ExtractTitle(str, beginTime, removeNums);
		        
		        Timestamp timestamp = new Timestamp(title, removeChars, trimChars, beginTime, removeNums);
		   //     System.out.println(timestamp.title + " " + timestamp.beginTime);
		       
		        ts.add(timestamp);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return ts;
	}

	static void sliceOST(String ost, String ostLength, String directory, ArrayList<Timestamp> timestamps, String artist, String director, String series, String year, String company,
			String medium, String art, String sortCode)
	{
		try
		{
			String command = "";
			for(int i = 0; i < timestamps.size() - 1; i++)
			{
				Timestamp timestamp = timestamps.get(i);
				Timestamp next = timestamps.get(i + 1);
				int length = GetLength(timestamp, next);
				command = CommandPrompt.BuildCommand(Globals.MUSIC_LIBRARY_PATH + ost, timestamp.title, timestamp.beginTime, length);
				CommandPrompt.RunCommand(command, directory);
				setSongProperties(timestamp.title, i+1, ost.replace(".mp3", ""), artist, director, series, year, company, medium, art, sortCode);
			}

				command = CommandPrompt.BuildCommand(Globals.MUSIC_LIBRARY_PATH + ost, 
		 								  timestamps.get(timestamps.size() - 1).title, 
		 								  timestamps.get(timestamps.size() - 1).beginTime, 
		 								GetLength(timestamps.get(timestamps.size() -1).beginTime, ostLength));
				CommandPrompt.RunCommand(command, directory);
				setSongProperties(timestamps.get(timestamps.size() - 1).title, timestamps.size(), ost.replace(".mp3", ""), artist, director, series, year, company, medium, art, sortCode);
		}
		catch(Exception e)
		{
			e.printStackTrace(); 
		}
			
		
	}
	
	static void setSongProperties(String song, int trackNumber, String album, String artist, String director,   
			  String series, String year, String company, String medium, String art, String sortCode)
	{
		try
		{
			Mp3File mp3file = new Mp3File(Globals.MUSIC_LIBRARY_PATH + "\\temp\\" + song + ".mp3");

	        if (mp3file.hasId3v2Tag()) {
	        	ID3v2 id3v2Tag = mp3file.getId3v2Tag();
	            byte[] imageData = id3v2Tag.getAlbumImage();
	            if (imageData != null) {
					String mimeType = id3v2Tag.getAlbumImageMimeType();
					//System.out.println("Mime type: " + mimeType);
					// Write image to file - can determine appropriate file extension from the mime type
					RandomAccessFile file = new RandomAccessFile("album-artwork", "rw");
					file.write(imageData);
					file.close();
	            }
	        }
	        
	       
	        
	        
	        
	        ID3v2 id3v2Tag;
	        
	        if (mp3file.hasId3v2Tag()) {
	        	id3v2Tag =  mp3file.getId3v2Tag();
	        } else {
	        	id3v2Tag = new ID3v24Tag();
	        	mp3file.setId3v2Tag(id3v2Tag);
	        }

	        
	        id3v2Tag.setTrack("" + trackNumber);
	        id3v2Tag.setArtist(artist);
	        id3v2Tag.setAlbumArtist(artist);
	        //id3v2Tag.setComposer(director);
	        
	        id3v2Tag.setAlbum(album);
	        
	        id3v2Tag.setGrouping(series);
	        id3v2Tag.setYear(year);
	       // id3v2Tag.set

	        //id3v2Tag.setPublisher(company);
	        id3v2Tag.setComment("Studio: " + company + ", Directed by: " + director);
	       
	        id3v2Tag.setTitle(song);
	        
	        
	        
	      //Before saving anything, check if there is a song that already has that name.

	        SaveSongAs(mp3file,  Globals.MUSIC_LIBRARY_PATH + album + "\\" + song);
	        SetYear(Globals.MUSIC_LIBRARY_PATH + album + "\\" + song + ".mp3", year);
	        SetSortCode(Globals.MUSIC_LIBRARY_PATH + album + "\\" + song + ".mp3", sortCode);

	        CommandPrompt.RunCommand("del " + Globals.MUSIC_LIBRARY_PATH + "temp\\" + StringFormatter.SurroundWithQuotes(song + ".mp3"));
	        if (mp3file.hasId3v2Tag()) {
	        	
	        }
	        
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	// This method checks if a song already exists. If it exists, save the song with an iteration of 2
	public static void SaveSongAs(Mp3File file, String song) throws UnsupportedTagException, InvalidDataException, IOException, NotSupportedException
	{
		File fileToCheck = new File(song + ".mp3");
		
		if(fileToCheck.exists())
		{
			SaveSongAs(file, song, 2);
		}
		else
		{
			file.save(song + ".mp3");
		}
	}
	
	public static void SaveSongAs(Mp3File file, String song, int iterations)
	{
		try {
			File fileToCheck = new File(song + " " + iterations + ".mp3");
			
			if(fileToCheck.exists())
			{
				iterations++;
				SaveSongAs(file, song, iterations);
			}
			else
			{
				file.save(song + " " + iterations + ".mp3");
			}
		}
		catch(Exception e)
		{
			
		}
	}
	
	static String ReplaceHourlySongs(String song)
	{
		song = song.replace("10 AM", "Ten o clock AM");
		song = song.replace("11 AM", "Eleven o clock AM");
		song = song.replace("12 AM", "Midnight");
		
		song = song.replace("10 PM", "Ten o clock PM");
		song = song.replace("11 PM", "Eleven o clock PM");
		song = song.replace("12 PM", "Noon");
		
		song = song.replace("1 AM", "One o clock AM");
		song = song.replace("2 AM", "Two o clock AM");
		song = song.replace("3 AM", "Three o clock AM");
		song = song.replace("4 AM", "Four o clock AM");
		song = song.replace("5 AM", "Five o clock AM");
		song = song.replace("6 AM", "Six o clock AM");
		song = song.replace("7 AM", "Seven o clock AM");
		song = song.replace("8 AM", "Eight o clock AM");
		song = song.replace("9 AM", "Nine o clock AM");

		song = song.replace("1 PM", "One o clock PM");
		song = song.replace("2 PM", "Two o clock PM");
		song = song.replace("3 PM", "Three o clock PM");
		song = song.replace("4 PM", "Four o clock PM");
		song = song.replace("5 PM", "Five o clock PM");
		song = song.replace("6 PM", "Six o clock PM");
		song = song.replace("7 PM", "Seven o clock PM");
		song = song.replace("8 PM", "Eight o clock PM");
		song = song.replace("9 PM", "Nine o clock PM");

		
		return song;
	}
	
	// LATEST WORKING EDIT FUNCTION
	static void setSongProperties(String song, String dir, String toOmit, int trackNumber, String albumName, String artist, String director,   
			 String company, String year, boolean trimLeadingNums, boolean addTrackNums, String sortCode)
	{
		String tempDir = dir + "\\temp\\";

		try
		{	
			System.out.println("Changing file: " + dir + "\\" + song);
			Mp3File mp3file = new Mp3File(dir + "\\" + song);


	        if (mp3file.hasId3v2Tag()) {
	        	ID3v2 id3v2Tag = mp3file.getId3v2Tag();
	            byte[] imageData = id3v2Tag.getAlbumImage();
	            if (imageData != null) {
					String mimeType = id3v2Tag.getAlbumImageMimeType();
	            }
	        }

	        ID3v2 id3v2Tag;
	        
	        if (mp3file.hasId3v2Tag()) {
	        	id3v2Tag =  mp3file.getId3v2Tag();
	        } else {
	        	id3v2Tag = new ID3v24Tag();
	        	mp3file.setId3v2Tag(id3v2Tag);
	        }

	        if(id3v2Tag.getTrack()==null)
	        {
	        	id3v2Tag.setTrack("" + trackNumber);
	        }
	        
	        if(artist.length() > 0)
	        {
		        id3v2Tag.setArtist(artist);
			    id3v2Tag.setAlbum(albumName);
			    id3v2Tag.setAlbumArtist(artist);
	        }

	       /* if(year.length() > 0)
	        {
	        	id3v2Tag.setYear(year);
	        }*/
	        
	        String comment = "";
	        
	        if(company.length() > 0)
	        {
	        	comment += "Studio: " + company;
	        }
	        
	        
	        if(director.length() > 0)
	        {
	        	comment += " Directed by: " + director;
	        }
	        
	        if(comment.length() > 0)
	        {
	        	id3v2Tag.setComment(comment);
	        }
	        
	        
	        String newTrackName;
	   
	        if(toOmit.length() > 0 && song.contains(toOmit))
	        {
	        	newTrackName = song.replace(toOmit, ""); 
	        	newTrackName = newTrackName.trim();
	        	
				if(trimLeadingNums)
				{	
					while (Character.isDigit(newTrackName.charAt(0)) || newTrackName.charAt(0) == 0 || newTrackName.charAt(0) == ' ')
					{
						newTrackName = newTrackName.substring(1);
						newTrackName = newTrackName.trim();
					}
				}

	        	id3v2Tag.setTitle(newTrackName.replace(".mp3", ""));
	        	mp3file.save(dir + "\\" + newTrackName);
	        	SetYear(dir + newTrackName, year);
	        	SetSortCode(dir + newTrackName, sortCode);       	
	        	CommandPrompt.RunCommand("del " + StringFormatter.SurroundWithQuotes(Globals.MUSIC_LIBRARY_PATH + "\\" + albumName + "\\" + song), StringFormatter.SurroundWithQuotes(Globals.MUSIC_LIBRARY_PATH + "\\" + albumName + "\\"));
	        }
	        else
	        {
	        	
	        	newTrackName = song; 
	        	newTrackName = newTrackName.trim();
	        	
				if(trimLeadingNums)
				{	
					while (Character.isDigit(newTrackName.charAt(0)) || newTrackName.charAt(0) == 0 || newTrackName.charAt(0) == ' ')
					{
						newTrackName = newTrackName.trim();
						newTrackName = newTrackName.substring(1);  
						newTrackName = newTrackName.trim();
					}
				}
				
				//newTrackName = ReplaceHourlySongs(newTrackName);
	        	
	        	CommandPrompt.RunCommand("mkdir " + StringFormatter.SurroundWithQuotes(tempDir));
	        	id3v2Tag.setTitle(newTrackName.replace(".mp3", ""));
	        	mp3file.save(tempDir + newTrackName);
	        	SetYear(tempDir + newTrackName, year);
	        	SetSortCode(tempDir + newTrackName, sortCode);
	        	
	        	String copyCommand = "xcopy " + StringFormatter.SurroundWithQuotes(tempDir + newTrackName) + " " + StringFormatter.SurroundWithQuotes(dir);
	        	System.out.println("Here's the copy command: " + copyCommand);
	        	CommandPrompt.RunCommand("del " + StringFormatter.SurroundWithQuotes(dir + "\\" + song), dir);
	        	CommandPrompt.RunCommand(copyCommand, dir);
	        	CommandPrompt.RunCommand("del " + StringFormatter.SurroundWithQuotes(tempDir + "\\" + newTrackName), tempDir);
	        	CommandPrompt.RunCommand("rmdir " + StringFormatter.SurroundWithQuotes(tempDir));
	        }
	        if (mp3file.hasId3v2Tag()) {

	        } 
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	//gets the length of a song
	public static int  GetLength(Timestamp timestamp1, Timestamp timestamp2)
	{
		LocalTime start = LocalTime.parse(timestamp1.beginTime);
        LocalTime end = LocalTime.parse(timestamp2.beginTime);

		return (int)SECONDS.between(start, end);
	}
	
	public static int GetLength(String timestamp1, String timestamp2)
	{
		LocalTime start = LocalTime.parse(timestamp1);
        LocalTime end = LocalTime.parse(timestamp2);

		return (int)SECONDS.between(start, end);
	}
	
	
	//gets the name of the current folder (not the full directory name). there might be an easier way to do this but I'm tired...
	public static String getCurrentFolder(String dir)
	{
		String folder = "";
		
		Path path = Paths.get(dir);
		
		folder = path.getFileName().toString();
		
		return folder;
	}
	
	//LATEST VERSION
	public static void editPlaylist(String path, String strToOmit, String artist, String director,
			String year, String company, boolean trimLeadingNums, boolean addTrackNums, String sortCode)
	{
		String album = getCurrentFolder(path);
		
		File dir = new File(path);
		  File[] files = dir.listFiles();
		  
		  Arrays.sort(files, new Comparator<File>(){
			    public int compare(File f1, File f2)
			    {
			        return Long.valueOf(f1.lastModified()).compareTo(f2.lastModified());
			    } });
		  
		  
		  if (files != null) 
		  {
		    for (File file : files) 
		    {
		    	Date date = new Date(file.lastModified());
		    }
		  } 

		  for(int i = 0; i < files.length; i++)
		  {
			  String name = files[i].getName();

				  setSongProperties(name, path, strToOmit, i+1, album, artist, director, 
						  year, company, trimLeadingNums, addTrackNums, sortCode);
		  }
		  
		  try {
				CommandPrompt.RunCommand("rmdir " + StringFormatter.SurroundWithQuotes(dir + "//temp"), Globals.MUSIC_LIBRARY_PATH);
			  } catch (Exception e) 
		  		{
				  // TODO Auto-generated catch block
				  System.out.println("Couldn't remove the temp directory");
			    } 
	 }
	
	
	public static void SetSortCode(String pathToFile, String sortCode)
	{
		if(sortCode.length() > 0)
		{
			FrameWriter.SetSortCode(pathToFile, sortCode);
		}
	}
	
	public static void SetYear(String pathToFile, String year)
	{
		if(year.length() > 0)
		{
			FrameWriter.SetYear(pathToFile, year);
		}
	}
	
	public static void delete(String path) throws Exception
	{
		CommandPrompt.RunCommand("unlocker " + StringFormatter.SurroundWithQuotes(path) + " -S -D", "C:\\Program Files\\Unlocker");
	}
}

