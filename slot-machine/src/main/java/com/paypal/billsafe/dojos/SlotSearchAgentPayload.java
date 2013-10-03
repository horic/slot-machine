package com.paypal.billsafe.dojos;

import java.io.File;
import java.io.Serializable;

public class SlotSearchAgentPayload implements Serializable {

	private static final long serialVersionUID = 1L;

	private File file;
	
	private String needle;

	public SlotSearchAgentPayload(File file, String needle) {

		this.file = file;
		this.needle = needle;
	}


	
	public File getFile() {
		return file;
	}



	public String getNeedle() {
		return needle;
	}
	
}
