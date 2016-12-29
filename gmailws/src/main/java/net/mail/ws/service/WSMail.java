package net.mail.ws.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Created by greenlucky on 12/28/16.
 */
@WebService(targetNamespace = "http://david.bromberg.fr/", name = "WSMail")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface WSMail {

    @WebMethod
    public void sendMail(
            @WebParam(name = "smtpHost") String smtpHost,
            @WebParam(name = "smtpPort") int smtpPort,
            @WebParam(name = "from") String from,
            @WebParam(name = "to") String to,
            @WebParam(name = "username") String username,
            @WebParam(name = "password") String password,
            @WebParam(name = "isSSL") boolean isSSL
    );
}
