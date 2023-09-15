package com.southwind.controller;

import com.southwind.dao.BuildingDao;
import com.southwind.dao.LivingquartersDao;
import com.southwind.dao.StudentDao;
import com.southwind.entity.Livingquarters;
import com.southwind.entity.Student;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/livingquarters")
public class LivingqyartersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String method = req.getParameter("method");
        LivingquartersDao livingquartersDao = new LivingquartersDao();
        BuildingDao buildingDao = new BuildingDao();
        StudentDao studentDao = new StudentDao();
        if (method.equals("list")){
            List<Livingquarters> list = livingquartersDao.list();
            req.setAttribute("buildingList",buildingDao.list());
            req.setAttribute("list",list);
            req.getRequestDispatcher("dormitorymanager.jsp").forward(req,resp);
        } else if (method.equals("search")) {
            String key = req.getParameter("key");
            String value = req.getParameter("value");
            if(value.equals("")){
                req.getRequestDispatcher("/livingquarters?method=list").forward(req,resp);
            }else {
                req.setAttribute("list",livingquartersDao.search(key,value));
                req.setAttribute("buildingList",buildingDao.list());
                req.getRequestDispatcher("dormitorymanager.jsp").forward(req,resp);
            }
        } else if (method.equals("save")) {
            String buildingIdStr = req.getParameter("buildingId");
            int buildingId = Integer.parseInt(buildingIdStr);
            String name = req.getParameter("name");
            String type = req.getParameter("type");
            String telephone = req.getParameter("telephone");
            Livingquarters livingquarters = new Livingquarters(buildingId, name, type, type, telephone);
            if(livingquartersDao.save(livingquarters)!=1){
                System.out.println("我出错了");
            }else{
                req.getRequestDispatcher("/livingquarters?method=list").forward(req,resp);
            }
        } else if (method.equals("update")) {
            String idStr = req.getParameter("id");
            int id = Integer.parseInt(idStr);
            String name = req.getParameter("name");
            String telephone = req.getParameter("telephone");
            Livingquarters livingquarters = new Livingquarters(id, name, telephone);
            if(livingquartersDao.update(livingquarters)!=1){
                System.out.println("修改错误");
            }else{
                req.getRequestDispatcher("/livingquarters?method=list").forward(req,resp);
            }
        } else if (method.equals("delete")) {
            String idStr = req.getParameter("id");
            int id = Integer.parseInt(idStr);
            if(livingquartersDao.delete(id)!=1){
                System.out.println("删除失败");
            }else{
                req.getRequestDispatcher("/livingquarters?method=list").forward(req,resp);
            }
        } else if (method.equals("findByBuildingId")) {
            String buildingIdStr = req.getParameter("buildingId");
            int buildingId = Integer.parseInt(buildingIdStr);
            List<Livingquarters> dormitoryList = livingquartersDao.findByBuildingIdTwo(buildingId);
            List<Student> studentList = studentDao.findByStudent(dormitoryList.get(0).getId());
            Map<String,List> map=new HashMap<>();
            map.put("dormitoryList",dormitoryList);
            map.put("studentList",studentList);
            JSONArray jsonArray = JSONArray.fromObject(map);
            resp.setContentType("text/json;charset=UTF-8");
            resp.getWriter().write(jsonArray.toString());
        }
    }
}
