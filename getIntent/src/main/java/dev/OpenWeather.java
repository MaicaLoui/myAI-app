package dev;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenWeather {

    private Main main;

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public  OpenWeather(){ }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public List<Weather> weather;

    public List<Weather> getWeather() {
        return weather;
    }

    @Override
    public String toString() {
        return weather+ ","+ main;
    }
}
