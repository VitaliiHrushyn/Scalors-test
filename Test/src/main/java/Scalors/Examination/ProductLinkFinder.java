package Scalors.Examination;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import org.jsoup.Connection;
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
	
	public static void main(String[] args) throws IOException {
		String url ="https://www.aboutyou.de/p/marc-o-polo/hemd-button-down-long-sleeve-without-pocket-100co-regular-3769422";
		
		Connection con = Jsoup.connect(url);
		Document doc = con.get();
		StringBuffer sb = new StringBuffer();
		Element body = doc.body();
		Element script = body.selectFirst("script");
		sb.append(script.data());
		
		Document doc2 = Jsoup.parse(sb.toString());
				
	//	Element body = doc.body();// .select("script").first().html();
	//	Element script = body.selectFirst("script");
	//	Element innerHtml = script.outerHtml();
		
	//	System.out.println(window);
	//	System.out.println("---");
	//	System.out.println(script.attr("data-reactid"));
		System.out.println(doc.body().dataNodes());
		
	}
}
