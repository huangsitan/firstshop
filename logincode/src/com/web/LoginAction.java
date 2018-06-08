package com.web;

import java.io.IOException;
import java.io.OutputStream;

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

        OutputStream out = resp.getOutputStream();

        String username = req.getParameter("username");

        String password = req.getParameter("password");

        String validationCode = req.getParameter("validationCode");

        HttpSession session = req.getSession();

        String validation_code = (String)session.getAttribute("validation_code");

        if(validationCode.equalsIgnoreCase(validation_code)){

            System.out.println("验证码正确");
            
            
            //验证用户名密码是否正确

        }else{

            System.out.println("验证码错误");

        }

        

        //转发到result.jsp

        RequestDispatcher rd = req.getRequestDispatcher("result.jsp");

        rd.forward(req, resp);

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         doGet(request,response);
    }

}
