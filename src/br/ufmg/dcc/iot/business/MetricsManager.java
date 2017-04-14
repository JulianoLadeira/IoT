package br.ufmg.dcc.iot.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alien.enterpriseRFID.tags.Tag;

import br.ufmg.dcc.iot.business.common.Metrics;
import br.ufmg.dcc.iot.business.enums.ReadOutcome;

/**
 * Faz os cálculos das métricas dos resultados obtidos.
 */
public class MetricsManager {

	private final List<ReadAttempt> results;
	
	public MetricsManager() {
		this.results = new ArrayList<ReadAttempt>();
	}
	
	public MetricsManager(List<ReadAttempt> results) {
		this.results = results;
	}

	public List<ReadAttempt> getResults() {
		return results;
	}

	public void addResult(ReadAttempt result) {
		results.add(result);
	}
	
	public void clearResults() {
		results.clear();
	}
	
	public Integer getTotalReads() {
		return results.size();
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
	
	public Map<String, Integer> getTagCount(){
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		for (ReadAttempt attempt : results) {
			if (attempt.getResult().getTags() != null) {
				for (Tag tag : attempt.getResult().getTags()) {
					if (!map.containsKey(tag.getTagID()))
						map.put(tag.getTagID(), 0);						
					map.put(tag.getTagID(), map.get(tag.getTagID()) + 1);
				}
			}
		}
		
		return map;
	}
	
	public Metrics getMetrics() {
		return new Metrics(getTotalReads(), getReadRate(), getSuccessRatePercentage(), getTagCount());
	}
}