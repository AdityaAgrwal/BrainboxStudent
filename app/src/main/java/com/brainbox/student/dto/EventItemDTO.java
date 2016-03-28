package com.brainbox.student.dto;

/**
 * Created by Admin-PC on 2/19/2016.
 */
public class EventItemDTO
{
	private String name;
	private Integer imageLink;
	private String disc;
	private String date;

	public String getDate()
	{
		return date;
	}

	public void setDate(String date)
	{
		this.date = date;
	}

	public String getDisc()
	{
		return disc;
	}

	public void setDisc(String disc)
	{
		this.disc = disc;
	}

	public Integer getImageLink()
	{
		return imageLink;
	}

	public void setImageLink(Integer imageLink)
	{
		this.imageLink = imageLink;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
}
