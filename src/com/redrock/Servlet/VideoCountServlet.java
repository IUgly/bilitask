package com.redrock.Servlet;

import com.redrock.Secret.Token;
import com.redrock.dao.VideoCountDao;
import net.sf.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="videocount",urlPatterns = "/videocount")
public class VideoCountServlet extends HttpServlet {

        @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String strtoken =  request.getParameter("token");
        JSONObject resp= new JSONObject();

        if (strtoken == null || strtoken.isEmpty()){
                resp.put("errorcode",1);
                resp.put("desc","invalid parameter");
                response.getOutputStream().write(resp.toString().getBytes());
                response.getOutputStream().flush();
                return;
        }
        else {
            Token token = Token.CreateFrom(strtoken);
            if (null == token) {
                resp.put("errorcode", 3);
                resp.put("desc", "invalid token");
            } else {
                if (token.IsExpired()) {
                    resp.put("errorcode", 2);
                    resp.put("desc", "token expired");
                } else {
                    resp.put("errorcode", 0);
                    resp.put("desc", "success");

                    VideoCountDao vdao = new VideoCountDao();
                    resp.put("count", vdao.VideoCountUser(token.getUserName()));
                }
            }
        }

        response.getOutputStream().write(resp.toString().getBytes());
        response.getOutputStream().flush();
    }


}
