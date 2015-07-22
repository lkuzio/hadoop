package com.pgs.HadoopExample;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class SortReducer extends Reducer<IntWritable, Text, Text,  IntWritable> {



    @Override
    protected void reduce(IntWritable key, Iterable<Text> values, Context context) throws IOException, InterruptedException {

        String word ="";
        for (Text value : values) {
            word += value.toString();
        }
        context.write(new Text(word), key);

    }

}