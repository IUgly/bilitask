package com.redrock.Servlet;

import com.google.gson.Gson;
import com.redrock.dao.CommentListDao;
import net.sf.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "commentlist",urlPatterns = "/commentlist")
public class CommentListServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String videoid = request.getParameter("videoid");
        String start = request.getParameter("start");
        String count = request.getParameter("count");
        JSONObject resp = new JSONObject();

        if     (start == null || start.isEmpty()||
                count == null || count.isEmpty()){
            resp.put("errorcode",1);
            resp.put("desc","invalid parameter");
        }
        else{
            try {
            int sta = Integer.parseInt(start);
                int cou = Integer.parseInt(count);

                CommentListDao cld = new CommentListDao();
                List<Map<String,Object>> cd = cld.CommentList(Integer.parseInt(videoid),sta,cou);

                Gson gson = new Gson();
                String json = gson.toJson(cd);

                resp.put("errorcode",0);
                resp.put("desc","suceess");
                resp.put("comment",json);
            }catch (NumberFormatException e){
                e.printStackTrace();
                resp.put("errorcode",2);
                resp.put("desc","invalid parameter");
            }
        }
        response.getOutputStream().write(resp.toString().getBytes());
        response.getOutputStream().flush();
    }
}
