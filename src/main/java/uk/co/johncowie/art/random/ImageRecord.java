package uk.co.johncowie.art.random;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Key;
import com.google.code.morphia.Morphia;
import com.google.code.morphia.annotations.Id;
import com.mongodb.Mongo;
import org.bson.types.ObjectId;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Date;

public class ImageRecord {

    @Id
    private ObjectId id;

    private Date creationDate;

    private byte[] image;

    public ImageRecord() {}

    public ImageRecord(BufferedImage bufferedImage) throws Exception {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "jpg", os);
        image = os.toByteArray();
        creationDate = new Date();
    }

    public BufferedImage getImage() throws Exception {
        ByteArrayInputStream is = new ByteArrayInputStream(image);
        return ImageIO.read(is);
    }

    public byte[] getImageBytes() {
        return image;
    }

    public static void main(String[] args) throws Exception {
        Datastore ds = new Morphia().createDatastore(new Mongo("localhost", 27017), "random-art");
        RandomImage image = new RandomImage(400, 400);

        Key<ImageRecord> id = ds.save(new ImageRecord(image));
        ImageRecord record = ds.find(ImageRecord.class).field("_id").equal(id.getId()).get();

        ImageDisplayer displayer = new ImageDisplayer();
        displayer.displayImage(record.getImage());

    }



}
