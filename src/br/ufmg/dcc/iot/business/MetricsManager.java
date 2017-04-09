package br.ufmg.dcc.iot.business;

import java.util.ArrayList;
import java.util.List;

/**
 * Faz os cálculos das métricas dos resultados obtidos.
 */
public class MetricsManager {

	private final List<ReadAttempt> results = new ArrayList<ReadAttempt>();
	
	public void addResult(ReadAttempt result) {
		results.add(result);
	}
	
	/**
	 * @return Taxa de sucesso.
	 */
	private double successRate() {
		
		int totalReads = results.size();
		int successfulReads = 0;
		for (ReadAttempt result : results) {
			if(result.getResult().equals(ReadResult.SUCCESS)) {
				successfulReads++;
			}
		}
		
		return (double)successfulReads / (double)totalReads;
	}
	
	/**
	 * @return Taxa de leitura por nanosegundos.
	 */
	public Double getReadRate() {
		
		int totalReads = results.size();
		long waitTime = 0;
		for (ReadAttempt result : results) {
			waitTime += result.getElapsedTime();
		}
				
		return ((double)totalReads  * 1000000000.0 / (double)waitTime);
	}
	
	/**
	 * @return Taxa de sucesso em porcentagem.
	 */
	public Double getSuccessRatePercentage() {
		return successRate() * 100.0;
	}
}
