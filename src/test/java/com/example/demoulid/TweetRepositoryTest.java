package com.example.demoulid;


import de.huxhorn.sulky.ulid.ULID;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest // (1)
@Transactional
public class TweetRepositoryTest {

    @Autowired
    TweetRepository tweetRepository;

    final ULID ulid = new ULID();

    @Test
    public void insertAndCount() {
        tweetRepository.save(new Tweet(ulid.nextValue(), "test", "foo"));
        long count = tweetRepository.count();
        assertThat(count).isEqualTo(1);
    }

    @Test
    public void insertAndFindAll() {
        Tweet tweet1 = new Tweet(ulid.nextValue(), "test", "foo");
        Tweet tweet2 = new Tweet(ulid.nextValue(), "test", "foo");

        tweetRepository.save(tweet1);
        tweetRepository.save(tweet2);

        Iterable<Tweet> tweets = tweetRepository.findAll();
        assertThat(tweets).containsExactly(tweet1, tweet2);
    }
}