package uk.co.johncowie.art.random;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Key;
import com.google.code.morphia.Morphia;
import com.mongodb.Mongo;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class ArtController {

    public int getInt(HttpServletRequest request, String field, int defaultVal) throws Exception {
        String fieldVal = request.getParameter(field);
        if(fieldVal == null) {
            return defaultVal;
        } else {
            return Integer.parseInt(fieldVal);
        }
    }

    @RequestMapping(value="art")
    public void generate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int width = getInt(request, "width", 300);
        int height = getInt(request, "height", 300);
        int deviation = getInt(request, "deviation", 20);
        ImageRecord record = new ImageRecord(new RandomImage(width, height, deviation));
        Datastore ds = new Morphia().createDatastore(new Mongo("localhost", 27017), "random-art");
        ds.save(record);
        response.getOutputStream().write(record.getImage());
    }

    @RequestMapping(value="/images/{imageId}.jpg")
    public void getRandomArt(@PathVariable String imageId, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Datastore ds = new Morphia().createDatastore(new Mongo("localhost", 27017), "random-art");
        ImageRecord record = ds.find(ImageRecord.class).field("_id").equal(imageId).get();
        if(record == null) {
            response.getOutputStream().write("OH".getBytes());
        } else {
            response.setContentType("image/jpg");
            response.getOutputStream().write(record.getImage());
        }
    }

    @RequestMapping(value="/thumbnails/{thumbnailId}.jpg")
    public void getThumbnail(@PathVariable String thumbnailId, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Datastore ds = new Morphia().createDatastore(new Mongo("localhost", 27017), "random-art");
        Thumbnail thumbnail = ds.find(Thumbnail.class).field("_id").equal(thumbnailId).get();
        if(thumbnail == null) {
            response.getOutputStream().write("OH".getBytes());
        } else {
            response.setContentType("image/jpg");
            response.getOutputStream().write(thumbnail.getImage());
        }

    }

    @RequestMapping(value="list")
    public void getRandomArtList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/html");
        response.getOutputStream().write(getList().getBytes());
    }

    public String getList() throws Exception {
        StringBuilder b = new StringBuilder();
        Datastore ds = new Morphia().createDatastore(new Mongo("localhost", 27017), "random-art");
        List<Key<ImageRecord>> keys = ds.find(ImageRecord.class).asKeyList();
        for(Key<ImageRecord> key : keys) {
            b.append("<a href=\"/images/");
            b.append(key.getId());
            b.append(".jpg\" >");
            b.append("<img src=\"thumbnails/");
            b.append(key.getId());
            b.append(".jpg\" />");
            b.append("</a>");
        }
        return b.toString();
    }

}
