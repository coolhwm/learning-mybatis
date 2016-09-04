package com.learn.servlet;

import com.learn.bean.Message;
import com.learn.service.impl.MessageQueryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * ListServlet
 *
 * @author hwm
 * @since 2016/8/30
 **/
@WebServlet(name = "ListServlet", urlPatterns = "/list.action")
public class ListServlet extends HttpServlet {

    private MessageQueryService messageQueryService = new MessageQueryService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接受参数
        String command = req.getParameter("command");
        String description = req.getParameter("description");
        String currentPage = req.getParameter("currentPage");
        String pageNumber = req.getParameter("pageNumber");

        Message query = new Message();
        query.setCommand(command);
        query.setDescription(description);

        //业务处理
        List<Message> messages = messageQueryService.queryMessages(currentPage, pageNumber, query);
        //返回参数
        req.setAttribute("messageList", messages);
        req.setAttribute("command", command);
        req.setAttribute("description", description);
        //页面处理
        req.getRequestDispatcher("/WEB-INF/content/list.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }


}
