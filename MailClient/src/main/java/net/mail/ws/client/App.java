package net.mail.ws.client;

/**
 * Created by greenlucky on 12/28/16.
 */
public class App {

    public static void main(String[] args){
        MyOwnMailAgent service = new MyOwnMailAgent();
        WSMail mail = service.getWSMailImplPort();
        mail.sendMail("smtp.gmail.com",
                465,
                "nguyenlamit86@gmail.com",
                "nguyenlamit86@gmail.com",
                "tntthao87@gmail.com",
                "lam1986men1987",true);
    }
}
