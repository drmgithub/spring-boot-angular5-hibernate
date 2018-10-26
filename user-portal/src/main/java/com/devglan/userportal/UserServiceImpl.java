package com.devglan.userportal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public ResponseStatus create(User user) {
    	//user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userDao.create(user);
    }

    @Override
    public User login(User user) {
    	//user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    	
/*    	List<User> users= findAll();
    	users = users.stream().filter(u-> (user.getEmail().equals(u.getEmail()) && user.getPassword().equals(u.getPassword()))).map(p->p).collect(Collectors.toList());
    	if(users.size()>0) {
    		User u = new User();
        	u.setFirstName(users.get(0).getFirstName());
        	return u;
    	}
*/    	
    	return userDao.login(user);
    }

    @Override
    public User delete(int id) {
       
            userDao.delete(id);
       
        return null;
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User findById(int id) {
        return userDao.findById(id);
    }

    @Override
    public User update(User user) {
        return userDao.update(user);
    }
    
    @Override
    public User forgetPassword(String email) {
    	 return userDao.forgetPassword(email);
    }
}
