package movie;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MovieReducer extends Reducer<Text, Text, Text, Text> {

	
	private static class MyPair implements Comparable<MyPair> {
	        public final String key;
	        public final double value;

	        MyPair(String key, double value) {
	            this.key = key;
	            this.value = value;
	        }

	        @Override
	        public int compareTo(MyPair o) {
	            return Double.compare(value, o.value); 
	        }
	    }
	
	private final PriorityQueue<MyPair> topN = new PriorityQueue<MyPair>();
	
  @Override
  public void reduce(Text key, Iterable<Text> values, Context context)
      throws IOException, InterruptedException {
		double average = 0;
		double count = 0;
		String[] arrOfStr;
		String temp;
		List<String> a = new ArrayList<String>();
		 
		
		/*
		 * For each value in the set of values passed to us by the mapper:
		 */
		for (Text value : values) {

			temp = value.toString();
			arrOfStr = temp.split(",", 2);
			topN.add(new MyPair(arrOfStr[0], Double.valueOf(arrOfStr[1])));
			
	        if (topN.size() <= 100) { // simple optimization
	            continue;
	        }
	        while (topN.size() > 50) { // retain only top 3 elements in queue
	            topN.poll();
	        }
			
		}
		while (topN.size() > 50) {
            topN.poll(); // retain only top 50 elements in queue
        }
		
		for (MyPair myPair : topN) { // write top 50 elements
            a.add(myPair.key);
        }
        
            context.write(new Text(key), new Text(Arrays.toString(a.toArray())));
        
		
  }
}