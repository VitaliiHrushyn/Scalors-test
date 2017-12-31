package Scalors.Examination;

import java.util.HashMap;
import java.util.Map;

public class ProductStringParser {
	
	private String stringWithProduct;
	private Map<String,String> productProperties = new HashMap<>();
	
	public String getWholeProductSubstring(String stringWithProduct){		
		return new String(stringWithProduct.substring(stringWithProduct.indexOf("\"product\":")));		
	}
	
	public String getBrandProductSubstring(String productString){		
		return new String(productString.substring(productString.indexOf("\"brand\":")));		
	}
	
	public String getStylesProductSubstring(String productString){		
		return new String(productString.substring(productString.indexOf("\"styles\":")));		
	}
	
	public String getShipmentProductSubstring(String productString){		
		return new String(productString.substring(productString.indexOf("\"promise:shipmentAndReturn\":")));		
	}
	

}
