package com.example.resources;

public class pdfs {

    String name;
    String url;

    public pdfs(){

    }
    public pdfs(String name,String url){
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
