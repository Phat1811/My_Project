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
import phat.dtos.ProductDTO;
import phat.utils.DBUtils;

/**
 *
 * @author ACER
 */
public class ProductDAO {
    public List<ProductDTO> getListProduct(String searchName,String searchType) throws SQLException{
        List<ProductDTO> list = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if(conn != null){
                String sql = "Select productID,productName,price,quantity,catagoryID\n" +
                    "from tblProducts\n" +
                    "where productName like ? and catagoryID like ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%"+ searchName +"%");
                stm.setString(2, "%"+searchType+"%");
                rs = stm.executeQuery();
                while(rs.next()){
                    String productID = rs.getString("productID").trim();
                    String productName = rs.getString("productName").trim();
                    float price = rs.getFloat("price");
                    int quantity = rs.getInt("quantity");
                    String catagoryID =rs.getString("catagoryID").trim();
                    if(list == null){
                        list = new ArrayList<>();
                    }
                    list.add(new ProductDTO(productID, productName, price, quantity, catagoryID));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(rs != null) rs.close();
            if(stm != null) stm.close();
            if(conn != null) conn.close();
        }
        return list;
    }
    
    public boolean checkProductID(String productID) throws SQLException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT productName FROM tblProducts\n"
                        + " WHERE productID=?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, productID);

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
    
    public void insert(ProductDTO product) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if(conn != null){
                String sql = "Insert into tblProducts(productID,productName,price,quantity,catagoryID)\n"
                        + "values (?,?,?,?,?)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, product.getProductID());
                stm.setString(2, product.getProductName());
                stm.setFloat(3, product.getPrice());
                stm.setInt(4, product.getQuantity());
                stm.setString(5, product.getCatagoryID());
                stm.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
    public void deleteProduct(String productID) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "DELETE tblProducts\n"
                        + "Where productID=?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, productID);
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
    public void updateProduct(ProductDTO product) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "UPDATE tblProducts\n"
                        + "SET productName=?, price=?, quantity=?, catagoryID=?\n"
                        + "Where productID=?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, product.getProductName());
                stm.setFloat(2, product.getPrice());
                stm.setInt(3, product.getQuantity());
                stm.setString(4, product.getCatagoryID());
                stm.setString(5, product.getProductID());
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
    
    public boolean checkQuantity(String productID, int quantity) throws SQLException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT productName\n" +
                            "FROM tblProducts\n" +
                            "Where productID = ? And quantity >= ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, productID);
                stm.setInt(2, quantity);
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
}
