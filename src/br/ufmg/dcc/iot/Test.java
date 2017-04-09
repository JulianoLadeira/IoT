package br.ufmg.dcc.iot;

import com.alien.enterpriseRFID.reader.AlienReaderConnectionException;
import com.alien.enterpriseRFID.reader.AlienReaderConnectionRefusedException;
import com.alien.enterpriseRFID.reader.AlienReaderNotValidException;
import com.alien.enterpriseRFID.reader.AlienReaderTimeoutException;

import br.ufmg.dcc.iot.business.MetricsManager;
import br.ufmg.dcc.iot.reader.rfid.RFIDReader;


public class Test {

	public static void main (String[] args) {
		
		RFIDReader test = null;
		try {
			test = new RFIDReader();
		} catch (AlienReaderConnectionRefusedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AlienReaderNotValidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AlienReaderTimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AlienReaderConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		MetricsManager manager = new MetricsManager();
		
		int i = 200;
		while(i > 0) {
			manager.addResult(test.read());
			i--;
		}		
		
		System.out.println(manager.getSuccessRatePercentage());
		System.out.println(manager.getReadRate());
		
	}
		
//		String networkAddress = "Teste";
//		int networkPort = 23;
//
//		AlienClass1Reader reader = new AlienClass1Reader();
//		reader.setConnection(networkAddress, networkPort);
//		
//		try {
//			reader.open();
//		} catch (AlienReaderConnectionRefusedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (AlienReaderNotValidException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (AlienReaderTimeoutException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (AlienReaderConnectionException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		reader.close();
//	}
	
}
