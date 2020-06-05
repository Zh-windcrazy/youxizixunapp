package cn.edu.scujcc;

import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ch.qos.logback.classic.Logger;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class GameService {
	@Autowired
	private GameRepository repo;
	public static final Logger logger = (Logger) LoggerFactory.getLogger(GameService.class);
	
	/**
	 * ��ȡһ����Ϸ
	 */
	public Game getGame(String gameId) {
		logger.debug("׼�������ݿ��ȡ����"+gameId);
		Optional<Game> result = repo.findById(gameId);
		
		if(result.isPresent()) {
			return result.get();
		}else {
		return null;
		}
	}
	
	/**
	 * ��ȡ������Ϸ
	 */
	public List<Game> getAllGames(){
		logger.debug("׼�������ݿ��ȡ������Ϸ��Ϣ...");
		return repo.findAll();
	}
	
	/**
	 * ɾ��ָ����Ϸ
	 */
	public boolean deleteGame(String gameId) {
		boolean result = true;
		repo.deleteById(gameId);
		
		return result;
	}
	
	/**
	 * ����һ����Ϸ
	 */
	public Game updateGame(Game c) {
		Game saved = getGame(c.getId());
		if (saved != null) {
			if (c.getTitle() != null) {
				saved.setTitle(c.getTitle());
			}
			if (c.getPrice() != null) {
				saved.setPrice(c.getPrice());
			}
					
		}
		if(c.getCover()!=null) {
			saved.setCover(c.getCover());
		}
		return repo.save(saved);
	}
	
	/**
	 * ����
	 */
	public Game createGame(Game c) {
		return repo.save(c);
		
	}
	
	public List<Game> searchbiaoti(String title){
		return repo.findByTitle(title);
	}
}
