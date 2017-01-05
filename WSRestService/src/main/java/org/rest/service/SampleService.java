package org.rest.service;

import org.rest.service.entities.Album;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.*;

/**
 * Created by greenlucky on 1/5/17.
 */
@Path("/sampleservice")
public class SampleService{

    private static Map<String, Album> albums = new HashMap<String, Album>();
    
    /** The application logger */
    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(SampleService.class);

    static {
        Album album1 = new Album();
        album1.setAlbumId("1");
        album1.setTitle("Elephunk");
        album1.setGenre("Hiphop");
        album1.setRdate(Calendar.getInstance().getTime());
        albums.put(album1.getAlbumId(), album1);
        Album album2 = new Album();
        album2.setAlbumId("2");
        album2.setTitle("Elephunk");
        album2.setGenre("Hiphop");
        album2.setRdate(Calendar.getInstance().getTime());
        albums.put(album2.getAlbumId(), album2);
        Album album3 = new Album();
        album3.setAlbumId("3");
        album3.setTitle("Elephunk");
        album3.setGenre("Hiphop");
        album3.setRdate(Calendar.getInstance().getTime());
        albums.put(album3.getAlbumId(), album3);
    }

    @GET
    @Path("/albums")
    @Produces(MediaType.APPLICATION_XML)
    public List<Album> albumList(){
        LOGGER.info("Get albums {}", albums);
        return new ArrayList<>(albums.values());
    }

    @GET
    @Path("/album/{albumId}")
    @Produces(MediaType.APPLICATION_XML)
    public Album getAlbum(@PathParam("albumId") String albumId){
        return albums.get(albumId);
    }

    @GET
    @Path("/json/albums")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Album> listAlbumJSON(){
        LOGGER.info("Get albums by json {}", albums);
        return new ArrayList<>(albums.values());
    }

    @GET
    @Path("/json/album/{albumId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Album getAlbumJSON(@PathParam("albumId") String albumId){
        LOGGER.info("Get album by json given by {}", albumId);
        return getAlbum(albumId);
    }
}
