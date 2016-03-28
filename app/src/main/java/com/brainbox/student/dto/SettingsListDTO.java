package com.brainbox.student.dto;

/**
 * Created by Admin-PC on 2/20/2016.
 */
public class SettingsListDTO
{
	private Boolean isHeading;
	private String text;
	private Boolean isCheckBox;

	public Boolean getIsCheckBox()
	{
		return isCheckBox;
	}

	public void setIsCheckBox(Boolean isCheckBox)
	{
		this.isCheckBox = isCheckBox;
	}

	public Boolean getIsHeading()
	{
		return isHeading;
	}

	public void setIsHeading(Boolean isHeading)
	{
		this.isHeading = isHeading;
	}

	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = text;
	}
}
