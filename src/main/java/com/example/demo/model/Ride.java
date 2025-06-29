package com.example.demo.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Ride {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private BigDecimal currentLatitude;
    private BigDecimal currentLongitude;
    private BigDecimal homeLatitude;
    private BigDecimal homeLongitude;
    private boolean needsRide;
    private LocalDateTime requestTime;
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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public BigDecimal getCurrentLatitude() {
        return currentLatitude;
    }
    public void setCurrentLatitude(BigDecimal currentLatitude) {
        this.currentLatitude = currentLatitude;
    }
    public BigDecimal getCurrentLongitude() {
        return currentLongitude;
    }
    public void setCurrentLongitude(BigDecimal currentLongitude) {
        this.currentLongitude = currentLongitude;
    }
    public BigDecimal getHomeLatitude() {
        return homeLatitude;
    }
    public void setHomeLatitude(BigDecimal homeLatitude) {
        this.homeLatitude = homeLatitude;
    }
    public BigDecimal getHomeLongitude() {
        return homeLongitude;
    }
    public void setHomeLongitude(BigDecimal homeLongitude) {
        this.homeLongitude = homeLongitude;
    }
    public boolean isNeedsRide() {
        return needsRide;
    }
    public void setNeedsRide(boolean needsRide) {
        this.needsRide = needsRide;
    }
    
    public LocalDateTime getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(LocalDateTime requestTime) {
        this.requestTime = requestTime;
    }
    
    @Override
    public String toString() {
        return "Ride{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", currentLatitude=" + currentLatitude +
                ", currentLongitude=" + currentLongitude +
                ", homeLatitude=" + homeLatitude +
                ", homeLongitude=" + homeLongitude +
                ", needsRide=" + needsRide +
                ", requestTime=" + requestTime +
                '}';
    }
}
