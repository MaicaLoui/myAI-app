package dev;

import org.springframework.hateoas.ResourceSupport;
import java.util.List;

public class Intent extends ResourceSupport {

    private String name;
    private List<Entity> entities;

    public Intent() {
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
