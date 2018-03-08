package com.redrock.Servlet;

import com.redrock.dao.VideoCoverDao;
import com.redrock.utils.FileUtils;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "videocover",urlPatterns = "/videocover")
public class VideoCoverServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException,ServletException{
        String videoid = request.getParameter("videoid");
        int id = Integer.parseInt(videoid);
        List<String> filePaths = FileUtils.upload(request,"cover/",
                ".jpg .png");
        String rootdir = getServletContext().getRealPath("/");

        List<Object> arg = new ArrayList<Object>();
        arg.add(filePaths.get(0).substring(rootdir.length()));
        arg.add(id);

        VideoCoverDao vc = new VideoCoverDao();
        int errorcode = vc.VideoCover(arg);
        JSONObject resp = new JSONObject();

        if (errorcode == 0){
            resp.put("errorcode",0);
            resp.put("desc","add success");
        }else if (errorcode == 2) {
            resp.put("errorcode", 2);
            resp.put("desc", "insert dasebase error");
        } else if (filePaths == null) {
            resp.put("errorcode", 3);
            resp.put("desc", "upload fail");
        }
        response.getOutputStream().write(resp.toString().getBytes());
        response.getOutputStream().flush();
    }
}
