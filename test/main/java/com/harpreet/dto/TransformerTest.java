package com.harpreet.dto;

import org.junit.Test;

import java.util.UUID;

public class TransformerTest {

    @Test
    public void overallRatingShouldWork() {
        Transformer t = new Transformer();
        assert t.overallRating() == 5;
    }


    @Test
    public void canScareShouldWork1() {
        Transformer firstT = new Transformer();
        firstT.setName("FirstT");
        firstT.setId(UUID.randomUUID());
        firstT.setSkill(8);
        Transformer secondT = new Transformer();
        secondT.setName("SecondT");
        secondT.setId(UUID.randomUUID());
        secondT.setSkill(4);
        assert firstT.canScare(secondT);
        assert !secondT.canScare(firstT);
    }

    @Test
    public void canScareShouldWork2() {
        Transformer firstT = new Transformer();
        firstT.setName("FirstT");
        firstT.setId(UUID.randomUUID());
        firstT.setStrength(8);
        firstT.setCourage(10);
        Transformer secondT = new Transformer();
        secondT.setName("SecondT");
        secondT.setId(UUID.randomUUID());
        secondT.setStrength(4);
        secondT.setCourage(6);
        secondT.setFirepower(10);
        secondT.setEndurance(10);
        assert firstT.canScare(secondT);
        assert !secondT.canScare(firstT);
    }

    @Test
    public void hasBetterRatingThan() {
        Transformer firstT = new Transformer();
        firstT.setName("FirstT");
        firstT.setId(UUID.randomUUID());
        firstT.setStrength(8);
        firstT.setCourage(9);
        Transformer secondT = new Transformer();
        secondT.setName("SecondT");
        secondT.setId(UUID.randomUUID());
        secondT.setStrength(10);
        secondT.setCourage(10);
        secondT.setFirepower(10);
        secondT.setEndurance(10);
        assert secondT.hasBetterRatingThan(firstT);
        assert !firstT.hasBetterRatingThan(secondT);
    }

}
