package com.southwind.controller;

import com.southwind.dao.MoveoutDao;
import com.southwind.entity.Moveout;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/moveoutrecord")
public class MoveoutRecordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String method = req.getParameter("method");
        MoveoutDao moveoutDao = new MoveoutDao();
        if (method.equals("list")){
            List<Moveout> list = moveoutDao.list();
            req.setAttribute("list",list);
            req.getRequestDispatcher("moveoutrecord.jsp").forward(req,resp);
        } else if (method.equals("recordSearch")) {
            String key = req.getParameter("key");
            String value = req.getParameter("value");
            if(key.equals("studentName")){
                key="s.name";
            }else if(key.equals("dormitoryName")){
                key="l.name";
            }
            if(value.equals("")){
                req.getRequestDispatcher("/moveoutrecord?method=list").forward(req,resp);
            }else{
                List<Moveout> moveouts = moveoutDao.recordSearch(key, value);
                req.setAttribute("list",moveouts);
                req.getRequestDispatcher("moveoutrecord.jsp").forward(req,resp);
            }
        }
    }
}
