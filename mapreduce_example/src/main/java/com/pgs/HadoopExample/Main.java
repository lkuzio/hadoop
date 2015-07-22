package com.pgs.HadoopExample;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class Main {

    public static void main(String[] arguments) throws IOException, ClassNotFoundException, InterruptedException {
        mapReduceLottoJob();
    }

    private static void mapReduceLottoJob() throws IOException, InterruptedException, ClassNotFoundException {
        Configuration hadoopConf = new Configuration();
        hadoopConf.set("fs.defaultFS", "hdfs://localhost:9000");
        hadoopConf.set("mapred.job.tracker", "localhost:9001 ");
        hadoopConf.set("mapreduce.app-submission.cross-platform", "true");

        Job job = Job.getInstance(hadoopConf);
        //Mapper class to make Key, Value pairs
        /*job.setMapperClass(TwoNumberMapper.class);
        job.setMapperClass(ThreeNumberMapper.class);
        job.setMapperClass(FourNumberMapper.class);*/
        job.setMapperClass(FiveNumberMapper.class);
        //Reducer class to group by key
        job.setReducerClass(WordReducer.class);
        job.setJarByClass(Main.class);
        job.setJobName("lottoAnalyzer");

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        FileInputFormat.addInputPath(job, new Path("/input/lotto"));
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(new Date());
        String path = "/output/lottosort_" + calendar.get(Calendar.HOUR) + "_" + calendar.get(Calendar.MINUTE) + "_" + calendar.get(Calendar.SECOND);
        FileOutputFormat.setOutputPath(job, new Path(path));

        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        job.waitForCompletion(true);

        //sorting output

        Job sort = job.getInstance(hadoopConf);
        sort.setMapperClass(SortMapper.class);
        sort.setReducerClass(SortReducer.class);
        sort.setJarByClass(Main.class);
        sort.setJobName("sorter");

        FileInputFormat.addInputPath(sort, new Path(path));
        FileOutputFormat.setOutputPath(sort, new Path(path + "/sorted"));

        sort.setMapOutputKeyClass(IntWritable.class);
        sort.setMapOutputValueClass(Text.class);

        sort.setOutputKeyClass(IntWritable.class);
        sort.setOutputValueClass(Text.class);

        sort.setInputFormatClass(TextInputFormat.class);
        sort.setOutputFormatClass(TextOutputFormat.class);
        sort.waitForCompletion(true);
    }
}