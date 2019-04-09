package com.harpreet.dao;

import com.harpreet.dto.Transformer;

import java.util.List;
import java.util.UUID;

public interface TransformerDAO {

    void createTransformer(Transformer transformer);

    int getCount();

    List<Transformer> getTransformers();

    void updateTransformer(Transformer transformer);

    void deleteTransformer(UUID id);

    Transformer getTransformer(UUID id);
}
