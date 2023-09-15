package com.southwind.dao;

import com.southwind.entity.systemAdmin;
import com.southwind.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SystemAdminDao {
    public systemAdmin login(String unname)  {
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement pstmt=null;
        ResultSet res=null;
        String sql="select * from system_admin where username=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,unname);
            res = pstmt.executeQuery();
//            int id = res.getInt(1);
//            System.out.println(id);
            res.next();
            String username= res.getString(2);
            String passworod=res.getString(3);
            String name = res.getString(4);
            String telephone = res.getString(5);
            systemAdmin system=new systemAdmin(1,username,passworod,name,telephone);
            return system;
        } catch (SQLException e) {
            throw new RuntimeException();
        }finally {
        JDBCUtil.close(res,pstmt,conn);
        }

    }
}
