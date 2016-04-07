package wordcount;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class WordCountDriver extends Configured implements Tool {
	
	@Override
	public int run(String[] args) throws Exception {
		
		return runCount(args);
	}
	
	private int runMap(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
		System.out.println("WordCountDriver BEGIN");
		
		Job job = Job.getInstance(getConf(), "WordMapJob");
		Configuration conf = job.getConfiguration();
		job.setJarByClass(getClass());
		
		Path in = new Path(args[0]);
		Path out = new Path(args[1]);
		out.getFileSystem(conf).delete(out, true);
		
		FileInputFormat.setInputPaths(job, in);
		FileOutputFormat.setOutputPath(job, out);
		
		// WordCountMapper extrait chaque mot, et y donne la valeur "1"
		job.setMapperClass(WordCountMapper.class);
		
		// pas de reduce
		
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		return job.waitForCompletion(true) ? 0 : 1;
	}
	
	private int runList(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
		System.out.println("WordCountDriver BEGIN");
		
		Job job = Job.getInstance(getConf(), "WordCountJob");
		Configuration conf = job.getConfiguration();
		job.setJarByClass(getClass());
		
		Path in = new Path(args[0]);
		Path out = new Path(args[1]);
		out.getFileSystem(conf).delete(out, true);
		
		FileInputFormat.setInputPaths(job, in);
		FileOutputFormat.setOutputPath(job, out);
		
		// WordListMapper est un simple passe plat, on récupère l'offset et les valeurs d'entrées (lignes)
		job.setMapperClass(WordListMapper.class);
		
		// Pas de reduce
		
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		job.setMapOutputKeyClass(LongWritable.class);
		job.setMapOutputValueClass(Text.class);
		
		job.setOutputKeyClass(LongWritable.class);
		job.setOutputValueClass(Text.class);
		
		return job.waitForCompletion(true) ? 0 : 1;
	}
	
	private int runCount(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
		System.out.println("WordCountDriver BEGIN");
		
		Job job = Job.getInstance(getConf(), "WordCountJob");
		Configuration conf = job.getConfiguration();
		job.setJarByClass(getClass());
		
		Path in = new Path(args[0]);
		Path out = new Path(args[1]);
		out.getFileSystem(conf).delete(out, true);
		
		FileInputFormat.setInputPaths(job, in);
		FileOutputFormat.setOutputPath(job, out);
		
		// WordCountMapper extrait chaque mot, et y donne la valeur "1"
		job.setMapperClass(WordCountMapper.class);
		// Le reducer compte le nombre d'occuence.
		job.setReducerClass(WordCountReducer.class);
		
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		return job.waitForCompletion(true) ? 0 : 1;
	}
	
	public static void main(String[] args) {
		int result = 0;
		try {
			result = ToolRunner.run(new Configuration(), new WordCountDriver(), args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.exit(result);
	}
	
}
