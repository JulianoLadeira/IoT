package br.ufmg.dcc.iot.binding.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="metricsManager")
public class MetricsManagerXMLBind {

	private List<ReadAttemptXMLBind> attempts;

	public List<ReadAttemptXMLBind> getAttempts() {
		return attempts;
	}

	@XmlElement(name="attempt")
	@XmlElementWrapper(name="attempts")
	public void setAttempts(List<ReadAttemptXMLBind> attempts) {
		this.attempts = attempts;
	}
	
}
