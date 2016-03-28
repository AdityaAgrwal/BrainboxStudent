package com.brainbox.student.dto;

/**
 * Created by Admin-PC on 2/24/2016.
 */
public class SchoolListItemDTO
{
	private String id;
	private String name;
	private String address;
	private String aboutUs;
	private String image;


	public String getAboutUs()
	{
		return aboutUs;
	}

	public void setAboutUs(String aboutUs)
	{
		this.aboutUs = aboutUs;
	}

	public String getImage()
	{
		return image;
	}

	public void setImage(String image)
	{
		this.image = image;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}


	@Override
	public String toString()
	{
		return "SchoolListItemDTO{" +
				"address='" + address + '\'' +
				", id='" + id + '\'' +
				", name='" + name + '\'' +
				", image='" + image + '\'' +
				'}';
	}
}
