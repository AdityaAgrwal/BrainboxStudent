package com.brainbox.student.dto;

/**
 * Created by adityaagrawal on 18/02/16.
 */
public class LibraryItemDTO {
    private String name;
    private Integer imageLink;
    private String link;
    private String id;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getImageLink() {
        return imageLink;
    }

    public void setImageLink(Integer imageLink) {
        this.imageLink = imageLink;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
