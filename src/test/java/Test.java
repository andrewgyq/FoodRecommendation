import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * Created by kimmylover on 2015/4/8.
 */
public class Test {
    public static void main(String[] args) {
        String url = "http://www.amazon.com/dp/B005EZJDA4";
        Document doc = null;
        Connection.Response response = null;
        try{
            response = Jsoup.connect(url).timeout(10000).execute();
            if(response.statusCode() == 200){
                doc = response.parse();
                Element img = doc.getElementById("imgTagWrapperId");
                System.out.println(img);
                for (Element e : img.select("img")) {
                    System.out.println(e.attr("src"));
                }
            }


        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
