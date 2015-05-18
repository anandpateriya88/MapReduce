package org.mapreduce.examples.anagrams;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class AnagramsReducer extends Reducer<Text, Text, Text, Text> {

	@Override
	  public void reduce(Text key, Iterable<Text> values, Context context)
	      throws IOException, InterruptedException {
		 String anagrams = new String();
		 for(Text iw:values)
		  {
			 anagrams = iw + " "+anagrams;
		  }
		 String[] anagramsArray = anagrams.split(" ");
		 if(anagramsArray.length >1){
		  context.write(key, new Text(anagrams));
		 }
	  }
}
