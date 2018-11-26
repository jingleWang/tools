package com.jingl.wang.hadoop.hdfs;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.BlockLocation;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.apache.hadoop.hdfs.protocol.DatanodeInfo;


/**
 * @Date : 2018/8/7
 * @Author : 汪京陆(Ben)[wangjinglu@souche.com]
 * @CopyRight : DataTeam @ SouChe Inc
 * @Desc :
 */
public class HDFSTest {
//    public static void main(String[] args) throws IOException {
//        Configuration conf = new Configuration();
//
//        FileSystem hdfs = FileSystem.get(conf);
//        Path src =new Path("/Users/jinglu/projects/github/tools/dataset/test.txt");
//
//        //HDFS为止
//
//        Path dst =new Path("/");
//
//        hdfs.copyFromLocalFile(src, dst);
//
//        System.out.println("Upload to"+conf.get("fs.default.name"));
//
//        FileStatus files[]=hdfs.listStatus(dst);
//
//        for(FileStatus file:files){
//            System.out.println(file.getPath());
//        }
//
//    }

    public static void main(String[] args) throws IOException {

        Configuration conf = new Configuration();
        conf.set("fs.default.name", "hdfs://localhost:9000");
        FileSystem fs = FileSystem.get(conf);
        Path src = new Path("/Users/jinglu/projects/github/tools/dataset/dao-info-log.log");
        Path dst = new Path("/");
        fs.copyFromLocalFile(src, dst);
        fs.close();
    }

}
