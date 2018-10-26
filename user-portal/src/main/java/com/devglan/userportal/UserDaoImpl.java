package com.devglan.userportal;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@ComponentScan
public class UserDaoImpl implements UserDao {

	@Autowired
	 private SessionFactory sessionFactory;
	
	

    @Override
    public ResponseStatus create(User user) {
    	Session session = sessionFactory.openSession();
    	Query query = session.createQuery(
    	        "select count(*) from User login where login.email=:email ");
    	query.setString("email", user.getEmail());
    	Long count = (Long)query.uniqueResult();
    	if(count==1) {
    		session.close();
    		return new ResponseStatus(200,"Account already exist.");
    	}
    	session.saveOrUpdate(user);
    	session.close();
         return new ResponseStatus(200,"Success");
        
    }

    @Override
    public User login(User user) {
    	
    	Session session = sessionFactory.openSession();
    	Query query = session.createQuery(
    	        "select count(*) from User login where login.email=:email and login.password=:password");
    	query.setString("email", user.getEmail());
    	query.setString("password", user.getPassword());
    	Long count = (Long)query.uniqueResult();
    	if(count==1) {
    		 User userObj = getUserByEmail(user.getEmail());
    		 
    		 forgetPassword(user.getEmail());
    		session.close();
    		User u =new User();
        	u.setFirstName(userObj.getFirstName());
        	u.setEmail(user.getEmail());
        	return u;
    		//return new ResponseStatus(200,"Login success.");
    	}
    	
    	
/*    	Query q =session.createQuery("  from User where email=? and password=?");
    	q.setParameter(0, user.getEmail());
    	q.setParameter(1,user.getPassword());
    	List<User> list = (ArrayList<User>)q.list();
*/    	
    	//User u =(User)q.uniqueResult();
    	
    	
        return null;
        
    	/*List<User> users= findAll();
    	users = users.stream().filter(u-> (user.getEmail().equals(u.getEmail()) && user.getPassword().equals(u.getPassword()))).map(p->p).collect(Collectors.toList());
    	if(users.size()>0) {
    		User u = new User();
        	u.setFirstName(users.get(0).getFirstName());
        	return u;
    	}
    	*/
    }

	private User getUserByEmail(String email) {

		Session session = sessionFactory.openSession();
		Query q = session.createQuery("select login from User login where login.email=:email");
		q.setString("email", email);
		User userObj = (User) q.uniqueResult();
		session.close();
		return userObj;
	}
	
	public User forgetPassword(String email) {

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		User user = (User) session.load(User.class, getUserByEmail(email).getId());
		if (user != null) {
			user.setPassword(UUID.randomUUID() + "");
			session.update(user);
			tx.commit();

		}

		//session.close();
		return user;
	}

    @Override
    public User delete(int id) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        User user =  (User)session.load(User.class, id);
        if(user != null){
        	session.delete(user);
        	tx.commit();
        	session.close();
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        return (List<User>)sessionFactory.openSession().createCriteria(User.class).list();
    }

    @Override
    public User findById(int id) {
    	Session session = sessionFactory.openSession();
    	Query q =session.createQuery(" from User where id=?");
    	q.setParameter(0, id);
    	
        return (User)q.uniqueResult();
    }

	@Override
	public User update(User user) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		User u = (User) session.load(User.class, user.getId());
		if (u != null) {
			u.setEmail(user.getEmail());
			u.setPassword(user.getPassword());
			u.setFirstName(user.getFirstName());
			u.setLastName(user.getLastName());
			session.update(u);
			tx.commit();

		}

		return u;
	}
}
