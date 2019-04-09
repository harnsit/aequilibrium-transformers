package com.harpreet;

import com.harpreet.dao.TransformerDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Objects;

@RunWith(MockitoJUnitRunner.class)
public class TransformerControllerTest {

    @InjectMocks
    TransformerController controller;
    @Mock
    private TransformerDAO transformerDAO;

    @Test
    public void listAPIShouldWork() {
        Mockito.when(
                transformerDAO.getTransformers()
        ).thenReturn(new ArrayList<>());
        assert Objects.requireNonNull(
                controller.listTransformers().getBody()
        ).getTransformers().isEmpty();
    }
}
