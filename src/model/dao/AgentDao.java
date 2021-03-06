package model.dao;

import model.entity.Agent;
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
public class AgentDao  {
    
    public static Agent getAgent(int id) throws DataNotFoundException {
        Connection conn = DBConnection.getConn();
        try {
            String sql = "SELECT * FROM agent WHERE id=? ";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            Agent agent = new Agent();
            if (rs.next()) {
                agent.setId(rs.getInt("id"));
                agent.setName(rs.getString("name"));
                agent.setPhone(rs.getString("phone"));
                agent.setEmail(rs.getString("email"));
                return agent;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new DataNotFoundException("agentDao: getAgent");

    }

    public static Agent getAgentByEmail(String email) throws DataNotFoundException {
        Connection conn = DBConnection.getConn();
        try {
            String sql = "SELECT * FROM agent WHERE Email=? ";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();
            Agent agent = new Agent();
            if (rs.next()) {
                agent.setId(rs.getInt("id"));
                agent.setName(rs.getString("name"));
                agent.setPhone(rs.getString("phone"));
                agent.setEmail(rs.getString("email"));
                return agent;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new DataNotFoundException("agentDao: getAgent");

    }

    public static List<Agent> getAllAgent() throws DataNotFoundException {
        Connection conn = DBConnection.getConn();
        try {
            String sql = "SELECT * FROM agent";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            List<Agent> list = new ArrayList<Agent>();
            while (rs.next()) {
                Agent agent = new Agent();
                agent.setId(rs.getInt("id"));
                agent.setName(rs.getString("name"));
                agent.setPhone(rs.getString("phone"));
                agent.setEmail(rs.getString("email"));
                list.add(agent);
            }
            if (list.isEmpty())
                throw new DataNotFoundException("agentDao: getAllAgent");
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    
    public static void addAgent(Agent agent) {
        Connection conn = DBConnection.getConn();
        try {
            String sql = "INSERT INTO agent (name,phone,email) VALUE (?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, agent.getName());
            preparedStatement.setString(2, agent.getPhone());
            preparedStatement.setString(3, agent.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return;
    }

    
    public static boolean delAgent(int id) {
        Connection conn = DBConnection.getConn();
        try {
            String sql = "DELETE FROM agent WHERE id=?";
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

    
    public static boolean updateAgent(Agent agent) {
        Connection conn = DBConnection.getConn();
        try {
            String sql = "UPDATE agent SET name=?,phone=?,email=? WHERE id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1, agent.getName());
            preparedStatement.setString(2, agent.getPhone());
            preparedStatement.setString(3, agent.getEmail());
            preparedStatement.setInt(4, agent.getId());
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
