
package com.tax.webservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.tax.webservice package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Service_QNAME = new QName("http://webservice.tax.com/", "service");
    private final static QName _ServiceResponse_QNAME = new QName("http://webservice.tax.com/", "serviceResponse");
    private final static QName _InterException_QNAME = new QName("http://webservice.tax.com/", "InterException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.tax.webservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Service }
     * 
     */
    public Service createService() {
        return new Service();
    }

    /**
     * Create an instance of {@link ServiceResponse }
     * 
     */
    public ServiceResponse createServiceResponse() {
        return new ServiceResponse();
    }

    /**
     * Create an instance of {@link InterException }
     * 
     */
    public InterException createInterException() {
        return new InterException();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Service }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.tax.com/", name = "service")
    public JAXBElement<Service> createService(Service value) {
        return new JAXBElement<Service>(_Service_QNAME, Service.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ServiceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.tax.com/", name = "serviceResponse")
    public JAXBElement<ServiceResponse> createServiceResponse(ServiceResponse value) {
        return new JAXBElement<ServiceResponse>(_ServiceResponse_QNAME, ServiceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InterException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.tax.com/", name = "InterException")
    public JAXBElement<InterException> createInterException(InterException value) {
        return new JAXBElement<InterException>(_InterException_QNAME, InterException.class, null, value);
    }

}
