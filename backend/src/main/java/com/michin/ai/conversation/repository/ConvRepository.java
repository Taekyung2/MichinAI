package com.michin.ai.conversation.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.michin.ai.conversation.model.Conversation;

@Repository
public interface ConvRepository extends JpaRepository<Conversation, Long>, ConvRepositoryCustom {

	List<Conversation> findByDate(LocalDate date);
}
