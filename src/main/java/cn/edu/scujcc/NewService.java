package cn.edu.scujcc;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewService {
	@Autowired
	private NewsRepository repo;
	
	
	
	/**
	 * ��ȡһ������
	 * @param id
	 * @return
	 */
	public News getNews(String newsId) {
		Optional<News> result = repo.findById(newsId);
		
		if(result.isPresent()) {
			return result.get();
		}else {
		return null;
		}
	}
	
	/**
	 * ��ȡ��������
	 */
	public List<News> getAllNews(){
//		repo.findByTitleContaining("����"��PageRequest.of(0, 10));
//		return page.toList();
		return repo.findAll();
	}
	
	/**
	 * ɾ��ָ������
	 * @param id
	 * @return
	 */
	public boolean deleteNews(String newsId) {
		boolean result = true;
		repo.deleteById(newsId);
		
		return result;
	}

	
	/**
	 * ����һ������
	 * @param c �����µ�����
	 * @return ���º������
	 */
	public News updateNews(News c) {
		News saved = getNews(c.getId());
		if (saved != null) {
			if (c.getTitle() != null) {
				saved.setTitle(c.getTitle());
			}
			if (c.getContent() != null) {
				saved.setContent(c.getContent());
			}
			if (c.getCover() != null) {
				saved.setCover(c.getCover());
			}
		}
		return repo.save(saved);
	}
	
	/**
	 * 
	 * @param c
	 * @return
	 */
	public News createNews(News c) {
		return repo.save(c);
		/*c.setId(this.channels.get(this.channels.size()-1).getId()+1);
		this.channels.add(c);
		return c;*/
		
	}	
	public List<News> searchbiaoti(String title){
		return repo.findByTitle(title);
	}

}
