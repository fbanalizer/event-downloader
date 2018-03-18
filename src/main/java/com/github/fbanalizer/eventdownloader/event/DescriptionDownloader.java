package com.github.fbanalizer.eventdownloader.event;

import com.github.fbanalizer.eventdownloader.event.entity.EventDescription;
import com.restfb.FacebookClient;
import com.restfb.types.Event;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class DescriptionDownloader {
    private final FacebookClient facebookClient;

    public DescriptionDownloader(FacebookClient facebookClient) {
        this.facebookClient = facebookClient;
    }

    @Async
    public CompletableFuture<EventDescription> download(String facebookId) {
        Event event = facebookClient.fetchObject(facebookId, Event.class);
        return CompletableFuture.completedFuture(EventDescription.ofEvent(event));
    }
}
