package com.github.fbanalizer.eventdownloader.event;

import com.github.fbanalizer.eventdownloader.event.entity.DownloadedEvent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface DownloadedEventRepository extends CrudRepository<DownloadedEvent, String> {
    Optional<DownloadedEvent> findByFacebookId(String id);
}
