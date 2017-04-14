package br.ufmg.dcc.iot.binding.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="tag")
public class TagXMLBind {

	private int antenna;
	private int direction;
	private int protocol;
	private int receiveAntenna;
	private int renewCount;
	private int transmitAntenna;
	private long crc;
	private long discoverTime;
	private List<String> g2Data;
	private List<String> g2Ops;
	private long hostDiscoverTime;
	private long hostRenewTime;
	private String pcWord;
	private long persistTime;
	private String protocolString;
	private long renewTime;
	private double rssi;
	private double smoothPosition;
	private double smoothSpeed;
	private double speed;
	private String tagAuth;
	private String tagId;
	private long timeToLive;
	private String xpc;
	
	public int getAntenna() {
		return antenna;
	}
	
	@XmlElement
	public void setAntenna(int antenna) {
		this.antenna = antenna;
	}
	
	public int getDirection() {
		return direction;
	}
	
	@XmlElement
	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	public int getProtocol() {
		return protocol;
	}
	
	@XmlElement
	public void setProtocol(int protocol) {
		this.protocol = protocol;
	}
	
	public int getReceiveAntenna() {
		return receiveAntenna;
	}
	
	@XmlElement
	public void setReceiveAntenna(int receiveAntenna) {
		this.receiveAntenna = receiveAntenna;
	}
	
	public int getRenewCount() {
		return renewCount;
	}
	
	@XmlElement
	public void setRenewCount(int renewCount) {
		this.renewCount = renewCount;
	}
	
	public int getTransmitAntenna() {
		return transmitAntenna;
	}
	
	@XmlElement
	public void setTransmitAntenna(int transmitAntenna) {
		this.transmitAntenna = transmitAntenna;
	}
	
	public long getCrc() {
		return crc;
	}
	
	@XmlElement
	public void setCrc(long crc) {
		this.crc = crc;
	}
	
	public long getDiscoverTime() {
		return discoverTime;
	}
	
	@XmlElement
	public void setDiscoverTime(long discoverTime) {
		this.discoverTime = discoverTime;
	}
	
	public List<String> getG2Data() {
		return g2Data;
	}
	
	@XmlElement
	@XmlElementWrapper(name="g2Data")
	public void setG2Data(List<String> g2Data) {
		this.g2Data = g2Data;
	}
	
	public List<String> getG2Ops() {
		return g2Ops;
	}
	
	@XmlElement
	@XmlElementWrapper(name="g2Ops")
	public void setG2Ops(List<String> g2Ops) {
		this.g2Ops = g2Ops;
	}
	
	public long getHostDiscoverTime() {
		return hostDiscoverTime;
	}
	
	@XmlElement
	public void setHostDiscoverTime(long hostDiscoverTime) {
		this.hostDiscoverTime = hostDiscoverTime;
	}
	
	public long getHostRenewTime() {
		return hostRenewTime;
	}
	
	@XmlElement
	public void setHostRenewTime(long hostRenewTime) {
		this.hostRenewTime = hostRenewTime;
	}
	
	public String getPcWord() {
		return pcWord;
	}
	
	@XmlElement
	public void setPcWord(String pcWord) {
		this.pcWord = pcWord;
	}
	
	public long getPersistTime() {
		return persistTime;
	}
	
	@XmlElement
	public void setPersistTime(long persistTime) {
		this.persistTime = persistTime;
	}
	
	public String getProtocolString() {
		return protocolString;
	}
	
	@XmlElement
	public void setProtocolString(String protocolString) {
		this.protocolString = protocolString;
	}
	
	public long getRenewTime() {
		return renewTime;
	}
	
	@XmlElement
	public void setRenewTime(long renewTime) {
		this.renewTime = renewTime;
	}
	
	public double getRssi() {
		return rssi;
	}
	
	@XmlElement
	public void setRssi(double rssi) {
		this.rssi = rssi;
	}
	
	public double getSmoothPosition() {
		return smoothPosition;
	}
	
	@XmlElement
	public void setSmoothPosition(double smoothPosition) {
		this.smoothPosition = smoothPosition;
	}
	
	public double getSmoothSpeed() {
		return smoothSpeed;
	}
	
	@XmlElement
	public void setSmoothSpeed(double smoothSpeed) {
		this.smoothSpeed = smoothSpeed;
	}
	
	public double getSpeed() {
		return speed;
	}
	
	@XmlElement
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	public String getTagAuth() {
		return tagAuth;
	}
	
	@XmlElement
	public void setTagAuth(String tagAuth) {
		this.tagAuth = tagAuth;
	}
	
	public String getTagId() {
		return tagId;
	}
	
	@XmlElement
	public void setTagId(String tagId) {
		this.tagId = tagId;
	}
	
	public long getTimeToLive() {
		return timeToLive;
	}
	
	@XmlElement
	public void setTimeToLive(long timeToLive) {
		this.timeToLive = timeToLive;
	}
	
	public String getXpc() {
		return xpc;
	}
	
	@XmlElement
	public void setXpc(String xpc) {
		this.xpc = xpc;
	}
}
