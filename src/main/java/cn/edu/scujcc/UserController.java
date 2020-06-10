package cn.edu.scujcc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/user")
public class UserController {
	private final static Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService service;

	@PostMapping("/register")
	public Response register(@RequestBody User u) {
		Response result = new Response();
		logger.debug("�û�ע�᣺"+u);
		try {
			User saved = service.register(u);
			result.setStatus(Response.STATUS_OK);
			result.setData(saved);
		} catch (UserExistException e) {
			logger.error("�û��Ѵ��ڣ�����ע�ᡣ");
			result.setStatus(Response.STATUS_ERROR);
			result.setMessage("�û��Ѵ��ڣ�����ע�ᡣ");
		}
		return result;
	}
	@GetMapping("/login/{username}/{password}")
	public Response login(@PathVariable("username") String username, @PathVariable("password") String password) {
		Response result = new Response();
		User saved = service.login(username, password);
		if (saved != null) { //��¼�ɹ�
			result.setStatus(Response.STATUS_OK);
			result.setData(saved);
		} else {//��¼ʧ��
			logger.error("�û��Ѵ��ڣ�����ע�ᡣ");
			result.setStatus(Response.STATUS_ERROR);
			result.setMessage("�������");
		}
		return result;
	}
}
