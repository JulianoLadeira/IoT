package br.ufmg.dcc.iot.reader.rfid;

import br.ufmg.dcc.iot.reader.AbstractReader;
import br.ufmg.dcc.iot.business.ReadResult;

import com.alien.enterpriseRFID.reader.AlienClass1Reader;
import com.alien.enterpriseRFID.reader.AlienReaderConnectionException;
import com.alien.enterpriseRFID.reader.AlienReaderConnectionRefusedException;
import com.alien.enterpriseRFID.reader.AlienReaderException;
import com.alien.enterpriseRFID.reader.AlienReaderNotValidException;
import com.alien.enterpriseRFID.reader.AlienReaderTimeoutException;
import com.alien.enterpriseRFID.tags.Tag;

public class RFIDReader extends AbstractReader {

	private final String connectionMethod = "COM1";
	private final String connectionAddress = "150.164.10.42";
	private final Integer connectionPort = 23;
	private final String username = "alien";
	private final String password = "password";
	private final Integer readTries = 300;

	private final AlienClass1Reader reader;

	public RFIDReader () throws AlienReaderConnectionRefusedException, AlienReaderNotValidException, AlienReaderTimeoutException, AlienReaderConnectionException {
		this.reader = new AlienClass1Reader();

		reader.setConnection(this.connectionMethod);

		reader.setConnection(this.connectionAddress, this.connectionPort);
		reader.setUsername(this.username);
		reader.setPassword(this.password);

		reader.open();
	}

	public void dispose() {
		reader.close();
	}

	@Override
	protected ReadResult doRead(){
		for (int k = 0; k < readTries; ++k) {
			Tag tagList[] = null;
			try {
				tagList = reader.getTagList();
			} catch (AlienReaderException e) {
				e.printStackTrace();
			}
			if(tagList != null) {
				return ReadResult.SUCCESS;
			}
		}
		return ReadResult.FAILURE;
	}

}
