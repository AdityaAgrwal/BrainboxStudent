package com.brainbox.student.dto;

/**
 * Created by smpx-imac1 on 28/03/16.
 */
public class SearchSchoolDTO {
    private String name;
    private String location;
    private String id;
    private String admin;
    private String mission;
    private String facities;
    private String infrastructure;
    private String about;
    private String[] classRange;
    private String[] mobile;
    private SocialMediaDTO[] socialMedia;
    private String board;
    private String admissionProcess;
    private String address;
    private String website;
    private Integer reputation;
    private String imageLink;
    private String logoLink;

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getLogoLink() {
        return logoLink;
    }

    public void setLogoLink(String logoLink) {
        this.logoLink = logoLink;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getMission() {
        return mission;
    }

    public void setMission(String mission) {
        this.mission = mission;
    }

    public String getFacities() {
        return facities;
    }

    public void setFacities(String facities) {
        this.facities = facities;
    }

    public String getInfrastructure() {
        return infrastructure;
    }

    public void setInfrastructure(String infrastructure) {
        this.infrastructure = infrastructure;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String[] getClassRange() {
        return classRange;
    }

    public void setClassRange(String[] classRange) {
        this.classRange = classRange;
    }

    public String[] getMobile() {
        return mobile;
    }

    public void setMobile(String[] mobile) {
        this.mobile = mobile;
    }

    public SocialMediaDTO[] getSocialMedia() {
        return socialMedia;
    }

    public void setSocialMedia(SocialMediaDTO[] socialMedia) {
        this.socialMedia = socialMedia;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public String getAdmissionProcess() {
        return admissionProcess;
    }

    public void setAdmissionProcess(String admissionProcess) {
        this.admissionProcess = admissionProcess;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Integer getReputation() {
        return reputation;
    }

    public void setReputation(Integer reputation) {
        this.reputation = reputation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
