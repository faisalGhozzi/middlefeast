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
public class Tutorial {
    private int id;
    private String video;
    private String image;
    private LocalDateTime dateTuto;
    private String category;
    private String titre;
    private String description;
    private String prix;

    public Tutorial(int id, String video, String image, LocalDateTime dateTuto, String category, String titre, String description, String prix) {
        this.id = id;
        this.video = video;
        this.image = image;
        this.dateTuto = dateTuto;
        this.category = category;
        this.titre = titre;
        this.description = description;
        this.prix = prix;
    }

    public Tutorial(String video, String image, LocalDateTime dateTuto, String category, String titre, String description, String prix) {
        this.video = video;
        this.image = image;
        this.dateTuto = dateTuto;
        this.category = category;
        this.titre = titre;
        this.description = description;
        this.prix = prix;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LocalDateTime getDateTuto() {
        return dateTuto;
    }

    public void setDateTuto(LocalDateTime dateTuto) {
        this.dateTuto = dateTuto;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }
    
    @Override
    public String toString() {
        return "Tutorial{" + "video=" + video + ", image=" + image + ", dateTuto=" + dateTuto + ", category=" + category + ", titre=" + titre + ", description=" + description + ", prix=" + prix + '}';
    }
    
}
