package br.ufmg.dcc.iot.binding.converter;

import br.ufmg.dcc.iot.binding.beans.ReadResultXMLBind;
import br.ufmg.dcc.iot.business.ReadResult;
import br.ufmg.dcc.iot.business.enums.ReadOutcome;

import com.alien.enterpriseRFID.tags.Tag;

public class ReadResultBinder {

	public static ReadResultXMLBind bind(ReadResult result) {
		ReadResultXMLBind boundBean = new ReadResultXMLBind();
		
		boundBean.setOutcome(result.getOutcome().name());
		boundBean.setTags(TagBinder.bind(result.getTags()));
		return boundBean;
	}

	public static ReadResult convert(ReadResultXMLBind result) {
		ReadOutcome outcome = ReadOutcome.FAILURE;
		if(result.getOutcome().equals(ReadOutcome.SUCCESS.name())){
			outcome = ReadOutcome.SUCCESS;
		}
		
		Tag tags[] = TagBinder.convert(result.getTags());
		
		ReadResult bean = new ReadResult(outcome, tags);
		return bean;
	}

}
