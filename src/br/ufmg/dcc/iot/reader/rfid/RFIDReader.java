package br.ufmg.dcc.iot.reader.rfid;

import com.alien.enterpriseRFID.reader.AlienClass1Reader;
import com.alien.enterpriseRFID.reader.AlienReaderException;
import com.alien.enterpriseRFID.tags.Tag;

import br.ufmg.dcc.iot.business.ReadResult;
import br.ufmg.dcc.iot.business.common.ReaderConn;
import br.ufmg.dcc.iot.business.enums.ReadOutcome;
import br.ufmg.dcc.iot.reader.AbstractReader;

public class RFIDReader extends AbstractReader {

	private AlienClass1Reader reader;

	public RFIDReader (ReaderConn connection) {
		super(connection);
		this.reader = new AlienClass1Reader();
	}
		
	public Boolean isOpen() {
		return reader.isOpen();
	}
	
	@Override
	public void dispose() {
		reader.close();
	}
	
	@Override
	public void connect() throws Exception {
		reader.setConnection(connection.getConnectionMethod());
		reader.setConnection(connection.getConnectionAddress(), connection.getConnectionPort());
		reader.setUsername(connection.getUsername());
		reader.setPassword(connection.getPassword());
		reader.open();
	}

	@Override
	protected ReadResult doRead(){
		Tag tagList[] = null;
		
		try {
			tagList = reader.getTagList();
		} catch (AlienReaderException e) {
			e.printStackTrace();
		}
		
		if(tagList != null) {
			return new ReadResult(ReadOutcome.SUCCESS, tagList);
		}

		return new ReadResult(ReadOutcome.FAILURE);
	}

}