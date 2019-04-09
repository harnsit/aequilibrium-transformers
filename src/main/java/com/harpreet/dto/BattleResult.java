package com.harpreet.dto;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Builder
@Data
public class BattleResult{
    private Team winningTeam;
    private List<String> winnerTransformers;
    private List<String> survivorTransformers;
    private int numOfBattles;
}
