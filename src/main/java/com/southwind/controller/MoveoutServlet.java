package com.southwind.controller;

import com.southwind.dao.LivingquartersDao;
import com.southwind.dao.MoveoutDao;
import com.southwind.dao.StudentDao;
import com.southwind.entity.Moveout;
import com.southwind.entity.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/moveoutregister")
public class MoveoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       req.setCharacterEncoding("UTF-8");
        String method = req.getParameter("method");
        StudentDao studentDao = new StudentDao();
        MoveoutDao moveoutDao = new MoveoutDao();
        if(method.equals("list")){
            List<Student> students = studentDao.moveoutList();
            req.setAttribute("list",students);
            req.getRequestDispatcher("moveoutregister.jsp").forward(req,resp);
        } else if (method.equals("search")) {
            String key = req.getParameter("key");
            String value = req.getParameter("value");
            if(value.equals("")){
                req.getRequestDispatcher("/moveoutregister?method=list").forward(req,resp);
            }else{
                List<Student> students = studentDao.moveoutSearch(key, value);
                req.setAttribute("list",students);
                System.out.println(students);
                req.getRequestDispatcher("moveoutregister.jsp").forward(req,resp);
                System.out.println("2");
            }
        }else if(method.equals("moveout")){
            String studentIdStr = req.getParameter("studentId");
            int studentId = Integer.parseInt(studentIdStr);
            String dormitoryIdStr = req.getParameter("dormitoryId");
            int dormitoryId = Integer.parseInt(dormitoryIdStr);
            String reason = req.getParameter("reason");
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
            String createDate = simpleDateFormat.format(date);
            Moveout moveout = new Moveout(studentId, dormitoryId, reason, createDate);
            LivingquartersDao livingquartersDao = new LivingquartersDao();
            if( studentDao.updateState(studentId)!=1||livingquartersDao.addAvailable(dormitoryId)!=1||moveoutDao.save(moveout)!=1){
                System.out.println("迁出失败");
            }else{
                req.getRequestDispatcher("/moveoutregister?method=list").forward(req,resp);
            }
        }
    }
}
