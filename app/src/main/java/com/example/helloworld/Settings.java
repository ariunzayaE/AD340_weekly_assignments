package com.example.helloworld;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Settings {
    @PrimaryKey
    @NonNull
    private String id = "";

    @ColumnInfo(name = "match_reminder_time")
    private String matchReminderTime;

    @ColumnInfo(name = "max_distance")
    private String maxDistance;

    @ColumnInfo(name = "gender_preference")
    private String genderPreference;

    @ColumnInfo(name = "min_age")
    private int minAge;

    @ColumnInfo(name = "max_age")
    private int maxAge;

    @ColumnInfo(name = "account_privacy")
    private String accountPrivacy;

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getMatchReminderTime() {
        return matchReminderTime;
    }

    public void setMatchReminderTime(String matchReminderTime) {
        this.matchReminderTime = matchReminderTime;
    }

    public String getMaxDistance() {
        return maxDistance;
    }

    public void setMaxDistance(String maxDistance) {
        this.maxDistance = maxDistance;
    }

    public String getGenderPreference() {
        return genderPreference;
    }

    public void setGenderPreference(String genderPreference) {
        this.genderPreference = genderPreference;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public String getAccountPrivacy() {
        return accountPrivacy;
    }

    public void setAccountPrivacy(String accountPrivacy) {
        this.accountPrivacy = accountPrivacy;
    }
}
