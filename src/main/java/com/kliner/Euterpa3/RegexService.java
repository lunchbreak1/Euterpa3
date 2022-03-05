package com.kliner.Euterpa3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexService {
	
	public static final String REGEX_TIME_PATTERN = "\\d{1,2}[:]\\d\\d[:]\\d\\d|\\d{1,2}[:]\\d\\d";
	public static final String NUMBER_PATTERN = "\\d+";
	
	//uses regex to extract the title of a song
			public static String ExtractTitle(String str, String time)
			{
				String title = "";
				title = str.replace(time, "");
				return title;
			}
			
			public static String ExtractTitle(String str, String time, boolean removeNumbers)
			{
				String title = "";
				title = str.replace(time, "");
				title = title.replace("\"", " ");
				title = title.replace("// ", " ");
				title = title.replace(":", "");
				title = title.replace("?", "");

				return title;
			}
			
			//uses regex to extract the time of a song
			public static String ExtractTime(String str)
			{
				Pattern pattern = Pattern.compile(REGEX_TIME_PATTERN);
				Matcher matcher = pattern.matcher(str);
				String time = "";
				while (matcher.find())
				{
					time = matcher.group(0);
				}
				return time;
			}

}
