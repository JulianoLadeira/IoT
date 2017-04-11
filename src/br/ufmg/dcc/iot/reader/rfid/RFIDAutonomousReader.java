package br.ufmg.dcc.iot.reader.rfid;

import com.alien.enterpriseRFID.notify.Message;
import com.alien.enterpriseRFID.notify.MessageListenerService;
import com.alien.enterpriseRFID.reader.AlienClass1Reader;
import com.alien.enterpriseRFID.reader.AlienReaderException;

import br.ufmg.dcc.iot.business.ReadResult;
import br.ufmg.dcc.iot.business.common.ReaderConn;
import br.ufmg.dcc.iot.business.enums.ReadOutcome;
import br.ufmg.dcc.iot.reader.AbstractReader;

public class RFIDAutonomousReader extends AbstractReader {
	
	private AlienClass1Reader reader;
	private Message message;
	private MessageListenerService service;
	
	public RFIDAutonomousReader(ReaderConn connection) {
		super(connection);
		reader = new AlienClass1Reader();
	}
	
	public void disposable() {
	}
	
	public void addMessage(Message message) {
		this.message = message;
	}
	
	public void setService(MessageListenerService service) {
		this.service = service;
	}

	@Override
	public void dispose() {
		try {
			reader.open();
			reader.autoModeReset();
			reader.setNotifyMode(AlienClass1Reader.OFF);
			reader.close();
		} catch (AlienReaderException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected ReadResult doRead() {
		if(message.getTagList() != null)
			return new ReadResult(ReadOutcome.SUCCESS, message.getTagList());
		return new ReadResult(ReadOutcome.FAILURE);
	}

	@Override
	public void connect() throws Exception {
		reader.setConnection(connection.getConnectionMethod());
		reader.setConnection(connection.getConnectionAddress(), connection.getConnectionPort());
		reader.setUsername(connection.getUsername());
		reader.setPassword(connection.getPassword());
		
		reader.open();
		reader.setNotifyAddress("150.164.0.247", service.getListenerPort());
		reader.setNotifyFormat(AlienClass1Reader.XML_FORMAT);
		reader.setNotifyTrigger("OFF");
		reader.setNotifyMode(AlienClass1Reader.ON);
		
		reader.autoModeReset();
		reader.setAutoStopTimer(1000);
		reader.setAutoMode(AlienClass1Reader.ON);
		reader.close();
	}

}
