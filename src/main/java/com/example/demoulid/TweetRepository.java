package com.example.demoulid;

import de.huxhorn.sulky.ulid.ULID;
import org.springframework.data.repository.CrudRepository;

public interface TweetRepository extends CrudRepository<Tweet, ULID.Value> {

}
