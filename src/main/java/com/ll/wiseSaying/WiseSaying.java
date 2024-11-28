package com.ll.wiseSaying;

public class WiseSaying {
    private int id;
    private String author;
    private String comment;

    public WiseSaying(int id, String author, String comment) {
        this.id = id;
        this.author = author;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getComment() {
        return comment;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
