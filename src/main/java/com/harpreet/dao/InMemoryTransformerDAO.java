package com.harpreet.dao;

import com.harpreet.dto.Transformer;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InMemoryTransformerDAO implements TransformerDAO {

    Map<UUID, Transformer> transformerHashMap = new ConcurrentHashMap<>();

    @Override
    public void createTransformer(Transformer transformer) {
        if (transformer != null) {
            transformer.setId(UUID.randomUUID());
            transformerHashMap.put(transformer.getId(), transformer);
        }
    }

    @Override
    public int getCount() {
        return transformerHashMap.size();
    }

    @Override
    public List<Transformer> getTransformers() {
        return new LinkedList<>(transformerHashMap.values());
    }

    @Override
    public void updateTransformer(Transformer transformer) {
        if (transformer != null) {
            UUID id = transformer.getId();
            if (transformerHashMap.containsKey(id)) {
                transformerHashMap.put(id, transformer);
            } else throw new IllegalArgumentException("Id " + id + " not found");
        }
    }

    @Override
    public void deleteTransformer(UUID id) {
        if (id != null) {
            if (transformerHashMap.containsKey(id)) {
                transformerHashMap.remove(id);
            }
        }
    }

    @Override
    public Transformer getTransformer(UUID id) {
        if (id != null) {
            return transformerHashMap.get(id);
        }
        return null;
    }

}
