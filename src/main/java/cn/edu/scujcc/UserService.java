package cn.edu.scujcc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public @Service class UserService {
	@Autowired
	private UserRepository repo;
	private static final Logger logger =  LoggerFactory.getLogger(UserService.class);

	/**
	 * �û�ע�ᣬ�����û���Ϣ����������
	 * @param user ע���û���Ϣ
	 * @return �������û���Ϣ���������ݿ�id��
	 */
	public User register(User user) throws UserExistException {
		User result = null;
		//�����û����Ƿ��������ݿ��д���
		User saved = repo.findFirstByUsername(user.getUsername());
		if (saved == null) {
			result = repo.save(user);
		} else { 
			//�û��Ѵ���
			logger.error("�û�"+user.getUsername()+"�Ѵ��ڡ�");
			throw new UserExistException();
		}
		return result;
	}

	/**
	 * �û���¼��
	 * @param user
	 * @return
	 */
	public User login(String u, String p) {
		User result = null;
		result = repo.findOneByUsernameAndPassword(u, p);
		return result;
	}
}
