package com.example.demoulid;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import de.huxhorn.sulky.ulid.ULID;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Entity
public class Tweet implements Serializable {

    @Id
    @Column(columnDefinition = "CHAR(26)")
    @Type(type = "com.example.demoulid.UlidType")
    @JsonSerialize(using = ToStringSerializer.class)
    private ULID.Value ulid;

    private String text;

    private String username;

    private Tweet() {
    }

    public Tweet(ULID.Value ulid, String text, String username) {
        this.ulid = ulid;
        this.text = text;
        this.username = username;
    }

    public ULID.Value getUlid() {
        return ulid;
    }

    public void setUlid(ULID.Value ulid) {
        this.ulid = ulid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Instant getCreatedAt() {
        return Instant.ofEpochMilli(this.ulid.timestamp());
    }

    @Override
    public String toString() {
        return "Tweet{" + "ulid=" + ulid + ", text='" + text + '\'' + ", username='"
            + username + '\'' + ", createdAt=" + getCreatedAt() + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Tweet tweet = (Tweet) o;
        return Objects.equals(ulid, tweet.ulid);
    }

    @Override
    public int hashCode() {

        return Objects.hash(ulid);
    }
}
