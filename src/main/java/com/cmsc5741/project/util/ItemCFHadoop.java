package com.cmsc5741.project.util;

import org.apache.hadoop.mapred.JobConf;
import org.apache.mahout.cf.taste.hadoop.item.RecommenderJob;

import java.util.List;

public class ItemCFHadoop {

    private static final String HDFS = "hdfs://localhost:9000";

    public List<String> cf() throws Exception {
        String localFile = "/home/ubuntu/item.csv";
        String inPath = HDFS + "/user/ubuntu/CF";
        String outPath = HDFS + "/user/ubuntu/CF/result/";
        String outFile = outPath + "/part-r-00000";
        String tmpPath = HDFS + "/tmp/" + System.currentTimeMillis();

        JobConf conf = config();
        HdfsDAO hdfs = new HdfsDAO(HDFS, conf);
	    hdfs.rmr(outPath);
        hdfs.copyFile(localFile, inPath);
        hdfs.ls(inPath);

        StringBuilder sb = new StringBuilder();
        String [] args;
        sb.append("--input ").append(inPath);
        sb.append(" --output ").append(outPath);
        //sb.append(" --usersFile ").append("D:/IdeaProjects/Food Recommendation/web/user.txt");
        sb.append(" --usersFile ").append("/home/ubuntu/user.txt");
        sb.append(" --booleanData true");
        sb.append(" --similarityClassname org.apache.mahout.math.hadoop.similarity.cooccurrence.measures.EuclideanDistanceSimilarity");
        sb.append(" --tempDir ").append(tmpPath);
        args = sb.toString().split(" ");
        System.out.println(sb);
        RecommenderJob job = new RecommenderJob();
        job.setConf(conf);
        job.run(args);

        List<String> result = hdfs.cat(outFile);
        return result;
    }

    public static JobConf config() {
        JobConf conf = new JobConf(ItemCFHadoop.class);
        conf.setJobName("ItemCFHadoop");
        conf.addResource("classpath:/hadoop/core-site.xml");
        conf.addResource("classpath:/hadoop/hdfs-site.xml");
        conf.addResource("classpath:/hadoop/mapred-site.xml");
        return conf;
    }
}
