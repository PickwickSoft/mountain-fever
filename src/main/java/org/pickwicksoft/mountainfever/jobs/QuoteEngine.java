package org.pickwicksoft.mountainfever.jobs;

import org.pickwicksoft.mountainfever.models.CurrentQuote;
import org.pickwicksoft.mountainfever.models.Quote;
import org.pickwicksoft.mountainfever.repositories.CurrentQuoteRepository;
import org.pickwicksoft.mountainfever.repositories.QuoteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

@Service
@Transactional
public class QuoteEngine {
    @Autowired
    private QuoteRepository quoteRepository;
    @Autowired
    private CurrentQuoteRepository currentQuoteRepository;

    private static Long getRandom(List<Long> list, Long exclude) {
        int rnd = new Random().nextInt(list.size());
        Long newID = list.get(rnd);
        if (Objects.equals(newID, exclude)) {
            // Quote is already current
            getRandom(list, exclude);
        }
        return newID;
    }

    @Scheduled(fixedDelayString = "${app.quotes.refresh-interval}")
    public void updateQuote() {
        Long currentQuoteID = (long) -1;
        try {
            currentQuoteID = currentQuoteRepository.findAll().get(0).getId();
        } catch (Exception ignored) {
        }
        List<Long> quotesIDList = quoteRepository.findAll().stream().map(Quote::getQuote_id).toList();

        Long newQuoteID = getRandom(quotesIDList, currentQuoteID);

        updateQuoteByID(currentQuoteID);

        CurrentQuote newQuote = new CurrentQuote();
        newQuote.setId(newQuoteID);
        setNewCurrentQuote(newQuote);

        Logger logger = Logger.getLogger("");
        logger.log(new LogRecord(Level.INFO, "Set new current quote"));
    }

    private void setNewCurrentQuote(CurrentQuote newQuote) {
        currentQuoteRepository.deleteAll();
        currentQuoteRepository.saveAndFlush(newQuote);
    }

    private void updateQuoteByID(long id) {
        Quote currentQuote = quoteRepository.getById(id);
        Quote newQuote = new Quote();
        newQuote.setUsed_times(currentQuote.getUsed_times() + 1);
        newQuote.setText(currentQuote.getText());
        newQuote.setAuthor_name(currentQuote.getAuthor_name());
        BeanUtils.copyProperties(newQuote, currentQuote, "quote_id");
        quoteRepository.saveAndFlush(currentQuote);
    }

}
