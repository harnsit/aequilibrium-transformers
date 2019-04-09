package com.harpreet.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.annotation.sql.DataSourceDefinition;
import java.util.UUID;
@Data
@ToString
public class Transformer implements Comparable<Transformer> {

    private static final int COURAGE_DIFF = 4;
    private static final int SKILL_DIFF = 3;
    private static final int STRENGTH_DIFF = 3;

    public Transformer() {
        strength = intelligence = speed = endurance = 1;
        rank = courage = firepower = skill = 1;
    }
    private UUID id;
    private String name;
    private Team team;
    private int strength;
    private int intelligence;
    private int speed;
    private int endurance;
    private int rank;
    private int courage;
    private int firepower;
    private int skill;

    //TODO: remove if not required
    public int getOverallRating() {
        return strength + intelligence + speed + endurance + firepower;
    }

    @JsonIgnore
    public int overallRating() {
        return strength + intelligence + speed + endurance + firepower;
    }

    @JsonIgnore
    public boolean canScare(Transformer other) {
        if (this.courage - other.courage >= COURAGE_DIFF && this.strength - other.strength >= STRENGTH_DIFF)
            return true;
        if (this.skill - other.skill >= SKILL_DIFF)
            return true;
        return false;

    }

    @JsonIgnore
    @Override
    public int compareTo(Transformer other) {
        return this.rank - other.rank;
    }

    @JsonIgnore
    public boolean hasBetterRatingThan(Transformer other) {
        return this.overallRating() > other.overallRating();
    }
}