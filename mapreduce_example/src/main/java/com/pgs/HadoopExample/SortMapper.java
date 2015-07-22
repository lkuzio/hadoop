package com.pgs.HadoopExample;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by devhadoop on 7/3/15.
 */
public class SortMapper extends Mapper<LongWritable, Text, IntWritable, Text> {

    //private Text word = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] split = line.split("\t");
        if (split.length == 2) {
                    //word.set(split[0]);
                    context.write(new IntWritable(Integer.parseInt(split[1])), new Text(split[0]));
        }
    }

}
