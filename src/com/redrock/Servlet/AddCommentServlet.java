package com.redrock.Servlet;

import com.redrock.Secret.Token;
import com.redrock.dao.AddCommentDao;
import net.sf.json.JSONObject;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "addcomment",urlPatterns = "/addcomment")
public class AddCommentServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException,IOException{
        String videoid = request.getParameter("videoid");
        String strtoken = request.getParameter("token");
//        int commentlen = request.getContentLength();
//        byte[] comment = new byte[commentlen];
//        request.getInputStream().read(comment, 0, commentlen);

        String comment = IOUtils.toString(request.getInputStream(), "UTF-8");

        JSONObject resp = new JSONObject();

        if (videoid==null||videoid.isEmpty()|| strtoken.isEmpty()||strtoken==null||
                null==comment || comment.isEmpty()){
            resp.put("errorcode",1);
            resp.put("desc","paramer error");
            return;
        }

        Token token = Token.CreateFrom(strtoken);

        if (token==null){
            resp.put("errorcode",3);
            resp.put("desc","token invalid");
        }
        else{
            if (token.IsExpired()){
                resp.put("errorcode",4);
                resp.put("desc","token expire");
            }
            else {
                List<Object> arg = new ArrayList<Object>();
                arg.add(videoid);
                arg.add(comment);
                arg.add(token.getUserName());

                AddCommentDao ad = new AddCommentDao();
                int vid = Integer.parseInt(videoid);
                Boolean errorcode = ad.addComment(comment,token.getUserName(),vid);

                if (errorcode){
                    resp.put("errorcode",0);
                    resp.put("desc","comment success");
                }
                else {
                    resp.put("errorcode",-1);
                    resp.put("desc","comment fail");
                }
            }
        }
        response.getOutputStream().write(resp.toString().getBytes());
        response.getOutputStream().flush();
    }
}
