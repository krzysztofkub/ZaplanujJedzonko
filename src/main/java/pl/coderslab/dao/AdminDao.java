package pl.coderslab.dao;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.model.Admin;
import pl.coderslab.exception.NotFoundException;
import pl.coderslab.utils.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDao {
    private static final String CREATE_ADMIN = "INSERT INTO admins(first_name, last_name, email, password, superadmin,enable) VALUES (?,?,?,?,?,1)";
    private static final String DELETE_ADMIN = "DELETE FROM admins where id = ?";
    private static final String FIND_ALL_ADMIN = "SELECT * FROM admins";
    private static final String FIND_ADMIN_BY_ID = "SELECT * from admins where id = ?";
    private static final String FIND_ADMIN_BY_EMAIL = "SELECT * from admins where email = ?";
    private static final String UPDATE_ADMIN = "UPDATE	admins SET first_name = ? , last_name = ?, email = ?, password= ?, superadmin = ?, enable = ? WHERE	id = ?";


    /**
     * registration new user/admin
     */
    public Integer logIn(String mail, String password) {
        int idlogin = 0;
        try (Connection connection = DbUtil.getConnection(); PreparedStatement statement = connection.prepareStatement(FIND_ADMIN_BY_EMAIL);) {

            statement.setString(1, mail);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {                                                      //szukam maila w bazie
                    String password2 = resultSet.getString("password");//sczytuje zaczytane hasło
                    int enable2 = resultSet.getInt("enable");

                    if (BCrypt.checkpw(password, password2) && enable2 == 1) {
                        // if(password2.equals(password) ){  //jeśli hasło podane i hasło z bazy sie zgadzają
                        idlogin = resultSet.getInt("id"); //pobieram login danego obiektu
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return idlogin; //zwraca id znalezionego obiektu o potwierdzonym mailu i haśle
    }


    /**
     * Get admin by id
     *
     * @param adminId
     * @return
     */
    public Admin read(Integer adminId) {

        try (Connection connection = DbUtil.getConnection(); PreparedStatement statement = connection.prepareStatement(FIND_ADMIN_BY_ID);) {
            statement.setInt(1, adminId);
            Admin admin = new Admin();
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    admin.setId(resultSet.getInt("id"));
                    admin.setFirstName(resultSet.getString("first_name"));
                    admin.setLastName(resultSet.getString("last_name"));
                    admin.setEmail(resultSet.getString("email"));
                    admin.setPassword(resultSet.getString("password"));
                    admin.setSuperadmin(resultSet.getInt("superadmin"));
                    admin.setEnable(resultSet.getInt("enable"));
                    return admin;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }


    /**
     * Get admin by email
     *
     * @param email
     * @return
     */
    public Admin readByEmail(String email) {

        try (Connection connection = DbUtil.getConnection(); PreparedStatement statement = connection.prepareStatement(FIND_ADMIN_BY_EMAIL);) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                Admin admin = new Admin();
                while (resultSet.next()) {
                    admin.setId(resultSet.getInt("id"));
                    admin.setFirstName(resultSet.getString("first_name"));
                    admin.setLastName(resultSet.getString("last_name"));
                    admin.setEmail(email);
                    admin.setPassword(resultSet.getString("password"));
                    admin.setSuperadmin(resultSet.getInt("superadmin"));
                    admin.setEnable(resultSet.getInt("enable"));
                    return admin;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }


    /**
     * Return all admin
     *
     * @return
     */
    public List<Admin> findAll() {
        List<Admin> adminList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();

             PreparedStatement statement = connection.prepareStatement(FIND_ALL_ADMIN); ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Admin adminToAdd = new Admin();
                adminToAdd.setId(resultSet.getInt("id"));
                adminToAdd.setFirstName(resultSet.getString("first_name"));
                adminToAdd.setLastName(resultSet.getString("last_name"));
                adminToAdd.setEmail(resultSet.getString("email"));
                adminToAdd.setPassword(resultSet.getString("password"));
                adminToAdd.setSuperadmin(resultSet.getInt("superadmin"));
                adminToAdd.setEnable(resultSet.getInt("enable"));
                adminList.add(adminToAdd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adminList;

    }


    /**
     * Create admin
     *
     * @param admin
     * @return
     */
    public Admin create(Admin admin) {
        try (Connection connection = DbUtil.getConnection();

             PreparedStatement insertStm = connection.prepareStatement(CREATE_ADMIN, PreparedStatement.RETURN_GENERATED_KEYS)) {
            insertStm.setString(1, admin.getFirstName());
            insertStm.setString(2, admin.getLastName());
            insertStm.setString(3, admin.getEmail());
            insertStm.setString(4, admin.getPassword());
            insertStm.setInt(5, admin.getSuperadmin());
            int result = insertStm.executeUpdate();

            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }

            try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    admin.setId(generatedKeys.getInt(1));
                    return admin;
                } else {
                    throw new RuntimeException("Generated key was not found");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Remove admin by id
     *
     * @param adminId
     */
    public void delete(Integer adminId) {
        try (Connection connection = DbUtil.getConnection();

             PreparedStatement statement = connection.prepareStatement(DELETE_ADMIN);) {
            statement.setInt(1, adminId);
            statement.executeUpdate();

            boolean deleted = statement.execute();
            if (!deleted) {
                throw new NotFoundException("Admin not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Update admin
     *
     * @param admin
     */
    public void update(Admin admin) {
        try (Connection connection = DbUtil.getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_ADMIN);) {

            statement.setString(1, admin.getFirstName());
            statement.setString(2, admin.getLastName());
            statement.setString(3, admin.getEmail());
            statement.setString(4, admin.getPassword());
            statement.setInt(5, admin.getSuperadmin());
            statement.setInt(6, admin.isEnable());
            statement.setInt(7, admin.getId());

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}