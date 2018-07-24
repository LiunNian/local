package cn.org.cpcca.feignserver.paper.util;

import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;
import feign.form.ContentType;
import feign.form.FormEncoder;
import feign.form.MultipartFormContentProcessor;
import feign.form.spring.SpringManyMultipartFilesWriter;
import feign.form.spring.SpringSingleMultipartFileWriter;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Map;

public class FeignSpringFormEncoder extends FormEncoder {

    public FeignSpringFormEncoder() {
        this(new Default());
    }

    public FeignSpringFormEncoder(Encoder delegate) {
        super(delegate);
        MultipartFormContentProcessor processor = (MultipartFormContentProcessor)this.getContentProcessor(ContentType.MULTIPART);
        processor.addWriter(new SpringSingleMultipartFileWriter());
        processor.addWriter(new SpringManyMultipartFilesWriter());
    }

    public void encode(Object object, Type bodyType, RequestTemplate template) throws EncodeException {
        if (!bodyType.equals(MultipartFile.class)) {
            super.encode(object, bodyType, template);
        } else {
            MultipartFile file = (MultipartFile)object;
            Map<String, Object> data = Collections.singletonMap(file.getName(), object);
            super.encode(data, MAP_STRING_WILDCARD, template);
        }
    }
}
