package com.southwind.dao;

import com.southwind.entity.DormitoryAdmin;
import com.southwind.entity.systemAdmin;
import com.southwind.util.JDBCUtil;

import javax.servlet.http.HttpServlet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DormitoryAdminDao{
    public List<DormitoryAdmin> selectAll(){
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement pstmt=null;
        ResultSet res =null;
        DormitoryAdmin dormitoryAdmin=null;
        List<DormitoryAdmin> dormitoryAdmins=new ArrayList<>();
        String sql="select * from housemaster";
        try {
             pstmt = conn.prepareStatement(sql);
             res = pstmt.executeQuery();

             while(res.next()){
                 int id=res.getInt(1);
                 String username=res.getString(2);
                 String password=res.getString(3);
                 String name=res.getString(4);
                 String sex=res.getString(5);
                 String telephone=res.getString(6);
                 dormitoryAdmin = new DormitoryAdmin(id,username,password,name,sex,telephone);
                 dormitoryAdmins.add(dormitoryAdmin);
             }
             return dormitoryAdmins;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtil.close(res,pstmt,conn);
        }
    }
    public List<DormitoryAdmin> search(String key,String value){
            Connection conn = JDBCUtil.getConnection();
            PreparedStatement pstmt=null;
            ResultSet res =null;
            DormitoryAdmin dormitoryAdmin=null;
            List<DormitoryAdmin> dormitoryAdmins=new ArrayList<>();
            String sql="select * from housemaster where "+key+" like '%"+ value+"%'";
            try {
                pstmt = conn.prepareStatement(sql);
                res = pstmt.executeQuery();
                while(res.next()){
                    int id=res.getInt(1);
                    String username=res.getString(2);
                    String password=res.getString(3);
                    String name=res.getString(4);
                    String sex=res.getString(5);
                    String telephone=res.getString(6);
                    dormitoryAdmin = new DormitoryAdmin(id,username,password,name,sex,telephone);
                    System.out.println(dormitoryAdmin);
                    dormitoryAdmins.add(dormitoryAdmin);
                }
                return dormitoryAdmins;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }finally {
                JDBCUtil.close(res,pstmt,conn);
            }
    }
    public Integer save(DormitoryAdmin dormitoryAdmin){
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement pstmt=null;
        Integer res =null;
        String sql="insert into housemaster(id,username,password,name,sex,telephone) values(null,?,?,?,?,?)";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,dormitoryAdmin.getUsername());
            pstmt.setString(2,dormitoryAdmin.getPassword());
            pstmt.setString(3,dormitoryAdmin.getName());
            pstmt.setString(4,dormitoryAdmin.getSex());
            pstmt.setString(5,dormitoryAdmin.getTelephone());
            res = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtil.close(pstmt,conn);
        }
        return res;
    }
    public Integer update(DormitoryAdmin dormitoryAdmin){
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement pstmt=null;
        Integer res =null;
        String sql="update housemaster set username=?,password=?,name=?,sex=?,telephone=? where id=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,dormitoryAdmin.getUsername());
            pstmt.setString(2,dormitoryAdmin.getPassword());
            pstmt.setString(3,dormitoryAdmin.getName());
            pstmt.setString(4,dormitoryAdmin.getSex());
            pstmt.setString(5,dormitoryAdmin.getTelephone());
            pstmt.setInt(6,dormitoryAdmin.getId());
            res = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtil.close(pstmt,conn);
        }
      return res;
    }
    public Integer delete(int id){
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement pstmt=null;
        Integer res =null;
        String sql="delete from housemaster where id=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            res = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtil.close(pstmt,conn);
        }
        return res;
    }
    public DormitoryAdmin login(String unname)  {
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement pstmt=null;
        DormitoryAdmin dormitoryAdmin=null;
        ResultSet res=null;
        String sql="select * from housemaster where username=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,unname);
            res = pstmt.executeQuery();
            if( res.next()){
                int id = res.getInt(1);
                String username= res.getString(2);
                String passworod=res.getString(3);
                String name = res.getString(4);
                String sex = res.getString(5);
                String telephone = res.getString(6);
                dormitoryAdmin=new DormitoryAdmin(id,username,passworod,name,sex,telephone);
                return dormitoryAdmin;
            }else{
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException();
        }finally {
            JDBCUtil.close(res,pstmt,conn);
        }
    }
}
