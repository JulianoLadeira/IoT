package br.ufmg.dcc.iot.services;

import br.ufmg.dcc.iot.business.MetricsManager;
import br.ufmg.dcc.iot.business.common.Metrics;
import br.ufmg.dcc.iot.business.common.ReaderConn;
import br.ufmg.dcc.iot.reader.rfid.RFIDReader;
import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import io.reactivex.schedulers.Schedulers;

public class ReaderService {

	private RFIDReader reader;
	private MetricsManager manager;
	
	public ReaderService(ReaderConn connection, Long attemps) throws Exception {
		reader = new RFIDReader(connection, attemps);
		reader.connect();
		manager = new MetricsManager();
	}
	
	public Disposable read(Consumer<Metrics> subscriber) {
		Flowable<Metrics> source = Flowable.fromCallable(() -> {
			for (int i = 0; i < reader.getAttempts(); i++) 
			    manager.addResult(reader.read());
		    return manager.getMetrics();
		});
		
		source
			.subscribeOn(Schedulers.computation())
			.observeOn(JavaFxScheduler.platform());
			
		return source.subscribe(subscriber);
	}
	
	public void stop() {
		reader.dispose();
	}
	
}
