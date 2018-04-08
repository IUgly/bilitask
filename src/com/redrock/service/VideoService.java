package com.redrock.service;

import com.redrock.been.Media;
import com.redrock.helper.DatabaseHelper;

import java.util.List;

public class VideoService {

    public List<Media> getVideoList(String username, int start, int count){
        String sql = "select id,title,src,descript,picture,uptime "+
                "from tb_media where username=? limit ?,?";
        return DatabaseHelper.queryEntityList(Media.class, sql, username, start, count);
    }

}
