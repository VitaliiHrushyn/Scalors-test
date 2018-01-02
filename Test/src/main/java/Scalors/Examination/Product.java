package Scalors.Examination;

import java.math.BigDecimal;

import org.jsoup.nodes.Element;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="offer")
@XmlType (propOrder={"name","brand", "color", "price", "initialPrice", "description", "articleId", "shippingCosts"})
public class Product {
	
	private static ProductStringParser psp = new ProductStringParser();
	
	private String name;	
	private String brand;	
	private String color;	
	private String price;	
	private String initialPrice;	
	private String description;	
	private String articleId;	
	private String shippingCosts;	
	
	public Product() {
		super();
	}

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
	
	@XmlElement
	public void setName(String name) {
		this.name = name;
	}
	
	public String getBrand() {
		return brand;
	}
	
	@XmlElement
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public String getColor() {
		return color;
	}
	
	@XmlElement
	public void setColor(String color) {
		this.color = color;
	}
	
	@XmlElement
	public void setPrice(String price) {
		if (price.equals("null")) this.price = null;
		else this.price = ((new BigDecimal(price)).divide(new BigDecimal(100)).toString());
	}
		
	@XmlElement(name="initial_price")
	public void setInitialPrice(String initialPrice) {
		if (initialPrice.equals("null")) this.initialPrice = null;
		else this.initialPrice = ((new BigDecimal(initialPrice)).divide(new BigDecimal(100)).toString());
	}
	
	public String getDescription() {
		return description;
	}
	
	@XmlElement
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getArticleId() {
		return articleId;
	}
	
	@XmlElement(name="article_ID")
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}	
	
	public String getPrice() {
		return price;
	}

	public String getInitialPrice() {
		return initialPrice;
	}

	public String getShippingCosts() {
		return shippingCosts;
	}

	@XmlElement(name="shipping_costs")
	public void setShippingCosts(String shippingCosts) {
		if (shippingCosts.equals("Kostenloser Versand & Rückversand")) this.shippingCosts = new BigDecimal(0).toString();
		else this.shippingCosts = new BigDecimal(shippingCosts).toString();
	}

	@Override
	public String toString() {
		return "Product:\n name: " + name + "\n brand: " + brand + "\n color: " + color + "\n price: " + price
				+ "\n initialPrice: " + initialPrice + "\n description: " + description + "\n articleId: " + articleId
				+ "\n shippingCosts: " + shippingCosts + "";
	}
}