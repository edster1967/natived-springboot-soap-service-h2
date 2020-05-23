package com.natived.soapsservice.mysoapservice.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {
    private final Logger log =  LoggerFactory.getLogger(WebServiceConfig.class);


    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        log.debug("Starting messageDispatcherServlet with no value");
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/ws/*");
    }


    @Bean(name = "articles")
    public DefaultWsdl11Definition articlesWsdl11Definition(XsdSchema articlesSchema) {
        log.debug("Starting articlesWsdl11Definition with value -" + articlesSchema.toString());
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("ArticlesPort");
        wsdl11Definition.setLocationUri("/soapws");
        wsdl11Definition.setTargetNamespace("http://natived.com/soapservice/ws/my-soap-service");
        wsdl11Definition.setSchema(articlesSchema);
        return wsdl11Definition;
    }
    @Bean(name = "articlesSchema")
    public XsdSchema articlesSchema() {
        return new SimpleXsdSchema(new ClassPathResource("/xsd/articles.xsd"));
    }
}