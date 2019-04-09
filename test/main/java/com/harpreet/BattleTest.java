package com.harpreet;

import com.harpreet.dto.BattleResult;
import com.harpreet.dto.Team;
import com.harpreet.dto.Transformer;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BattleTest {
    private List<Transformer> configureTransformers(){
        Transformer firstT = new Transformer();
        firstT.setRank(1);

        firstT.setTeam(Team.D);
        firstT.setId(UUID.randomUUID());
        firstT.setName("1DECEPTICON");
        firstT.setSkill(5);
        firstT.setRank(7);

        Transformer secondT = new Transformer();
        secondT.setTeam(Team.D);
        secondT.setId(UUID.randomUUID());
        secondT.setName("2DECEPTICON");
        secondT.setStrength(10);
        secondT.setCourage(10);
        secondT.setFirepower(1);
        secondT.setEndurance(1);
        secondT.setRank(10);

        Transformer thirdT = new Transformer();
        thirdT.setTeam(Team.A);
        thirdT.setId(UUID.randomUUID());
        //thirdT.setName("Optimus Prime");
        thirdT.setSkill(1);
        thirdT.setName("1AUTOBOT");
        thirdT.setStrength(10);
        thirdT.setRank(6);

        Transformer fourthT = new Transformer();
        fourthT.setTeam(Team.A);
        fourthT.setId(UUID.randomUUID());
        //fourthT.setName("Predaking");
        fourthT.setStrength(6);
        fourthT.setCourage(6);
        fourthT.setName("2AUTOBOT");
        fourthT.setFirepower(10);
        fourthT.setEndurance(10);
        fourthT.setRank(9);

        List<Transformer> listT = new ArrayList<Transformer>();
        listT.add(firstT);
        listT.add(secondT);
        listT.add(thirdT);
        listT.add(fourthT);
        return listT;

    }
    @Test
    public void testWhoWins(){
        List<Transformer> listT = configureTransformers();
        Battle battle = new Battle(listT);
        BattleResult battleResult = battle.fight();
        assert battleResult.getNumOfBattles() ==2;
        assert battleResult.getWinningTeam() == Team.D;
    }
}
