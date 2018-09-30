package com.web;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/dolog")
public class LoginAction extends HttpServlet {


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    	resp.setContentType("text/html;charset=utf-8");
        PrintWriter out =resp.getWriter();

        String username = req.getParameter("username");

        String password = req.getParameter("password");

        String validationCode = req.getParameter("valCode");

        HttpSession session = req.getSession();

        String validation_code = (String)session.getAttribute("val_code");

        if(validationCode.equalsIgnoreCase(validation_code)){

            System.out.println("验证码正确");
            //验证用户名密码是否正确
            if(username.equals("admin")&&password.equals("123"))
            {
               req.getSession().setAttribute("log", username);
               resp.sendRedirect("index.jsp");
            
            }else
            {
            	 out.print("<script>alert('用户名或密码错误');location='login.jsp';</script>");
            }
        }else{

            out.print("<script>alert('验证码错误');location='login.jsp';</script>");

        }

   

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         doGet(request,response);
    }

}
