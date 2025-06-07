package se.elias.postgresassignment.service;


import org.springframework.stereotype.Service;
import se.elias.postgresassignment.model.Match;
import se.elias.postgresassignment.model.MatchRepository;

@Service
public class MatchService {

    private final MatchRepository matchRepository;

    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public Match createMatch(Match match) {
        if (match.getTeam1().getId().equals(match.getTeam2().getId())) {
            throw new IllegalArgumentException("Team 1 and Team 2 must be different");
        }
        return matchRepository.save(match);
    }
}
