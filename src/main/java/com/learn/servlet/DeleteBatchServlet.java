package com.learn.servlet;

import com.learn.service.impl.MessageMaintainService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * DeleteBatchServlet
 *
 * @author hwm
 * @since 2016/9/1
 **/
@WebServlet(name = "DeleteOnServlet", urlPatterns = "/deleteBatch.action")
public class DeleteBatchServlet extends HttpServlet {

    private MessageMaintainService messageMaintainService = new MessageMaintainService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接受参数
        String[] ids = req.getParameterValues("ids");
        //业务处理
        messageMaintainService.delete(Arrays.asList(ids));
        //页面处理
        req.getRequestDispatcher("/list.action").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

}
