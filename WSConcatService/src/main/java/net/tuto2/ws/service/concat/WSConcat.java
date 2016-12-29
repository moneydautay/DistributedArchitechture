package net.tuto2.ws.service.concat;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Created by greenlucky on 12/28/16.
 */
@WebService(targetNamespace = "http://david.bromberg.fr/service/concat",
        name = "WSConcat")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface WSConcat {
    @WebMethod
    public String concat(@WebParam(name="str1") String str1, @WebParam(name = "str2") String str2);
}
