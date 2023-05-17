package com.example.project;

public class User {
    private String name;
    private String gameTime;

    public User(String gameTime, String name)
    {
        this.gameTime=gameTime;
        this.name=name;
    }
    public User()
    {

    }

    public String getGameTime() {
        return this.gameTime;
    }

    public void setGameTime(String gameTime) {
        this.gameTime=gameTime;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
