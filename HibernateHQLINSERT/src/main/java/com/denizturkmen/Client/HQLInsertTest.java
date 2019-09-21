package com.denizturkmen.Client;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.denizturkmen.Util.HibernateUtil;

public class HQLInsertTest {
	public static void main(String[] args) {

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		insertEmployeeRecords(sessionFactory);

	}

	private static void insertEmployeeRecords(SessionFactory sf) {
		try (Session session = sf.openSession()) {
				
				String HQL = "INSERT INTO Employee(employeeName,doj,salary,bonus,email,designation)"+
				"SELECT employeeName,doj,salary,bonus,email,designation FROM BackupEmployee";
				
				Query query = session.createQuery(HQL);
				
				session.beginTransaction();
				
				int executeUpdate = query.executeUpdate();
				if(executeUpdate>0)
					System.out.println(executeUpdate+"kayıt başarılı şekilde oluştu");
				
				session.getTransaction().commit();
				
			} catch (HibernateException e) {
				e.printStackTrace();
			}
			
		}
	}
