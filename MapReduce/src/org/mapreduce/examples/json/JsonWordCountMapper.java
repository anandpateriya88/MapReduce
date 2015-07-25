package org.mapreduce.examples.json;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonWordCountMapper extends Mapper<Object, Text, Text, IntWritable>{
	
	public void map(Object key, Text value, Context context){
	
		try {
			JSONObject jsn = new JSONObject(value.toString());
			 String text = (String) jsn.get("text"); // getting all the values in json.
			 StringTokenizer itr = new StringTokenizer(text);
			 Text word = new Text();
	            while (itr.hasMoreTokens()) {
	                word.set(itr.nextToken());
	                context.write(word, new IntWritable(1));
	            }
		} catch (JSONException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


}
