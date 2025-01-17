package main.java.databaseBuilder.fileparser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeSet;

/**
 * @author Felix Tristram
 * Parser for the stopwords file, returns all stopwords in a TreeSet.
 */
public class StopWordParser {

	public static TreeSet<String> parseStopwords(String filePath) throws IOException{
		TreeSet<String> stopWords = new TreeSet<String>();
		
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String line;
		while((line = br.readLine()) != null){
			String[] splitLine = line.split(",");
			for(String word: splitLine){
				stopWords.add(word);
			}
		}
		br.close();
		
		return stopWords;
	}
}
