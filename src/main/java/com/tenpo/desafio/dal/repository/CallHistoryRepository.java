package com.tenpo.desafio.dal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tenpo.desafio.domain.entities.CallHistory;

public interface CallHistoryRepository extends JpaRepository<CallHistory, Long> {

}
