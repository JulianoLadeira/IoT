package br.ufmg.dcc.iot.services;

import br.ufmg.dcc.iot.business.MetricsManager;
import br.ufmg.dcc.iot.business.common.Metrics;
import br.ufmg.dcc.iot.reader.AbstractReader;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

public abstract class RFIDService {
	
	protected MetricsManager manager;
	
	protected PublishSubject<Metrics> observable;

	protected Thread runThread;
	protected Long attemptsSeconds;

	protected AbstractReader reader;
	
	public RFIDService(Long attemptSeconds) {
		attemptsSeconds = attemptSeconds;
		manager = new MetricsManager();
		observable = PublishSubject.create();
		
		observable.subscribeOn(Schedulers.newThread())
			.observeOn(JavaFxScheduler.platform());
	}
	
	public void setReader(AbstractReader reader) {
		this.reader = reader;
	}
	
	@SuppressWarnings("deprecation")
	public void stop() {
		runThread.stop();
		reader.dispose();
	}

	public abstract Disposable start(Consumer<Metrics> subscriber);

}
