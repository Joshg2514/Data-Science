package stubs;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class LogMonthMapper extends Mapper<LongWritable, Text, Text, Text> {

  /**
   * Example input line:
   * 96.7.4.14 - - [24/Apr/2011:04:20:11 -0400] "GET /cat.jpg HTTP/1.1" 200 12433
   *
   */
	HashMap<String, Integer> months = new HashMap<String, Integer>();
	private Text exit = new Text();
	@Override
	public void map(LongWritable key, Text value, Context context)
		throws IOException, InterruptedException {
		
		String line = value.toString();//reads lines from file
		String ip;
	    ip = line.substring(0,line.indexOf("-")-1); //gets the ip
	    line = line.substring(line.indexOf("/")+1,line.indexOf("/")+4); //gets the date values
	
		 months.put("jan", 0);
		 months.put("feb", 1);
		 months.put("mar", 2);
		 months.put("apr", 3);
		 months.put("may", 4);
		 months.put("jun", 5);
		 months.put("jul", 6);
		 months.put("aug", 7);
		 months.put("sep", 8);
		 months.put("oct", 9);
		 months.put("nov", 10);
		 months.put("dec", 11);

		
		exit.set(line.toLowerCase());
	 
		if(months.containsKey(line.toLowerCase()))//checks if the month value is valid
			context.write(new Text(ip), exit);
  }
}
