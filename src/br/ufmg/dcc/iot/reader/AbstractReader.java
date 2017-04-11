package br.ufmg.dcc.iot.reader;

import br.ufmg.dcc.iot.business.ReadAttempt;
import br.ufmg.dcc.iot.business.ReadResult;
import br.ufmg.dcc.iot.business.common.ReaderConn;

public abstract class AbstractReader {

	protected ReaderConn connection;
	
	public AbstractReader(ReaderConn connection) {
		this.connection = connection;
	}
	
	public final ReadAttempt read() {
		long startTime = System.nanoTime();
		ReadResult result = this.doRead();
		long endTime = System.nanoTime();
		return new ReadAttempt(result, new Long(endTime - startTime));
	}
	
	/**
	 * Método que deve ser implementado pelas classes filhas.
	 * @return
	 */
	protected abstract ReadResult doRead();
	
	/**
	 * Metodo que deve ser implementado pelas classes filhas para conexao.
	 */
	public abstract void connect() throws Exception;
}
