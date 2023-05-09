package com.yuyun.todochecker.quote.controller;

import com.yuyun.todochecker.quote.domain.Quote;
import com.yuyun.todochecker.quote.dto.QuoteDto;
import com.yuyun.todochecker.quote.repository.QuoteRepository;
import com.yuyun.todochecker.quote.service.QuoteService;
import com.yuyun.todochecker.todo.dto.ProgressDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class QuoteController {

    @Autowired
    private QuoteService quoteService;

    // 명언 랜덤 1개 조회
    @RequestMapping(value = "/quote", method = RequestMethod.GET)
    public ResponseEntity<QuoteDto> getQuoteOne(){
        QuoteDto res = quoteService.getQuote();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
