package br.ufmg.dcc.iot.reader;

import br.ufmg.dcc.iot.business.ReadAttempt;
import br.ufmg.dcc.iot.business.ReadResult;

public class AbstractReader {

	public final ReadAttempt read () {
		long startTime = System.nanoTime();
		ReadResult result = this.doRead();
		long endTime = System.nanoTime();
		return new ReadAttempt(result, new Long(endTime - startTime));
	}
	
	/**
	 * Método que deve ser implementado pelas classes filhas.
	 * @return
	 */
	protected ReadResult doRead () {
		return null;
	}
}

