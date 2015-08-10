package test.hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateTest {

	public static void main(String[] args) {
		UzytkownikDane dane = new UzytkownikDane();
		dane.setUserID(1);
		dane.setUserName("cos");
		
		SessionFactory sessionfactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionfactory.openSession();
		session.beginTransaction();
		session.save(dane);
		session.getTransaction().commit();
		session.close();
		sessionfactory.close();
	}

}
