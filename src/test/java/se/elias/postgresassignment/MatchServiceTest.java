package se.elias.postgresassignment;


import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import se.elias.postgresassignment.model.Match;
import se.elias.postgresassignment.model.Region;
import se.elias.postgresassignment.model.Team;
import se.elias.postgresassignment.model.TeamRepository;
import se.elias.postgresassignment.service.MatchService;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
public class MatchServiceTest {

    @Autowired
    private MatchService matchService;
    @Autowired
    private TeamRepository teamRepository;

    @Test
    public void when_same_team_should_throw_exception(){
        Team team = new Team();
        team.setName("GenG");
        team.setRegion(Region.LCK);
        teamRepository.save(team);

        Match match = new Match();
        match.setTeam1(team);
        match.setTeam2(team);
        match.setScheduledTime(LocalDateTime.now());
        match.setRegion(Region.LCK);

        assertThrows(IllegalArgumentException.class, () -> matchService.createMatch(match));
    }

    @Test
    public void when_different_team_shouldnt_throw_exception(){
        Team team1 = new Team();
        team1.setName("BLG");
        team1.setRegion(Region.LPL);
        teamRepository.save(team1);

        Team team2 = new Team();
        team2.setName("JDG");
        team2.setRegion(Region.LPL);
        teamRepository.save(team2);

        Match match = new Match();
        match.setTeam1(team1);
        match.setTeam2(team2);
        match.setScheduledTime(LocalDateTime.now());
        match.setRegion(Region.LPL);

        Match savedMatch = matchService.createMatch(match);
        assertNotNull(savedMatch.getId());
    }
}
