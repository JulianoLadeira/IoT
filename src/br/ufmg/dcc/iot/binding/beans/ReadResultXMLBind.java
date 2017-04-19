package br.ufmg.dcc.iot.binding.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="readResult")
public class ReadResultXMLBind {

	private List<TagXMLBind> tags;
	private String outcome;
	
	public List<TagXMLBind> getTags() {
		return tags;
	}
	
	@XmlElement(name="tag")
	@XmlElementWrapper(name="tags")
	public void setTags(List<TagXMLBind> tags) {
		this.tags = tags;
	}
	
	public String getOutcome() {
		return outcome;
	}
	
	@XmlElement
	public void setOutcome(String readOutcome) {
		this.outcome = readOutcome;
	}
	
	
}
	