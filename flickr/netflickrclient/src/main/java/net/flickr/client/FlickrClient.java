package net.flickr.client;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.MessageFormat;

/**
 * Created by greenlucky on 12/31/16.
 */
public class FlickrClient {

    private String key;
    private String useId;

    private String format;

    private String method;

    private String targetUrl;

    public FlickrClient() {
        format = "rest";
    }

    public FlickrClient(String method,String key, String useId, String format) {
        this.method = method;
        this.key = key;
        this.useId = useId;
        this.format = format;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUseId() {
        return useId;
    }

    public void setUseId(String useId) {
        this.useId = useId;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getTargetUrl() throws UnsupportedEncodingException {
        String urlEncoding = "UTF-8";
        return MessageFormat.format("/?method={0}&api_key={1}&user_id={2}3&format={3}",
                URLEncoder.encode(String.valueOf(getMethod()), urlEncoding),
                                URLEncoder.encode(String.valueOf(getKey()), urlEncoding),
                                URLEncoder.encode(String.valueOf(getUseId()), urlEncoding),
                                URLEncoder.encode(String.valueOf(getFormat()), urlEncoding)
                );
    }
}
