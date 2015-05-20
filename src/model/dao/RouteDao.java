package model.dao;

import model.entity.Entity;
import model.entity.Route;
import util.DB.DBConnection;
import util.common.DataNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hdd on 13/05/15.
 */
public class RouteDao implements DaoInterface {
    @Override
    public Entity getEntity(int id) throws DataNotFoundException {
        Connection conn = DBConnection.getConn();
        try {
            String sql = "SELECT * FROM route WHERE id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            Route route = new Route();
            if (rs.next()) {
                route.setId(rs.getInt("id"));
                route.setSourceAirport(rs.getString("Source_airport"));
                route.setDestinationAirport(rs.getString("Destination_airport"));
                if (rs.getString("Codeshare") == null)
                    route.setCodeShared(false);
                else
                    route.setCodeShared(true);
                route.setStop(rs.getInt("Stops"));
                return route;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        throw new DataNotFoundException("RouteDao: getEntity");
    }

    @Override
    public List<Entity> getAllEntity() throws DataNotFoundException {
        Connection conn = DBConnection.getConn();
        try {
            String sql = "SELECT * FROM route";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            List<Entity> list = new ArrayList<Entity>();
            while (rs.next()) {
                Route route = new Route();
                route.setId(rs.getInt("id"));
                route.setSourceAirport(rs.getString("Source_airport"));
                route.setDestinationAirport(rs.getString("Destination_airport"));
                if (rs.getString("Codeshare") == null)
                    route.setCodeShared(false);
                else
                    route.setCodeShared(true);
                route.setStop(rs.getInt("Stops"));
                list.add(route);
            }
            if (list.isEmpty())
                throw new DataNotFoundException("RouteDao: getAllEntity");
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Route getRoute(String sourceCode, String destinationCode) throws DataNotFoundException {
        Connection conn = DBConnection.getConn();
        try {
            String sql = "SELECT * FROM route WHERE Source_airport=? AND Destination_airport=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, sourceCode);
            preparedStatement.setString(2, destinationCode);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                Route route = new Route();
                route.setId(rs.getInt("id"));
                route.setSourceAirport(rs.getString("Source_airport"));
                route.setDestinationAirport(rs.getString("Destination_airport"));
                if (rs.getString("Codeshare") == null)
                    route.setCodeShared(false);
                else
                    route.setCodeShared(true);
                route.setStop(rs.getInt("Stops"));
                return route;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new DataNotFoundException("RouteDao: getRoute");
    }

    @Override
    public void addEntity(Entity entity) {
        Connection conn = DBConnection.getConn();
        try {
            String sql = "INSERT INTO route (Source_airport,Destination_airport,Codeshare,Stops) VALUE (?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            Route route = (Route) entity;
            preparedStatement.setString(1, route.getSourceAirport());
            preparedStatement.setString(2, route.getDestinationAirport());
            if (route.isCodeShared())
                preparedStatement.setString(3, "Y");
            else {
                preparedStatement.setString(3, null);
            }
            preparedStatement.setInt(4, route.getStop());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean delEntity(int id) {
        Connection conn = DBConnection.getConn();
        try {
            String sql = "DELETE FROM route WHERE id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            int col = preparedStatement.executeUpdate();
            if (col > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateEntity(Entity entity) {
        Connection conn = DBConnection.getConn();
        try {
            String sql = "UPDATE route SET Source_airport=?,Destination_airport=?,Codeshare=?,Stops=? WHERE id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            Route route = (Route) entity;
            preparedStatement.setString(1, route.getSourceAirport());
            preparedStatement.setString(2, route.getDestinationAirport());
            if (route.isCodeShared())
                preparedStatement.setString(3, "Y");
            else {
                preparedStatement.setString(3, null);
            }
            preparedStatement.setInt(4, route.getStop());
            preparedStatement.setInt(5, route.getId());
            int col = preparedStatement.executeUpdate();
            if (col > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
