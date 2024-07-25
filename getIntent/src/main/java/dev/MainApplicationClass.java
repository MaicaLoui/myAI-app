package dev;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MainApplicationClass {

    public static void main(String[] args) {
        SpringApplication.run(MainApplicationClass.class, args);
    }

    private static final Logger log = LoggerFactory.getLogger(OpenWeather.class);

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
        return args -> {
            OpenWeather openWeather = restTemplate.getForObject(
                    "http://api.openweathermap.org/data/2.5/weather?q=Aruba&appid=ed3e9970de4ea07bd311b8437eac2a40",
                    OpenWeather.class);
            log.info(openWeather.toString());

        };
    }

}
