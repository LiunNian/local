package cn.org.cpcca.feignserver.paper.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Value("${swagger2.basePackage}")
    private String swaggerConfig ;

    /**
     * Swagger2创建Docket的Bean
     *
     * @return Docket
     */
//    @Bean
//    public Docket createRestApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .select()
//                .apis(basePackage(swaggerConfig))
//                .paths(PathSelectors.any())
//                .build();
//    }

    @Bean
    public Docket loginDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(loginApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.org.cpcca.feignserver"))
                .paths(PathSelectors.any())
                .build();
    }


    private ApiInfo loginApiInfo() {
        return new ApiInfoBuilder()
                .title("文化馆协会论文管理系统")
                .description("权限相关接口测试")
                .contact(new Contact("Guardpure","","740673998"))
                .version("1.0")
                .build();
    }

    /**
     * Predicate that matches RequestHandler with given base package name for the class of the handler method.
     * This predicate includes all request handlers matching the provided basePackage
     *
     * @param basePackage - base package of the classes
     * @return this
     */
//    public static Predicate<RequestHandler> basePackage(final String basePackage) {
//        return new Predicate<RequestHandler>() {
//            @Override
//            public boolean apply(RequestHandler input) {
//                return declaringClass(input).transform(handlerPackage(basePackage)).or(true);
//            }
//        };
//    }

    /**
     * 处理包路径配置规则,支持多路径扫描匹配以逗号隔开
     *
     * @param basePackage 扫描包路径
     * @return Function
     */
//    private static Function<Class<?>, Boolean> handlerPackage(final String basePackage) {
//        return new Function<Class<?>, Boolean>() {
//
//            @Override
//            public Boolean apply(Class<?> input) {
//                String[] strings = basePackage.split(",");
//                for (String strPackage : strings) {
//                    boolean isMatch = input.getPackage().getName().startsWith(strPackage);
//                    if (isMatch) {
//                        return true;
//                    }
//                }
//                return false;
//            }
//        };
//    }

    /**
     * @param input RequestHandler
     * @return Optional
     */
//    private static Optional<? extends Class<?>> declaringClass(RequestHandler input) {
//        return Optional.fromNullable(input.declaringClass());
//    }


//    @Bean
//    public Docket createRestApi() {
//        System.out.println(swaggerConfig);
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.basePackage(swaggerConfig))
//                .paths(PathSelectors.any())
//                .build();
//    }

}
