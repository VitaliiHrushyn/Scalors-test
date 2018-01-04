package Scalors.Examination;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Element;

public class ProductStringParser {
	
	public String fetch(Element scriptElement, String findingProperty) {
		String regex = ""+findingProperty+":\"?(.+?)\"";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(scriptElement.html());
		m.find();
		String result = "null";
		try {
			result = m.group(1);
		} catch (IllegalStateException e) {
			System.err.println(e+" for: "+scriptElement.baseUri());
		}		
		return result;
	}
}
