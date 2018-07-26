package cn.momosv.blog.base.file.controller;

import cn.momosv.blog.base.interfaces.AuthIgnore;
import cn.momosv.blog.base.mybatis.model.base.Msg;
import cn.momosv.blog.common.util.DatePattern;
import cn.momosv.blog.common.util.XDateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

@CrossOrigin("*")
@RestController
@RequestMapping("upload")
@AuthIgnore
public class FileController {

    private static final Logger log = LoggerFactory.getLogger(FileController.class);

    public static final String ROOT = "E:/upload-dir";

    private final ResourceLoader resourceLoader;

    @Autowired
    public FileController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    //显示图片的方法关键 匹配路径像 localhost:8080/b7c76eb3-5a67-4d41-ae5c-1642af3f8746.png
    @RequestMapping(value = "/{type}/{upLoadUser}/{filename:.+}")
    public ResponseEntity<?> getFile(@PathVariable String filename,@PathVariable String type,@PathVariable String upLoadUser) {
       String path = Paths.get(ROOT+"/"+type+"/"+upLoadUser, filename).toString();
        try {
            return ResponseEntity.ok(resourceLoader.getResource("file:" + path ));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }
    @RequestMapping( value = "up")
    public Object handleFileUpload(@RequestParam("file") MultipartFile file,@PathVariable @RequestParam(defaultValue = "default") String type,@PathVariable @RequestParam(defaultValue = "default")String upLoadUser,
                                   RedirectAttributes redirectAttributes, HttpServletRequest request) throws IOException {
      //  Files.delete(Paths.get(ROOT, "momo.jpg"));
        String fileName="";
        if (!file.isEmpty()) {
            fileName= XDateUtils.dateToString(new Date(), DatePattern.DATE_TIME_FULL_NUM.getPattern())+file.getOriginalFilename();
            Files.copy(file.getInputStream(),  Paths.get(ROOT+"/"+type+"/"+upLoadUser, fileName));
            return Msg.success().add("url","/upload/"+type+"/"+upLoadUser+"/"+fileName);
        }
        return Msg.fail("上传的文件为空");
    }
}
