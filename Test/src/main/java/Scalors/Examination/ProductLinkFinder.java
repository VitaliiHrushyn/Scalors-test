package Scalors.Examination;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ProductLinkFinder {
	
//	private String startUrl;
//	private Set<String> nonOfferLinkSet = new HashSet<>(50000);
//	private Set<String> findingLinkSet = new HashSet<>(20000);
	private Set<String> offerLinkSet = new HashSet<>();
//	private String otherRegex = "^https://www.aboutyou.de/[^p]/?.*";
	private String offerRegex = "^https://www.aboutyou.de/p/.+";
//	private String sitemapRegex = ".*sitemap.*";
	
	public ProductLinkFinder() {
		super();
	//	this.startUrl = startUrl;
//		findingLinkSet.add(startUrl);
	}

	public static void main(String[] args) throws IOException {		
        
		ProductLinkFinder plf = new ProductLinkFinder(); // where p - product page
        
		
     //   plf.gatherLinks();
		plf.findLinks("shirt");
       
      
      
  //    plf.testElementBoby("https://www.aboutyou.de/suche?gender=kids&term=shirt");
              
//System.out.println("other links");
//      
//      for (String link : plf.otherLinkSet) {
//		System.out.println(link);
//	}
      		
//      	System.out.println("offer links");
//            for (String link : plf.offerLinkSet) {
//			System.out.println("\""+link+"\",");
//		}
		
	}
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++	
	
	public void findLinks(String find) throws IOException {
		String[] genders = {"female", "male", "kids"};
		for (String gender : genders) {
			
		
		
		Document docOne = Jsoup.connect("https://www.aboutyou.de/suche?gender="+gender+"&term="+find).get(); // https://www.aboutyou.de/suche?gender=kids&term=shirt
        
		String fullUrl = docOne.baseUri();
	//	System.out.println("==="+fullUrl);
		int n = 1;
		Document doc;
		do {
			String url = fullUrl+"&page="+ n++;
//			System.out.println(url);
		doc = Jsoup.connect(url).get();
		Element body = doc.body();
		
		Elements links = body.select("a[href]");
		this.devideLinks(links);
	//	System.out.println("---"+doc.baseUri());
		} while(!doc.baseUri().equals(fullUrl));
      //  System.out.println(fullUrl);
        
		}
        
        
 //      System.out.println("links quantity: "+links.size());
//        System.out.println("other links: " + this.otherLinkSet.size());
//		System.out.println("offer links: " + this.offerLinkSet.size());
       
        
	}
	
//	public void gatherLinks() {
//		
//		do {
//			nonOfferLinkSet.addAll(findingLinkSet);
////			findingLinkSet.removeAll(nonOfferLinkSet);
////			Set<String> tempSet = new LinkedHashSet<>(findingLinkSet);
////			System.out.println("---finding links: " + findingLinkSet.size()); 
////		    System.out.println("---nonOffer links: " + nonOfferLinkSet.size());
////		    System.out.println("---offer links: " + offerLinkSet.size());
//			
//			for (String link : new HashSet<>(findingLinkSet)) {
//				try {
//					getLinks(link);
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					System.err.println(e);
//				}
//			} 			
//			findingLinkSet.removeAll(nonOfferLinkSet);
//			
////			System.out.println("+++finding links: " + findingLinkSet.size()); 
////		    System.out.println("+++nonOffer links: " + nonOfferLinkSet.size());
////		    System.out.println("+++offer links: " + offerLinkSet.size());
//		      
//		} while (!findingLinkSet.isEmpty());
//	}
	
	public void getLinks(String link) throws IOException {
	
		Document doc = Jsoup.connect(link).get();
        Element body = doc.body();       
        
        Elements links = body.select("a[href]");
        
 //      System.out.println("links quantity: "+links.size());
//        System.out.println("other links: " + this.otherLinkSet.size());
//		System.out.println("offer links: " + this.offerLinkSet.size());
       
        this.devideLinks(links);
       
	}
	
	public void devideLinks(Elements links) {
		
		for (Element el : links) {
	    	String link = el.attr("abs:href");
//	    System.out.println(link);
//	    System.out.println("linkDevider");
//if(Pattern.matches(sitemapRegex, link)) {System.err.println("!!! sitemap !!! "+link); break;};
			if (Pattern.matches(offerRegex, link)) {offerLinkSet.add(link);}
//			 else if (Pattern.matches(otherRegex, link)) {findingLinkSet.add(link) ;}
		} 
//		System.out.println("finding links: " + findingLinkSet.size()); 
//	      System.out.println("nonOffer links: " + nonOfferLinkSet.size());
	      System.out.println("offer links: " + offerLinkSet.size());
	      System.out.println("----------------------");
	}
	
	public void testElementBoby(String url) throws IOException {
		Document doc = Jsoup.connect(url).get();
        Element body = doc.body(); 
     //   Element scriptElement = body.select("script").first();
        System.out.println(body);
	}

//	public Set<String> getOtherLinkSet() {
//		return otherLinkSet;
//	}

	public Set<String> getOfferLinkSet() {
System.out.println("gerOfferLinkSet");
		return offerLinkSet;
	}
	
	

	
//	private boolean validateLink(String link) throws IOException {
//		return pattern.matches(regex, link);
//	}
//	
//	private String getBodyData(String link) throws IOException {
//		Document doc = Jsoup.connect(link).get();
//        return doc.body().data();
//	}
	
	
}
