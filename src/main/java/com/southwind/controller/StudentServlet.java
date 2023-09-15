package com.southwind.controller;

import com.southwind.dao.LivingquartersDao;
import com.southwind.dao.StudentDao;
import com.southwind.entity.Student;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String method = req.getParameter("method");
        StudentDao studentDao = new StudentDao();
        LivingquartersDao livingquartersDao = new LivingquartersDao();
        if(method.equals("list")){
               List<Student> list =studentDao.list();
               req.setAttribute("list",list);
               req.setAttribute("dormitoryList",livingquartersDao.availableList());
               req.getRequestDispatcher("studentmanager.jsp").forward(req,resp);
           } else if (method.equals("search")) {
               String key = req.getParameter("key");
               String value = req.getParameter("value");
               if(value.equals("")){
                   List<Student> list =studentDao.list();
                   req.setAttribute("list",list);
                   req.getRequestDispatcher("studentmanager.jsp").forward(req,resp);
               }else{
                   req.setAttribute("list",studentDao.search(key,value));
                   req.getRequestDispatcher("studentmanager.jsp").forward(req,resp);
               }
           } else if(method.equals("save")){
            String dormitoryIdstr = req.getParameter("dormitoryId");
            int dormitoryId = Integer.parseInt(dormitoryIdstr);
            String number = req.getParameter("number");
            String name = req.getParameter("name");
            String sex = req.getParameter("sex");
            Student student = new Student(number, name, sex, dormitoryId);
            student.setState("入住");
            Date date=new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            student.setCreateDate(simpleDateFormat.format(date));
            if(studentDao.save(student)==null||livingquartersDao.subAvailable(dormitoryId)==null){
                System.out.println("我出错了");
            }else{
               req.getRequestDispatcher("/student?method=list").forward(req,resp);
            }
        } else if (method.equals("update")) {
            String idstr = req.getParameter("id");
            int id = Integer.parseInt(idstr);
            String number = req.getParameter("number");
            String name = req.getParameter("name");
            String sex = req.getParameter("sex");
            String dormitoryIdstr = req.getParameter("dormitoryId");
            String oldDormitoryIdStr = req.getParameter("oldDormitoryId");
            int oldDormitoryId = Integer.parseInt(oldDormitoryIdStr);
            int dormitoryId = Integer.parseInt(dormitoryIdstr);
            Student student = new Student(id, number, name, sex, dormitoryId);
            if(studentDao.update(student)!=1||livingquartersDao.addAvailable(oldDormitoryId)!=1||livingquartersDao.subAvailable(dormitoryId)!=1){
                System.out.println("修改错误");
            }else{
                req.getRequestDispatcher("/student?method=list").forward(req,resp);
            }
        } else if (method.equals("delete")) {
            String idStr = req.getParameter("id");
            int id = Integer.parseInt(idStr);
            String dormitoryIdStr = req.getParameter("dormitoryId");
            int dormitoryId = Integer.parseInt(dormitoryIdStr);
            if (studentDao.delete(id)!=1||livingquartersDao.addAvailable(dormitoryId)!=1){
                System.out.println("删除错误");

            }else {
                req.getRequestDispatcher("/student?method=list").forward(req,resp);
            }
        } else if (method.equals("findByDormitoryId")) {
            String dormitoryIdStr = req.getParameter("dormitoryId");
            int dormitoryId = Integer.parseInt(dormitoryIdStr);
            List<Student> studentList = studentDao.findByStudent(dormitoryId);
            JSONArray jsonArray = JSONArray.fromObject(studentList);
            resp.setContentType("text/json;charset=UTF-8");
            resp.getWriter().write(jsonArray.toString());
        }
    }
}
