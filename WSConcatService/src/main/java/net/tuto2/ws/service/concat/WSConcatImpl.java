package net.tuto2.ws.service.concat;

import javax.jws.WebService;

/**
 * Created by greenlucky on 12/28/16.
 */
@WebService(endpointInterface = "net.tuto2.ws.service.concat.WSConcat",
    serviceName = "MyConcatAgent")
public class WSConcatImpl {

    public String concat(String str1, String str2){
        return str1+str2;
    }
}
