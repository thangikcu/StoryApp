package com.junior.myapplication.model.entity;

public class Story {
    private String imageUrl;
    private String name;
    private String content;
    private String url;
    private String author;

    public Story(String imageUrl, String name,String author, String content, String url) {
        this.imageUrl = imageUrl;
        this.name = name;
        this.content = content;
        this.url = url;
        this.author=author;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public String getUrl() {
        return url;
    }

    public String getAuthor() {
        return author;
    }
}