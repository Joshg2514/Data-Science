package movie;
import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MovieMapper extends Mapper<LongWritable, Text, Text, Text> {

  @Override
  public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {

  
	if (key.get() == 0 && value.toString().contains("relevance") /*Some condition satisfying it is header*/)
         return;
	
    String line = value.toString();

 
    String[] arrOfStr = line.split(",", 2);
     

    context.write(new Text(arrOfStr[0]), new Text(arrOfStr[1]));
      
    }

  }

