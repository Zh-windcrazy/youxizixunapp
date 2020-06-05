package cn.edu.scujcc;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;





@Repository
public interface GameRepository extends MongoRepository<Game,String>{
	List<Game> findByTitle(String title);

}
