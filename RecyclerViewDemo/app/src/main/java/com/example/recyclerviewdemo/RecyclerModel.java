package com.example.recyclerviewdemo;


public class RecyclerModel {
    private int imageView;
    private String nameView;
    private String messageView;
    private String timeView;
    RecyclerModel(int imageView, String nameView, String messageView, String timeView){
        this.imageView = imageView;
        this.nameView = nameView;
        this.messageView = messageView;
        this.timeView = timeView;
    }

    public int getImageView() {
        return imageView;
    }

    public String getNameView() {
        return nameView;
    }

    public String getMessageView() {
        return messageView;
    }

    public String getTimeView() {
        return timeView;
    }
}
