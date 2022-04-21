package org.pickwicksoft.mountainfever.controllers;

import org.jetbrains.annotations.NotNull;
import org.pickwicksoft.mountainfever.models.Quote;
import org.pickwicksoft.mountainfever.repositories.CurrentQuoteRepository;
import org.pickwicksoft.mountainfever.repositories.QuoteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quotes")
public class QuotesController {
    @Autowired
    private QuoteRepository quoteRepository;
    @Autowired
    private CurrentQuoteRepository currentQuoteRepository;

    @GetMapping
    @RequestMapping("/current")
    public Quote current() {
        return quoteRepository.getById(currentQuoteRepository.findAll().get(0).getId());
    }

    @GetMapping
    @RequestMapping("")
    public List<Quote> get() {
        return quoteRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Quote getQuote(@PathVariable Long id) {
        return quoteRepository.getById(id);
    }

    @PostMapping
    public Quote createQuote(@RequestBody final @NotNull Quote quote) {
        return quoteRepository.saveAndFlush(quote);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void deleteQuote(@PathVariable Long id) {
        quoteRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Quote update(@PathVariable Long id, @RequestBody @NotNull Quote quote) {
        Quote existingQuote = quoteRepository.getById(id);
        BeanUtils.copyProperties(quote, existingQuote, "quote_id");
        return quoteRepository.saveAndFlush(existingQuote);
    }
}
