package com.southwind.controller;

import com.southwind.dao.DormitoryAdminDao;
import com.southwind.dao.SystemAdminDao;
import com.southwind.entity.DormitoryAdmin;
import com.southwind.entity.systemAdmin;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class loginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String method = req.getParameter("method");
        String type = req.getParameter("type");
        if(type.equals("systemAdmin")){
            switch (method){
                case "login":
                    String username = req.getParameter("username");
                    String password = req.getParameter("password");
                    SystemAdminDao systemAdminDao = new SystemAdminDao();
                    systemAdmin login = systemAdminDao.login(username);
                    req.setAttribute("usernameError", "");
                    req.setAttribute("passwordError", "");
                    if (login == null) {
                        req.setAttribute("usernameError", "用户名不存在");
                        req.getRequestDispatcher("register.jsp").forward(req, resp);
                    } else {
                        if (login.getPassword().equals(password)) {
                            HttpSession session = req.getSession();
                            session.setAttribute("systemAdmin",login);
                            req.getRequestDispatcher("systemmanager.jsp").forward(req, resp);
                        } else {
                            req.setAttribute("passwordError", "密码错误");
                            req.getRequestDispatcher("register.jsp").forward(req, resp);
                        }
                    }
                    break;
                case "logout":
                    req.getSession().invalidate();
                    resp.sendRedirect("register.jsp");
                    break;
            }
        }else if(type.equals("dormitoryAdmin")){
            switch (method){
                case "login":
                    String username = req.getParameter("username");
                    String password = req.getParameter("password");
                    DormitoryAdminDao dormitoryAdminDao = new DormitoryAdminDao();
                    DormitoryAdmin logins = dormitoryAdminDao.login(username);
                    req.setAttribute("usernameError", "");
                    req.setAttribute("passwordError", "");
                    if (logins == null) {
                        req.setAttribute("usernameError", "用户名不存在");
                        req.getRequestDispatcher("register.jsp").forward(req, resp);
                    } else {
                        if (logins.getPassword().equals(password)) {
                            HttpSession session = req.getSession();
                            session.setAttribute("dormitoryAdmin",logins);
                            req.getRequestDispatcher("dormitoryadmin.jsp").forward(req, resp);
                        } else {
                            req.setAttribute("passwordError", "密码错误");
                            req.getRequestDispatcher("register.jsp").forward(req, resp);
                        }
                    }
                    break;
                case "logout":
                    req.getSession().invalidate();
                    resp.sendRedirect("register.jsp");
                    break;
            }
        }
    }
}
