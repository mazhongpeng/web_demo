package com.southwind.controller;

import com.southwind.dao.BuildingDao;
import com.southwind.dao.DormitoryAdminDao;
import com.southwind.entity.Building;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/building")
public class BuildingServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
             this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String method = req.getParameter("method");
        BuildingDao buildingDao = new BuildingDao();
        DormitoryAdminDao dormitoryAdminDao = new DormitoryAdminDao();
        if(method.equals("list")){
            List<Building> buildings=buildingDao.list();
            req.setAttribute("adminList",dormitoryAdminDao.selectAll());
            req.setAttribute("list",buildings);
            req.getRequestDispatcher("buildingmanager.jsp").forward(req,resp);
        } else if (method.equals("search")) {
            String key = req.getParameter("key");
            String value = req.getParameter("value");
            req.setAttribute("adminList",dormitoryAdminDao.selectAll());
            List<Building> buildings = buildingDao.search(key, value);
            req.setAttribute("list",buildings);
            if(value.equals("")){
                req.getRequestDispatcher("building?method=list").forward(req,resp);
            }else{
                req.getRequestDispatcher("buildingmanager.jsp").forward(req,resp);
            }
        }else if(method.equals("save")){
            String name = req.getParameter("name");
            String introduction = req.getParameter("introduction");
            String adminIdStr = req.getParameter("adminId");
            int adminId = Integer.parseInt(adminIdStr);
            Building building = new Building(name, introduction, adminId);
            if(buildingDao.save(building)!=1){
                System.out.println("你出错了");
            }else{
                req.getRequestDispatcher("building?method=list").forward(req,resp);
            }
        } else if (method.equals("update")) {
            String idStr = req.getParameter("id");
            int id = Integer.parseInt(idStr);
            String name = req.getParameter("name");
            String introduction = req.getParameter("introduction");
            String adminIdStr = req.getParameter("adminId");
            int adminId = Integer.parseInt(adminIdStr);
            Building building = new Building(id, name, introduction, adminId);
            if(buildingDao.update(building)!=1){
                System.out.println("修改错误");
            }else{
                req.getRequestDispatcher("building?method=list").forward(req,resp);
            }
        } else if (method.equals("delete")) {
            String idStr = req.getParameter("id");
            int id = Integer.parseInt(idStr);
            Integer delete = buildingDao.delete(id);
            if(delete!=1){
                System.out.println("我错了");
            }else{
                req.getRequestDispatcher("building?method=list").forward(req,resp);
            }
        }
    }
}
