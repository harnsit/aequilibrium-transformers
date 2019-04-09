package com.harpreet;

import com.harpreet.dto.BattleResult;
import com.harpreet.dto.Team;
import com.harpreet.dto.Transformer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Battle {
    List<Transformer> autobots = new ArrayList<>();
    List<Transformer> decepticons = new ArrayList<>();
    List<Transformer> survivors = new ArrayList<>();

    /**
     * @param transformers
     */
    Battle(List<Transformer> transformers) {
        for(Transformer transformer : transformers){
            if (transformer.getTeam().equals(Team.A))
                autobots.add(transformer);
            else if (transformer.getTeam().equals(Team.D))
                decepticons.add(transformer);
            else
                throw new IllegalArgumentException("Transformer " +
                        transformer.toString() + " is not AUTOBOT or DECEPTICON");
        }

        Collections.sort(autobots);
        Collections.sort(decepticons);
        superBots.add("Optimus Prime");
        superBots.add("Predaking");
    }

    private Set<String> superBots = new HashSet<>();

    /**
     * @param autobot    first transformer
     * @param decepticon second transformer
     * @return null if it was Optimus Prime vs Predaking or winning team otherwise
     */
    private Team whoWins(Transformer autobot, Transformer decepticon) {

        if (superBots.contains(autobot.getName()) && superBots.contains(decepticon.getName())) {
            return null;
        }
        if (superBots.contains(autobot.getName()))
            return autobot.getTeam();
        else if (superBots.contains(decepticon.getTeam()))
            return decepticon.getTeam();

        if (autobot.canScare(decepticon))
            return autobot.getTeam();

        if (decepticon.canScare(autobot))
            return decepticon.getTeam();

        if (autobot.hasBetterRatingThan(decepticon))
            return autobot.getTeam();

        if (decepticon.hasBetterRatingThan(autobot))
            return decepticon.getTeam();

        return Team.None;
    }

    private List<String> getNames(List<Transformer> transformers){
        return transformers.stream().map(t->t.getName()).collect(Collectors.toList());
    }

    BattleResult fight() {
        int autobotsLength = autobots.size();
        int decepticonsLength = decepticons.size();
        BattleResult.BattleResultBuilder resultBuilder = BattleResult.builder();

        int numMaxBattles = autobotsLength > decepticonsLength ? decepticonsLength : autobotsLength;
        int autobotsScore = 0;
        int decepticonsScore = 0;

        int battleCount = 0;
        for (; battleCount < numMaxBattles; battleCount++) {
            Team winner = whoWins(autobots.get(battleCount), decepticons.get(battleCount));
            if (winner == null) {
                resultBuilder.numOfBattles(battleCount + 1);
                resultBuilder.survivorTransformers(new ArrayList<>());
                resultBuilder.winnerTransformers(new ArrayList<>());
                return resultBuilder.build();
            }
            if (winner.equals(Team.A)) {
                autobotsScore++;
                survivors.add(autobots.get(battleCount));
            } else if (winner.equals(Team.D)) {
                decepticonsScore++;
                survivors.add(decepticons.get(battleCount));
            } else {
                // Both have died.
            }
        }
        if (autobotsScore > decepticonsScore) {
            resultBuilder.winningTeam(Team.A);
            resultBuilder.winnerTransformers(getNames(autobots));
            List<Transformer> survivingDecepticons = survivors.stream()
                    .filter(it -> Team.D.equals(it.getTeam()))
                    .collect(Collectors.toList());
            if (battleCount < decepticonsLength) {
                survivingDecepticons.addAll(decepticons.subList(battleCount, decepticonsLength));
            }
            resultBuilder.survivorTransformers(getNames(survivingDecepticons));
            resultBuilder.numOfBattles(battleCount);
            return resultBuilder.build();
        }

        else if (autobotsScore < decepticonsScore) {
            resultBuilder.winningTeam(Team.D);
            resultBuilder.winnerTransformers(getNames(decepticons));
            List<Transformer> survivingAutobots = survivors.stream()
                    .filter(it -> Team.A.equals(it.getTeam()))
                    .collect(Collectors.toList());
            if (battleCount < autobotsLength) {
                survivingAutobots.addAll(autobots.subList(battleCount, autobotsLength));
            }
            resultBuilder.survivorTransformers(getNames(survivingAutobots));
            resultBuilder.numOfBattles(battleCount);
            return resultBuilder.build();
        }

        else {
            resultBuilder.winnerTransformers(new ArrayList<>());
            resultBuilder.numOfBattles(battleCount);
            resultBuilder.survivorTransformers(new ArrayList<>());
            return resultBuilder.build();
        }
    }

}
