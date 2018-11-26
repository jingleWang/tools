package com.jingl.wang.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @Date : 2018/8/6
 * @Author : 汪京陆(Ben)[wangjinglu@souche.com]
 * @CopyRight : DataTeam @ SouChe Inc
 * @Desc :
 */
public class TestWordCount {

    static class WordCountMapper extends Mapper<Object, Text, Text, IntWritable> {
        private static Text word = new Text();
        private static IntWritable one = new IntWritable(1);

        @Override
        protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            String[] words = value.toString().split(" ");
            for (String w : words) {
                word.set(w);
                context.write(word, one);
            }
        }
    }


    static class WordCountReduce extends Reducer<Text, IntWritable, Text, IntWritable> {
        @Override
        protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            int sum = 0;
            for (IntWritable value : values) {
                sum += value.get();
            }
            IntWritable val = new IntWritable(sum);
            context.write(key, val);
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "word-count");
        job.setJarByClass(TestWordCount.class);
        job.setMapperClass(WordCountMapper.class);
        job.setCombinerClass(WordCountReduce.class);
        job.setReducerClass(WordCountReduce.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        FileInputFormat.addInputPath(job, new Path("/Users/jinglu/projects/github/tools/dataset/test.txt"));
        FileOutputFormat.setOutputPath(job, new Path("/Users/jinglu/projects/github/tools/dataset/out.txt"));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
