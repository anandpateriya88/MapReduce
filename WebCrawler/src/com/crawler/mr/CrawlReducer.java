package com.crawler.mr;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class CrawlReducer extends Reducer<Text, LongWritable, Text, LongWritable> {

	
	 private Path[] localArchives;
     private Path[] localFiles;
     
   /*  public void configure(JobConf job) {
    	 try{
       // Get the cached archives/files
       localArchives = DistributedCache.getLocalCacheArchives(job);
       localFiles = DistributedCache.getLocalCacheFiles(job);
    	 }catch (Exception e){
    		 System.out.println("exception ----====>>"+ e);
    	 }
     }*/
	@Override
	  public void reduce(Text key, Iterable<LongWritable> values, Context context)
	      throws IOException, InterruptedException {
		  
		  long sum = 0;
		  for(LongWritable iw:values)
		  {
			  sum += iw.get();
		  }
		  context.write(key, new LongWritable(sum));
	  }
}
