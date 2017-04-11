package br.ufmg.dcc.iot.services;

import br.ufmg.dcc.iot.business.common.Metrics;
import br.ufmg.dcc.iot.business.common.ReaderConn;
import br.ufmg.dcc.iot.reader.rfid.RFIDReader;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class RFIDReaderService extends RFIDService {

	public RFIDReaderService(ReaderConn connection, Long attemps) throws Exception {
		super(attemps);
		
		setReader(new RFIDReader(connection));

		reader.connect();
	}

	@Override
	public Disposable start(Consumer<Metrics> subscriber) {
		Disposable disp = observable.subscribe(subscriber);
		
		runThread = new Thread(() -> {			
			for (int i = 0; i < attemptsSeconds;) {
			    manager.addResult(reader.read());
			    observable.onNext(manager.getMetrics());
			}
			
			stop();
			observable.onNext(new Metrics(0, 0d, 0d, null));
		});
		
		runThread.start();
		
		return disp;
	}
	
}
