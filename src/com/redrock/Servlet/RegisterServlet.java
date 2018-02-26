package com.redrock.Servlet;

import net.sf.json.JSONObject;
import com.redrock.dao.RegisterDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ugly
 */
@WebServlet(name = "register",urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    //private RegisterService service;


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userName = (String) request.getParameter("username");
        String pswd=(String)request.getParameter("pswd");

        if (null == userName || userName.isEmpty() || null == pswd || pswd.isEmpty()) {
            JSONObject resp = new JSONObject();
            resp.put("errorcode", 1);
            resp.put("desc", "invalid parameters");
            response.getOutputStream().write(resp.toString().getBytes());
            response.getOutputStream().flush();
            return;
        }

        List<Object> args = new ArrayList<Object>();
        args.add(userName);
        args.add(pswd);

        RegisterDao rd = new RegisterDao();
        int errorcode = rd.registerUser(args);
        JSONObject resp = new JSONObject();
        if (errorcode == 0) {
            resp.put("errorcode", 0);
            resp.put("desc", "success");
        }
        else if (errorcode == 1) {
            resp.put("errorcode", 2);
            resp.put("desc", "user exists");
        }
        else if (errorcode == 2) {
            resp.put("errorcode", 3);
            resp.put("desc", "failed to insert user into databasr");
        }

        response.getOutputStream().write(resp.toString().getBytes());
        response.getOutputStream().flush();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    public void init() throws ServletException {
        // service = new RegisterDao();
    }
}