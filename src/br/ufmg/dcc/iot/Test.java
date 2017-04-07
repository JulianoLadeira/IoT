package br.ufmg.dcc.iot;

import com.alien.enterpriseRFID.reader.AlienClass1Reader;

public class Test {

	public static void main (String[] args) {
		
		String networkAddress = "Teste";
		int networkPort = 23;

		AlienClass1Reader reader = new AlienClass1Reader();
		reader.setConnection(networkAddress, networkPort);
		
		reader.open();
		
		reader.close();
	}
	
}
