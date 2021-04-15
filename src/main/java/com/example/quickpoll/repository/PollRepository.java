package com.example.quickpoll.repository;

import com.example.quickpoll.domain.Poll;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PollRepository extends PagingAndSortingRepository<Poll, Long> {

}
