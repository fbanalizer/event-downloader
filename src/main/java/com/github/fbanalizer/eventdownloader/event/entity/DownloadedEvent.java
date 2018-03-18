package com.github.fbanalizer.eventdownloader.event.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
    @OneToMany
    private Set<EventAttendee> eventAttendees;
    @OneToOne
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
