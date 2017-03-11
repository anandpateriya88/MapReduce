package com.crawler.mr;

import java.io.IOException;
import java.util.List;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.arch.domainobjects.PropertyDetails;
import com.crawler.services.Spider;

public class CrawlMapper extends Mapper<Object, Text, Text, LongWritable> {

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
     
	
	public void map(Object key, Text value, Context context)
		      throws IOException, InterruptedException {
			  String url = value.toString().trim();
			  Spider spider = new Spider();
			  System.out.println("position Mapper 1");
			  List<PropertyDetails> allPropoertiesLst = spider.getAllPropertiesDtls(url);
			  System.out.println("All properties list creation Done . No of properties added -"+allPropoertiesLst.size());
			  //String tempVar = success.split(" ")[0];
				//  context.write(new Text(tempVar + "====>>>>"+url), new LongWritable(1));
		  }
}
