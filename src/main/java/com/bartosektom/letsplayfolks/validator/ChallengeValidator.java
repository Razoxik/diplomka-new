package com.bartosektom.letsplayfolks.validator;

import com.bartosektom.letsplayfolks.model.ChallengeModel;
import io.micrometer.core.lang.NonNull;
import io.micrometer.core.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDateTime;

@Component
public class ChallengeValidator implements Validator {

    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return ChallengeModel.class.equals(clazz);
    }

    @Override
    public void validate(@Nullable Object target, @NonNull Errors errors) {
        ChallengeModel challengeModel = (ChallengeModel) target;
        assert challengeModel != null;
        LocalDateTime start = challengeModel.getStart();
        LocalDateTime end = challengeModel.getEnd();
        if (start.isBefore(LocalDateTime.now())|| start.isAfter(end)) {
            errors.reject("start", "create.challenge.bad.date");
        }
    }
}
