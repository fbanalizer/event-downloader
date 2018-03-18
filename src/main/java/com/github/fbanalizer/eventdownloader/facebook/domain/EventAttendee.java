package com.github.fbanalizer.eventdownloader.facebook.domain;

import com.restfb.Facebook;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
public class EventAttendee {
    @Facebook
    private String name;
    @Facebook
    private String id;
}
