package com.brainbox.student.models;

/**
 * Created by Admin-PC on 2/20/2016.
 */
public class ProfileModel {
	public String name;

	public static final String[] data = {"Phone Number", "E - Mail", "Gender",
			"School Name", "D.O.B."};

	public static final String[] values = {"+91-9532666272", "aidtya.agrawal@smartprix.com", "Male",
			"KIET Ghaziabad", "11 - Oct - 1995"};

	ProfileModel(String name){
		this.name=name;
	}
}
