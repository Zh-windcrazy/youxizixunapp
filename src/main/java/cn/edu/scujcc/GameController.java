package cn.edu.scujcc;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;







@RestController
@RequestMapping("/game")
public class GameController {
	public static final Logger logger=LoggerFactory.getLogger(GameController.class);
	
	@Autowired
	private GameService service;
	
	/**
	 * 获取所有游戏
	 */
	@GetMapping()
	public List<Game> getAllChannels() {
    	logger.info("正在读取所有游戏信息...");
    	List<Game> results = service.getAllGames();
    	
		return results;
	}
	
	/**
	 * 获取指定游戏
	 */
	@GetMapping("/{id}")
	public Game getGame(@PathVariable String id) {
		
		Game c = service.getGame(id);
		if(c!=null) {
			return c;
		}else {
			logger.error("找不到指定游戏。");
			return null;
		}
	}
	
	/**
	 * 删除指定游戏
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteGame(@PathVariable String id){
		System.out.println("即将删除游戏，id="+id);
		boolean result = this.service.deleteGame(id);
		if(result) {
			return ResponseEntity.ok().body("删除成功");
		}else {
			return ResponseEntity.ok().body("删除失败");
		}
	}
	
	/**
	 * 新建一个游戏
	 */
	@PostMapping
	public Game createGame(@RequestBody Game c) {
		System.out.println("即将新建游戏，游戏数据："+c);
		Game saved=service.createGame(c);
		return saved;
	}
	@PutMapping
	public Game updateGame(@RequestBody Game c) {
		System.out.println("即将更新游戏，游戏数据："+c);
		Game saved=service.updateGame(c);
		return saved;
	}
	@GetMapping("/t/{title}")
	public List<Game> searchByTitle(@PathVariable String title){
		return service.searchbiaoti(title);
	}
}
