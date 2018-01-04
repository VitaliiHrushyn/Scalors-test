package Scalors.Examination;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="offers")
public class OfferList {
	
	private List<Product> offers = new ArrayList<>();

	public OfferList() {
		super();
	}

	public List<Product> getOffers() {
		return offers;
	}

	@XmlElement(name="offer")
	public void setOffers(List<Product> offers) {
		this.offers = offers;
	}
	
	public void add(Product product) {
		offers.add(product);
	}
	
	public int size() {
		return this.offers.size();
	}
}
