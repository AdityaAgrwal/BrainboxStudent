package com.brainbox.student.provider;

/**
 * Created by Admin-PC on 2/20/2016.
 */
public enum ExtraArgumentKeys {
	OPEN_ACTIVITES("OPEN_ACTIVITES");

	private String text;

	private ExtraArgumentKeys(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return text;
	}
}