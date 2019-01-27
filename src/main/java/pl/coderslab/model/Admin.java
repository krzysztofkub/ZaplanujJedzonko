package pl.coderslab.model;

import pl.coderslab.utils.BCrypt;

public class Admin {

    private int id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private int superadmin;

    private int enable;

    public Admin(String firstName, String lastName, String email, String password, int superadmin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        setPassword(password);
        this.superadmin = superadmin;
    }

    public Admin(int id, String firstName, String lastName, String email, String password, int superadmin) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.superadmin = superadmin;
    }

    public Admin() {
    }

    public void setPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSuperadmin(int superadmin) {
        this.superadmin = superadmin;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getSuperadmin() {
        return superadmin;
    }

    public int isEnable() {
        return enable;
    }
}
