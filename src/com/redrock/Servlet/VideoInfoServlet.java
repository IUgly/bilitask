package com.redrock.Servlet;

import com.redrock.Secret.Token;
import com.redrock.dao.IdPointSrcDao;
import com.redrock.dao.VideoInfoDao;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "videoinfo",urlPatterns = "/videoinfo")
public class VideoInfoServlet extends HttpServlet{
@Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException,ServletException {
    String videoid = request.getParameter("id");
    String title = request.getParameter("title");
    String describe = request.getParameter("describe");
    String sort = request.getParameter("sort");
    String strtoken = request.getParameter("token");

    JSONObject resp = new JSONObject();

    if (videoid == null || videoid.isEmpty() || title.isEmpty() || title == null
            ||strtoken.isEmpty()||strtoken== null||sort.isEmpty()||sort==null) {
        resp.put("errorcode", 1);
        resp.put("desc", "paramer error");
        return;
    }
    if (describe == null)
    {
        describe="";
    }

    Token token = Token.CreateFrom(strtoken);
    if (token==null)
    {
        resp.put("errorcode",3);
        resp.put("desc","token invalid");
    }else if (token.IsExpired()){
        resp.put("errorcode",4);
        resp.put("desc","token expired");
    }else {
        int vid = Integer.parseInt(videoid);
        VideoInfoDao vd = new VideoInfoDao();

        int errorcode = vd.videoinfo(title,describe,sort,token.getUserName(),vid);

        if (errorcode == 0) {
            resp.put("errorcode", 0);
            resp.put("desc", "success");
        } else if (errorcode == 2) {
            resp.put("errorcode", 2);
            resp.put("desc", "database error");
        }
        response.getOutputStream().write(resp.toString().getBytes());
        response.getOutputStream().flush();
    }
}
}

