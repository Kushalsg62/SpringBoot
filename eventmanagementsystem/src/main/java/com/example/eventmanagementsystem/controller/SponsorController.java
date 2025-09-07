package com.example.eventmanagementsystem.controller;

import com.example.eventmanagementsystem.model.Sponsor;
import com.example.eventmanagementsystem.model.Event;
import com.example.eventmanagementsystem.service.SponsorJpaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class SponsorController {
    @Autowired
    private SponsorJpaService sponsorJpaService;

    @GetMapping("/events/sponsors")
    public ArrayList<Sponsor> getSponsors() {
        return sponsorJpaService.getSponsors();
    }

    @GetMapping("/events/sponsors/{sponsorId}")
    public Sponsor getSponsorById(@PathVariable("sponsorId") int sponsorId) {
        return sponsorJpaService.getSponsorById(sponsorId);
    }

    @PostMapping("/events/sponsors")
    public Sponsor addSponsor(@RequestBody Sponsor sponsor) {
        return sponsorJpaService.addSponsor(sponsor);
    }

    @PutMapping("/events/sponsors/{sponsorId}")
    public Sponsor updateSponsor(@PathVariable("sponsorId") int sponsorId, @RequestBody Sponsor sponsor) {
        return sponsorJpaService.updateSponsor(sponsorId, sponsor);
    }

    @DeleteMapping("/events/sponsors/{sponsorId}")
    public void deleteSponsor(@PathVariable("sponsorId") int sponsorId) {
        sponsorJpaService.deleteSponsor(sponsorId);
    }

    @GetMapping("/sponsors/{sponsorId}/events")
    public List<Event> getSponsorEvents(@PathVariable("sponsorId") int sponsorId) {
        return sponsorJpaService.getSponsorEvents(sponsorId);
    }
}