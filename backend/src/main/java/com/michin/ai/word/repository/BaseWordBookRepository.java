package com.michin.ai.word.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.michin.ai.word.model.Basewordbook;

@Repository
public interface BaseWordBookRepository extends MongoRepository<Basewordbook, ObjectId>{

}