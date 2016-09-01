package com.learn.servlet;

import com.learn.service.QueryService;
import com.learn.service.impl.CommandQueryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * AutoReplyServlet
 *
 * @author hwm
 * @since 2016/9/2
 **/
@WebServlet(name = "AutoReplyServlet", urlPatterns = "/reply.action")
public class AutoReplyServlet extends HttpServlet {

    private QueryService queryService = new CommandQueryService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接受参数
        String content = req.getParameter("content");

        String result = queryService.queryByCommand(content);
        resp.setContentType("text/html;charset=utf-8");

        PrintWriter out = resp.getWriter();

        out.print(result);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
