package dev;

import dev.model.IntentResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;


public class GetIntent {

    public static IntentResult currentWeather(String key, String value) {

        IntentResult result = new IntentResult();
        RestTemplate restTemplate = new RestTemplate();
        String weatherResult = null;

        switch (key) {

            case "location":
                switch (value) {
                    case "Curacao":
                        OpenWeather curacao = restTemplate.getForObject(
                                "http://api.openweathermap.org/data/2.5/weather?id=3513090&appid=ed3e9970de4ea07bd311b8437eac2a40", OpenWeather.class);

                        for (Weather weather : curacao.getWeather()) {
                            weatherResult = String.valueOf(weather);

                        }
                        result.setResult("We have " + weatherResult + " today in Curacao");
                        result.setNote("notDatabase");

                        break;
                    case "Aruba":
                        OpenWeather aruba = restTemplate.getForObject(
                                "http://api.openweathermap.org/data/2.5/weather?q=" + key + "&appid=ed3e9970de4ea07bd311b8437eac2a40",
                                OpenWeather.class);


                        for (Weather weather : aruba.getWeather()) {
                            weatherResult = String.valueOf(weather);

                        }
                        result.setResult("We have " + weatherResult + " today in Aruba");
                        result.setNote("notDatabase");

                        break;
                    case "Suriname":
                        OpenWeather suriname = restTemplate.getForObject(
                                "http://api.openweathermap.org/data/2.5/weather?q=Surinam&appid=ed3e9970de4ea07bd311b8437eac2a40", OpenWeather.class);

                        for (Weather weather : suriname.getWeather()) {
                            weatherResult = String.valueOf(weather);

                        }
                        result.setResult("We have " + weatherResult + " today in Suriname");
                        result.setNote("notDatabase");
                        break;
                }
                break;


            default:
                result.setResult("Where are you currently located ?");
                result.setNote("notDatabase");

        }
        return result;
    }


    public static IntentResult openAccount(String key, String value) {

        IntentResult result = new IntentResult();

        switch (key) {

            case "account.type":
                if (value.equals("savings account")) {
                    result.setResult("Okay, what's your name ?");
                    result.setNote("notDatabase");
                    result.setTable("insertInto");
                } else if(value.equals("personal account"))
                    result.setResult("Okay, what's your name ?");
                result.setNote("notDatabase");
                result.setTable("insertInto");

                break;


            default:
                result.setResult("What type of account do you want to open ?");
                result.setNote("notDatabase");

        }
        return result;
    }

    public static IntentResult information(String key, String value) {

        IntentResult result = new IntentResult();
        RestTemplate restTemplate = new RestTemplate();


        switch (key) {

            case "account.type":
                if (value.equals("savings account")) {
                    result.setResult("With a savings account, you are encouraged to save your money for a period of time");
                    result.setNote("notDatabase");
                } else if (value.equals("private account")) {

                }
                break;

            case "location":
                if (value.equals("Curacao")) {
                    OpenWeather curacao = restTemplate.getForObject(
                            "http://api.openweathermap.org/data/2.5/weather?id=3513090&appid=ed3e9970de4ea07bd311b8437eac2a40",
                            OpenWeather.class);

                    for (Weather weather : curacao.getWeather()) {
                        result.setResult(String.valueOf(weather));
                        switch (String.valueOf(weather)) {
                            case "few clouds":
                                result.setResult("You can go to the beach today");
                                result.setNote("notDatabase");
                                break;
                            case "scattered clouds":
                                result.setResult("You can go hiking at Sint Christofel berg");
                                result.setNote("notDatabase");
                                break;
                            case "broken clouds":
                                result.setResult("You can go to Shete Boka today !");
                                result.setNote("notDatabase");
                                break;
                        }

                    }
                }

                break;

            default:
                String[] s = {"information about what do you need ?", "Can you be more Specific about what you want to know ?", "i didn't quite understand, what information you need"};
                Random r = new Random();
                int randomAnswer = r.nextInt(s.length);
                result.setResult(s[randomAnswer]);
                result.setNote("notDatabase");
        }
        return result;
    }

    public static IntentResult currentTemp(String key, String value) { // was created for test purposes.
        IntentResult result = new IntentResult();
        Main tempValue;
        RestTemplate restTemplate = new RestTemplate();
        double temp;
        switch (key) {

            case "location":
                if (value.equals("Aruba")) {
                    OpenWeather aruba = restTemplate.getForObject(
                            "http://api.openweathermap.org/data/2.5/weather?q=Aruba&appid=ed3e9970de4ea07bd311b8437eac2a40", OpenWeather.class);


                    tempValue = aruba.getMain();
                    temp = Double.valueOf(String.valueOf(tempValue)) - 273.15;
                    if (temp >= 30) {
                        result.setResult("It's very hot today, it's " + Math.round(temp) + " degrees celsius in Aruba");
                        result.setNote("notDatabase");
                    } else if (temp >= 25 && temp < 30) {
                        result.setResult("It's hot today, it's " + Math.round(temp) + " degrees celsius in Aruba");
                        result.setNote("notDatabase");
                    }
                } else if (value.equals("Curacao")) {
                    OpenWeather curacao = restTemplate.getForObject(
                            "http://api.openweathermap.org/data/2.5/weather?id=3513090&appid=ed3e9970de4ea07bd311b8437eac2a40", OpenWeather.class);


                    tempValue = curacao.getMain();
                    temp = Double.valueOf(String.valueOf(tempValue)) - 273.15;
                    if (temp >= 30) {
                        result.setResult("It's very hot today, it's " + Math.round(temp) + " degrees celsius in Curacao");
                        result.setNote("notDatabase");
                    } else if (temp >= 25 && temp < 30) {
                        result.setResult("It's hot today, it's " + Math.round(temp) + " degrees celsius in Curacao");
                        result.setNote("notDatabase");
                    }
                }

                break;

            default:
                result.setResult("Where are you currently located ?");
                result.setNote("notDatabase");

        }
        return result;
    }


    public static IntentResult unknownIntent(String entity, String value) {

        IntentResult result = new IntentResult();

        switch (entity) {

            case "person":
                result.setResult("Nice name, can i get your address " + value + " ?");
                result.setNote("database");
                break;

            case "money":
                result.setResult("Alright, what's your name ?");
                result.setNote("database");
                break;

            case "number":
                result.setResult("Okay Got it!, is all your information filled in correctly ?");
                result.setNote("database");
                break;

            case "address":
                result.setResult("Okay, what is your birth date ?");
                result.setNote("database");
                break;

            case "carBrand":
                result.setResult("Okay, how much money do you think you will need?");
                result.setNote("notDatabase");
                break;

            case "date":
                result.setResult("What is your birth place ?");
                result.setNote("database");
                break;

            case "location":
                result.setResult("Okay, can i get your telephone number please ?");
                result.setNote("database");
                break;

            default: // when there is no intent nor entity
                String[] s = {"I didnt understand that", "Sorry but i didn't quite understand that"};
                Random r = new Random();
                int randomAnswer = r.nextInt(s.length);
                result.setResult(s[randomAnswer]);
                result.setNote("notDatabase");
        }

        return result;
    }

    public static IntentResult validationCheck(String key, String value) {

        IntentResult result = new IntentResult();

        switch (key) {

            case "check":
                if (value.equals("yes")) {
                    result.setResult("Okay, i will submit your form!");
                    result.setNote("notDatabase");
                    return result;
                } else if (value.equals("no")) {
                    result.setResult("can you please fill your information in correctly, and say so when your done");
                    result.setNote("notDatabase");
                    return result;
                }

                break;

            default: // when there is no intent nor entity
                result.setResult("I didnt understand that");
                result.setNote("notDatabase");
        }
        return result;
    }

    public static IntentResult applyMortgage(String entity, String object) {
        IntentResult result = new IntentResult();

        switch (entity) {

            case "object":
                if (object.equals("car")) {
                    result.setResult("Okay, what's the brand of the car you want to buy?");
                    result.setNote("notDatabase");
                    result.setTable("insertInto");
                } else if (object.equals("house")) {
                    result.setResult("Okay, what's your name ?");
                    result.setNote("notDatabase");
                    result.setTable("insertInto");
                }
                break;

            case "":

            default:
                result.setResult("For what do you need a mortgage ?");
                result.setNote("notDatabase");

        }

        return result;
    }

    public static IntentResult repeatIntent(String key, String value) {

        IntentResult result = new IntentResult();

        switch (key) {

            case "person":
                String s [] ={"Yes, "+value+" I already have you're name noted.", "Sounds familiar, yes I have your name "+value+"", "" + value + ", you already introduced yourself to me!"};
                Random r = new Random();
                int randomAnswer = r.nextInt(s.length);
                result.setResult(s[randomAnswer]);
               // result.setResult("" + value + ", you already introduced yourself to me!");
                result.setNote("notDatabase");
                break;

            case "address":
                result.setResult("I think i already have your address");
                result.setNote("notDatabase");
                break;

            case "number":
                result.setResult("according to my memory you already gave me your number.");
                result.setNote("notDatabase");
                break;

            case "check":
                if (value.equals("yes")) {
                    result.setResult("Okay, whats your address ?");
                    result.setNote("notDatabase");

                } else if (value.equals("no")) {
                    result.setResult("can you please spell it for me ?");
                    result.setNote("notDatabase");

                }

                break;

            default: // when there is no intent nor entity
                result.setResult("yes, i already answered that question");
                result.setNote("notDatabase");


        }
        return result;
    }

    public static IntentResult dateAndTime(String key, String value) {

        IntentResult result = new IntentResult();

        switch (key) {

            case "date":
                Date date = new Date();
                Calendar calendar = Calendar.getInstance();
                calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
                result.setResult("Today is "+calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault())+
                        ". "+calendar.get(Calendar.DAY_OF_MONTH)+" of "
                        +calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())+"");
              //  result.setResult("" + value + ", you already introduced yourself to me!");
                result.setNote("notDatabase");
                LocalDate localDate = LocalDate.now();
                break;

            case "time":
                result.setResult("I think i already have your address");
                result.setNote("notDatabase");
                break;

            default: // when there is no intent nor entity
                result.setResult("yes, i already answered that question");
                result.setNote("notDatabase");


        }
        return result;
    }

}
