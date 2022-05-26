package dev;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;


@RestController
public class DevController {
    private static final Logger log = LoggerFactory.getLogger(DevController.class);

    private OpenNLP openNLP;

    public DevController() {
        try {
            openNLP = new OpenNLP();
        } catch (IOException e) {
            log.error(e.getMessage(),e);
        }
    }

    @RequestMapping("/intent")
    @CrossOrigin(origins = "http://localhost:8000")
    public HttpEntity<Intent> intent(@RequestParam(value = "sentence", required = false) String sentence) {
        Intent intent = null;

        try {
            intent = openNLP.nlp(sentence);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(intent, HttpStatus.OK);
    }

}
