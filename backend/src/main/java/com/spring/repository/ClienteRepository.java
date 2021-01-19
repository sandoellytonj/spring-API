package com.spring.repository;

import com.spring.models.Cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;




public interface ClienteRepository extends MongoRepository <Cliente, String>, PagingAndSortingRepository<Cliente, String> {

    Page<Cliente> findAllByIdGreaterThan(String id, Pageable pageable);
}
