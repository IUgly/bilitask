package com.redrock.Servlet;

import com.redrock.dao.IdPointSrcDao;
import com.redrock.dao.SrcPointIdDao;
import com.redrock.dao.UpvideoDao;
import com.redrock.utils.FileUtils;
import com.redrock.utils.JDBC;
import com.redrock.utils.StrCut;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ugly
 * 单独上传视频
 * 返回该视频的id
 */
@WebServlet(name = "upvideo",urlPatterns = "/upvideo")
public class UpvideoServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<String> filePaths = FileUtils.upload(request, "videos/",
                ".mp4 .rmvb .avi .flv .3gp");
//        List<String> picPaths = FileUtils.upload(request, "videopic/",
//                ".jpg .png");
        JSONObject resp = new JSONObject();
        if (filePaths.isEmpty() || filePaths == null) {
            resp.put("errorcode", 1);
            resp.put("desc", "parameter error");
            response.getOutputStream().write(resp.toString().getBytes());
            response.getOutputStream().flush();
            return;
        } else {
            String rootdir = getServletContext().getRealPath("/");
//        String rootdirpic = getServletContext().getRealPath("/");

            List<Object> arg = new ArrayList<Object>();
            arg.add(filePaths.get(0).substring(rootdir.length()));
//        arg.add(picPaths.get(0).substring(rootdirpic.length()));

            UpvideoDao ud = new UpvideoDao();
            int errorcode = ud.Upvideo(arg);

            if (errorcode == 0) {
                resp.put("errorcode", 0);
                resp.put("desc", "upload success");
                String src = String.valueOf(arg);
                SrcPointIdDao spi = new SrcPointIdDao();
                resp.put("videoid", spi.SrcPointId(src.substring(1, src.length() - 1)));

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
