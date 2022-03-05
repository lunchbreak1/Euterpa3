package com.kliner.Euterpa3;

import static java.time.temporal.ChronoUnit.SECONDS;

import java.time.LocalTime;



// A timestamp consists of a song and a time. For example,
// the song "Peach's Castle" that appears at 06:14 in the Super Mario 64 OST

public class Timestamp 
{
	public String beginTime;
	public String endTime;
	public String title;
	public int length;
	
	public Timestamp(String title, String beginTime)
	{
		this.beginTime = FormatTime(beginTime);
		this.title = FormatTitle(title);
	}
	
	public Timestamp(String title, String removeChars, String trimChars, String beginTime)
	{
		this.beginTime = FormatTime(beginTime);
		this.title = FormatTitle(title, removeChars, trimChars);
	}
	
	// LATEST VERSION 
	public Timestamp(String title, String removeChars, String trimChars, String beginTime, boolean removeLeadingNums)
	{
		this.beginTime = FormatTime(beginTime);
		this.title = FormatTitle(title, removeChars, trimChars, removeLeadingNums);
	}
	
	public Timestamp(String title, String beginTime, String endTime)
	{
		this.beginTime = FormatTime(beginTime);
		this.endTime = FormatTime(endTime);
		this.title = FormatTitle(title);
		length = GetLength(beginTime, endTime);
	}
	
	public Timestamp(String title, String removeChars, String trimChars, String beginTime, String endTime)
	{
		this.beginTime = FormatTime(beginTime);
		this.endTime = FormatTime(endTime);
		this.title = FormatTitle(title, removeChars, trimChars);
		length = GetLength(beginTime, endTime);
	}
	
	//formats the begin and end times
	private String FormatTime(String time)
	{
		if(time.length() == 7)
		{
			return "0" + time;
		}
		if(time.length() == 5)
		{
			return "00:" + time;
		}
		else if(time.length() == 4)
		{
			return "00:0" + time;
		}
		else
		{
			return time;
		}
	}
	
	//formats the title
	private String FormatTitle(String str)
	{
		//System.out.print("oy");
		
		str = str.replace("•", "");

		str = str.replace("\\", "");
		str = str.replace("\"", "");
		str = str.replace("/", "");
		str = str.replace("{}", "");
		str = str.replace("[]", "");
		str = str.replace("()", "");
		str = str.replace("{ }", "");
		str = str.replace("[ ]", "");
		str = str.replace("( )", "");
		str = str.replace("|", "");

		
		str = str.trim();
		
		while(str.charAt(0) == '-' || str.charAt(0) == ':' || str.charAt(0) == '.'
				|| str.charAt(0) == ')'
				|| str.charAt(0) == '/'
				|| (str.charAt(0) >= '0' && str.charAt(0) <= '9'))
		{
			str = str.substring(1);
			str = str.trim();
		}
		
		while(str.charAt(str.length() - 1) == '-' || str.charAt(str.length() - 1) == ':'
				|| str.charAt(str.length() - 1) == '.'
				|| str.charAt(str.length() - 1) == '('
				|| str.charAt(str.length() - 1) == '/')
		{
			str = str.substring(0, str.length() - 1);
			str = str.trim();
		}
		
		return str.trim();
	}
	
	private String FormatTitle(String str, String removeStr, String trimStr)
	{	
		for(int i = 1; i < removeStr.length(); i++)
		{
			str = str.replace(removeStr.substring(i - 1, i), "");
		}
		

		str = str.trim();

		
		while(trimStr.contains(str.substring(0,1)))
		{
			str = str.substring(1);
			str = str.trim();
		}
		
		while(trimStr.contains(str.substring(str.length() - 1,str.length())))
		{
			str = str.substring(0, str.length() - 1);
			str = str.trim();
		}
		
		return str.trim();
	}
	
	private String FormatTitle(String str, String removeStr, String trimStr, boolean trimLeadingNums)
	{	
		str = str.trim();
		
		if(trimLeadingNums)
		{	
			while (Character.isDigit(str.charAt(0)) || str.charAt(0) == 0)
			{
				str = str.trim();
				str = str.substring(1);
				str = str.trim();
			}
		}
		
		for(int i = 1; i < removeStr.length(); i++)
		{
			str = str.trim();
			str = str.replace(removeStr.substring(i - 1, i), "");
			str = str.trim();
		}
		

		str = str.trim();

		
		while(trimStr.contains(str.substring(0,1)))
		{
			str = str.substring(1);
			str = str.trim();
		}
		
		while(trimStr.contains(str.substring(str.length() - 1,str.length())))
		{
			str = str.substring(0, str.length() - 1);
			str = str.trim();
		}
		
		return str.trim();
	}
	
	public int GetLength()
	{
		LocalTime start = LocalTime.parse(beginTime);
        LocalTime end = LocalTime.parse(endTime);

		return (int)SECONDS.between(start, end);
	}
	
	//returns the length of the song in seconds
	public int GetLength(String timestamp1, String timestamp2)
	{
		LocalTime start = LocalTime.parse(timestamp1);
        LocalTime end = LocalTime.parse(timestamp2);

		return (int)SECONDS.between(start, end);
	}
}
