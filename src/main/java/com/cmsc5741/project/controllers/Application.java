package com.cmsc5741.project.controllers;

import com.cmsc5741.project.model.AmazonProduct;
import com.cmsc5741.project.util.AmazonPageParser;
import com.cmsc5741.project.util.ItemCFHadoop;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by andrewgyq on 2015/4/8.
 */

@Controller
public class Application {
    private AmazonPageParser amazonPageParser = new AmazonPageParser();
    private ItemCFHadoop itemCFHadoop = new ItemCFHadoop();
    private static final String HDFS = "hdfs://localhost:9000";
    private List<String> lines = new ArrayList<String>();
    HashMap<String, String> map = new HashMap<String, String>();
    private  Boolean flag = true;
    @RequestMapping("/rate")
    public ModelAndView rateMain(HttpServletRequest request) throws Exception{
        String itemId = request.getParameter("item");
        String rating = request.getParameter("rating");

        if(itemId != null && rating != null){
            flag = false;

            File csv = new File("/home/ubuntu/item.csv"); // ubuntu
            //File csv = new File("D:/IdeaProjects/Food Recommendation/web/item.csv"); // windows
            BufferedWriter bw = new BufferedWriter(new FileWriter(csv, true));
            bw.newLine();
            bw.write("999999"  + "," + map.get(itemId) + "," + rating);
            bw.close();
        }
        ModelAndView mv = new ModelAndView("rate");

        if(flag){
            //File file = new File("D:/IdeaProjects/Food Recommendation/web/products_id.txt"); // windows
            File file = new File("/home/ubuntu/products_id.txt"); // linux
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            while( line != null ) {
                String [] str = line.split(" ");
                lines.add(str[0]);
                map.put(str[0], str[1]);
                line = br.readLine();
            }
        }

        Random r = new Random();
        String id = lines.get(r.nextInt(lines.size()));
        AmazonProduct product = null;
        while(product == null){
            product = amazonPageParser.amazonPageParser(id);
            id = lines.get(r.nextInt(lines.size()));
        }
        mv.addObject("title",product.getTitle());
        mv.addObject("desc", product.getDesc());
        mv.addObject("url", product.getUrl());
        mv.addObject("img", product.getImg());
        mv.addObject("itemId", id);
        return mv;
    }

    @RequestMapping("/recommendation")
    public ModelAndView recommendationMain() throws Exception{
          ModelAndView mv = new ModelAndView("recommendation");
        List<String> list = itemCFHadoop.cf();
        List<String> recommendList = new ArrayList<String>();
        List<AmazonProduct> returnList = new ArrayList<AmazonProduct>();

        HashMap<String, String> map = new HashMap<String, String>();
        File file = new File("/home/ubuntu/products_id.txt"); // linux
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = br.readLine();
        while( line != null ) {
            String [] str = line.split(" ");
            map.put(str[1], str[0]);
            line = br.readLine();
        }
        br.close();
        for(String id : list){
            id = String.format("%05d", Integer.valueOf(id));
            recommendList.add(map.get(id));
        }

        for(String id : recommendList){
            AmazonProduct product = amazonPageParser.amazonPageParser(id);
            if(product != null)
                returnList.add(product);
        }

        mv.addObject("list", returnList);
        return mv;
    }
}
