package Scalors.Examination;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class OfferXMLWritter {
	
	private File file;
	
	public OfferXMLWritter(File file) {
		super();
		this.file = file;
	}

	public void write(OfferList offers)  {
	
	try {			
			JAXBContext jaxbContext = JAXBContext.newInstance(OfferList.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(offers, file);
	//		jaxbMarshaller.marshal(offers, System.out);

		      } catch (JAXBException e) {
			e.printStackTrace();
		      }
	}
}
