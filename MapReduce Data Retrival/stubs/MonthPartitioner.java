package stubs;

import java.util.HashMap;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.conf.Configurable;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.Partitioner;

public class MonthPartitioner<K2, V2> extends Partitioner<Text, Text> implements
    Configurable {

  private Configuration configuration;
  HashMap<String, Integer> months = new HashMap<String, Integer>();

  /**
   * Set up the months hash map in the setConf method.
   */
  @Override
  public void setConf(Configuration configuration) {
	 this.configuration = configuration;
     /*
     * Add the months to a HashMap.
     */ 
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

  }

  
  @Override
  public Configuration getConf() {
    return configuration;
  }


  public int getPartition(Text key, Text value, int numReduceTasks) {

	
	 
		 return months.get(value.toString());//returns reducer to use
	 
  }
}
