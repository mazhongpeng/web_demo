package com.southwind.dao;

import com.southwind.entity.Absent;
import com.southwind.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AbsentDao {
    public Integer save(Absent absent) {
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement pstmt = null;
        Integer res = null;
        String sql = "insert into absent(building_id,dormitory_id,student_id,dormitory_admin_id,create_date,reason) values(?,?,?,?,?,?)";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,absent.getBuildingId());
            pstmt.setInt(2,absent.getDormitoryId());
            pstmt.setInt(3, absent.getStudentId());
            pstmt.setInt(4, absent.getDormitoryAdminId());
            pstmt.setString(5, absent.getCreateDate());
            pstmt.setString(6,absent.getReason());
            res = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close(pstmt, conn);
        }
        return res;
    }
    public List<Absent> list() {
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet res = null;
        Absent absent = null;
        List<Absent> absents = new ArrayList<>();
        String sql = "select a.id,b.name,l.name,s.name,a.reason,h.name,a.create_date from absent a,livingquarters l,building b,student s,housemaster h where a.building_id=b.id and a.dormitory_id=l.id and a.student_id=s.id and a.dormitory_admin_id=h.id";
        try {
            pstmt = conn.prepareStatement(sql);
            res = pstmt.executeQuery();
            while (res.next()) {
                Integer id = res.getInt(1);
                String buildingName = res.getString(2);
                String dormitoryName = res.getString(3);
                String studentName = res.getString(4);
                String  reason = res.getString(5);
                String  dormitoryAdminName = res.getString(6);
                String createDate= res.getString(7);
                absent = new Absent(id, buildingName, dormitoryName, studentName,reason, dormitoryAdminName,createDate);
                absents.add(absent);
            }
            return absents;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close(res, pstmt, conn);
        }
    }
}
