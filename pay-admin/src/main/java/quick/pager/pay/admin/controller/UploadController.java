package quick.pager.pay.admin.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import quick.pager.pay.Constants;
import quick.pager.pay.common.constants.ResponseStatus;
import quick.pager.pay.common.upload.QiniuUpload;
import quick.pager.pay.response.Response;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 *
 */
@RestController
@RequestMapping(Constants.ADMIN_MODULE)
@Api(tags = "文件上传")
public class UploadController {

    @ApiOperation("上传")
    @RequestMapping("/upload")
    public Response upload(MultipartHttpServletRequest request) {

        List<MultipartFile> fileList = request.getFiles("file");

        if (CollectionUtils.isEmpty(fileList)) {
            return new Response(ResponseStatus.PARAMETER_ERROR.code, ResponseStatus.PARAMETER_ERROR.msg);
        }

        Response<List<Map<String, String>>> response = new Response<>();
        List<Map<String, String>> mapList = Lists.newArrayList();
        fileList.forEach(file -> {
            Map<String, String> map = Maps.newConcurrentMap();

            try {
                map.put("path", QiniuUpload.uploadToStream(file.getInputStream(), file.getName()));
                mapList.add(map);
                response.setData(mapList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return response;
    }
}
