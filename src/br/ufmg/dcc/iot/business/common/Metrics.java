package br.ufmg.dcc.iot.business.common;

import java.util.Map;
import java.util.Map.Entry;

public class Metrics {

	private Integer readAmount;
	private Double readRate;
	private Double successRatePercentage;
	private Map<String, Integer> tagReads;
	
	public Metrics(Integer readAmount, Double readRate, Double successRatePercentage, Map<String, Integer> tagReads) {
		this.readAmount = readAmount;
		this.readRate = readRate;
		this.successRatePercentage = successRatePercentage;
		this.tagReads = tagReads;
	}
	public Integer getReadAmount() {
		return readAmount;
	}
	public Double getReadRate() {
		return readRate;
	}
	public Double getSuccessRatePercentage() {
		return successRatePercentage;
	}
	
	public Map<String, Integer> getTagReads() {
		return tagReads;
	}
	
	public String getTagString() {
		StringBuilder builder = new StringBuilder();
		
		for(Entry<String, Integer> data : tagReads.entrySet()) {
			builder.append("TAG ID: ")
				.append(data.getKey())
				.append("\t QUANTIDADE DE LEITURAS: ")
				.append(data.getValue())
				.append("\n");
		}
		
		return builder.toString();
	}
	
}
