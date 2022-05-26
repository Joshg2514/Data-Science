package stubs;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

public class CountReducer extends Reducer<Text, Text, Text, IntWritable> {

  @Override
  public void reduce(Text key, Iterable<Text> values, Context context)
      throws IOException, InterruptedException {
    
	 
	  Set<Text> ipset = new HashSet<>();
	  int ipcount = 1;
			
	  /*
	  * For each value in the set of values passed to us by the mapper:
	  */
	  for (Text value : values) {
			  
		/*
		* Add the value to the word count counter for this key.
		*/
		if(!ipset.add(value))
			ipcount++;
		}
			
		/*
		* Call the write method on the Context object to emit a key
		* and a value from the reduce method. 
		*/
		context.write(key, new IntWritable(ipcount));
  }
}
