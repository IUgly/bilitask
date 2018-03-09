package com.redrock.Servlet;

import com.redrock.Secret.Token;
import com.redrock.dao.UpPhotoDao;
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
 * 上传/修改用户头像
 */
@WebServlet(name = "upphoto",urlPatterns = "/upphoto")
public class UpPhotoServlet extends HttpServlet{
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
        String strtoken = request.getParameter("token");

        JSONObject resp = new JSONObject();
        if (strtoken.isEmpty()||strtoken == null){
            resp.put("errorcode",1);
            resp.put("desc","invalid parameter");
            return;
        }

        Token token = Token.CreateFrom(strtoken);
        if (token == null){
            resp.put("errorcode",3);
            resp.put("desc","token invalid");
        }else {
            List<String> filePaths = FileUtils.upload(request,"photo/",
                    ".jpg .png");
            String rootdir = getServletContext().getRealPath("/");

            List<Object> arg = new ArrayList<Object>();
            arg.add(filePaths.get(0).substring(rootdir.length()));
            arg.add(token.getUserName());

            UpPhotoDao upd = new UpPhotoDao();
            int errorcode = upd.UpPhoto(arg);

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
            response.getOutputStream().write(resp.toString().getBytes());
            response.getOutputStream().flush();
        }
    }
}
