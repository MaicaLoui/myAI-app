package dev;

import dev.model.IntentResult;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IntentController {

    @GetMapping("/currentWeather")
    public HttpEntity<IntentResult> intent(
            @RequestParam(value = "key", required = false) String key, String value) {

        IntentResult intent = null;
        intent = GetIntent.currentWeather(key, value);
        return new ResponseEntity<>(intent, HttpStatus.OK);
    }

    @GetMapping("/openAccount")
    public HttpEntity<IntentResult> bank(
            @RequestParam(value = "key", required = false) String key, String value) {

        IntentResult intent = null;
        intent = GetIntent.openAccount(key, value);
        return new ResponseEntity<>(intent, HttpStatus.OK);
    }

    @GetMapping("/information")
    public HttpEntity<IntentResult> info(
            @RequestParam(value = "key", required = false) String key, String value) {

        IntentResult intent = null;
        intent = GetIntent.information(key, value);
        return new ResponseEntity<>(intent, HttpStatus.OK);
    }

    @GetMapping("/unknownIntent")
    public HttpEntity<IntentResult> entity(
            @RequestParam(value = "key", required = false) String key, String value) {

       IntentResult intent = null;
        intent = GetIntent.unknownIntent(key, value);
        return new ResponseEntity<>(intent, HttpStatus.OK);
    }

    @GetMapping("/currentTemp")
    public HttpEntity<IntentResult> temp(
            @RequestParam(value = "key", required = false) String key, String value) {

        IntentResult intent = null;
        intent = GetIntent.currentTemp(key, value);
        return new ResponseEntity<>(intent, HttpStatus.OK);
    }

    @GetMapping("/validationCheck")
    public HttpEntity<IntentResult> check(
            @RequestParam(value = "key", required = false) String key, String value) {

        IntentResult intent = null;
        intent = GetIntent.validationCheck(key, value);
        return new ResponseEntity<>(intent, HttpStatus.OK);
    }

    @GetMapping("/applyMortgage")
    public HttpEntity<IntentResult> mortgage(
            @RequestParam(value = "key", required = false) String key, String value) {

        IntentResult intent = null;
        intent = GetIntent.applyMortgage(key, value);
        return new ResponseEntity<>(intent, HttpStatus.OK);
    }

    @GetMapping("/repeatIntent")
    public HttpEntity<IntentResult> repeat(
            @RequestParam(value = "key", required = false) String key, String value) {

        IntentResult intent = null;
        intent = GetIntent.repeatIntent(key, value);
        return new ResponseEntity<>(intent, HttpStatus.OK);
    }

    @GetMapping("/date")
    public HttpEntity<IntentResult> dateAndTime(
            @RequestParam(value = "key", required = false) String key, String value) {

        IntentResult intent = null;
        intent = GetIntent.dateAndTime(key, value);
        return new ResponseEntity<>(intent, HttpStatus.OK);
    }

}

