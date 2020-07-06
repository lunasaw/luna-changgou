package com.changgou.file.controller;

import com.changgou.file.FastDFSFile;
import com.changgou.util.FastDFSClient;
import entity.Result;
import entity.StatusCode;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 描述
 *
 * @author luna
 * @version 1.0
 * @package com.changgou.file.controller *
 * @since 1.0
 * 跨域:
 * 不同的域名A 访问 域名B 的数据就是跨域
 *  端口不同 也是跨域  loalhost:18081----->localhost:18082
 *  协议不同 也是跨域  http://www.jd.com  --->  https://www.jd.com
 *  域名不同 也是跨域  http://www.jd.com  ---> http://www.taobao.com
 * 协议一直,端口一致,域名一致就不是跨域  http://www.jd.com:80 --->http://www.jd.com:80 不是跨域
 */
@RestController
@CrossOrigin
// 支持跨域
public class FileController {


    /**
     * 返回 图片的全路径
     * 1. 创建图片文件对象(封装)
     * 2. 调用工具类实现图片上传
     *
     * @param file 页面的文件对象
     * @return
     */
    @PostMapping("/upload")
    public Result<String> upload(@RequestParam(value = "file") MultipartFile file) {
        try {
            FastDFSFile fastdfsfile = new FastDFSFile(
                    file.getOriginalFilename(),
                    //原来的文件名
                    file.getBytes(),
                    //文件本身的字节数组
                    StringUtils.getFilenameExtension(file.getOriginalFilename())
            );
            String[] upload = FastDFSClient.upload(fastdfsfile);
            String path = FastDFSClient.getTrackerUrl() + "/" + upload[0] + "/" + upload[1];

            return new Result(true, StatusCode.OK, "上传成功", path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
