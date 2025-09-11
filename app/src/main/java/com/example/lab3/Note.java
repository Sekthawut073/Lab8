package com.example.lab3;

public abstract class Note {
    //Attribute
    public String title;
    public String createdDate;
    protected User owner;
    //getter
    public String getTitle() {
        return title;
    }
    public User getOwner() {
        return owner;
    }
    //setter
    public void setTitle(String newTitle) {
        this.title = newTitle;
    }
    //getter
    public String getCreatedDate() {
        return createdDate;
    }
    public void setOwner(User owner) {
        this.owner = owner;
    }
    //setter

    public void setCreatedDate(String newCreatedDate) {
        this.createdDate = newCreatedDate;
    }

    //Method
    abstract public String getSummary();

    //System.out.println (title=":"+textContent+"("+createdDate+")");
}




// System.out.println(title);
       // System.out.println(content);
       // System.out.println(createdDate);





