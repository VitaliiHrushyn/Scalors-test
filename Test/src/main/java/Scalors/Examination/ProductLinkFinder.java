package Scalors.Examination;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ProductLinkFinder {
	
	private Set<String> offerLinkSet = new HashSet<>();
	private String offerRegex = "^https://www.aboutyou.de/p/.+";
	private int httpRequest = 0;
	
	public ProductLinkFinder() {
		super();
	}

	public Set<String> findLinks(String find) throws IOException {
		String[] genders = {"female", "male", "kids"};
		
		for (String gender : genders) {		
			String startUrl = "https://www.aboutyou.de/suche?gender="+gender+"&term="+find;	
			
			Document docOne = Jsoup.connect(startUrl).get();
			httpRequest++;
			String fullUrl = docOne.location();	
			int n = 1;
			Document doc;
			do {
				String url = fullUrl+"&page="+ n++;		
				doc = Jsoup.connect(url).get();
				httpRequest++;
				Element body = doc.body();				
				Elements links = body.select("a[href]");
				this.devideLinks(links);
			} while(doc.location().contains("&page="));       
		}
 		 return offerLinkSet;
 	}
	
	public void getLinks(String link) throws IOException {
	
		Document doc = Jsoup.connect(link).get();
		httpRequest++;
        Element body = doc.body();              
        Elements links = body.select("a[href]"); 
        this.devideLinks(links);
       
	}
	
	public void devideLinks(Elements links) {
		
		for (Element el : links) {
	    	String link = el.attr("abs:href");
			if (Pattern.matches(offerRegex, link)) {offerLinkSet.add(link);}
		} 
	}
	
	public int getHttpRequest() {
		return this.httpRequest;
	}
}
