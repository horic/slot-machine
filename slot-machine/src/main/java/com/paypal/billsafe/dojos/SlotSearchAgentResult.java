package com.paypal.billsafe.dojos;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class SlotSearchAgentResult {
	
	private String file;
	
	private MessageType result;

	public SlotSearchAgentResult(String file, MessageType result) {
		this.file = file;
		this.result = result;
	}

	public String getFile() {
		return file;
	}

	public MessageType getResult() {
		return result;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("file", getFile()).append("result", getResult()).toString();
	}
	
	
}
