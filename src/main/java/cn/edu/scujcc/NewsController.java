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

/**
 * 频道接口，提供客户端访问的入口。
 * @author asus
 *
 */
@RestController
@RequestMapping("/news")
public class NewsController {
	
		public static final Logger logger=LoggerFactory.getLogger(NewsController.class);
	
		@Autowired
		private NewService service;
		/**
		 * 获取所有频道
		 * @return 所有频道的数组
		 */
	    @GetMapping()
		public List<News> getAllNews() {
	    	logger.info("正在获取所有新闻..");
	    	List<News> results = service.getAllNews();
	    	
			return results;
		}
		
		/**
		 * 获取一个指定频道的JSON数据
		 * @param id 指定频道的编号
		 * @return id对象频道的JSON数据
		 */
		@GetMapping("/{id}")
		public News getNews(@PathVariable String id) {
			
			News c = service.getNews(id);
			if(c!=null) {
				return c;
			}else {
				logger.error("找不到指定新闻。");
				return null;
			}
		}
		
		/**
		 * 删除一个指定频道
		 * @param id 待删除频道的编号	
		 * @return 成功或失败的消息
		 */
		@DeleteMapping("/{id}")
		public ResponseEntity<String> deleteNews(@PathVariable String id){
			System.out.println("即将删除，id="+id);
			boolean result = this.service.deleteNews(id);
			if(result) {
				return ResponseEntity.ok().body("删除成功");
			}else {
				return ResponseEntity.ok().body("删除失败");
			}
		}
		
		/**
		 * 新建一个频道
		 * 
		 * @param c 待新建频道的数据
		 * @return 保存后的频道数据
		 */
		@PostMapping
		public News createNews(@RequestBody News c) {
			System.out.println("即将新建，数据："+c);
			News saved=service.createNews(c);
			return saved;
		}
		@PutMapping
		public News updateChannel(@RequestBody News c) {
			System.out.println("即将更新频道，频道数据："+c);
			News saved=service.updateNews(c);
			return saved;
		}
		@GetMapping("/t/{title}")
		public List<News> searchByTitle(@PathVariable String title){
			return service.searchbiaoti(title);
		}
}