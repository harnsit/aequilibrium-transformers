package com.harpreet.dao;

import com.harpreet.dto.Transformer;

import java.util.UUID;
import java.util.stream.Stream;

public interface TransformerDAO {

    void createTransformer(Transformer transformer);

    int getCount();

    Stream<Transformer> getTransformers();

    void updateTransformer(Transformer transformer);

    void deleteTransformer(UUID id);

    Transformer getTransformer(UUID id);
}
