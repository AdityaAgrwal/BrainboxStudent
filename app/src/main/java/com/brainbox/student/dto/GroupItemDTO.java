package com.brainbox.student.dto;

/**
 * Created by Admin-PC on 2/19/2016.
 */
public class GroupItemDTO
{
	private String name;
	private Integer imageLink;
	private String disc;

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
