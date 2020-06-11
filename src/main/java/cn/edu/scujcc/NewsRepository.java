package cn.edu.scujcc;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends MongoRepository<News,String>{

	List<News> findByTitle(String title);


	
}
