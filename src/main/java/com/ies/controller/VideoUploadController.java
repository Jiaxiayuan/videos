package com.ies.controller;

import com.alibaba.fastjson.JSON;
import com.ies.utils.AppFileUtils;
import com.ies.utils.PluploadUtil;
import com.ies.utils.RandomUtils;
import com.ies.vo.Plupload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: videos
 * @description:
 * @author: fuchen
 * @create: 2020-03-31 12:37
 **/
@Controller
@RequestMapping("file")
public class VideoUploadController {

    @RequestMapping(value="/uploadVideo", method = RequestMethod.POST)
    public void upload(Plupload plupload, HttpServletRequest request, HttpServletResponse response) throws IOException {

        System.out.println(plupload.getChunk() + "===" + plupload.getName() + "---" + plupload.getChunks());

        plupload.setRequest(request);
        // 文件上传的父目录
        String parentPath = AppFileUtils.PATH;
        // 得到当前日期作为文件夹名称
        String dirName = RandomUtils.getCurrentDateForString();
        // 构造文件夹对象
        File dirFile = new File(parentPath, dirName);
        if (!dirFile.exists()) {
            dirFile.mkdirs();// 创建文件夹
        }
        try {
            //上传文件
            PluploadUtil.upload(plupload, dirFile);
            //判断文件是否上传成功（被分成块的文件是否全部上传完成）
            if (PluploadUtil.isUploadFinish(plupload)) {
                Map<String, Object> m = new HashMap<>();
                m.put( "status", true);
                m.put( "fileUrl", dirFile.getName() + "/" + plupload.getFileName());
                response.getWriter().write(JSON.toJSONString(m));
            }

        } catch (Exception e) {
            Map<String, Object> m = new HashMap<>();
            m.put( "status", false);
            response.getWriter().write(JSON.toJSONString(m));
        }
    }

}
