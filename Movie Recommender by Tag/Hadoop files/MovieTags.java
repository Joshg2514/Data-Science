package movie;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import stubs.CountReducer;
import stubs.LogMonthMapper;
import stubs.MonthPartitioner;
import stubs.ProcessLogs;

public class MovieTags {

  public static void main(String[] args) throws Exception {

	  if (args.length != 2) {
	      System.out.printf("Usage: MovieTags <input dir> <output dir>\n");
	      System.exit(-1);
	    }

	    Configuration conf = new Configuration();
	    Job job = Job.getInstance(conf, "MovieTags");
	    job.setJarByClass(MovieTags.class);

	    FileInputFormat.setInputPaths(job, new Path(args[0]));
	    FileOutputFormat.setOutputPath(job, new Path(args[1]));

	    job.setMapperClass(MovieMapper.class);
	    job.setReducerClass(MovieReducer.class);
	    
	  
	    job.setMapOutputValueClass(Text.class);

	    job.setOutputKeyClass(Text.class);
	    job.setOutputValueClass(Text.class);
	    
	    

	    
	    

    boolean success = job.waitForCompletion(true);
    System.exit(success ? 0 : 1);
  }
}

