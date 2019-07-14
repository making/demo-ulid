package com.example.demoulid;


import de.huxhorn.sulky.ulid.ULID;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TweeterApplicationRunner implements ApplicationRunner {

    private final TweetRepository tweetRepository;

    private final ULID ulid;

    public TweeterApplicationRunner(TweetRepository tweetRepository, ULID ulid) {
        this.tweetRepository = tweetRepository;
        this.ulid = ulid;
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        this.tweetRepository.save(new Tweet(ulid.nextValue(), "Demo1", "making"));
        this.tweetRepository.save(new Tweet(ulid.nextValue(), "Demo2", "making"));
        long count = this.tweetRepository.count();
        System.out.println("Number of tweets: " + count);
        Iterable<Tweet> tweets = this.tweetRepository.findAll();
        tweets.forEach(tweet -> System.out.println(tweet));
    }
}