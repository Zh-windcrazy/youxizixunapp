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
 * Ƶ���ӿڣ��ṩ�ͻ��˷��ʵ���ڡ�
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
		 * ��ȡ����Ƶ��
		 * @return ����Ƶ��������
		 */
	    @GetMapping()
		public List<News> getAllNews() {
	    	logger.info("���ڻ�ȡ��������..");
	    	List<News> results = service.getAllNews();
	    	
			return results;
		}
		
		/**
		 * ��ȡһ��ָ��Ƶ����JSON����
		 * @param id ָ��Ƶ���ı��
		 * @return id����Ƶ����JSON����
		 */
		@GetMapping("/{id}")
		public News getNews(@PathVariable String id) {
			
			News c = service.getNews(id);
			if(c!=null) {
				return c;
			}else {
				logger.error("�Ҳ���ָ�����š�");
				return null;
			}
		}
		
		/**
		 * ɾ��һ��ָ��Ƶ��
		 * @param id ��ɾ��Ƶ���ı��	
		 * @return �ɹ���ʧ�ܵ���Ϣ
		 */
		@DeleteMapping("/{id}")
		public ResponseEntity<String> deleteNews(@PathVariable String id){
			System.out.println("����ɾ����id="+id);
			boolean result = this.service.deleteNews(id);
			if(result) {
				return ResponseEntity.ok().body("ɾ���ɹ�");
			}else {
				return ResponseEntity.ok().body("ɾ��ʧ��");
			}
		}
		
		/**
		 * �½�һ��Ƶ��
		 * 
		 * @param c ���½�Ƶ��������
		 * @return ������Ƶ������
		 */
		@PostMapping
		public News createNews(@RequestBody News c) {
			System.out.println("�����½������ݣ�"+c);
			News saved=service.createNews(c);
			return saved;
		}
		@PutMapping
		public News updateChannel(@RequestBody News c) {
			System.out.println("��������Ƶ����Ƶ�����ݣ�"+c);
			News saved=service.updateNews(c);
			return saved;
		}
		@GetMapping("/t/{title}")
		public List<News> searchByTitle(@PathVariable String title){
			return service.searchbiaoti(title);
		}
}