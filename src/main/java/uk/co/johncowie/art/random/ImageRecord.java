package uk.co.johncowie.art.random;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Key;
import com.google.code.morphia.Morphia;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.PostPersist;
import com.google.code.morphia.annotations.Reference;
import com.mongodb.Mongo;
import org.bson.types.ObjectId;
import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.UUID;

public class ImageRecord {

    @Id
    private String id = UUID.randomUUID().toString();

    private Date creationDate;

    private byte[] image;

    @Reference
    private Thumbnail thumbnail;

    public ImageRecord() {}

    public ImageRecord(BufferedImage bufferedImage) throws Exception {
        ByteArrayOutputStream imageOs = new ByteArrayOutputStream();
        ByteArrayOutputStream thumbOs = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "jpg", imageOs);
        image = imageOs.toByteArray();
        ImageIO.write(Scalr.resize(bufferedImage, 50), "jpg", thumbOs);
        thumbnail = new Thumbnail(id, thumbOs.toByteArray());
        creationDate = new Date();
    }

    public byte[] getImage() {
        return image;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    @PostPersist
    private void postPersist() throws Exception {
        new Morphia().createDatastore(new Mongo("localhost", 27017), "random-art").save(thumbnail);
    }



}
