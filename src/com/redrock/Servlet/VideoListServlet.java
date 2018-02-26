package com.redrock.Servlet;

import com.google.gson.Gson;
import com.redrock.Secret.Token;
import com.redrock.dao.VideoListDao;
import net.sf.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "videolist",urlPatterns = "/videolist")
public class VideoListServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String strtoken = request.getParameter("token");
        String start = request.getParameter("start");
        String count = request.getParameter("count");
        JSONObject resp =new JSONObject();

        if (start == null || start.isEmpty() ||
            count == null || count.isEmpty() ||
            strtoken == null || strtoken.isEmpty()){
            resp.put("errorcode", 1);
            resp.put("desc", "invalid parameter");
        }
        else {
            try {
                Token token = Token.CreateFrom(strtoken);
                if (null == token) {
                    resp.put("errorcode", 2);
                    resp.put("desc", "invalid token");
                }
                else {
                    if (token.IsExpired()) {
                        resp.put("errorcode", 3);
                        resp.put("desc","token expired");
                    }
                    else {
                        int sta = Integer.parseInt(start);
                        int cou = Integer.parseInt(count);

                        VideoListDao vld =new VideoListDao();
                        List<Map<String, Object>> rd =vld.VideoListUser(token.getUserName(),sta,cou);

                        Gson gson = new Gson();
                        String json = gson.toJson(rd);

                        resp.put("errorcode", 0);
                        resp.put("desc", "success");
                        resp.put("video",json);
                    }
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                resp.put("errorcode", 1);
                resp.put("desc", "invalid parameter");
            }
        }
        response.getOutputStream().write(resp.toString().getBytes());
        response.getOutputStream().flush();
    }
}
