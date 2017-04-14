package br.ufmg.dcc.iot.binding.beans;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="readAttempt")
public class ReadAttemptXMLBind {

	private ReadResultXMLBind result;
	private Long elapsedTime;
	
	public ReadResultXMLBind getResult() {
		return result;
	}
	
	@XmlElement
	public void setResult(ReadResultXMLBind result) {
		this.result = result;
	}
	
	public Long getElapsedTime() {
		return elapsedTime;
	}
	
	@XmlElement
	public void setElapsedTime(Long elapsedTime) {
		this.elapsedTime = elapsedTime;
	}
}
