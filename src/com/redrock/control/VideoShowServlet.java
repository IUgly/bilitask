package com.redrock.control;

import com.google.gson.Gson;
import com.redrock.Secret.Token;
import com.redrock.been.Media;
import com.redrock.service.VideoService;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

/**
 * @author ugly
 */
@WebServlet("/videoList")
public class VideoShowServlet extends HttpServlet {

    private VideoService videoService;

    @Override
    public void init() throws ServletException{
        videoService = new VideoService();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException{
        String strtoken = req.getParameter("token");
        Token token = Token.CreateFrom(strtoken);

        String start = req.getParameter("start");
        String count = req.getParameter("count");
        int sta = Integer.parseInt(start);
        int cou = Integer.parseInt(count);

        List<Media> videoList = videoService.getVideoList(token.getUserName(), sta, cou);

        JSONObject res = new JSONObject();
        Gson gson = new Gson();
        String json = gson.toJson(videoList);

        res.put("info",json);

        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                        resp.getOutputStream(), "UTF-8"
                )
        );
        writer.write(String.valueOf(res));
        writer.flush();
        writer.close();
    }
}
