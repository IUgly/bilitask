package com.redrock.Servlet;

import com.redrock.Secret.Token;
import com.redrock.dao.DeleteMediaDao;
import com.redrock.dao.PointSqlDao;
import com.redrock.service.DeleteMediaService;
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
import java.util.Map;

@WebServlet(name = "deletemedia",urlPatterns = "/deletemedia")
public class DeleteMediaServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,IOException {
        String id = request.getParameter("id");
        String strtoken = request.getParameter("token");

        JSONObject resp = new JSONObject();
        if (id == null || id.isEmpty() || strtoken == null || strtoken.isEmpty()) {
            resp.put("errorcode", 1);
            resp.put("desc", "parameter error");
            response.getOutputStream().write(resp.toString().getBytes());
            response.getOutputStream().flush();
            return;
        }

        Token token = Token.CreateFrom(strtoken);
        if (token == null) {
            resp.put("errorcode", 3);
            resp.put("desc", "token valid");
        } else {
            if (token.IsExpired()) {
                resp.put("errorcode", 4);
                resp.put("desc", "token expired");
            } else {
                PointSqlDao pd=new PointSqlDao();
                Map fileroot = pd.PointSqlUser(id);

                String path = getServletContext().getRealPath(String.valueOf(fileroot.get("src")));
                FileUtils.DeleteFileUtil.deleteFile(path);

                List<Object> arg = new ArrayList<Object>();
                arg.add(id);
                DeleteMediaDao md = new DeleteMediaDao();
                int errorcode = md.Media(arg);
                if (errorcode == 0) {
                    resp.put("errorcode", 0);
                    resp.put("desc", "delete file success");
                } else if (errorcode == 2) {
                    resp.put("errorcode", 2);
                    resp.put("desc", "delete file in datebase");
                } else if (id == null) {
                    resp.put("errorcode", 3);
                    resp.put("desc", "delete file fail");
                }
            }
        }
        response.getOutputStream().write(resp.toString().getBytes());
        response.getOutputStream().flush();
    }
}

