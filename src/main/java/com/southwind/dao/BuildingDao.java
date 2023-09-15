package com.southwind.dao;

import com.southwind.entity.Building;
import com.southwind.entity.Livingquarters;
import com.southwind.entity.Student;
import com.southwind.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BuildingDao {
    public List<Building> list(){
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement pstmt=null;
        ResultSet res =null;
        Building building=null;
        List<Building> buildings=new ArrayList<>();
        String sql="select b.id,b.name,b.introduction,h.name,h.id from building b,housemaster h where b.admin_id=h.id";
        try {
            pstmt = conn.prepareStatement(sql);
            res = pstmt.executeQuery();
            while(res.next()){
                Integer id=res.getInt(1);
                String name=res.getString(2);
                String introduction = res.getString(3);
                String adminName = res.getString(4);
                int adminId = res.getInt(5);
                building= new Building(id,name,introduction,adminName,adminId);
                buildings.add(building);
            }
            return buildings;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtil.close(res,pstmt,conn);
        }
    }
    public List<Building> search(String key,String value){
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet res = null;
        Building building= null;
        List<Building> buildings = new ArrayList<>();
        String sql = "select b.id,b.name,b.introduction,h.name,h.id from building b,housemaster h where b.admin_id=h.id and b." + key + " like '%" + value + "%'";
        try {
            pstmt = conn.prepareStatement(sql);
            res = pstmt.executeQuery();
            while (res.next()) {
                Integer id = res.getInt(1);
                String name = res.getString(2);
                String introduction = res.getString(3);
                String adminName = res.getString(4);
                Integer adminId = res.getInt(5);
                building = new Building(id, name,introduction,adminName,adminId);
                buildings.add(building);
            }
            return buildings;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close(res, pstmt, conn);
        }
    }
    public Integer save(Building building) {
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement pstmt = null;
        Integer res = null;
        String sql = "insert into building(name,introduction,admin_id) values(?,?,?)";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,building.getName());
            pstmt.setString(2,building.getIntroduction());
            pstmt.setInt(3, building.getAdminId());
            res = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close(pstmt, conn);
        }
        return res;
    }
    public Integer update(Building building){
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement pstmt=null;
        Integer res =null;
        String sql="update building set name=?,introduction=?,admin_id=? where id=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,building.getName());
            pstmt.setString(2,building.getIntroduction());
            pstmt.setInt(3,building.getAdminId());
            pstmt.setInt(4,building.getId());
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
        LivingquartersDao livingquartersDao = new LivingquartersDao();
        List<Integer> dormitoryIdList = livingquartersDao.findByBuildingId(id);
        StudentDao studentDao = new StudentDao();
        for (Integer integer :dormitoryIdList) {
            List<Integer> studentIdList = studentDao.findByDormitory(integer);
            for (Integer studentId:studentIdList) {
                Integer availableId = livingquartersDao.availableId();
                Integer updateDorimtory = studentDao.updateDorimtory(studentId, availableId);
                Integer subAvailable = livingquartersDao.subAvailable(availableId);
                if (updateDorimtory!=1||subAvailable!=1){
                    System.out.println("学生宿舍更新失败");
                }
            }
        }
        for (Integer integer:dormitoryIdList) {
            Integer delete = livingquartersDao.delete(integer);
            if (delete!=1){
                System.out.println("删除宿舍失败");
            }
        }
        String sql="delete from building where id=?";
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
}
