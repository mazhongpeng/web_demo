package com.southwind.dao;

import com.southwind.entity.Student;
import com.southwind.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {
    public List<Student> list() {
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet res = null;
        Student student = null;
        List<Student> students = new ArrayList<>();
        String sql = "select s.id,s.number,s.name,s.sex,l.id,l.name,s.state,s.create_date from student s,livingquarters l where s.dormitory_id=l.id";
        try {
            pstmt = conn.prepareStatement(sql);
            res = pstmt.executeQuery();
            while (res.next()) {
                Integer id = res.getInt(1);
                String number = res.getString(2);
                String name = res.getString(3);
                String sex = res.getString(4);
                Integer dormitoryId = res.getInt(5);
                String dormitoryName = res.getString(6);
                String state = res.getString(7);
                String createDate = res.getString(8);
                student = new Student(id, number, name, sex, dormitoryId, dormitoryName, state, createDate);
                students.add(student);
            }
            return students;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close(res, pstmt, conn);
        }
    }

    public List<Student> search(String key, String value) {
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet res = null;
        Student student = null;
        List<Student> students = new ArrayList<>();
        String sql = "select s.id,s.number,s.name,s.sex,l.id,l.name,s.state,s.create_date from student s,livingquarters l where s.dormitory_id=l.id and s." + key + " like '%" + value + "%'";
        try {
            pstmt = conn.prepareStatement(sql);
            res = pstmt.executeQuery();
            while (res.next()) {
                Integer id = res.getInt(1);
                String number = res.getString(2);
                String name = res.getString(3);
                String sex = res.getString(4);
                Integer dormitoryId = res.getInt(5);
                String dormitoryName = res.getString(6);
                String state = res.getString(7);
                String createDate = res.getString(8);
                student = new Student(id, number, name, sex, dormitoryId, dormitoryName, state, createDate);
                students.add(student);
            }
            return students;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close(res, pstmt, conn);
        }
    }

    public Integer save(Student student) {
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement pstmt = null;
        Integer res = null;
        String sql = "insert into student(number,name,sex,dormitory_id,state,create_date) values(?,?,?,?,?,?)";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,student.getNumber());
            pstmt.setString(2, student.getName());
            pstmt.setString(3, student.getSex());
            pstmt.setInt(4, student.getDormitoryId());
            pstmt.setString(5, student.getState());
            pstmt.setString(6,student.getCreateDate());
            res = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close(pstmt, conn);
        }
        return res;
    }
    public Integer update(Student student){
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement pstmt=null;
        Integer res =null;
        String sql="update Student set number=?,name=?,sex=?,dormitory_id=? where id=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,student.getNumber());
            pstmt.setString(2,student.getName());
            pstmt.setString(3,student.getSex());
            pstmt.setInt(4,student.getDormitoryId());
            pstmt.setInt(5,student.getId());
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
        String sql="delete from student where id=?";
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
    public List<Integer> findByDormitory(Integer id) {
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet res = null;
        List<Integer> list=new ArrayList<>();
        String sql = "select id from student where dormitory_id="+id;
        try {
            pstmt = conn.prepareStatement(sql);
            res = pstmt.executeQuery();
            while (res.next()) {
                list.add(res.getInt(1));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close(res, pstmt, conn);
        }
    }
    public Integer updateDorimtory(Integer studentId,Integer dormitoryId){
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement pstmt=null;
        Integer res =null;
        String sql="update Student set dormitory_id=? where id=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,dormitoryId);
            pstmt.setInt(2,studentId);
            res = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtil.close(pstmt,conn);
        }
        return res;
    }
    public List<Student> moveoutList(){
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet res = null;
        Student student = null;
        List<Student> students = new ArrayList<>();
        String sql = "select s.id,s.number,s.name,s.sex,l.id,l.name,s.state from student s,livingquarters l where s.dormitory_id=l.id and s.state='入住'";
        try {
            pstmt = conn.prepareStatement(sql);
            res = pstmt.executeQuery();
            while (res.next()) {
                Integer id = res.getInt(1);
                String number = res.getString(2);
                String name = res.getString(3);
                String sex = res.getString(4);
                Integer dormitoryId = res.getInt(5);
                String dormitoryName = res.getString(6);
                String state = res.getString(7);
                student = new Student(id, number, name, sex, dormitoryId, dormitoryName, state);
                students.add(student);
            }
            return students;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close(res, pstmt, conn);
        }
    }
    public List<Student> moveoutSearch(String key, String value) {
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet res = null;
        Student student = null;
        List<Student> students = new ArrayList<>();
        String sql = "select s.id,s.number,s.name,s.sex,l.id,l.name,s.state from student s,livingquarters l where s.dormitory_id=l.id and s.state='入住' and s." + key + " like '%" + value + "%'";
        try {
            pstmt = conn.prepareStatement(sql);
            res = pstmt.executeQuery();
            while (res.next()) {
                Integer id = res.getInt(1);
                String number = res.getString(2);
                String name = res.getString(3);
                String sex = res.getString(4);
                Integer dormitoryId = res.getInt(5);
                String dormitoryName = res.getString(6);
                String state = res.getString(7);
                student = new Student(id, number, name, sex, dormitoryId, dormitoryName, state);
                students.add(student);
            }
            return students;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close(res, pstmt, conn);
        }
    }
    public Integer updateState(Integer id){
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement pstmt=null;
        Integer res =null;
        String sql="update Student set state='迁出' where id="+id;
        try {
            pstmt = conn.prepareStatement(sql);
            res = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtil.close(pstmt,conn);
        }
        return res;
    }
    public List<Student> findByStudent(Integer dormitoryId){
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet res = null;
        List<Student> list=new ArrayList<>();
        String sql = "select id,name from student where dormitory_id="+dormitoryId;
        try {
            pstmt = conn.prepareStatement(sql);
            res = pstmt.executeQuery();
            while (res.next()) {
                int id = res.getInt(1);
                String name = res.getString(2);
                Student student = new Student(id, name);
                list.add(student);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close(res, pstmt, conn);
        }
    }
}