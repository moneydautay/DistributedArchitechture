package net.flickr.client;


import java.io.UnsupportedEncodingException;

/**
 * Created by greenlucky on 12/31/16.
 */
public class App {

    public static void main(String[] args) throws UnsupportedEncodingException {
       FlickrClient flickrClient = new FlickrClient("flickr.photos.search","64ff90bfeb2e143d1a970e9ab7ae7608","109870888@N03","rest");
        FlickrClientRepository app = new FlickrClientRepository(flickrClient);
        app.getPhotos();

    }
}
