
package myWebservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the myWebservice package. 
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

    private final static QName _GetAddressResponse_QNAME = new QName("http://chapter45/", "getAddressResponse");
    private final static QName _StoreAddress_QNAME = new QName("http://chapter45/", "storeAddress");
    private final static QName _GetAddress_QNAME = new QName("http://chapter45/", "getAddress");
    private final static QName _StoreAddressResponse_QNAME = new QName("http://chapter45/", "storeAddressResponse");
    private final static QName _InitializeJdbcResponse_QNAME = new QName("http://chapter45/", "initializeJdbcResponse");
    private final static QName _InitializeJdbc_QNAME = new QName("http://chapter45/", "initializeJdbc");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: myWebservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link InitializeJdbcResponse }
     * 
     */
    public InitializeJdbcResponse createInitializeJdbcResponse() {
        return new InitializeJdbcResponse();
    }

    /**
     * Create an instance of {@link InitializeJdbc }
     * 
     */
    public InitializeJdbc createInitializeJdbc() {
        return new InitializeJdbc();
    }

    /**
     * Create an instance of {@link StoreAddress }
     * 
     */
    public StoreAddress createStoreAddress() {
        return new StoreAddress();
    }

    /**
     * Create an instance of {@link GetAddressResponse }
     * 
     */
    public GetAddressResponse createGetAddressResponse() {
        return new GetAddressResponse();
    }

    /**
     * Create an instance of {@link StoreAddressResponse }
     * 
     */
    public StoreAddressResponse createStoreAddressResponse() {
        return new StoreAddressResponse();
    }

    /**
     * Create an instance of {@link GetAddress }
     * 
     */
    public GetAddress createGetAddress() {
        return new GetAddress();
    }

    /**
     * Create an instance of {@link Address }
     * 
     */
    public Address createAddress() {
        return new Address();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAddressResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://chapter45/", name = "getAddressResponse")
    public JAXBElement<GetAddressResponse> createGetAddressResponse(GetAddressResponse value) {
        return new JAXBElement<GetAddressResponse>(_GetAddressResponse_QNAME, GetAddressResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StoreAddress }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://chapter45/", name = "storeAddress")
    public JAXBElement<StoreAddress> createStoreAddress(StoreAddress value) {
        return new JAXBElement<StoreAddress>(_StoreAddress_QNAME, StoreAddress.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAddress }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://chapter45/", name = "getAddress")
    public JAXBElement<GetAddress> createGetAddress(GetAddress value) {
        return new JAXBElement<GetAddress>(_GetAddress_QNAME, GetAddress.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StoreAddressResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://chapter45/", name = "storeAddressResponse")
    public JAXBElement<StoreAddressResponse> createStoreAddressResponse(StoreAddressResponse value) {
        return new JAXBElement<StoreAddressResponse>(_StoreAddressResponse_QNAME, StoreAddressResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InitializeJdbcResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://chapter45/", name = "initializeJdbcResponse")
    public JAXBElement<InitializeJdbcResponse> createInitializeJdbcResponse(InitializeJdbcResponse value) {
        return new JAXBElement<InitializeJdbcResponse>(_InitializeJdbcResponse_QNAME, InitializeJdbcResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InitializeJdbc }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://chapter45/", name = "initializeJdbc")
    public JAXBElement<InitializeJdbc> createInitializeJdbc(InitializeJdbc value) {
        return new JAXBElement<InitializeJdbc>(_InitializeJdbc_QNAME, InitializeJdbc.class, null, value);
    }

}
