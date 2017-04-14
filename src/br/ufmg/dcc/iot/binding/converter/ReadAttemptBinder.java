package br.ufmg.dcc.iot.binding.converter;

import java.util.ArrayList;
import java.util.List;

import br.ufmg.dcc.iot.binding.beans.ReadAttemptXMLBind;
import br.ufmg.dcc.iot.business.ReadAttempt;
import br.ufmg.dcc.iot.business.ReadResult;

public class ReadAttemptBinder {

	public static List<ReadAttemptXMLBind> bind(List<ReadAttempt> results) {
		List<ReadAttemptXMLBind> list = new ArrayList<ReadAttemptXMLBind>();
		
		for(ReadAttempt result : results) {
			list.add(bindElement(result));
		}
		
		return list;
	}
	
	private static ReadAttemptXMLBind bindElement(ReadAttempt attempt){
		
		ReadAttemptXMLBind boundBean = new ReadAttemptXMLBind();
		boundBean.setElapsedTime(attempt.getElapsedTime());
		boundBean.setResult(ReadResultBinder.bind(attempt.getResult()));
		return boundBean;
	}

	public static List<ReadAttempt> convert(List<ReadAttemptXMLBind> attempts) {
		List<ReadAttempt> convertedAttempts = new ArrayList<ReadAttempt>();
		
		for(ReadAttemptXMLBind attempt : attempts){
			convertedAttempts.add(convertAttempt(attempt));	
		}
		
		return convertedAttempts;
	}
	
	private static ReadAttempt convertAttempt(ReadAttemptXMLBind attempt) {
		ReadResult result = ReadResultBinder.convert(attempt.getResult());
		Long elapsedTime = attempt.getElapsedTime();
		ReadAttempt bean = new ReadAttempt(result, elapsedTime);
		return bean;
	}
}
