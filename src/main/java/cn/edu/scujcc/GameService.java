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
	 * 获取一个游戏
	 */
	public Game getGame(String gameId) {
		logger.debug("准备从数据库读取数据"+gameId);
		Optional<Game> result = repo.findById(gameId);
		
		if(result.isPresent()) {
			return result.get();
		}else {
		return null;
		}
	}
	
	/**
	 * 获取所有游戏
	 */
	public List<Game> getAllGames(){
		logger.debug("准备从数据库读取所有游戏信息...");
		return repo.findAll();
	}
	
	/**
	 * 删除指定游戏
	 */
	public boolean deleteGame(String gameId) {
		boolean result = true;
		repo.deleteById(gameId);
		
		return result;
	}
	
	/**
	 * 更新一个游戏
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
	 * 创建
	 */
	public Game createGame(Game c) {
		return repo.save(c);
		
	}
	
	public List<Game> searchbiaoti(String title){
		return repo.findByTitle(title);
	}
}
