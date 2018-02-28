package com.redrock.Servlet;

import com.google.gson.Gson;
import com.redrock.dao.SearchVideoDao;
import net.sf.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "searchvideo",urlPatterns = "/searchvideo")
public class SearchVideoServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String videonameback = request.getParameter("videoname");
        String videoname = "%"+ videonameback +"%";
        String start = request.getParameter("start");
        String count = request.getParameter("count");
        JSONObject resp = new JSONObject();

        if (videoname==null||videoname.isEmpty()||
                start==null|| start.isEmpty()||
                count==null||count.isEmpty()){
            resp.put("errorcode",1);
            resp.put("desc","invalid parameter");
        }
        else {
            int sta = Integer.parseInt(start);
            int cou = Integer.parseInt(count);

            SearchVideoDao svd = new SearchVideoDao();
            List<Map<String,Object>> sd = svd.SearchVideo(videoname,sta,cou);

            Gson gson = new Gson();
            String json = gson.toJson(sd);

            resp.put("errorcode",0);
            resp.put("desc","success");
            resp.put("video",json);
        }
        response.getOutputStream().write(resp.toString().getBytes());
        response.getOutputStream().flush();
    }
}
