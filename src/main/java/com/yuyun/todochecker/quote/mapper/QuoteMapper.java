package com.yuyun.todochecker.quote.mapper;

import com.yuyun.todochecker.quote.domain.Quote;
import com.yuyun.todochecker.quote.dto.QuoteDto;
import java.util.List;
import java.util.stream.Collectors;

public class QuoteMapper {

    // Model -> Dto
    public static QuoteDto convertToDto(Quote quote) {
        QuoteDto quoteDto = new QuoteDto();

        quoteDto.setQuoteId(quote.getQuoteId());
        quoteDto.setAuthor(quote.getAuthor());
        quoteDto.setContent(quote.getContent());

        return quoteDto;
    }

    // Dto -> List
    public static List<QuoteDto> convertToDtoList(List<Quote> quoteList) {
        return quoteList.stream().map(QuoteMapper::convertToDto).collect(Collectors.toList());
    }

    // Dto -> Model
    public static Quote convertToModel(QuoteDto quoteDto) {
        Quote quote = new Quote();
        quote.setQuoteId(quoteDto.getQuoteId());
        quote.setAuthor(quoteDto.getAuthor());
        quote.setContent(quoteDto.getContent());
        return quote;
    }


}
