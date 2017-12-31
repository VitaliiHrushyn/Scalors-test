package Scalors.Examination;

import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Set;

/**
 * Parser
 *
 */
public class WebParser 
{
    public static void main( String[] args ) throws IOException
    {
        String url = 
        		//"https://www.aboutyou.de";
        		"https://www.aboutyou.de/p/calvin-klein-jeans/t-shirt-mit-logo-print-3728834";
        	//	"https://www.aboutyou.de/p/nike/trainingsschuh-metcon-3-3548844";
        
        Document doc = Jsoup.connect(url).get();
        Element body = doc.body(); 
 //       Elements links = body.select("a[href]");
        
//        System.out.println("links quantity: "+links.size());
        
 //       System.out.println(body);
        
        Element scriptElement = body.select("script").first();
        String stringWithProduct = scriptElement.data();
        
       
        System.out.println(stringWithProduct);
     //   System.out.println("products size: " + scriptElement.data());

  //      System.out.println(new ProductStringParser().getProductString(stringWithProduct));
      
    }
}
