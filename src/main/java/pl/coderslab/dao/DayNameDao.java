package pl.coderslab.dao;

import pl.coderslab.model.DayName;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DayNameDao {
    private static final String FIND_ALL_DAYS_QUERY = "SELECT * FROM day_name";
    private static final String READ_DAY_QUERY = "SELECT * from day_name where id = ?";


    public DayName read(int id) {
        DayName dayName = new DayName();
        try (Connection connection = DbUtil.getConnection(); PreparedStatement statement = connection.prepareStatement(READ_DAY_QUERY);) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                dayName.setId(resultSet.getInt("id"));
                dayName.setName(resultSet.getString("name"));
                dayName.setOrder(resultSet.getInt("order"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } return dayName;
    }


    public List<DayName> findAll() {
        List<DayName> dayNames = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection(); PreparedStatement statement = connection.prepareStatement(FIND_ALL_DAYS_QUERY); ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                DayName daysToAdd = new DayName();
                daysToAdd.setId(resultSet.getInt("id"));
                daysToAdd.setName(resultSet.getString("name"));
                daysToAdd.setOrder(resultSet.getInt("order"));
                dayNames.add(daysToAdd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dayNames;
    }
}