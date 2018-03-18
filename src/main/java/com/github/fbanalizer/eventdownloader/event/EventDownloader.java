package com.github.fbanalizer.eventdownloader.event;

import com.github.fbanalizer.eventdownloader.event.entity.DownloadedEvent;
import com.github.fbanalizer.eventdownloader.event.entity.EventAttendee;
import com.github.fbanalizer.eventdownloader.event.entity.EventDescription;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Service
public class EventDownloader {
    private final DescriptionDownloader descriptionDownloader;
    private final AttendeeDownloader attendeeDownloader;
    private final DownloadedEventRepository downloadedEventRepository;

    public EventDownloader(DescriptionDownloader descriptionDownloader,
                           AttendeeDownloader attendeeDownloader,
                           DownloadedEventRepository downloadedEventRepository) {
        this.descriptionDownloader = descriptionDownloader;
        this.attendeeDownloader = attendeeDownloader;
        this.downloadedEventRepository = downloadedEventRepository;
    }

    public DownloadedEvent downloadEvent(String id) {
        return getEventFromLocalStorage(id)
                .orElse(downloadFromFacebook(id));
    }

    private Optional<DownloadedEvent> getEventFromLocalStorage(String id) {
        return downloadedEventRepository.findByFacebookId(id);
    }

    private DownloadedEvent downloadFromFacebook(String id) {
        CompletableFuture<Set<EventAttendee>> attendees = attendeeDownloader.download(id);
        CompletableFuture<EventDescription> description = descriptionDownloader.download(id);
        CompletableFuture.allOf(attendees, description).join();

        try {
            return DownloadedEvent.ofDescriptionAttendeesAndFacebookId(description.get(), attendees.get(), id);
        }  catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
