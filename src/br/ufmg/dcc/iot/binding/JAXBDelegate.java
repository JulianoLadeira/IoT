package br.ufmg.dcc.iot.binding;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import br.ufmg.dcc.iot.binding.beans.MetricsManagerXMLBind;
import br.ufmg.dcc.iot.binding.converter.MetricsManagerBinder;
import br.ufmg.dcc.iot.business.MetricsManager;
import br.ufmg.dcc.iot.reader.test.TestReader;

public class JAXBDelegate {

	public static void saveToFile(MetricsManager bean, File file) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(MetricsManagerXMLBind.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		jaxbMarshaller.marshal(MetricsManagerBinder.bind(bean), file);
	}
	
	public static MetricsManager loadFromFile (File file) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(MetricsManagerXMLBind.class);

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		MetricsManagerXMLBind bean = (MetricsManagerXMLBind) jaxbUnmarshaller.unmarshal(file);
		return MetricsManagerBinder.convert(bean); 
	}	
	
	public static void main(String args[]) {
		
		TestReader reader = null;
		reader = new TestReader(null);
		MetricsManager manager = new MetricsManager();
		
		int i = 200;
		while(i > 0) {
			manager.addResult(reader.read());
			i--;
		}		
		File file = new File("file.xml");
		try{
			saveToFile(manager, file);
			MetricsManager managerr = loadFromFile(file);
			System.out.println(managerr.getSuccessRatePercentage());
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
