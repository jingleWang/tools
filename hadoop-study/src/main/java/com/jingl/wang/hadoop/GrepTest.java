package com.jingl.wang.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.net.URI;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Date : 2018/8/7
 * @Author : 汪京陆(Ben)[wangjinglu@souche.com]
 * @CopyRight : DataTeam @ SouChe Inc
 * @Desc :
 */
public class GrepTest {

    public static class GrepMapper extends
            Mapper<Object, Text, Text, IntWritable> {

        @Override
        public void map(Object obj, Text text, Context context)
                throws IOException, InterruptedException {
            String pattern = context.getConfiguration().get("grep");
            // System.out.println(split.getPath().toString());
            String str = text.toString();
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(str);
            if (m.find()) {
                FileSplit split = (FileSplit) context.getInputSplit();
                String filename = split.getPath().getName();
                context.write(new Text(filename), new IntWritable(1));
            }
        }
    }

    public static class GrepReducer extends
            Reducer<Text, IntWritable, Text, IntWritable> {
        @Override
        public void reduce(Text text, Iterable<IntWritable> values,
                           Context context) throws IOException, InterruptedException {
            int sum = 0;
            for (IntWritable t : values) {
                sum += t.get();
            }
            context.write(text, new IntWritable(sum));
        }
    }


    public static void main(String[] args) throws IOException,
            ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        String pattern = "hello";//匹配含有w字符,这里修改我们需要匹配的模式
        conf.set("grep", pattern);// 在这里设置需要匹配的正则表达式
        Job job = Job.getInstance(conf, "grep");
        job.setJarByClass(GrepTest.class);
        job.setMapperClass(GrepMapper.class);
        job.setReducerClass(GrepReducer.class);
        job.setCombinerClass(GrepReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        //下面根据具体情况进行修改
        String args1 = "hdfs://localhost:9000/";
        String args2 = "hdfs://localhost:9000/out";
        FileSystem fs = FileSystem.newInstance(URI.create(args1), conf);
        fs.delete(new Path(args2), true);
        FileInputFormat.addInputPath(job, new Path(args1));
        FileOutputFormat.setOutputPath(job, new Path(args2));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
