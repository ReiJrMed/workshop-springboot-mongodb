package com.empresa.workshopmongo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.empresa.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{
    //MongoRepository<Classe, tipo do Id dela>
	
	//o @Query indica que será gerada uma consulta no MongoDB no formato Json
	//{ <field>: { $regex: /pattern/, $options: '<options>' } }, o '?0' aponta o 1º parâmetro, o '?1' o 2º e assim por diante
	//o 'i' no options indica ignore case, já o regex indica o parâmetro
	@Query("{'title': { $regex: ?0, $options: 'i' } }")
	List<Post> searchTitle(String text);
	
	//Query method
	List<Post> findByTitleContainingIgnoreCase(String text);
	//expressão para o springboot gerar automaticamente o método de busca
	//O "Containing" é uma palavra especial descrita na documentação do springboot voltada a mongoDB
	//ela monta o método para buscar no banco de dados todo post que contenha text enviado no parâmetro no title
	//as palavras findBy também são reservadas, o que vem entre o findBy e oContaining é o nome da varíavel avalidada
	//o "IgnoreCase" é uma palavra reservada que indica que vai desconsiderar letras maúisculas e minúsculas
	
	// { $and: [ { <expression1> }, { <expression2> } , ... , { <expressionN> } ] }
	//{ field: {$gte: value} } -> field maior ou igual a algum valor
	//{ field: { $lte: value} } -> field menor ou igual a algum valor
	//{ $or: [ { <expression1> }, { <expression2> }, ... , { <expressionN> } ] }
	//comments vai nos elementos da list de comentários e busca no seu atributo text com comments.text
	@Query(" { $and: [ { date: {$gte: ?1} }, { date: { $lte: ?2} }, { $or: [ {'title': { $regex: ?0, $options: 'i' } }, {'body': { $regex: ?0, $options: 'i' } }, {'comments.text': { $regex: ?0, $options: 'i' } } ] } ] }")
	List<Post> fullSearch(String text, Date minDate, Date maxDate);
}
