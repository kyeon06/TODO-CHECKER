package com.yuyun.todochecker.quote.repository;

import com.yuyun.todochecker.quote.domain.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteRepository extends JpaRepository<Quote, Long> {
    
}
