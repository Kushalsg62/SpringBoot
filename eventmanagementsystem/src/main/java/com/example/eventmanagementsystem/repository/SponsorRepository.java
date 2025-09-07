package com.example.eventmanagementsystem.repository;

import com.example.eventmanagementsystem.model.Sponsor;
import com.example.eventmanagementsystem.model.Event;

import java.util.ArrayList;
import java.util.List;

public interface SponsorRepository {
    ArrayList<Sponsor> getSponsors();
    Sponsor getSponsorById(int sponsorId);
    Sponsor addSponsor(Sponsor sponsor);
    Sponsor updateSponsor(int sponsorId, Sponsor sponsor);
    void deleteSponsor(int sponsorId);
    List<Event> getSponsorEvents(int sponsorId);
}