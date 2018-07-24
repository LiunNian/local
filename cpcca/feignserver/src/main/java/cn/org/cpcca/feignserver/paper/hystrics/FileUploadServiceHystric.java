package cn.org.cpcca.feignserver.paper.hystrics;

import cn.org.cpcca.feignserver.paper.services.FileUploadService;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
@Component
public class FileUploadServiceHystric extends DefaultHystric implements FileUploadService {
    @Override
    public String excelUpload(MultipartFile file, Integer revid) {
        System.out.println("upload--error");return this.defaultZone();
    }
}
