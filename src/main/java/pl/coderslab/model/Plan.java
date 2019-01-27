package pl.coderslab.model;

import java.sql.Date;

public class Plan {
    private int id;
    private String name;
    private String description;
    private Date created;
    private Admin admin;


    public Plan() {
    }


    public Plan(String name, String description, Date created, Admin admin) {
        this.name = name;
        this.description = description;
        this.created = created;
        this.admin = admin;
    }


    public Plan(int id, String name, String description, Date created, Admin admin) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.created = created;
        this.admin = admin;
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public Date getCreated() {
        return created;
    }


    public void setCreated(Date created) {
        this.created = created;
    }


    public Admin getAdmin() {
        return admin;
    }


    public void setAdmin(Admin admin) {
        this.admin = admin;
    }


}
