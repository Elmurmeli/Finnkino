package com.example.finnkino;

public class Theatre {
    private String id;
    private String theatre;

    public String getId(){
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTheatre(){
        return theatre;
    }

    public void setTheatre(String theatre) {
        this.theatre = theatre;
    }

    public Theatre(String newid, String newtheatre){
        id = newid;
        theatre = newtheatre;
    }
    @Override
    public String toString() {
        return theatre;
    }
}
