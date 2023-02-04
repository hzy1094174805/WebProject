package com.itheima.reggie.controller;

import com.itheima.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
@RequestMapping("/common")
@Slf4j
public class CommonController {
    @Value("${reggie.path}")
    public String baseUrl;


    @PostMapping("/upload")
    public R<String> upload(MultipartFile file) throws IOException {
        file.transferTo(new File(baseUrl + file.getOriginalFilename()));
        return R.success(file.getOriginalFilename());
    }

    @GetMapping("/download")
    public void download(String name, HttpServletResponse response) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(baseUrl + name);
        ServletOutputStream outputStream = response.getOutputStream();
        response.setContentType("image/jpeg");
        byte[] bytes = new byte[1024];
        int len = 0;
        while ((len = fileInputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, len);
            outputStream.flush();
        }
        fileInputStream.close();
    }
}
