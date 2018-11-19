package cz.upce.diplomovaprace.controller;

import cz.upce.diplomovaprace.entity.Challenge;
import cz.upce.diplomovaprace.entity.ChallengeResult;
import cz.upce.diplomovaprace.constants.ActiveTabConstants;
import cz.upce.diplomovaprace.repository.ChallengeRepository;
import cz.upce.diplomovaprace.repository.ChallengeResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes(ActiveTabConstants.ACTIVE_TAB)
public class OperatorController {

    @Autowired
    ChallengeRepository challengeRepository;

    @Autowired
    ChallengeResultRepository challengeResultRepository;

    @GetMapping("/operator")
    public ModelAndView renderOperatorChallenges(Map<String, Object> model) {

        // find challenges with different scores

        //for each Challenge
        //       find Result v challengeresult repku
        //      list<resultu pro tu challenge>
        //check if score of every result challenge is same if true add to list to decide

        List<Challenge> differentScoreChallenges = new ArrayList<>();
        List<Challenge> withoutScoreChallenges = new ArrayList<>();
        for (Challenge challenge : challengeRepository.findAll()) {
            List<ChallengeResult> challengeResults = challengeResultRepository.findByChallengeByChallengeId(challenge);
            int scoreWinner = challengeResults.get(0).getScoreWinner();
            int scoreLoser = challengeResults.get(0).getScoreDefeated();
            LocalDate date = LocalDate.now().minusDays(10);
            Timestamp challengeEndDate = challenge.getEnd();
            for (ChallengeResult challengeResult : challengeResults) {
                if (challengeResult.getScoreWinner() != null && challengeResult.getScoreDefeated() != null) {
                    if (challengeResult.getScoreWinner() != scoreWinner || challengeResult.getScoreDefeated() != scoreLoser) {
                        differentScoreChallenges.add(challenge);
                        break;
                    }

                    // Challenges with end date 10 dni po konci s neukoncenym skore
                    if (challengeEndDate.before(Date.valueOf(date)) && (challengeResult.getScoreWinner() == null || challengeResult.getScoreDefeated() == null)) {
                        withoutScoreChallenges.add(challenge);
                    }
                }
            }
        }


        model.put("challenges", differentScoreChallenges);
        model.put("challengesWithoutScore", withoutScoreChallenges);

        return new ModelAndView("operator/challenges", model);
    }
}
