package com.example.syl.nobitaxuka;

import java.io.Serializable;

/**
 * Created by Syl on 10/28/2016.
 */
public class Miracle implements Serializable {
    private int status;
    private String name;
    private String guild;
    private String urlImage;

    public Miracle(){

    }

    public Miracle(int status, String name, String guild, String urlImage) {
        this.status = status;
        this.name = name;
        this.guild = guild;
        this.urlImage = urlImage;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGuild() {
        return guild;
    }

    public void setGuild(String guild) {
        this.guild = guild;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
}
