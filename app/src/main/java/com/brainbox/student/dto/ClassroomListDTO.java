package com.brainbox.student.dto;

/**
 * Created by adityaagrawal on 18/02/16.
 */
public class ClassroomListDTO {
    private Boolean isHeading;
    private String text;

    public Boolean getIsHeading() {
        return isHeading;
    }

    public void setIsHeading(Boolean isHeading) {
        this.isHeading = isHeading;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
