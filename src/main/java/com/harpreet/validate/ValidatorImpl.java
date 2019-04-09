package com.harpreet.validate;

import com.harpreet.dto.Transformer;
import org.springframework.stereotype.Component;

@Component
public class ValidatorImpl implements Validator<Transformer> {

    private boolean inRange(int input) {
        return input >= 1 && input <= 10;
    }

    @Override
    public ValidationResult validate(Transformer input) {
        ValidationResult.ValidationResultBuilder builder = ValidationResult.builder();

        if (input.getTeam() == null) {
            builder.isValid(false);
            builder.message("no team found");
            return builder.build();
        }


        if (!inRange(input.getStrength())) {
            builder.isValid(false);
            builder.message("strength out of range");
            return builder.build();
        }

        if (!inRange(input.getIntelligence())) {
            builder.isValid(false);
            builder.message("intelligence out of range");
            return builder.build();
        }

        if (!inRange(input.getSpeed())) {
            builder.isValid(false);
            builder.message("speed out of range");
            return builder.build();
        }

        if (!inRange(input.getEndurance())) {
            builder.isValid(false);
            builder.message("endurance out of range");
            return builder.build();
        }

        if (!inRange(input.getRank())) {
            builder.isValid(false);
            builder.message("rank out of range");
            return builder.build();
        }

        if (!inRange(input.getCourage())) {
            builder.isValid(false);
            builder.message("courage out of range");
            return builder.build();
        }

        if (!inRange(input.getFirepower())) {
            builder.isValid(false);
            builder.message("firepower out of range");
            return builder.build();
        }

        if (!inRange(input.getSkill())) {
            builder.isValid(false);
            builder.message("skill out of range");
            return builder.build();
        }

        builder.isValid(true);
        return builder.build();
    }
}
