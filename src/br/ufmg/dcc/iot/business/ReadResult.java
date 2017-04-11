package br.ufmg.dcc.iot.business;

import br.ufmg.dcc.iot.business.enums.ReadOutcome;

import com.alien.enterpriseRFID.tags.Tag;

public class ReadResult {
		
	private Tag tags[];
	private ReadOutcome outcome;

	public ReadResult (ReadOutcome outcome) {
		this.outcome = outcome;
		this.tags = null;
	}
	
	public ReadResult (ReadOutcome outcome, Tag tags[]) {
		this.outcome = outcome;
		this.tags = tags;
	}
	
	public Tag[] getTags() {
		return tags;
	}

	public void setTags(Tag[] tags) {
		this.tags = tags;
	}

	public ReadOutcome getOutcome() {
		return outcome;
	}

	public void setOutcome(ReadOutcome outcome) {
		this.outcome = outcome;
	}
}
