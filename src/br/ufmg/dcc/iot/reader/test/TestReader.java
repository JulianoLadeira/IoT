package br.ufmg.dcc.iot.reader.test;

import java.util.Random;

import br.ufmg.dcc.iot.business.ReadResult;
import br.ufmg.dcc.iot.business.common.ReaderConn;
import br.ufmg.dcc.iot.business.enums.ReadOutcome;
import br.ufmg.dcc.iot.reader.AbstractReader;

public class TestReader extends AbstractReader {

	public TestReader(ReaderConn connection) {
		super(connection);
	}

	@Override
	protected ReadResult doRead() {
		
		for(int i = 0; i< 100000; i++){}
		
		if(new Random().nextBoolean()){
			return new ReadResult(ReadOutcome.SUCCESS);
		}
		return new ReadResult(ReadOutcome.FAILURE);
	}

	@Override
	public void connect() throws Exception {
	}

	@Override
	public void dispose() {
	}
}