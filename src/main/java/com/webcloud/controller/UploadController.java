package com.webcloud.controller;

import com.webcloud.constant.CommonConstants;
import com.webcloud.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @version v1.0
 * @ProjectName: zzm_video
 * @ClassName: UploadController
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/upload")
public class UploadController extends ResponseVo {

    @Value("${filePath}")
    private String filePath;


    @RequestMapping("/image")
    public ResponseVo upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return failed("没有文件");
        }
        String fileName = file.getOriginalFilename();
        int size = (int) file.getSize();
        System.out.println(fileName + "-->" + size);

        File dest = new File(filePath + "/" + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }
        try {
            file.transferTo(dest);
            return restResult(fileName, CommonConstants.SUCCESS, "success");
        } catch (Exception e) {
            return failed("服务器异常，请稍后重试");
        }
    }

    @RequestMapping("/fullTextImage")
    public Object fullTextImage(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return failed("没有文件");
        }
        String fileName = file.getOriginalFilename();
        int size = (int) file.getSize();
        System.out.println(fileName + "-->" + size);

        File dest = new File(filePath + "/" + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }
        try {
            file.transferTo(dest);
            Map result = new HashMap(10);
            result.put("code", 0);
            Map img = new HashMap(2);
            img.put("src", "http://localhost/" + fileName);
            result.put("data", img);
            result.put("msg", "");
            return result;
        } catch (Exception e) {
            return failed("服务器异常，请稍后重试");
        }
    }

}
