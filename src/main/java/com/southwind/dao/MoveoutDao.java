package com.southwind.dao;

import com.southwind.entity.Livingquarters;
import com.southwind.entity.Moveout;
import com.southwind.entity.Student;
import com.southwind.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MoveoutDao {
    public Integer save(Moveout moveout) {
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement pstmt = null;
        Integer res = null;
        String sql = "insert into moveout(student_id,dormitory_id,reason,create_date) values(?,?,?,?)";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,moveout.getStudentId());
            pstmt.setInt(2, moveout.getDormitoryId());
            pstmt.setString(3,moveout.getReason());
            pstmt.setString(4, moveout.getCreateDate());
            res = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close(pstmt, conn);
        }
        return res;
    }
    public List<Moveout> list() {
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet res = null;
        Moveout moveout = null;
        List<Moveout> moveouts = new ArrayList<>();
        String sql = "select m.id,s.name,l.name,m.reason,m.create_date from moveout m,livingquarters l,student s where l.id=m.dormitory_id and s.id=m.student_id";
        try {
            pstmt = conn.prepareStatement(sql);
            res = pstmt.executeQuery();
            while (res.next()) {
                Integer id = res.getInt(1);
                String studentName= res.getString(2);
                String dormitoryName = res.getString(3);
                String reason = res.getString(4);
                String createDate = res.getString(5);
                moveout= new Moveout(id,studentName,dormitoryName,reason,createDate);
                moveouts.add(moveout);
            }
            return moveouts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close(res, pstmt, conn);
        }
    }
    public List<Moveout> recordSearch(String key, String value) {
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet res = null;
        Moveout moveout = null;
        List<Moveout> moveouts = new ArrayList<>();
        String sql = "select m.id,s.name,l.name,m.reason,m.create_date from moveout m,livingquarters l,student s where l.id=m.dormitory_id and s.id=m.student_id and " + key + " like '%" + value + "%'";
        try {
            pstmt = conn.prepareStatement(sql);
            res = pstmt.executeQuery();
            while (res.next()) {
                Integer id = res.getInt(1);
                String studentName= res.getString(2);
                String dormitoryName = res.getString(3);
                String reason = res.getString(4);
                String createDate = res.getString(5);
                moveout= new Moveout(id,studentName,dormitoryName,reason,createDate);
                moveouts.add(moveout);
            }
            return moveouts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close(res, pstmt, conn);
        }
    }
}
