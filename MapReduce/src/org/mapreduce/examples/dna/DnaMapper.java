package org.mapreduce.examples.dna;

import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class DnaMapper extends Mapper<Object, Text, Text, Text>{

	public void map(Object key, Text value, Context context)
		      throws IOException, InterruptedException {
		String input = value.toString();
		StringTokenizer strTkns = new StringTokenizer(input, " ");
		while(strTkns.hasMoreTokens()){
			String valueOp = strTkns.nextToken();
			String keyOp = strTkns.nextToken();
			context.write(new Text(keyOp), new Text(valueOp));
		}
	}
}
