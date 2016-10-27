package com.learn.servlet;

import com.learn.service.impl.MessageMaintainService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * DeleteOnServlet
 *
 * @author hwm
 * @since 2016/9/1
 **/
@WebServlet(name = "DeleteOneServlet", urlPatterns = "/deleteOne.action")
public class DeleteOneServlet extends HttpServlet {

    private MessageMaintainService messageMaintainService = new MessageMaintainService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接受参数
        String id = req.getParameter("id");
        //业务处理
        messageMaintainService.delete(id);
        //页面处理
        req.getRequestDispatcher("/list.action").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

}
