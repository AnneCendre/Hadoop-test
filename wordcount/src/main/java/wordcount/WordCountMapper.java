package wordcount;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.util.StringUtils;

public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	
	private static final IntWritable ONE = new IntWritable(1);
	
	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String currentLine = value.toString();
		String[] words = StringUtils.split(currentLine, ' ');
		for (String word : words) {
			Text outputKey = new Text();
			outputKey.set(word);
			context.write(outputKey, ONE);
		}
	}
	
}
