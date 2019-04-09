package com.harpreet.com.harpreet.response;

import com.harpreet.dto.Team;
import com.harpreet.dto.Transformer;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Builder
public class ListResponse {
    private List<Transformer> transformers;

    public List<TransformerRow> getTransformers() {
        return transformers.parallelStream().map(tran ->
                TransformerRow.builder().id(tran.getId())
                        .name(tran.getName())
                        .team(tran.getTeam()).build()
        ).collect(Collectors.toList());
    }

    @Builder
    private static class TransformerRow {
        @Getter
        private UUID id;
        @Getter
        private String name;
        @Getter
        private Team team;
    }

}

