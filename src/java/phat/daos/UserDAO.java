/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phat.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import phat.dtos.UserDTO;
import phat.utils.DBUtils;

/**
 *
 * @author ACER
 */
public class UserDAO {
    
    

    public UserDTO checkLogin(String userID, String password) throws SQLException {
        UserDTO result = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String url = "Select userID, fullName, address, phone, roleID\n"
                        + "From tblUsers\n"
                        + "Where userID=? AND password=?";
                stm = conn.prepareStatement(url);
                stm.setString(1, userID);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String fullName = rs.getString("fullName");
                    String address = rs.getString("address");
                    String phone = rs.getString("phone");
                    String roleID = rs.getString("roleID");
                    result = new UserDTO(userID, fullName, address, phone, "****", roleID);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }

    public boolean checkID(String userID) throws SQLException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT fullName FROM tblUsers\n"
                        + " WHERE userID=?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, userID);

                rs = stm.executeQuery();
                if (rs.next()) {
                    result = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }

    public void create(UserDTO user) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "insert into tblUsers(userID,fullName,address,phone,password,'US')\n"
                        + "values (?,?,?,?,?,?)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, user.getUserID());
                stm.setString(2, user.getFullName());
                stm.setString(3, user.getAddress());
                stm.setString(4, user.getPhone());
                stm.setString(5, user.getPassword());
                stm.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
}
