package com.southwind.controller;
import com.southwind.dao.DormitoryAdminDao;
import com.southwind.entity.DormitoryAdmin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/dormitoryAdmin")
public class DormitoryAdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String method = req.getParameter("method");
        String key = req.getParameter("key");
        String value = req.getParameter("value");
        DormitoryAdminDao dormitoryAdminDao = new DormitoryAdminDao();
        if(method.equals("list")){
            req.setAttribute("dormitoryAdmins",dormitoryAdminDao.selectAll());
            req.getRequestDispatcher("adminmanager.jsp").forward(req,resp);
        }else if(method.equals("search")){
            if(value.equals("")){
                req.setAttribute("dormitoryAdmins",dormitoryAdminDao.selectAll());
                req.getRequestDispatcher("adminmanager.jsp").forward(req,resp);
            }else{
                req.setAttribute("dormitoryAdmins",dormitoryAdminDao.search(key,value));
                req.getRequestDispatcher("adminmanager.jsp").forward(req,resp);
            }
        } else if (method.equals("save")) {
            String username =req.getParameter("username");
            String password = req.getParameter("password");
            String name = req.getParameter("name");
            String sex = req.getParameter("sex");
            String telephone = req.getParameter("telephone");
            DormitoryAdmin dormitoryAdmin = new DormitoryAdmin(username, password, name, sex, telephone);
            Integer tuser = dormitoryAdminDao.save(dormitoryAdmin);
            if (tuser==null){
                System.out.println("我出错了");
            }else{
               req.getRequestDispatcher("/dormitoryAdmin?method=list").forward(req,resp);
            }
        } else if (method.equals("update")) {
            String idStr = req.getParameter("id");
            int id = Integer.parseInt(idStr);
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            String name = req.getParameter("name");
            String sex = req.getParameter("sex");
            String telephone = req.getParameter("telephone");
            DormitoryAdmin dormitoryAdmin = new DormitoryAdmin(id,username, password, name, sex, telephone);
            if(dormitoryAdminDao.update(dormitoryAdmin)==null){
                System.out.println("我出错了");
            }else {
                req.getRequestDispatcher("/dormitoryAdmin?method=list").forward(req,resp);
            }
        } else if (method.equals("delete")) {
            String idstr = req.getParameter("id");
            int id = Integer.parseInt(idstr);
            if(dormitoryAdminDao.delete(id)==null){
                System.out.println("哪里出错了呢");
            }else {
                req.getRequestDispatcher("/dormitoryAdmin?method=list").forward(req,resp);
            }
        }
    }
}
