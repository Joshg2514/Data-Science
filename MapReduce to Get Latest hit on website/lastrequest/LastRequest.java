package lastrequest;


import java.util.Calendar;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
public class LastRequest {

	public static void main(String[] args) throws Exception {
		if(args.length != 2) {
			System.err.println("Usage: LastRequest <input path> <output path>");
			System.exit(-1);
		}
		//configures job
		Configuration conf = new Configuration();
	    Job job = Job.getInstance(conf, "last request");
	    job.setJarByClass(LastRequest.class);
		
		
		//Job job = new Job();
		//job.setJarByClass(LastRequest.class);
		//job.setJobName("Last Request");
		
	    
	    //sets output/input
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		//sets the expected key/value types
		job.setOutputKeyClass(Text.class);
	    //job.setOutputValueClass(IntWritable.class);
		job.setOutputValueClass(Text.class);
		
		//sets both reducer and mapper class
		job.setReducerClass(RequestReducer.class);
		job.setMapperClass(RequestMapper.class);
		
		//sets expected output for map
		job.setMapOutputValueClass(Text.class);
		
		//checks to see if job is completed
		boolean success = job.waitForCompletion(true);
		    System.exit(success ? 0 : 1);
		
		
	}
}
