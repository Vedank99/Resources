package com.example.resources;

public class folders {

    String name;
    String url;

    public folders(){

    }
    public folders(String name,String url){
        this.name = name;
        this.url = url;
    }

    public String getName(){
        return name;
    }

    public String getUrl(){
        return url;
    }

}
