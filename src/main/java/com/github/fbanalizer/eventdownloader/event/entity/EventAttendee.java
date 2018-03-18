package com.github.fbanalizer.eventdownloader.event.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Getter
@Setter
public class EventAttendee {
    @Id
    private String id = UUID.randomUUID().toString();
    private String facebookId;
    private String firstName;
    private String lastName;

    public static EventAttendee ofFacebookEventAttendee(
            com.github.fbanalizer.eventdownloader.facebook.domain.EventAttendee fbEventAttendee) {
        EventAttendee eventAttendee = new EventAttendee();
        String [] firstAndLastName = fbEventAttendee.getName().split(" ");
        eventAttendee.setFacebookId(fbEventAttendee.getId());
        eventAttendee.setFirstName(firstAndLastName[0] != null ? firstAndLastName[0] : "");
        eventAttendee.setLastName(firstAndLastName[firstAndLastName.length - 1] != null ? firstAndLastName[firstAndLastName.length - 1] : "");

        return eventAttendee;
    }
}
