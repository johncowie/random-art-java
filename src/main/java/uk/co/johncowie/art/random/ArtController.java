package uk.co.johncowie.art.random;

import com.sun.org.apache.bcel.internal.generic.GETFIELD;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ArtController {

    @RequestMapping(value="art")
    public void getArt(HttpServletResponse response) throws Exception {
        ImageRecord record = new ImageRecord(new RandomImage(300, 300));
        response.getOutputStream().write(record.getImageBytes());
    }

}
