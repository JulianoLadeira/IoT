package br.ufmg.dcc.iot.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import br.ufmg.dcc.iot.business.enums.ReadOutcome;

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
			if(result.getResult().getOutcome().equals(ReadOutcome.SUCCESS)) {
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
	
	public void printTagCount(){
		
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		
		for (ReadAttempt attempt : results) {
			Integer tagCount = attempt.getResult().getTags().length;
			if(map.containsKey(tagCount)) {
				Integer value = map.get(tagCount);
				map.put(tagCount, value + 1);
			} else {
				map.put(tagCount, 1);
			}
		}
		
		for(Entry<Integer, Integer> entrySet : map.entrySet()) {
			System.out.println("Quantidade de tags lidas: " + entrySet.getKey() + " -> Número de vezes lidas: " + entrySet.getValue());
		}
	}
}
