package lastrequest;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;



public class RequestMapper extends Mapper<LongWritable, Text, Text, Text> {
	
	@Override
	public void map(LongWritable key, Text value, Context context)
		throws IOException, InterruptedException {
		
		String line = value.toString();//reads lines from file
		String ip;
	    ip = line.substring(0,line.indexOf("-")-1); //gets the ip
	    line = line.substring(line.indexOf("[")+1,line.indexOf("]")-6); //gets the date values
	    //SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy:HH:mm:ss");
	    //Date date = formatter.parse(line);

	     context.write(new Text(ip), new Text(line));
		
	}
	
	

		
}
