package org.rest.service.entities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Created by greenlucky on 1/5/17.
 */
@XmlRootElement(name = "album")
public class Album {

    private String albumId;
    private String title;
    private Date rdate;
    private String genre;

    public Album() {
    }

    public Album(String albumId, String title, Date rdate, String genre) {
        this.albumId = albumId;
        this.title = title;
        this.rdate = rdate;
        this.genre = genre;
    }

    @XmlElement
    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    @XmlElement
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @XmlElement
    public Date getRdate() {
        return rdate;
    }

    public void setRdate(Date rdate) {
        this.rdate = rdate;
    }

    @XmlElement(name = "date", type = Date.class)
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Album{" +
                "albumId='" + albumId + '\'' +
                ", title='" + title + '\'' +
                ", rdate=" + rdate +
                ", genre='" + genre + '\'' +
                '}';
    }
}
