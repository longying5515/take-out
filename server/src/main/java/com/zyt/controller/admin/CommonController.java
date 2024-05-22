package com.zyt.controller.admin;

import com.zyt.constant.MessageConstant;
import com.zyt.result.Result;
import com.zyt.utils.AliOssUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/admin/common")
public class CommonController {
    @Autowired
    private AliOssUtil aliOssUtil;

    /**
     *
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file)  {
        log.info("文件上传；{}",file);
        String originalFilename = file.getOriginalFilename();
        //截取后缀
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        //构造新文件名称
        String newName = UUID.randomUUID().toString() + extension;
        try {
            String filePath = aliOssUtil.upload(file.getBytes(),newName);
            return Result.success(filePath);
        } catch (IOException e) {
            log.info("文件上传失败：{}",e);
        }
        return Result.error(MessageConstant.UPLOAD_FAILED);
    }
}
