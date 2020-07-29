package com.empresa.workshopmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.empresa.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{
    //MongoRepository<Classe, tipo do Id dela>	
}
