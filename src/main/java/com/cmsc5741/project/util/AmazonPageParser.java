package com.cmsc5741.project.util;

import com.cmsc5741.project.model.AmazonProduct;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.HashMap;

/**
 * Created by kimmylover on 2015/4/8.
 */
public class AmazonPageParser {
    public AmazonProduct amazonPageParser(String productId){
        AmazonProduct amazonProduct = new AmazonProduct();
        String url = "http://www.amazon.com/dp/" + productId;
        Document doc = null;
        Connection.Response response = null;
        try{
            response = Jsoup.connect(url).timeout(10000).execute();
            if(response == null)
                return null;

            if(response.statusCode() == 200){
                doc = response.parse();
                Element productTitle = doc.getElementById("productTitle");
                amazonProduct.setTitle(productTitle.text());
                Element desc = doc.getElementById("feature-bullets");
                amazonProduct.setDesc(desc.text());
                amazonProduct.setUrl(url);
            }else{
                response = Jsoup.connect(url).timeout(10000).execute();
                doc = response.parse();
                Element productTitle = doc.getElementById("productTitle");
                amazonProduct.setTitle(productTitle.text());
                Element desc = doc.getElementById("feature-bullets");
                amazonProduct.setDesc(desc.text());
                amazonProduct.setUrl(url);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        if(doc == null)
            return null;
        Element img = doc.getElementById("imgTagWrapperId");
        for (Element e : img.select("img")) {
            amazonProduct.setImg(e.attr("src"));
        }
        return amazonProduct;
    }
}
