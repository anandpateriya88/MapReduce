package org.mapreduce.examples.anagrams;

import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class AnagramsMapper  extends Mapper<Object, Text, Text, Text> {
	public void map(Object key, Text value, Context context)
		      throws IOException, InterruptedException {
		String inputWord = value.toString();
		StringTokenizer strTkns = new StringTokenizer(inputWord, " ");
		while(strTkns.hasMoreTokens()){
			String word = strTkns.nextToken();
			char[] anagramKey = word.toCharArray();
			Arrays.sort(anagramKey);
			String anagramKeyFormatted = new String(anagramKey);
			context.write(new Text(anagramKeyFormatted), new Text(word));
		}
	}
}
