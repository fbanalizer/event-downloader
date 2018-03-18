package com.github.fbanalizer.eventdownloader.event.entity;

import com.restfb.types.Event;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Getter
@Setter
public class EventDescription {
    @Id
    private String id = UUID.randomUUID().toString();
    private String description;
    private String location;

    public static EventDescription ofEvent(Event event) {
        EventDescription eventDescription = new EventDescription();
        eventDescription.setLocation(event.getLocation());
        eventDescription.setDescription(event.getDescription());

        return eventDescription;
    }
}
