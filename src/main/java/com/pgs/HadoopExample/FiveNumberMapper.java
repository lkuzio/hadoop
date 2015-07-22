package com.pgs.HadoopExample;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FiveNumberMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    private Text word = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] split = line.split(";");
        if (split.length == 3) {
            String[] numbers = split[2].split(" ");
            if (numbers.length == 6) {

                List<String> pairs = generateCombinations(numbers);
                for (String pair : pairs) {
                    word.set(pair);
                    context.write(word, new IntWritable(1));
                }
            }
        }
    }

    private List<String> generateCombinations(String[] numbers) {
        List<String> pairs = new ArrayList<String>();
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                for (int k = j + 1; k < numbers.length; k++) {
                    for (int l = k + 1; l < numbers.length; l++) {
                        for (int m = l + 1; m < numbers.length; m++) {
                            pairs.add(numbers[i] + " " + numbers[j] + " " + numbers[k] + " " + numbers[l] + " " + numbers[m]);
                        }
                    }
                }
            }
        }
        return pairs;
    }
}
