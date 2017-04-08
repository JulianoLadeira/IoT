package br.ufmg.dcc.iot.business;

public class ReadAttempt {

	private ReadResult result;
	private Long elapsedTime;
	
	public ReadAttempt (ReadResult result, Long elapsedTime) {
		this.result = result;
		this.elapsedTime = elapsedTime;
	}
	
	/****************************************/
	public ReadResult getResult() {
		return result;
	}
	public void setResult(ReadResult result) {
		this.result = result;
	}
	public Long getElapsedTime() {
		return elapsedTime;
	}
	public void setElapsedTime(Long elapsedTime) {
		this.elapsedTime = elapsedTime;
	}
	
	
}
