package cn.org.cpcca.feignserver.paper.services;

import cn.org.cpcca.feignserver.paper.hystrics.FileUploadServiceHystric;
import feign.RequestLine;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.apache.ibatis.annotations.Param;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(value="service.paper",configuration = FileUploadService.MultipartSupportConfig.class,fallback = FileUploadServiceHystric.class)
public interface FileUploadService {
    @RequestMapping(value="/fileupload/excelupload",
        method = RequestMethod.POST,
        consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public String excelUpload(
            @RequestPart(value = "file") MultipartFile file,
            @RequestParam(value = "revid") Integer revid
    );
//    @Configuration
    class MultipartSupportConfig {
        @Bean
        public Encoder feignFormEncoder() {
            return new SpringFormEncoder();
        }
    }
}
