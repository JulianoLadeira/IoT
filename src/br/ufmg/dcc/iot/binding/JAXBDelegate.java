package br.ufmg.dcc.iot.binding;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import br.ufmg.dcc.iot.binding.beans.MetricsManagerXMLBind;
import br.ufmg.dcc.iot.binding.converter.MetricsManagerBinder;
import br.ufmg.dcc.iot.business.MetricsManager;
import br.ufmg.dcc.iot.business.ReadAttempt;
import br.ufmg.dcc.iot.business.common.ReaderConn;
import br.ufmg.dcc.iot.reader.rfid.RFIDReader;
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
	
	public static void main(String args[]) throws Exception {
		
		/**ReaderConn connection = ReaderConn.createALR9900Conn(); 
		
		
		RFIDReader leitorRFID = new RFIDReader(connection);
		//RFIDAutonomousReader leitorRFID = new RFIDAutonomousReader(connection);
		
		leitorRFID.connect();
		ReadAttempt tentativaLeitura;
		
		/*
		//ReadAttempt tentativaLeitura = leitorRFID.read();
		
		
		Tag tagList[] = tentativaLeitura.getResult().getTags();
		
		if (tagList == null) {
			//readingResult.incNoTagCount();
		} else {
			for (int i = 0; i < tagList.length; i++) {
				Tag tag = tagList[i];
				System.out.println("ID:" + tag.getTagID() +
                         ", Discovered:" + tag.getDiscoverTime() +
                         ", Last Seen:" + tag.getRenewTime() +
                         ", Antenna:" + tag.getAntenna() +
                         ", Reads:" + tag.getRenewCount()
                         );
			}
		}
		
		
		
		System.out.println(leitorRFID.isOpen());
		
		
		TestReader reader = null;
		reader = new TestReader(null);
		MetricsManager manager = new MetricsManager();
		
		int i = 1;
		while(i > 0) {
			tentativaLeitura = leitorRFID.read();
			manager.addResult(tentativaLeitura);
			i--;
			System.out.println(manager.getReadRate());
			
		}		*/
		File file = new File("file.xml");
		try{
			//saveToFile(manager, file);
			MetricsManager managerr = loadFromFile(file);
			System.out.println(managerr.getSuccessRatePercentage());
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
