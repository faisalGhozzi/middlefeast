/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.app.entity;


/**
 *
 * @author Faycal Ghozzi
 */
public class Course {
    private int id;
    private int price;
    private String mode;
    private String dateDebut;
    private String dateFin;
    private String duree;
    private String description;
    
    public Course(){}

    public Course(int id, int price, String mode, String dateDebut, String dateFin, String duree, String description) {
        this.id = id;
        this.price = price;
        this.mode = mode;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.duree = duree;
        this.description = description;
    }

    public Course(int price, String mode, String dateDebut, String dateFin, String duree, String description) {
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

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
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
