package net.flickr.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import net.flickr.data.ErrType;
import net.flickr.data.PhotoType;
import net.flickr.data.RspType;

import javax.ws.rs.core.MultivaluedMap;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by greenlucky on 12/31/16.
 */
public class FlickrClientRepository {

    private Client client;

    private FlickrClient flickrClient;

    public FlickrClientRepository() {
    }

    public FlickrClientRepository(FlickrClient flickrClient) {
        this.client = Client.create();
        this.flickrClient = flickrClient;
    }

    public List<String> buildUrlList(List<PhotoType> photos) throws UnsupportedEncodingException {
        List<String> urlList = new ArrayList<>();
        System.out.println();
        for (PhotoType photo : photos) {
            String urlEncoding = StandardCharsets.UTF_8.toString();
            String url = MessageFormat.format("http://farm{0}.static.flickr.com/{1}/{2}_{3}.jpg",
                    String.valueOf(photo.getFarm()),
                    URLEncoder.encode(String.valueOf(photo.getServer()), urlEncoding),
                    URLEncoder.encode(String.valueOf(photo.getId()), urlEncoding),
                    URLEncoder.encode(String.valueOf(photo.getSecret()), urlEncoding));
            urlList.add(url);
            System.out.println(url);
        }
        return urlList;
    }

    public String getError(ErrType errType) throws  UnsupportedEncodingException{
        return "Error code: "+errType.getCode() + " msg: "+errType.getMsg();
    }

    public void getPhotos(){
        RspType rspType = null;
        try {
            MultivaluedMap queryParams = new MultivaluedMapImpl();
            queryParams.add("method", flickrClient.getMethod());
            queryParams.add("api_key", flickrClient.getKey());
            queryParams.add("user_id", flickrClient.getUseId());
            queryParams.add("format", flickrClient.getFormat());
            WebResource webResource = client.resource("https://api.flickr.com/services/rest").queryParams(queryParams);


            ClientResponse response = webResource.get(ClientResponse.class);

            if (!(response.getStatus() == 201 || response.getStatus() == 200)) {
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
            }

            System.out.println("Output from Server .... \n");

            rspType =  response.getEntity(new GenericType<RspType>(){});
            if(rspType.getStat().equals("ok"))
                buildUrlList(rspType.getPhotos().getPhoto());
            else
                System.out.println(getError(rspType.getErr()));
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
