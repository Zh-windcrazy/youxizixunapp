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
	 * ��ȡ������Ϸ
	 */
	@GetMapping()
	public List<Game> getAllChannels() {
    	logger.info("���ڶ�ȡ������Ϸ��Ϣ...");
    	List<Game> results = service.getAllGames();
    	
		return results;
	}
	
	/**
	 * ��ȡָ����Ϸ
	 */
	@GetMapping("/{id}")
	public Game getGame(@PathVariable String id) {
		
		Game c = service.getGame(id);
		if(c!=null) {
			return c;
		}else {
			logger.error("�Ҳ���ָ����Ϸ��");
			return null;
		}
	}
	
	/**
	 * ɾ��ָ����Ϸ
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteGame(@PathVariable String id){
		System.out.println("����ɾ����Ϸ��id="+id);
		boolean result = this.service.deleteGame(id);
		if(result) {
			return ResponseEntity.ok().body("ɾ���ɹ�");
		}else {
			return ResponseEntity.ok().body("ɾ��ʧ��");
		}
	}
	
	/**
	 * �½�һ����Ϸ
	 */
	@PostMapping
	public Game createGame(@RequestBody Game c) {
		System.out.println("�����½���Ϸ����Ϸ���ݣ�"+c);
		Game saved=service.createGame(c);
		return saved;
	}
	@PutMapping
	public Game updateGame(@RequestBody Game c) {
		System.out.println("����������Ϸ����Ϸ���ݣ�"+c);
		Game saved=service.updateGame(c);
		return saved;
	}
	@GetMapping("/t/{title}")
	public List<Game> searchByTitle(@PathVariable String title){
		return service.searchbiaoti(title);
	}
}
