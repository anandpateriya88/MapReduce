import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class StubMapper extends Mapper<Object, Text, Text, LongWritable> {

  @Override
  public void map(Object key, Text value, Context context)
      throws IOException, InterruptedException {

    /*
     * TODO implement
     */
	  String[] words = value.toString().split("[ \t]+");
	  for(String word:words)
	  {
		  context.write(new Text(word), new LongWritable(1));
	  }
  }
}
