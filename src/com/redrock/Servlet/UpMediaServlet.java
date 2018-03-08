package com.redrock.Servlet;

import com.redrock.Secret.Token;
import com.redrock.dao.UpMediaDao;
import com.redrock.service.UpMediaServce;
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

/**
 * @author ugly
 */
@WebServlet(name = "upmedia",urlPatterns = "/upmedia")
public class UpMediaServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException,IOException{
        String title = request.getParameter("title");
        String descript = request.getParameter("descript");
        String sort = request.getParameter("sort");
        String strtoken = request.getParameter("token");

        if (title==null || title.isEmpty()||strtoken==null||
                strtoken.isEmpty()||sort==null||sort.isEmpty()){
            JSONObject resp =new JSONObject();
            resp.put("errorcode",1);
            resp.put("desc","video infomation error");
            response.getOutputStream().write(resp.toString().getBytes());
            response.getOutputStream().flush();
            return;
        }

        if (null == descript) {
            descript = "";
        }
        JSONObject resp=new JSONObject();

        Token token = Token.CreateFrom(strtoken);
        if (null == token) {
            resp.put("errocode",3);
            resp.put("desc","invalid token");
        }
        else {
            if (token.IsExpired()) {
                resp.put("errorcode", 4);
                resp.put("desc", "token expired");
            }
            else {
                List<String> filePaths = FileUtils.upload(request,
                        "videos/",
                        ".mp4 .flv. 3gp .avi .rmvb");
//                List<String> picfilePaths = FileUtils.upload(request,
//                        "videopictrue/",
//                        "jpg . png ");
                String rootdir = getServletContext().getRealPath("/");
//                String rootdirpic = getServletContext().getRealPath("/");


                if (filePaths.isEmpty()) {
                    resp.put("errorcode", 5);
                    resp.put("desc", "failed to store file");
                } else {
                    List<Object> arg = new ArrayList<Object>();
                    arg.add(title);
                    arg.add(descript);
                    arg.add(filePaths.get(0).substring(rootdir.length()));
                    arg.add(sort);
//                    arg.add(picfilePaths.get(0).substring(rootdirpic.length()));
                    arg.add(token.getUserName());

                    UpMediaDao rd = new UpMediaDao();
                    int errorcode = rd.upMedia(arg);

                    if (errorcode == 0) {
                        resp.put("errorcode", 0);
                        resp.put("desc", "upload success");
                    } else if (errorcode == 2) {
                        resp.put("errorcode", 2);
                        resp.put("desc", "insert dasebase error");
                    } else if (filePaths == null) {
                        resp.put("errorcode", 3);
                        resp.put("desc", "upload fail");
                    }
                }
            }
        }
        response.getOutputStream().write(resp.toString().getBytes());
        response.getOutputStream().flush();
    }
}
