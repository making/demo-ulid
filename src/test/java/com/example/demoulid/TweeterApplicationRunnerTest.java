package com.example.demoulid;

import de.huxhorn.sulky.ulid.ULID;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.test.rule.OutputCapture;

import java.time.Instant;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class TweeterApplicationRunnerTest {

    @Rule
    public OutputCapture capture = new OutputCapture();

    final ULID ulid = new ULID();

    @Test
    public void contextLoads() throws Exception {
        TweetRepository tweetRepository = mock(TweetRepository.class);

        given(tweetRepository.count()).willReturn(2L);
        Instant now = Instant.now();
        ULID.Value ulid1 = ulid.nextValue();
        ULID.Value ulid2 = ulid.nextValue();
        given(tweetRepository.findAll())
            .willReturn(Arrays.asList(new Tweet(ulid1, "test1", "making"),
                new Tweet(ulid2, "test2", "making")));

        TweeterApplicationRunner tweeterApplicationRunner = new TweeterApplicationRunner(
            tweetRepository, ulid);
        tweeterApplicationRunner.run(new DefaultApplicationArguments(new String[]{}));
        String output = capture.toString();
        assertThat(output).contains("Number of tweets: 2");
        assertThat(output).contains("Tweet{ulid=" + ulid1
            + ", text='test1', username='making', createdAt=" + now + "}");
        assertThat(output).contains("Tweet{ulid=" + ulid2
            + ", text='test2', username='making', createdAt=" + now + "}");
    }
}