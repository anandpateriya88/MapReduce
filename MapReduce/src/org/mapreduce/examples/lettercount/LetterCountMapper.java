package org.mapreduce.examples.lettercount;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class LetterCountMapper extends Mapper<Object, Text, Text, LongWritable> {

	public void map(Object key, Text value, Context context)
		      throws IOException, InterruptedException {
			  char[] inputText = value.toString().replaceAll(" ", "").toCharArray();
			  for(Character c: inputText){
				  context.write(new Text(c.toString()), new LongWritable(1));
			  }
		  }
}
