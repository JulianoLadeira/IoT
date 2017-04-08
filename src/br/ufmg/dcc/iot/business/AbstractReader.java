package br.ufmg.dcc.iot.business;

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

