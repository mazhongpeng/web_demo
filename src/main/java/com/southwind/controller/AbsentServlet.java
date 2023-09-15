package com.southwind.controller;

import com.southwind.dao.AbsentDao;
import com.southwind.dao.BuildingDao;
import com.southwind.dao.LivingquartersDao;
import com.southwind.dao.StudentDao;
import com.southwind.entity.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/absent")
public class AbsentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      req.setCharacterEncoding("UTF-8");
      String method = req.getParameter("method");
        BuildingDao buildingDao = new BuildingDao();
        LivingquartersDao livingquartersDao = new LivingquartersDao();
        StudentDao studentDao = new StudentDao();
        AbsentDao absentDao = new AbsentDao();
        if(method.equals("init")){
            List<Building> buildingList = buildingDao.list();
            List<Livingquarters> dormitoryList = livingquartersDao.findByBuildingIdTwo(buildingList.get(0).getId());
            List<Student> studentList = studentDao.findByStudent(dormitoryList.get(0).getId());
            req.setAttribute("buildingList",buildingList);
            req.setAttribute("dormitoryList",dormitoryList);
            req.setAttribute("studentList",studentList);
          req.getRequestDispatcher("absentregister.jsp").forward(req,resp);
      }else if(method.equals("save")){
            String buildingIdStr = req.getParameter("buildingId");
            Integer buildingId = Integer.parseInt(buildingIdStr);
            String dormitoryIdStr = req.getParameter("dormitoryId");
            Integer dormitoryId = Integer.parseInt(dormitoryIdStr);
            String studentIdStr = req.getParameter("studentId");
            Integer studentId = Integer.parseInt(studentIdStr);
            String reason = req.getParameter("reason");
            String date = req.getParameter("date");
            HttpSession session = req.getSession();
            DormitoryAdmin dormitoryAdmin = (DormitoryAdmin) session.getAttribute("dormitoryAdmin");
            Absent absent = new Absent(buildingId, dormitoryId, studentId, dormitoryAdmin.getId(), date, reason);
            if(absentDao.save(absent)!=1){
                System.out.println("添加信息失败");
            }else{
                req.getRequestDispatcher("/absent?method=init").forward(req,resp);
            }
        } else if (method.equals("list")) {
            List<Absent> list = absentDao.list();
            req.setAttribute("list",list);
            req.getRequestDispatcher("absentrecord.jsp").forward(req,resp);
        }
    }
}
