package com.crawler.mr;

import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import com.crawler.services.Spider;

public class CrawlDriver {
	public static void main(String[] args) throws Exception {
		if (args.length != 2) {
			System.out.printf("Usage: StubDriver <input dir> <output dir>\n");
			System.exit(-1);
		}
		JobConf conf = new JobConf();
	//	DistributedCache.addFileToClassPath(new Path("/user/hadoop/jsoup-1.7.2.jar"), conf);
		Job job = new Job(conf, "wordcount");
		job.setJarByClass(CrawlDriver.class);
		job.setMapperClass(CrawlMapper.class);
		job.setReducerClass(CrawlReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(LongWritable.class);
//		job.setCombinerClass(CrawlReducer.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(LongWritable.class);
		System.out.println("===============before Sucess =======");	
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		boolean result = job.waitForCompletion(true);
		System.exit(result ? 0 : 1);
	}
}
