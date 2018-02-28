package com.redrock.Servlet;

import com.google.gson.Gson;
import com.redrock.dao.SortVideoDao;
import net.sf.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "sortvideo",urlPatterns = "/sortvideo")
public class SortVideoServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{

        String sort = request.getParameter("sort");
        String start = request.getParameter("start");
        String count = request.getParameter("count");
        JSONObject resp = new JSONObject();

        if (start == null || start.isEmpty() ||
                count == null || count.isEmpty() ||
                sort == null || sort.isEmpty()){
            resp.put("errorcode", 1);
            resp.put("desc", "invalid parameter");
        }else {
            int sta = Integer.parseInt(start);
            int cou = Integer.parseInt(count);


            SortVideoDao svd =new SortVideoDao();
            List<Map<String, Object>> sd =svd.SortVideo(sort,sta,cou);

            Gson gson = new Gson();
            String json = gson.toJson(sd);

            resp.put("errorcode", 0);
            resp.put("desc", "success");
            resp.put("video",json);
        }
        response.getOutputStream().write(resp.toString().getBytes());
        response.getOutputStream().flush();
    }
}
