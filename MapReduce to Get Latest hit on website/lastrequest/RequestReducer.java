package lastrequest;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class RequestReducer extends Reducer<Text, Text, Text, Text>  {
	private Text exit = new Text();
	@Override
	public void reduce(Text key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {
		int ipCount = 0;
		Date date,time = null;
		String test;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss");
		/*
		 * For each value in the set of values passed to us by the mapper:
		 */	
		for (Text value : values) {
			test = (value.toString());
			try {
				date = formatter.parse(test);
				if(ipCount == 0)
					time = date;
				if(time.before(date))
					time = date;
			} catch (ParseException e) {
				e.printStackTrace();
			}
			ipCount ++;;
		}
		
		test = formatter.format(time);
		
		exit.set(test.toString());
		
		
		
		
		/*
		 * Call the write method on the Context object to emit a key
		 * and a value from the reduce method. 
		 */
		
		context.write(key, exit);
		
		
		
		//SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MMM/yyyy");
		//exit.set(test + "  " +key.toString());
		//context.write(exit, new IntWritable(ipCount));
	
	
	}
}
	
	
	
	



