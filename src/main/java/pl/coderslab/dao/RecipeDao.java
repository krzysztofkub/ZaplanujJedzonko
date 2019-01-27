package pl.coderslab.dao;

import pl.coderslab.exception.NotFoundException;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Recipe;
import pl.coderslab.utils.DbUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecipeDao {
    private static final String CREATE_Recipe_QUERY = "INSERT INTO recipe (name,ingredients,description,created,updated,preparation_time,admin_id) VALUES (?,?,?,NOW(),null ,?,?)";
    private static final String DELETE_Recipe_QUERY = "DELETE FROM recipe where id = ?";
    private static final String FIND_ALL_Recipe_QUERY = "SELECT * FROM recipe";
    private static final String FIND_BY_ADMIN_Recipes__QUERY = "SELECT * FROM recipe WHERE admin_id=? order by created desc";
    private static final String COUNT_Recipes_By_Admin_QUERY = "SELECT COUNT(*) FROM recipe WHERE admin_id=?";
    private static final String READ_Recipe_QUERY = "SELECT * from recipe where id = ?";
    private static final String UPDATE_Recipe_QUERY = "UPDATE recipe SET name = ? , ingredients = ?, description = ?, created = ?, updated = ?, preparation_time = ?, admin_id=? WHERE id = ?";


    public List<Recipe> findByAdmin(Admin admin) {
        List<Recipe> recipes = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection(); PreparedStatement statement = connection.prepareStatement(FIND_BY_ADMIN_Recipes__QUERY);) {
            statement.setInt(1, admin.getId());
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Recipe recipe = new Recipe();
                    recipe.setId(resultSet.getInt("id"));
                    recipe.setName(resultSet.getString("name"));
                    recipe.setIngriedients(resultSet.getString("ingredients"));
                    recipe.setDescription(resultSet.getString("description"));
                    recipe.setCreated(resultSet.getDate("created"));
                    recipe.setUpdated(resultSet.getDate("updated"));
                    recipe.setPreparationTime(resultSet.getInt("preparation_time"));
                    recipe.setAdmin(new AdminDao().read(resultSet.getInt("admin_id")));
                    recipes.add(recipe);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recipes;
    }


    public int countRecipesByAdmin(Admin admin) {
        int count = 0;
        try (Connection connection = DbUtil.getConnection(); PreparedStatement statement = connection.prepareStatement(COUNT_Recipes_By_Admin_QUERY);) {
            statement.setInt(1, admin.getId());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    count = resultSet.getInt("COUNT(*)");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }


    public Recipe read(int id) {
        Recipe recipe = new Recipe();
        try (Connection connection = DbUtil.getConnection(); PreparedStatement statement = connection.prepareStatement(READ_Recipe_QUERY);) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    recipe.setId(resultSet.getInt("id"));
                    recipe.setName(resultSet.getString("name"));
                    recipe.setIngriedients(resultSet.getString("ingredients"));
                    recipe.setDescription(resultSet.getString("description"));
                    recipe.setCreated(resultSet.getDate("created"));
                    recipe.setUpdated(resultSet.getDate("updated"));
                    recipe.setPreparationTime(resultSet.getInt("preparation_time"));
                    recipe.setAdmin(new AdminDao().read(resultSet.getInt("admin_id")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recipe;
    }


    public List<Recipe> findAll() {
        List<Recipe> recipesList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection(); PreparedStatement statement = connection.prepareStatement(FIND_ALL_Recipe_QUERY); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Recipe recipeToAdd = new Recipe();
                recipeToAdd.setId(resultSet.getInt("id"));
                recipeToAdd.setName(resultSet.getString("name"));
                recipeToAdd.setIngriedients(resultSet.getString("ingredients"));
                recipeToAdd.setDescription(resultSet.getString("description"));
                recipeToAdd.setCreated(resultSet.getDate("created"));
                recipeToAdd.setUpdated(resultSet.getDate("updated"));
                recipeToAdd.setPreparationTime(resultSet.getInt("preparation_time"));
                recipeToAdd.setAdmin(new AdminDao().read(resultSet.getInt("admin_id")));
                recipesList.add(recipeToAdd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipesList;

    }


    public Recipe create(Recipe recipe) {
        try (Connection connection = DbUtil.getConnection(); PreparedStatement insertStm = connection.prepareStatement(CREATE_Recipe_QUERY, PreparedStatement.RETURN_GENERATED_KEYS)) {
            insertStm.setString(1, recipe.getName());
            insertStm.setString(2, recipe.getIngriedients());
            insertStm.setString(3, recipe.getDescription());
            insertStm.setInt(4, recipe.getPreparationTime());
            insertStm.setInt(5, recipe.getAdmin().getId());
            int result = insertStm.executeUpdate();
            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }
            try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    recipe.setId(generatedKeys.getInt(1));
                    return recipe;
                } else {
                    throw new RuntimeException("Generated key was not found");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public void delete(Integer recipeId) {
        try (Connection connection = DbUtil.getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_Recipe_QUERY);) {
            statement.setInt(1, recipeId);
            statement.executeUpdate();
            boolean deleted = statement.execute();
            if (!deleted) {
                throw new NotFoundException("Product not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void update(Recipe recipe) {
        try (Connection connection = DbUtil.getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_Recipe_QUERY);) {
            statement.setString(1, recipe.getName());
            statement.setString(2, recipe.getIngriedients());
            statement.setString(3, recipe.getDescription());
            statement.setDate(4, recipe.getCreated());
            statement.setDate(5, recipe.getUpdated());
            statement.setInt(6, recipe.getPreparationTime());
            statement.setInt(7, recipe.getAdmin().getId());
            statement.setInt(8, recipe.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
