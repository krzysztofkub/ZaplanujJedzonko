package pl.coderslab.model;

import java.sql.Date;

public class Recipe {
    private int id;
    private String name;
    private String ingriedients;
    private String description;
    private Date created;
    private Date updated;
    private int preparationTime;
    private Admin admin;


    public Recipe() {
    }


    public Recipe(String name, String ingriedients, String description, int preparationTime, Admin admin) {
        this.name = name;
        this.ingriedients = ingriedients;
        this.description = description;
        this.preparationTime = preparationTime;
        this.admin = admin;
    }


    public Recipe(String name, String ingriedients, String description, Date created, Date updated, int preperationTime, Admin admin) {
        this.name = name;
        this.ingriedients = ingriedients;
        this.description = description;
        this.created = created;
        this.updated = updated;
        this.preparationTime = preperationTime;
        this.admin = admin;
    }


    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    public String getIngriedients() {
        return ingriedients;
    }


    public String getDescription() {
        return description;
    }


    public Date getCreated() {
        return created;
    }


    public Date getUpdated() {
        return updated;
    }


    public int getPreparationTime() {
        return preparationTime;
    }


    public Admin getAdmin() {
        return admin;
    }


    public void setId(int id) {
        this.id = id;
    }


    public void setName(String name) {
        this.name = name;
    }


    public void setIngriedients(String ingriedients) {
        this.ingriedients = ingriedients;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public void setCreated(Date created) {
        this.created = created;
    }


    public void setUpdated(Date updated) {
        this.updated = updated;
    }


    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }


    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
}


