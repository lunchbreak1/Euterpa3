package com.kliner.Euterpa3;

import org.jaudiotagger.*;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
//import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.FieldDataInvalidException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.KeyNotFoundException;
import org.jaudiotagger.tag.Tag;

import com.mpatric.mp3agic.Mp3File;

// This class writes IPv3 frames
public class FrameWriter {
	
	public static void SetSortCode(String pathToFile, String sortCode)
	{
		try
		{
			AudioFile f = new MP3File(pathToFile);
			
			Tag tag = f.getTag();
			
			AudioHeader header = f.getAudioHeader();
			
			tag.setField(FieldKey.ALBUM_SORT, sortCode);
			tag.setField(FieldKey.ALBUM_ARTIST_SORT, sortCode);
			tag.setField(FieldKey.ARTIST_SORT, sortCode);
			tag.setField(FieldKey.COMPOSER_SORT, sortCode);
			
			f.commit();
		}
		catch(Exception e)
		{
			System.out.print("Exception from FrameWriter, couldn't set the sort code for " + pathToFile);
		}

	}
	
	public static void SetYear(String pathToFile, String year)
	{
		try
		{
			AudioFile f = new MP3File(pathToFile);
			
			Tag tag = f.getTag();
			
			AudioHeader header = f.getAudioHeader();
			
			tag.setField(FieldKey.YEAR, year);
		
			f.commit();
		}
		catch(Exception e)
		{
			System.out.print("Exception from FrameWriter, couldn't set the year for " + pathToFile);
		}
	}

}
