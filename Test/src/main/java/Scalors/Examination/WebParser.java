package Scalors.Examination;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

/**
 * Parser
 *
 */
public class WebParser{
	
	private static OfferList offers = new OfferList();
	
    public static void main( String[] args ) throws IOException
    {
        String[] urls = {
        	//	"https://www.aboutyou.de",
        		"https://www.aboutyou.de/p/calvin-klein-jeans/t-shirt-mit-logo-print-3728834",
        		"https://www.aboutyou.de/p/nike/trainingsschuh-metcon-3-3548844",
        		"https://www.aboutyou.de/p/new-era/9fifty-los-angeles-dodgers-cap-3618671",
        };
        
        for (String url : urls) {
			
		
        Document doc = Jsoup.connect(url).get();
        Element body = doc.body(); 
  //      Elements links = body.select("a[href]");
        
//        System.out.println("links quantity: "+links.size());
        
 //       System.out.println(body);
        
        Element scriptElement = body.select("script").first();
//        String swp = scriptElement.data();
        
//        System.out.println(swp);
//        System.out.println("------------");
        
//        Product.createProduct(scriptElement);
        Product prod = new Product(scriptElement);
///        System.out.println();
//        System.out.println(prod);
        offers.add(prod);
        }
       
     //   System.out.println(stringWithProduct);
     //   System.out.println("products size: " + scriptElement.data());

  //      System.out.println(new ProductStringParser().getProductString(stringWithProduct));
        
        OfferXMLWritter writter = new OfferXMLWritter(new File("result.xml"));
		writter.write(offers);
      
		System.out.println("done");
    }
}
