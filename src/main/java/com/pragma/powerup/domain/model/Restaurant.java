package com.pragma.powerup.domain.model;

public class Restaurant {
    private Long id;
    private String name;
    private String address;
    private Long idOwner;
    private String phoneNumber;
    private String logoUrl;
    private Long nit;

    public Restaurant(Long id, String name, String address, Long idOwner, String phoneNumber, String logoUrl, Long nit) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.idOwner = idOwner;
        this.phoneNumber = phoneNumber;
        this.logoUrl = logoUrl;
        this.nit = nit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(Long idOwner) {
        this.idOwner = idOwner;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public Long getNit() {
        return nit;
    }

    public void setNit(Long nit) {
        this.nit = nit;
    }
}