package com.yuyun.todochecker.quote.service;

import com.yuyun.todochecker.quote.domain.Quote;
import com.yuyun.todochecker.quote.dto.QuoteDto;
import com.yuyun.todochecker.quote.mapper.QuoteMapper;
import com.yuyun.todochecker.quote.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class QuoteService {

    @Autowired
    private QuoteRepository quoteRepository;

    public QuoteDto getQuote(){
        List<Quote> quotes = quoteRepository.findAll();
        int index = new Random().nextInt(quotes.size());
        return QuoteMapper.convertToDto(quotes.get(index));
    }
}
