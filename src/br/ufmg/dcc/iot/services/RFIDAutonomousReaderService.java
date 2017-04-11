package br.ufmg.dcc.iot.services;

import java.util.concurrent.TimeUnit;

import com.alien.enterpriseRFID.notify.Message;
import com.alien.enterpriseRFID.notify.MessageListener;
import com.alien.enterpriseRFID.notify.MessageListenerService;

import br.ufmg.dcc.iot.business.common.Metrics;
import br.ufmg.dcc.iot.business.common.ReaderConn;
import br.ufmg.dcc.iot.reader.rfid.RFIDAutonomousReader;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class RFIDAutonomousReaderService extends RFIDService implements MessageListener {
	
	private final Integer MESSAGE_LISTENER_PORT = 4000;
	
	private MessageListenerService messageService;
	
	public RFIDAutonomousReaderService(ReaderConn connection, Long runtimeSeconds) throws Exception {
		super(runtimeSeconds);

		messageService = new MessageListenerService(MESSAGE_LISTENER_PORT);
		messageService.setMessageListener(this);

		setReader(new RFIDAutonomousReader(connection));
	}
	
	public Disposable start(Consumer<Metrics> subscriber) {
		Disposable disp = observable.subscribe(subscriber);
		
		try {
			messageService.startService();
			((RFIDAutonomousReader) reader).setService(messageService);
			reader.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		runThread = new Thread(() -> {
			  long startTime = System.currentTimeMillis();
			  
			  while(messageService.isRunning() && (System.currentTimeMillis() - startTime) < attemptsSeconds) {
				  try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			  }		    
			  
			  reader.dispose();
			  observable.onNext(new Metrics(0, 0d, 0d, null));
		});

		runThread.start();
		
		return disp;
	}

	@Override
	public void messageReceived(Message message) {
		((RFIDAutonomousReader) reader).addMessage(message);
		manager.addResult(reader.read());
		observable.onNext(manager.getMetrics());
	}

}
