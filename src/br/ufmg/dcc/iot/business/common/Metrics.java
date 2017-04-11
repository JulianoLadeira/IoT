package br.ufmg.dcc.iot.business.common;

import java.util.Map;

public class Metrics {

	private Integer readAmount;
	private Double readRate;
	private Double successRatePercentage;
	private Map<Integer, Integer> tagReads;
	
	public Metrics(Integer readAmount, Double readRate, Double successRatePercentage, Map<Integer, Integer> tagReads) {
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
	public Map<Integer, Integer> getTagReads() {
		return tagReads;
	}
	
}
