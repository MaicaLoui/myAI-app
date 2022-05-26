package dev;

import org.springframework.hateoas.ResourceSupport;

public class Intent extends ResourceSupport {

    private String name;

    public Intent() { }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}