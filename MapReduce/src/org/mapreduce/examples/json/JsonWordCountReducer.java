package org.mapreduce.examples.json;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class JsonWordCountReducer extends Reducer<Text, Iterable<IntWritable>, Text, IntWritable>{

	  public void reduce(Text key, Iterable<IntWritable> values, Context context)
	      throws IOException, InterruptedException {
		 int reducerValue = 0;
		 for(IntWritable iw:values)
		  {
			 reducerValue += iw.get();
		  }
		  context.write(key, new IntWritable(reducerValue));
	  }
}
