package uk.co.johncowie.art.random;

import com.google.code.morphia.Key;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import uk.co.johncowie.art.random.deviators.RandomDeviator;
import uk.co.johncowie.art.random.deviators.Deviator;
import uk.co.johncowie.art.random.numbers.NumberGenerator;
import uk.co.johncowie.art.random.numbers.StringNumberGenerator;
import uk.co.johncowie.art.random.pickers.PixelPicker;
import uk.co.johncowie.art.random.pickers.PixelPickerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ArtController {

    MongoDB db = MongoDB.getInstance("random-art");

    @RequestMapping(value="/images/{imageId}.jpeg", method= RequestMethod.GET, produces= MediaType.IMAGE_JPEG_VALUE)
    public void getRandomArt(@PathVariable String imageId, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ImageRecord record = db.getDS().find(ImageRecord.class).field("_id").equal(imageId).get();
        if(record == null) {
            response.getOutputStream().write("OH".getBytes());
        } else {
            response.setContentType("image/jpeg");
            response.getOutputStream().write(record.getImage());
        }
    }

    @RequestMapping(value="/thumbnails/{thumbnailId}.jpeg")
    public void getThumbnail(@PathVariable String thumbnailId, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Thumbnail thumbnail = db.getDS().find(Thumbnail.class).field("_id").equal(thumbnailId).get();
        if(thumbnail == null) {
            response.getOutputStream().write("OH".getBytes());
        } else {
            response.setContentType("image/jpeg");
            response.getOutputStream().write(thumbnail.getImage());
        }

    }

    @RequestMapping(value="list.html")
    public ModelAndView getRandomArtList() throws Exception {
        ModelAndView model = new ModelAndView("index");
        Iterable<Key<ImageRecord>> keys = db.getDS().createQuery(ImageRecord.class).fetchKeys();
        List<String> ids = new ArrayList<String>();
        for(Key<ImageRecord> key : keys) {
            ids.add(key.getId().toString());
        }
        model.addObject("ids", ids);
        return model;
    }

    @RequestMapping(value="/images/{imageId}.html", method= RequestMethod.GET, produces=MediaType.TEXT_HTML_VALUE)
    public ModelAndView getImage(@PathVariable String imageId) throws Exception {
        ModelAndView model = new ModelAndView("single-image");
        model.addObject("id", imageId);
        return model;
    }

    @RequestMapping(value="/generate.html", method= RequestMethod.GET, produces=MediaType.TEXT_HTML_VALUE)
    public ModelAndView getGeneratePage() {
        ModelAndView model = new ModelAndView("generate");
        model.addObject("variables", new Variables());
        return model;
    }

    @RequestMapping(value="/generate.html", method=RequestMethod.POST, produces=MediaType.TEXT_HTML_VALUE)
    public ModelAndView generate(@ModelAttribute("variables") Variables variables, BindingResult result) throws Exception {
        ModelAndView model = new ModelAndView("generate");
        Point start = new Point(variables.getSeedX(), variables.getSeedY());
        Color seedColour = Color.decode(variables.getSeedColour());
        NumberGenerator generator = new StringNumberGenerator(variables.getSeed());
        PixelPicker picker = PixelPickerFactory.getPixelPicker(variables.getPicker(), start, variables.getSeed());
        Deviator deviator = new RandomDeviator(generator, variables.getDeviation());
        BufferedImage image = new RandomImage(variables.getWidth(), variables.getHeight(), deviator, seedColour, picker);
        ImageRecord record = new ImageRecord(image);
        Key<ImageRecord> key = db.getDS().save(record);
        model.addObject("id", key.getId());
        model.addObject("variables", new Variables());
        return model;
    }



}
