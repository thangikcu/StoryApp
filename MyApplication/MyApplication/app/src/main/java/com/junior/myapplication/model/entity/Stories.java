package com.junior.myapplication.model.entity;

/**
 * Created by Admin on 02/10/2017.
 */

public class Stories {
    private long id;
    private String name;
    private String author;
    private String content;
    private int favorite;
    public Stories( String name , String author, String content, int favorite){
        this.name=name;
        this.author=author;
        this.content=content;
        this.favorite=favorite;

    }

    public Stories(long id, String name , String author, String content, int favorite){
        this.id=id;
        this.name=name;
        this.author=author;
        this.content=content;
        this.favorite=favorite;

    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public long getId() {
        return id;
    }

    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
