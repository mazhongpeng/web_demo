package com.southwind.dao;

import com.southwind.entity.Livingquarters;
import com.southwind.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivingquartersDao {
    public List<Livingquarters> availableList(){
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement pstmt=null;
        ResultSet res =null;
        Livingquarters livingquarters=null;
        List<Livingquarters> livingquarterss=new ArrayList<>();
        String sql="select id,name from livingquarters where available>0";
        try {
            pstmt = conn.prepareStatement(sql);
            res = pstmt.executeQuery();
            while(res.next()){
                Integer id=res.getInt(1);
                String name=res.getString(2);
                livingquarters = new Livingquarters(id,name);
                livingquarterss.add(livingquarters);
            }
            return livingquarterss;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtil.close(res,pstmt,conn);
        }
    }
    public Integer subAvailable(Integer id){
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement pstmt = null;
        Integer res = null;
        String sql = "update livingquarters set available=available-1 where id="+id;
        try {
            pstmt = conn.prepareStatement(sql);
            res = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close(pstmt, conn);
        }
        return res;
    }
    public Integer addAvailable(Integer id){
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement pstmt = null;
        Integer res = null;
        String sql = "update livingquarters set available=available+1 where id="+id;
        try {
            pstmt = conn.prepareStatement(sql);
            res = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close(pstmt, conn);
        }
        return res;
    }
    public List<Integer> findByBuildingId(Integer id){
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement pstmt=null;
        ResultSet res =null;
        List<Integer> livingquarterss=new ArrayList<>();
        String sql="select id from livingquarters where building_id="+id;
        try {
            pstmt = conn.prepareStatement(sql);
            res = pstmt.executeQuery();
            while(res.next()){
                id=res.getInt(1);
                livingquarterss.add(id);
            }
            return livingquarterss;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtil.close(res,pstmt,conn);
        }
    }
    public List<Livingquarters> list() {
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet res = null;
        Livingquarters livingquarters = null;
        List<Livingquarters> livingquarterss = new ArrayList<>();
        String sql = "select l.id,b.name,l.name,l.type,l.available,l.telephone from building b,livingquarters l where b.id=l.building_id";
        try {
            pstmt = conn.prepareStatement(sql);
            res = pstmt.executeQuery();
            while (res.next()) {
                Integer id = res.getInt(1);
                String buildingName= res.getString(2);
                String name = res.getString(3);
                String type = res.getString(4);
                String available = res.getString(5);
                String telephone = res.getString(6);
                livingquarters= new Livingquarters(id, buildingName, name, type, available, telephone);
                livingquarterss.add(livingquarters);
            }
            return livingquarterss;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close(res, pstmt, conn);
        }
    }
    public List<Livingquarters> search(String key, String value) {
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet res = null;
        Livingquarters livingquarters = null;
        List<Livingquarters> livingquarterss = new ArrayList<>();
        String sql = "select l.id,b.name,l.name,l.type,l.available,l.telephone from building b,livingquarters l where b.id=l.building_id and l." + key + " like '%" + value + "%'";
        try {
            pstmt = conn.prepareStatement(sql);
            res = pstmt.executeQuery();
            while (res.next()) {
                Integer id = res.getInt(1);
                String buildingName= res.getString(2);
                String name = res.getString(3);
                String type = res.getString(4);
                String available = res.getString(5);
                String telephone = res.getString(6);
                livingquarters= new Livingquarters(id, buildingName, name, type, available, telephone);
                livingquarterss.add(livingquarters);
            }
            return livingquarterss;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close(res, pstmt, conn);
        }
    }
    public Integer save(Livingquarters livingquarters) {
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement pstmt = null;
        Integer res = null;
        String sql = "insert into livingquarters(building_id,name,type,available,telephone) values(?,?,?,?,?)";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,livingquarters.getBuildingId());
            pstmt.setString(2,livingquarters.getName());
            pstmt.setString(3,livingquarters.getType());
            pstmt.setString(4, livingquarters.getAvailable());
            pstmt.setString(5, livingquarters.getTelephone());
            res = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close(pstmt, conn);
        }
        return res;
    }
    public Integer availableId(){
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement pstmt=null;
        ResultSet resultSet=null;
        Integer res =null;
        String sql="select id from livingquarters where available>0 limit 0,1";
        try {
            pstmt = conn.prepareStatement(sql);
           resultSet = pstmt.executeQuery();
            if(resultSet.next()){
               res = resultSet.getInt(1);
            }
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
        LivingquartersDao livingquartersDao = new LivingquartersDao();
        StudentDao studentDao = new StudentDao();
        List<Integer> studentList = studentDao.findByDormitory(id);
        for (Integer studentId:studentList) {
            Integer livingquatterId = livingquartersDao.availableId();
            Integer updateDorimtory = studentDao.updateDorimtory(studentId, livingquatterId);
            Integer subAvailable = livingquartersDao.subAvailable(livingquatterId);
            if(updateDorimtory!=1||subAvailable!=1) System.out.println("学生更换失败");
        }
        String sql="delete from livingquarters where id=?";
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
    public Integer update(Livingquarters livingquarters){
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement pstmt=null;
        Integer res =null;
        String sql="update livingquarters set name=?,telephone=? where id=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,livingquarters.getName());
            pstmt.setString(2,livingquarters.getTelephone());
            pstmt.setInt(3,livingquarters.getId());
            res = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtil.close(pstmt,conn);
        }
        return res;
    }
    public List<Livingquarters> findByBuildingIdTwo(Integer buildingId){
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement pstmt=null;
        ResultSet res =null;
        List<Livingquarters> livingquarterss=new ArrayList<>();
        String sql="select id,name from livingquarters where building_id="+buildingId;
        try {
            pstmt = conn.prepareStatement(sql);
            res = pstmt.executeQuery();
            while(res.next()){
                int id = res.getInt(1);
                String name = res.getString(2);
                Livingquarters livingquarters = new Livingquarters(id, name);
                livingquarterss.add(livingquarters);
            }
            return livingquarterss;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtil.close(res,pstmt,conn);
        }
    }
}
