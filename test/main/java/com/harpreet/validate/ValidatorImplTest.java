package com.harpreet.validate;

import com.harpreet.dto.Team;
import com.harpreet.dto.Transformer;
import org.junit.Test;

public class ValidatorImplTest {

    @Test
    public void testInvalidCourage() {
        Transformer t = new Transformer();
        t.setCourage(15);
        t.setTeam(Team.A);
        Validator v = new ValidatorImpl();
        assert !v.validate(t).isValid();
    }

    @Test
    public void testInvalidFirepower() {
        Transformer t = new Transformer();
        t.setFirepower(15);
        t.setTeam(Team.D);
        Validator v = new ValidatorImpl();
        assert !v.validate(t).isValid();
    }

    @Test
    public void testInvalidStrength() {
        Transformer t = new Transformer();
        t.setStrength(15);
        t.setTeam(Team.D);
        Validator v = new ValidatorImpl();
        assert !v.validate(t).isValid();
    }

    @Test
    public void testValidTrasformer() {
        Transformer t = new Transformer();
        t.setFirepower(10);
        t.setStrength(5);
        t.setEndurance(7);
        t.setCourage(4);
        t.setSkill(1);
        t.setRank(6);
        t.setTeam(Team.D);
        Validator v = new ValidatorImpl();
        assert v.validate(t).isValid();
    }
}
