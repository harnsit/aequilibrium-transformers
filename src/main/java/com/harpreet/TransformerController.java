package com.harpreet;

import com.harpreet.com.harpreet.response.ListResponse;
import com.harpreet.dao.TransformerDAO;
import com.harpreet.dto.BattleResult;
import com.harpreet.dto.Transformer;
import com.harpreet.validate.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 *
 */
@RestController
public class TransformerController {

    private static final String STATUS_DONE = "{\"status\":\"done\"}";

    @Autowired
    public void setTransformerDAO(TransformerDAO transformerDAO) {
        this.transformerDAO = transformerDAO;
    }

    @Autowired
    public void setTransformerValidator(Validator<Transformer> transformerValidator) {
        this.transformerValidator = transformerValidator;
    }

    private TransformerDAO transformerDAO;


    private Validator<Transformer> transformerValidator;

    @RequestMapping(value = "/transformer", method = RequestMethod.GET)
    public ResponseEntity<Transformer> getTransformer(@RequestParam UUID id) {
        Transformer transformer = transformerDAO.getTransformer(id);
        if (transformer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(transformer, HttpStatus.OK);
    }

    @RequestMapping(value = "/transformer", consumes = {"application/json"}, method = RequestMethod.POST)
    public ResponseEntity<String> createTransformer(@RequestBody Transformer transformer) {
        Validator.ValidationResult validationResult = transformerValidator.validate(transformer);
        if (!validationResult.isValid())
            return new ResponseEntity<>(validationResult.getMessage(), HttpStatus.BAD_REQUEST);
        transformerDAO.createTransformer(transformer);
        return new ResponseEntity<>(STATUS_DONE, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/transformer", consumes = {"application/json"}, method = RequestMethod.PUT)
    public ResponseEntity<String> updateTransformer(@RequestBody Transformer transformer) {
        try {
            Validator.ValidationResult validationResult = transformerValidator.validate(transformer);
            if (!validationResult.isValid())
                return new ResponseEntity<>(validationResult.getMessage(), HttpStatus.BAD_REQUEST);
            transformerDAO.updateTransformer(transformer);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(STATUS_DONE, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/transformer", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteTransformer(@RequestParam UUID id) {
        transformerDAO.deleteTransformer(id);
        return new ResponseEntity<>(STATUS_DONE, HttpStatus.OK);
    }

    @RequestMapping(value = "/transformers")
    public ResponseEntity<ListResponse> listTransformers() {
        return new ResponseEntity<>(
                ListResponse.builder()
                        .transformers(transformerDAO.getTransformers())
                        .build(),
                HttpStatus.OK
        );
    }

    @RequestMapping(value = "/battle")
    public ResponseEntity<BattleResult> battle(@RequestParam List<UUID> ids) {
        List<Transformer> transformers = ids.stream().map(
                i -> transformerDAO.getTransformer(i)
        ).collect(Collectors.toList());
        if (transformers.stream().anyMatch(Objects::isNull))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Battle battle = new Battle(transformers);
        return new ResponseEntity<>(battle.fight(), HttpStatus.OK);
    }


}