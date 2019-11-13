package by.epam.fitness.dao.impl;

import by.epam.fitness.builder.NutritionBuilder;
import by.epam.fitness.dao.NutritionDao;
import by.epam.fitness.dao.exception.DaoException;
import by.epam.fitness.entity.Nutrition;
import by.epam.fitness.pool.ConnectionPool;
import by.epam.fitness.service.ServiceException;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class NutritionDaoImpl implements NutritionDao {
    private static final String SQL_CREATE_TABLE = "INSERT INTO nutrition (name, morning_nutrition, dinner_nutrition, lunch_nutrition) VALUES (?,?,?,?)";
    private static final String SQL_UPDATE_TABLE = "UPDATE nutrition SET name=?, morning_nutrition=?, dinner_nutrition=?, lunch_nutrition=?, active=? WHERE id_nutrition=?";
    private static final String SQL_FIND_BY_CLIENT_ID = "SELECT * FROM nutrition JOIN program ON nutrition.id_nutrition = program.nutrition_id JOIN client ON program.id_program = client.program_id WHERE id_client=?";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM nutrition WHERE id_nutrition=?";
    private NutritionBuilder builder = new NutritionBuilder();;

    @Override
    public Long save(Nutrition nutrition) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String name = nutrition.getName();
        String morning_nutrition = nutrition.getMorningNutrition();
        String dinner_nutrition = nutrition.getDinnerNutrition();
        String lunch_nutrition = nutrition.getLunchNutrition();
        Long generatedId = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            if (nutrition.getId() != null) {
                preparedStatement = connection.prepareStatement(SQL_UPDATE_TABLE, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setBoolean(5, nutrition.isActive());
                preparedStatement.setLong(6, nutrition.getId());
            } else {
                preparedStatement = connection.prepareStatement(SQL_CREATE_TABLE, Statement.RETURN_GENERATED_KEYS);
            }
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, morning_nutrition);
            preparedStatement.setString(3, dinner_nutrition);
            preparedStatement.setString(4, lunch_nutrition);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                generatedId = resultSet.getLong(1);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return generatedId;
    }

    @Override
    public Optional<Nutrition> findByClientId(long clientId) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Nutrition nutrition = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_BY_CLIENT_ID);
            preparedStatement.setLong(1, clientId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                nutrition = builder.build(resultSet);
            }
        } catch (SQLException | ServiceException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return Optional.ofNullable(nutrition);
    }

    @Override
    public Optional<Nutrition> findById(long id) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Nutrition nutrition = null;
        try{
            connection = ConnectionPool.INSTANCE.getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                nutrition = builder.build(resultSet);
            }
        } catch (SQLException | ServiceException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return Optional.ofNullable(nutrition);
    }

    @Override
    public List<Nutrition> findAll() {
        return null;
    }

    @Override
    public List<Nutrition> findEntityById(Long id) {
        return null;
    }

    @Override
    public boolean delete(Nutrition nutrition) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public Nutrition update(Nutrition nutrition) {
        return null;
    }
}
