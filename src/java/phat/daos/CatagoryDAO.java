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
import java.util.ArrayList;
import java.util.List;
import phat.dtos.CatagoryDTO;
import phat.utils.DBUtils;

/**
 *
 * @author ACER
 */
public class CatagoryDAO {
    public List<CatagoryDTO> getListCatagory() throws SQLException{
        List<CatagoryDTO> result = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT catagoryID, catagoryName\n" +
                        "FROM tblCatagory";
            stm = conn.prepareStatement(sql);
//            stm.setString(1, "%" + name + "%");
            rs = stm.executeQuery();
            while (rs.next()){
                String catagoryID = rs.getString("catagoryID");
                String catagoryName = rs.getString("catagoryName");
                if (result == null) {
                    result = new ArrayList<>();
                }
                result.add(new CatagoryDTO(catagoryID, catagoryName));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
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
}
