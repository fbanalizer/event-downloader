package com.github.fbanalizer.eventdownloader.event;

import com.github.fbanalizer.eventdownloader.facebook.domain.EventAttendee;
import com.restfb.Connection;
import com.restfb.FacebookClient;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
class AttendeeDownloader {
    private final FacebookClient facebookClient;

    public AttendeeDownloader(FacebookClient facebookClient) {
        this.facebookClient = facebookClient;
    }

    @Async
    public CompletableFuture<Set<com.github.fbanalizer.eventdownloader.event.entity.EventAttendee>> download(String facebookId) {
        Set<EventAttendee> eventAttendees = new HashSet<>();
        Connection<EventAttendee> connection = facebookClient.fetchConnection("/" + facebookId + "/attending", EventAttendee.class);
        eventAttendees.addAll(connection.getData());
        while (connection.hasNext()) {
            eventAttendees.addAll(connection.getData());
            connection = facebookClient.fetchConnectionPage(connection.getNextPageUrl(), EventAttendee.class);
        }

        return CompletableFuture.completedFuture(eventAttendees.stream()
                .map(com.github.fbanalizer.eventdownloader.event.entity.EventAttendee::ofFacebookEventAttendee)
                .collect(Collectors.toSet())
        );
    }
}
