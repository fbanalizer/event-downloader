package com.github.fbanalizer.eventdownloader.event.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
public class DownloadedEvent {
    @Id
    private String id = UUID.randomUUID().toString();
    private String facebookId;
    private LocalDate lastUpdate = LocalDate.now();
    @OneToMany(cascade = CascadeType.ALL)
    private Set<EventAttendee> eventAttendees;
    @OneToOne(cascade = CascadeType.ALL)
    private EventDescription eventDescription;

    public static DownloadedEvent ofDescriptionAttendeesAndFacebookId(EventDescription eventDescription,
                                                                      Set<EventAttendee> eventAttendees, String id) {
        DownloadedEvent downloadedEvent = new DownloadedEvent();
        downloadedEvent.setEventDescription(eventDescription);
        downloadedEvent.setEventAttendees(eventAttendees);
        downloadedEvent.setFacebookId(id);

        return downloadedEvent;
    }
}
