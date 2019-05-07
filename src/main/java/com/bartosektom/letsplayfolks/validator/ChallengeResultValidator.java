package com.bartosektom.letsplayfolks.validator;

import com.bartosektom.letsplayfolks.constants.ResultStateConstants;
import com.bartosektom.letsplayfolks.model.ChallengeResultModel;
import io.micrometer.core.lang.NonNull;
import io.micrometer.core.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ChallengeResultValidator implements Validator {

    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return ChallengeResultModel.class.equals(clazz);
    }

    @Override
    public void validate(@Nullable Object target, @NonNull Errors errors) {
        ChallengeResultModel challengeResultModel = (ChallengeResultModel) target;
        assert challengeResultModel != null;
        String resultState = challengeResultModel.getResultState();
        int winnerScore = challengeResultModel.getWinnerScore();
        int loserScore = challengeResultModel.getLoserScore();

        if (resultState.equals(ResultStateConstants.TIE) && winnerScore != loserScore) {
            errors.reject("resultState", "info.message.badResultState.Draw");
        }
        // When result state is draw and winner score eq loser, we do NOT want to reject value cause winner score is not greater
        // cause in draw both scores are same right so just loserScore >= winnerScore doesnt work
        if (!(resultState.equals(ResultStateConstants.TIE) && winnerScore == loserScore) && loserScore >= winnerScore) {
            errors.reject("resultState", "info.message.badResultState.BadScore");
        }
    }
}
