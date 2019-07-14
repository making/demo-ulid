package com.example.demoulid.rest;

import com.example.demoulid.Tweet;
import de.huxhorn.sulky.ulid.ULID;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler
public class TweetEventHandler {

    private final ULID ulid;

    public TweetEventHandler(ULID ulid) {
        this.ulid = ulid;
    }

    @HandleBeforeCreate
    public void beforeCreate(Tweet tweet) {
        if (tweet.getUlid() == null) {
            tweet.setUlid(this.ulid.nextValue());
        }
    }
}
