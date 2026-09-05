package org.haven.havenapi.controller;

import lombok.RequiredArgsConstructor;
import org.haven.havenapi.model.Quote;
import org.haven.havenapi.service.QuoteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/quotes")
public class QuoteController {
    private final QuoteService quoteService;

    @GetMapping("/today")
    public Quote getToday() {
        return quoteService.getTodayQuote();
    }
}
