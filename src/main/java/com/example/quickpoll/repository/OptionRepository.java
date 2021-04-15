package com.example.quickpoll.repository;

import com.example.quickpoll.domain.Option;
import org.springframework.data.repository.CrudRepository;


public interface OptionRepository extends CrudRepository<Option, Long> {

}
