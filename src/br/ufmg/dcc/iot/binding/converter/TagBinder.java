package br.ufmg.dcc.iot.binding.converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.ufmg.dcc.iot.binding.beans.TagXMLBind;

import com.alien.enterpriseRFID.tags.Tag;

public class TagBinder {

	public static List<TagXMLBind> bind(Tag[] tags) {
		List<TagXMLBind> list = new ArrayList<TagXMLBind>();
		
		if(tags != null){
			for(Tag tag : tags) {
				list.add(bindElement(tag));
			}
		}
					
		return list;
	}

	private static TagXMLBind bindElement(Tag tag) {
		TagXMLBind boundBean = new TagXMLBind();
		
		boundBean.setAntenna(tag.getAntenna());
		boundBean.setCrc(tag.getCRC());
		boundBean.setDirection(tag.getDirection());
		boundBean.setDiscoverTime(tag.getDiscoverTime());
		boundBean.setG2Data(Arrays.asList(tag.getG2Data()));
		boundBean.setG2Ops(Arrays.asList(tag.getG2Ops()));
		boundBean.setHostDiscoverTime(tag.getHostDiscoverTime());
		boundBean.setHostRenewTime(tag.getHostRenewTime());
		boundBean.setPcWord(tag.getPCWord());
		boundBean.setPersistTime(tag.getPersistTime());
		boundBean.setProtocol(tag.getProtocol());
		boundBean.setProtocolString(tag.getProtocolString());
		boundBean.setReceiveAntenna(tag.getReceiveAntenna());
		boundBean.setRenewCount(tag.getRenewCount());
		boundBean.setRenewTime(tag.getRenewTime());
		boundBean.setRssi(tag.getRSSI());
		boundBean.setSmoothPosition(tag.getSmoothPosition());
		boundBean.setSmoothSpeed(tag.getSmoothSpeed());
		boundBean.setSpeed(tag.getSpeed());
		boundBean.setTagAuth(tag.getTagAuth());
		boundBean.setTagId(tag.getTagID());
		boundBean.setTimeToLive(tag.getTimeToLive());
		boundBean.setTransmitAntenna(tag.getTransmitAntenna());
		boundBean.setXpc(tag.getXPC());		
		
		return boundBean;
	}

	public static Tag[] convert(List<TagXMLBind> tags) {
		List<Tag> convertedTags = new ArrayList<Tag>();
		
		for(TagXMLBind tag : tags) {
			convertedTags.add(convertElement(tag));
		}
		
		return convertedTags.toArray(new Tag[convertedTags.size()]);
	}

	private static String buildList(List<String> strings) {
		StringBuilder sb = new StringBuilder();
		for(String s : strings){
			sb.append(s);
		}
		return sb.toString();
	}
	
	private static Tag convertElement(TagXMLBind tag) {
		Tag bean = new Tag(tag.getTagId());
		
		bean.setAntenna(tag.getAntenna());
		bean.setCRC(tag.getCrc());
		bean.setDirection(tag.getDirection());
		bean.setDiscoverTime(tag.getDiscoverTime());
		bean.setG2Data(0,buildList(tag.getG2Data()));
		bean.setG2Ops(0, buildList(tag.getG2Ops()));
		bean.setHostDiscoverTime(tag.getHostDiscoverTime());
		bean.setHostRenewTime(tag.getHostRenewTime());
		bean.setPCWord(tag.getPcWord());
		bean.setPersistTime(tag.getPersistTime());
		bean.setProtocol(tag.getProtocol());
		bean.setReceiveAntenna(tag.getReceiveAntenna());
		bean.setRenewCount(tag.getRenewCount());
		bean.setRenewTime(tag.getRenewTime());
		bean.setRSSI(tag.getRssi());
		bean.setSpeed(tag.getSpeed());
		bean.setTagAuth(tag.getTagAuth());
		bean.setTransmitAntenna(tag.getTransmitAntenna());
		bean.setXPC(tag.getXpc());
		
		return bean;
	}

}
