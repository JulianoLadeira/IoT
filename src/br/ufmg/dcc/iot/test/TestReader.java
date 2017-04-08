package br.ufmg.dcc.iot.test;

import java.util.Random;

import br.ufmg.dcc.iot.business.AbstractReader;
import br.ufmg.dcc.iot.business.ReadResult;

public class TestReader extends AbstractReader {

	@Override
	protected ReadResult doRead() {
		
		for(int i = 0; i< 100000; i++){}
		
		if(new Random().nextBoolean()){
			return ReadResult.SUCCESS;
		}
		return ReadResult.FAILURE;
	}
}
