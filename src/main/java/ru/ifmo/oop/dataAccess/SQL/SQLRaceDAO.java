package ru.ifmo.oop.dataAccess.SQL;

import ru.ifmo.oop.dataAccess.Exception.ExceptionDAO;
import ru.ifmo.oop.dataAccess.RaceDAO;
import ru.ifmo.oop.dataAccess.DTO.RaceDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLRaceDAO implements RaceDAO {
    private Connection connection;
    private int getIdBehavior(RaceDTO.Behavior behavior){
        switch (behavior){
            case ANGRY:
                return 1;
            case NEUTRAL:
                return 2;
                default:
                    return 2;
        }
    }
    public SQLRaceDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<RaceDTO> getCountryRaces(int idCountry) throws ExceptionDAO {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT Race.name,amount,B.name,Race.idRace " +
                            "FROM Race " +
                            "INNER JOIN Behavior B on Race.idBehavior = B.idBehavior " +
                            "WHERE idCountry = ?");
            preparedStatement.setInt(1,idCountry);
            ResultSet resultSet = preparedStatement.executeQuery();
            connection.commit();
            List<RaceDTO> races = new ArrayList<>();
            while (resultSet.next()) {
                races.add(new RaceDTO(resultSet.getInt(4),resultSet.getString(1),
                        resultSet.getLong(2),
                        resultSet.getString(3),
                        idCountry));
            }
            return races;
        } catch (SQLException e) {
            throw new ExceptionDAO(e);
        }
    }

    @Override
    public int insert(int idCountry, RaceDTO race) throws ExceptionDAO {
        try {
            PreparedStatement preparedStatementRace = connection.prepareStatement("INSERT INTO" +
                    " Race('idCountry','name','amount','idBehavior') VALUES (?,?,?,?)");

            preparedStatementRace.setInt(1, idCountry);
            preparedStatementRace.setString(2, race.getName());
            preparedStatementRace.setLong(3, race.getAmount());
            preparedStatementRace.setInt(4, race.getBoolBehavior() ? 2 : 1);
            preparedStatementRace.execute();
            int raceID = this.connection.createStatement().executeQuery(
                    "SELECT last_insert_rowid()").getInt(1);
            race.setId(raceID);
            return raceID;
        } catch (SQLException e) {
            throw new ExceptionDAO(e);
        }
    }

    @Override
    public void delete(int idRace) throws ExceptionDAO {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM Race WHERE idRace = ?");
            preparedStatement.setInt(1,idRace);
            preparedStatement.execute();
        }
        catch (SQLException e){
            throw new ExceptionDAO(e);
        }
    }

    @Override
    public void update(int idRace, RaceDTO race) throws ExceptionDAO {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE Race SET name = ?, amount = ?, idBehavior = ? WHERE idRace = ?");
            preparedStatement.setString(1, race.getName());
            preparedStatement.setLong(2, race.getAmount());
            preparedStatement.setInt(3,getIdBehavior(race.getBehavior()));
            preparedStatement.setInt(4,idRace);
            preparedStatement.execute();
        }
        catch (SQLException e) {
            throw new ExceptionDAO(e);
        }
    }
}
