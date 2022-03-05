package com.kliner.Euterpa3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandPrompt {
	
	
	
	//String MP3folder = "D:\\Music\\";
	
	//builds the ffmpeg command to slice the OST into a single song
	static String BuildCommand(String ost, String song, String startTime, int length)
	{
		return "ffmpeg -ss " + startTime + " -i " + StringFormatter.SurroundWithQuotes(ost) + " -t " + length + " -vcodec copy -acodec copy temp//" + StringFormatter.SurroundWithQuotes(song + ".mp3");
	}
	
	//runs the ffmpeg command in the MP3s directory
	static void RunCommand(String command) throws Exception
	{
		ProcessBuilder builder = new ProcessBuilder(
	            "cmd.exe", "/c", "cd " + Globals.MUSIC_LIBRARY_PATH + " && " + command);
	        builder.redirectErrorStream(true);
	        Process p = builder.start();
	        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
	        String line;
	        while (true) {
	            line = r.readLine();

	            if (line == null) { break; }
	        }
	}

	
	//runs the ffmpeg command in a different library
	static void RunCommand(String command, String dir) throws Exception
	{
		System.out.print("Time to run " +command);
		ProcessBuilder builder = new ProcessBuilder(
	            "cmd.exe", "/c", "cd " + StringFormatter.SurroundWithQuotes(dir) + " && " + command);

	        builder.redirectErrorStream(true);
	        Process p = builder.start();
	        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
	        String line;
	        while (true) {
	            line = r.readLine();
	            if (line == null) { break; }
	            System.out.println(line);
	        }
	}
	
	

}
