package Scalors.Examination;

import org.jsoup.Jsoup;

import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Date;


/**
 * Parser
 *@author Vitalii Hrushyn
 */
public class WebParser{	
	
    public static void main( String[] args ) throws IOException {
    	
    	Date start = new Date();
    	OfferList offers = new OfferList();
    	ProductLinkFinder plf = new ProductLinkFinder();
    	String find;
    	int httpRequest = 0;
    			
    	if (args.length < 1) {
    		find ="hh";
    		} else {
    			find = args[0];
    			}
   
        for (String url : plf.findLinks(find)) {
        	
	        offers.add(new Product(Jsoup.connect(url).get().body().select("script").first()));
	        httpRequest++;
        }
        
        OfferXMLWritter writter = new OfferXMLWritter(new File("result.xml"));
		writter.write(offers);
      
		System.out.println("Amount of triggered HTTP request: "+httpRequest + plf.getHttpRequest());
		long runTime = new Date().getTime() - start.getTime();
		System.out.printf("Run-time: %.2f \n", runTime / 1000.0);
			Runtime runtime = Runtime.getRuntime();	
			NumberFormat format = NumberFormat.getInstance();	
			StringBuilder sb = new StringBuilder();
			long maxMemory = runtime.maxMemory();
			long allocatedMemory = runtime.totalMemory();
			long freeMemory = runtime.freeMemory();	
			sb.append("\tfree memory: " + format.format(freeMemory / 1024) + " mb \n");
			sb.append("\tallocated memory: " + format.format(allocatedMemory / 1024) + " mb \n");
			sb.append("\tmax memory: " + format.format(maxMemory / 1024) + " mb \n");
			sb.append("\ttotal free memory: " + format.format((freeMemory + (maxMemory - allocatedMemory)) / 1024) + " mb");
		System.out.println("Memory Footprint: \n"+ sb.toString());
		System.out.println("Amount of extracted products: "+offers.size());
    }
}
