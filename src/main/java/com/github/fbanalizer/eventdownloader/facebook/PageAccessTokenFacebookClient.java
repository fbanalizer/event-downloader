package com.github.fbanalizer.eventdownloader.facebook;

import com.restfb.DefaultFacebookClient;
import com.restfb.Version;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
public class PageAccessTokenFacebookClient extends DefaultFacebookClient {
    public PageAccessTokenFacebookClient(Environment environment) {
        this.accessToken = environment.getRequiredProperty("fb.api.key");
        this.apiVersion = Version.VERSION_2_9;
    }
}
