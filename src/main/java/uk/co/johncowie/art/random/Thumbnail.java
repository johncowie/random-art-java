package uk.co.johncowie.art.random;

import com.google.code.morphia.annotations.Id;
import org.bson.types.ObjectId;

public class Thumbnail {

    @Id
    private String id;
    byte[] image;

    public Thumbnail(){}

    public Thumbnail(String id, byte[] bytes) {
        this.id = id;
        this.image = bytes;
    }

    public String getId() {
        return id;
    }

    public byte[] getImage() {
        return image;
    }
}
