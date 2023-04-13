package config;

import Commons.CommonLibrary;
import interceptors.MemberOnlyInterceptors;
import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.config.annotation.*;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import java.util.Locale;
import java.util.ResourceBundle;
@Import(DbConfig.class)
@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

    @Value("${environment}")
    private String environment;

    @Value("${file.upload.path}")
    private String fileUploadPath;

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }


    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        boolean isCacheable = environment.equals("real")?true:false;

        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/WEB-INF/view/");
        templateResolver.setSuffix(".html");
        templateResolver.setCacheable(false);
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        templateEngine.addDialect(new Java8TimeDialect());
        templateEngine.addDialect(new LayoutDialect());
        return templateEngine;
    }

    @Bean
    public ThymeleafViewResolver thymeleafViewResolver() {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setContentType("text/html");
        resolver.setCharacterEncoding("utf-8");
        resolver.setTemplateEngine(templateEngine());
        return resolver;
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.viewResolver(thymeleafViewResolver());
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource ms = new ResourceBundleMessageSource();
        ms.setBasenames("messages.commons");
        ms.setDefaultEncoding("UTF-8");
        // ms.setDefaultLocale(Locale.KOREAN);
        return ms;
    }

    @Bean
    public CommonLibrary cLib() {
        return new CommonLibrary();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/mypage")
                .setViewName("mypage/index");

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");

        // 파일 업로드 경로 정적 경로 매칭
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:///" + fileUploadPath); // 슬레시를 3개 하는 이유는
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(memberOnlyInterceptors())
                .addPathPatterns("/mypage/**");
    }

    @Bean
    public MemberOnlyInterceptors memberOnlyInterceptors(){

        return new MemberOnlyInterceptors();
    }
    @Bean
    public static PropertySourcesPlaceholderConfigurer properties(){
        PropertySourcesPlaceholderConfigurer conf = new PropertySourcesPlaceholderConfigurer();
        conf.setLocations(new ClassPathResource("application.properties"));

        return conf;
    }
}