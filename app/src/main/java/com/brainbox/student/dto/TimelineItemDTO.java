package com.brainbox.student.dto;

/**
 * Created by adityaagrawal on 17/02/16.
 */
public class TimelineItemDTO
{

    private String name;
    private String image;
    private String disc;
    private String profilePic;

    public String getProfilePic()
    {
        return profilePic;
    }

    public void setProfilePic(String profilePic)
    {
        this.profilePic = profilePic;
    }

    public String getDisc()
    {
        return disc;
    }

    public void setDisc(String disc)
    {
        this.disc = disc;
    }

    public String getImage()
    {
        return image;
    }

    public void setImage(String image)
    {
        this.image = image;
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