package se.elias.postgresassignment.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import se.elias.postgresassignment.model.*;
import se.elias.postgresassignment.service.MatchService;

import java.util.List;

@Controller
public class HomeController {

    private final MatchRepository matchRepository;
    private final TeamRepository teamRepository;
    private final MatchService matchService;

    @Autowired
    public HomeController(MatchRepository matchRepository, TeamRepository teamRepository, MatchService matchService) {
        this.matchRepository = matchRepository;
        this.teamRepository = teamRepository;
        this.matchService = matchService;
    }

    @GetMapping("/")
    public String schedulePage(Model model) {
        List<Match> matches = (List<Match>) matchRepository.findAll();
        model.addAttribute("matches", matches);
        return "home";
    }

    @GetMapping("/admin")
    public String adminPage(Model model) {
        model.addAttribute("team", new Team());
        model.addAttribute("match", new Match());
        model.addAttribute("regions", Region.values());
        model.addAttribute("teams", teamRepository.findAll());
        return "admin";
    }

    @PostMapping("/admin/add-game")
    public String addTeam(@ModelAttribute("team") Team team) {
        teamRepository.save(team);
        return "redirect:/admin";
    }

    @PostMapping("/add-match")
    public String addMatch(@ModelAttribute("match") Match match, Model model) {
        try{
            matchService.createMatch(match);
        } catch (IllegalArgumentException e) {
            model.addAttribute("match", match);
            model.addAttribute("team", new Team());
            model.addAttribute("teams", teamRepository.findAll());
            model.addAttribute("error", e.getMessage());
            return "admin";
        }

        matchRepository.save(match);
        return "redirect:/admin";
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        return "login";
    }



}
