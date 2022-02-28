/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.app.entity;

import java.time.LocalDateTime;

/**
 *
 * @author Faycal Ghozzi
 */
public class Course {
    private int id;
    private int price;
    private String mode;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private String duree;
    private String description;

    public Course(int id, int price, String mode, LocalDateTime dateDebut, LocalDateTime dateFin, String duree, String description) {
        this.id = id;
        this.price = price;
        this.mode = mode;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.duree = duree;
        this.description = description;
    }

    public Course(int price, String mode, LocalDateTime dateDebut, LocalDateTime dateFin, String duree, String description) {
        this.price = price;
        this.mode = mode;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.duree = duree;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public LocalDateTime getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDateTime getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDateTime dateFin) {
        this.dateFin = dateFin;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Course{" + "price=" + price + ", mode=" + mode + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", duree=" + duree + ", description=" + description + '}';
    }
    
    
    

}