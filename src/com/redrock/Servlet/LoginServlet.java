package com.redrock.Servlet;

import com.redrock.Secret.Token;
import com.redrock.dao.LoginDao;
import com.redrock.service.LoginService;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ugly
 */
@WebServlet(name = "login",urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID=1L;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,IOException{

        String userName=request.getParameter("username");
        String pswd=request.getParameter("pswd");

        if (null == userName || userName.isEmpty() || null == pswd || pswd.isEmpty()) {
            JSONObject resp = new JSONObject();
            resp.put("errorcode", 1);
            resp.put("desc", "parameter error");
            response.getOutputStream().write(resp.toString().getBytes());
            response.getOutputStream().flush();
            return;
        }

        List<Object> arg=new ArrayList<Object>();
        arg.add(userName);
        arg.add(pswd);

        LoginDao rd=new LoginDao();
        int promptcode=rd.LoginUser(arg);
        JSONObject resp=new JSONObject();
        if (promptcode==0){
            resp.put("promptcode",0);
            resp.put("desc","login success");
            Date date = new Date();
            Token token = new Token(userName,date.getTime()/1000+600);
            resp.put("token",token.CreateToken());
        }
        else if (promptcode==1){
            resp.put("promptcode",2);
            resp.put("desc","account or key is error");
        }
        else if (promptcode==2){
            resp.put("promptcode",3);
            resp.put("desc","failed to select user from database");
        }
        response.getOutputStream().write(resp.toString().getBytes());
        response.getOutputStream().flush();
    }




    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }


}
