package Scalors.Examination;

import java.math.BigDecimal;

public class Product {
	
	private String name;
	private String brand;
	private String color;
	private BigDecimal price;
	private BigDecimal campaignPrice;
	private BigDecimal oldPrice;
	private String description;
	private String articleId;
	private BigDecimal shippingCosts;
	
	public Product(String name, String brand, String color, String price, String campaignPrice,
			String oldPrice, String description, String articleId, String shippingCosts) {
		super();
		this.name = name;
		this.brand = brand;
		this.color = color;
		setPrice(price);
		setCampaignPrice(campaignPrice);
		setOldPrice(oldPrice);
		this.description = description;
		this.articleId = articleId;
		setShippingCosts(shippingCosts);;
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
	public BigDecimal getCampaignPrice() {
		return campaignPrice;
	}
	public void setCampaignPrice(String campaignPrice) {
		if (campaignPrice.equals("null")) this.campaignPrice = null;
		else this.campaignPrice = (new BigDecimal(campaignPrice)).divide(new BigDecimal(100));
	}
	public BigDecimal getOldPrice() {
		return oldPrice;
	}
	public void setOldPrice(String oldPrice) {
		if (oldPrice.equals("null")) this.oldPrice = null;
		else this.oldPrice = (new BigDecimal(oldPrice)).divide(new BigDecimal(100));
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
		if (shippingCosts.equals("null")) this.shippingCosts = null;
		else this.shippingCosts = new BigDecimal(shippingCosts);
	}	
}