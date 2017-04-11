package br.ufmg.dcc.iot;

import com.alien.enterpriseRFID.reader.AlienReaderConnectionException;
import com.alien.enterpriseRFID.reader.AlienReaderConnectionRefusedException;
import com.alien.enterpriseRFID.reader.AlienReaderNotValidException;
import com.alien.enterpriseRFID.reader.AlienReaderTimeoutException;

import br.ufmg.dcc.iot.business.MetricsManager;
import br.ufmg.dcc.iot.reader.rfid.RFIDReader;


public class Main {

	public static void main (String[] args) {
		
		RFIDReader reader = null;
		try {
			reader = new RFIDReader();
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
			manager.addResult(reader.read());
			i--;
		}		
		
		System.out.println(manager.getSuccessRatePercentage());
		System.out.println(manager.getReadRate());
		manager.printTagCount();
		
	}	
}
