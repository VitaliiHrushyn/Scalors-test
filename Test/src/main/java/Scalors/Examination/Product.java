package Scalors.Examination;

import java.math.BigDecimal;

import org.jsoup.nodes.Element;

public class Product {
	
	private static ProductStringParser psp = new ProductStringParser();
	
	private String name;
	private String brand;
	private String color;
	private BigDecimal price;
	private BigDecimal initialPrice;
	private String description;
	private String articleId;
	private BigDecimal shippingCosts;
	
	public Product(Element scriptElement) {
		super();
		this.name = psp.fetch(scriptElement, "\"styles\":\\[\\{.{0,220}\"name\"");
		this.brand = psp.fetch(scriptElement, "\"brand\":\\{.{0,220}\"name\"");
		this.color = psp.fetch(scriptElement, "\"styles\":\\[\\{.{0,520}\"color\"").replace("u002F", "");
		setPrice(psp.fetch(scriptElement, "\"styles\":\\[\\{.{0,520}\"campaignPrice\":\\{\"min\"").replace(",", ""));
		setInitialPrice((!psp.fetch(scriptElement, "\"styles\":\\[\\{.{0,520}\"oldPrice\"").replace(",", "").equals("null")) ?
				psp.fetch(scriptElement, "\"styles\":\\[\\{.{0,520}\"oldPrice\"").replace(",", "") :
					psp.fetch(scriptElement, "\"styles\":\\[\\{.{0,520}\"price\":\\{\"min\"").replace(",", ""));
		this.description = psp.fetch(scriptElement, "\"design\":\\[\\{.{0,1520}\"description\"").trim();
		this.articleId = psp.fetch(scriptElement, "\"design\":\\[\\{.{0,1520}\"articleNumber\"");
		setShippingCosts(psp.fetch(scriptElement, "\"promise:shipmentAndReturn\""));
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(String price) {
		if (price.equals("null")) this.price = null;
		else this.price = (new BigDecimal(price)).divide(new BigDecimal(100));
	}
	
	public BigDecimal getInitialPrice() {
		return initialPrice;
	}
	public void setInitialPrice(String initialPrice) {
		if (initialPrice.equals("null")) this.initialPrice = null;
		else this.initialPrice = (new BigDecimal(initialPrice)).divide(new BigDecimal(100));
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	public BigDecimal getShippingCosts() {
		return shippingCosts;
	}
	public void setShippingCosts(String shippingCosts) {
		if (shippingCosts.equals("Kostenloser Versand & Rückversand")) this.shippingCosts = new BigDecimal(0);
		else this.shippingCosts = new BigDecimal(shippingCosts);
	}

	@Override
	public String toString() {
		return "Product:\n name: " + name + "\n brand: " + brand + "\n color: " + color + "\n price: " + price
				+ "\n initialPrice: " + initialPrice + "\n description: " + description + "\n articleId: " + articleId
				+ "\n shippingCosts: " + shippingCosts + "";
	}
	
//	public static void createProduct(Element scriptElement) {
//	
//	ProductStringParser psp = new ProductStringParser();
//    
//    Map<String,String> props = new LinkedHashMap<>();      
//   
//    props.put("name", psp.fetch(scriptElement, "\"styles\":\\[\\{.{0,220}\"name\""));
//    props.put("brand", psp.fetch(scriptElement, "\"brand\":\\{.{0,220}\"name\""));
//    props.put("color", psp.fetch(scriptElement, "\"styles\":\\[\\{.{0,520}\"color\"").replace("u002F", ""));
//    props.put("price", psp.fetch(scriptElement, "\"styles\":\\[\\{.{0,520}\"campaignPrice\":\\{\"min\"").replace(",", ""));
//    props.put("initial price", 
//    		(!psp.fetch(scriptElement, "\"styles\":\\[\\{.{0,520}\"oldPrice\"").replace(",", "").equals("null")) ?
//    				psp.fetch(scriptElement, "\"styles\":\\[\\{.{0,520}\"oldPrice\"").replace(",", "") :
//    					psp.fetch(scriptElement, "\"styles\":\\[\\{.{0,520}\"price\":\\{\"min\"").replace(",", "")    		
//    		);
//    props.put("description", psp.fetch(scriptElement, "\"design\":\\[\\{.{0,1520}\"description\"").trim());
//    props.put("article ID", psp.fetch(scriptElement, "\"design\":\\[\\{.{0,1520}\"articleNumber\""));
//	props.put("shipment", psp.fetch(scriptElement, "\"promise:shipmentAndReturn\""));
//    
//    System.out.println(props);
//	}
	
	
	
}