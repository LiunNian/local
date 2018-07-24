package com.chinazxt.stdzfp.webservice.cxf.config;

import javax.annotation.Resource;
import javax.xml.ws.Endpoint;

import com.tax.webservice.IVatiWebService;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class CxfConfig {
    @Autowired
    private Bus bus;

    @Resource
    IVatiWebService vatiWebService;

    /** JAX-WS **/
    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, vatiWebService);
        endpoint.publish("/VatiWebService");
        return endpoint;
    }
}