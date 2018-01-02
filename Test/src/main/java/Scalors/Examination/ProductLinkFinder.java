package Scalors.Examination;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ProductLinkFinder {
	
	private static Set<String> linkSet = new HashSet<>();

	public static void main(String[] args) throws IOException {


		String[] urls = {
        		"https://www.aboutyou.de",
//        		"https://www.aboutyou.de/p/calvin-klein-jeans/t-shirt-mit-logo-print-3728834",
//        		"https://www.aboutyou.de/p/nike/trainingsschuh-metcon-3-3548844",
//        		"https://www.aboutyou.de/p/new-era/9fifty-los-angeles-dodgers-cap-3618671",
        };
        
        for (String url : urls) {
			
		
        Document doc = Jsoup.connect(url).get();
        Element body = doc.body(); 
        Elements links = body.select("a[href]");
        
        System.out.println("links quantity: "+links.size());
        
        
        
        for (Element el : links) {
        	String link = el.attr("href");
			System.out.println(link);
			linkSet.add(link);
		}
 //       linkSet.addAll(links);
 //       System.out.println(body);
        
        Element scriptElement = body.select("script").first();

	}
	}
}
