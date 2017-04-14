package br.ufmg.dcc.iot.binding.converter;

import java.util.List;

import br.ufmg.dcc.iot.binding.beans.MetricsManagerXMLBind;
import br.ufmg.dcc.iot.business.MetricsManager;
import br.ufmg.dcc.iot.business.ReadAttempt;

public class MetricsManagerBinder {

	public static MetricsManagerXMLBind bind(MetricsManager metricsManager) {
		
		MetricsManagerXMLBind boundBean = new MetricsManagerXMLBind();
		List<ReadAttempt> results = metricsManager.getResults();
		boundBean.setAttempts(ReadAttemptBinder.bind(results));
		
		return boundBean;
	}
	
	public static MetricsManager convert(MetricsManagerXMLBind bean) {
		List<ReadAttempt> attempts = ReadAttemptBinder.convert(bean.getAttempts());
		MetricsManager manager = new MetricsManager(attempts);		
		return manager;
	}
}
