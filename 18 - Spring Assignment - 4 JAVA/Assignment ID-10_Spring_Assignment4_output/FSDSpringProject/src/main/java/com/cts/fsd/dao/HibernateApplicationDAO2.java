package com.cts.fsd.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.cts.fsd.entity.BookEntity;
import com.cts.fsd.entity.SubjectEntity;
import com.cts.fsd.hibernate.util.HibernateUtil;



public class HibernateApplicationDAO2 {
	
	private static SessionFactory sessionFactory = null;
	
	public static void setSessionFactoryInDao() {
		System.out.println("HibernateApplicationDAO : Setting the Hibernate Session Factory in HibernateApplicationDAO");
		try {
			if (null == sessionFactory) {
				sessionFactory = HibernateUtil.getSessionFactory();
			}
		}  catch (Exception e) {
			System.out.println("HibernateApplicationDAO : Exception occured in setSessionFactoryInDao() " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static void unSetSessionFactoryInDao() {
		System.out.println("HibernateApplicationDAO : Un-Setting the Hibernate Session Factory in HibernateApplicationDAO");
		try {
			if (null != sessionFactory && !sessionFactory.isClosed()) {
				
				sessionFactory.close();
				
				System.out.println("SessionFactory is now closed...");
			}
		}  catch (Exception e) {
			System.out.println("HibernateApplicationDAO : Exception occured in unSetSessionFactoryInDao() " + e.getMessage());
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public static List<SubjectEntity> getAllSubjectsFromDB() {
		setSessionFactoryInDao();
		System.out.println("Inside HibernateApplicationDAO.getAllSubjectsFromDB() start");
		
		String QUERY_GET_ALL_SUBJECTS = "FROM SubjectEntity S order by S.subjectId asc";
		
		List<SubjectEntity> subjectEntityList = null;
		
		Session session = null;
		
		try {
			
			session = sessionFactory.getCurrentSession();
			
			session.beginTransaction();
			
			Query query = session.createQuery(QUERY_GET_ALL_SUBJECTS);
			
			subjectEntityList = (List<SubjectEntity>)query.list();
			
			System.out.println("HibernateApplicationDAO : subjectEntityList = " + subjectEntityList ); 
			
		}  catch (Exception e) {
			System.out.println("HibernateApplicationDAO : Exception occured in getAllSubjectsFromDB() " + e.getMessage());
			e.printStackTrace();
		} finally {
			session.flush();
            session.clear();
            session.close();
		}
		
		System.out.println("Inside HibernateApplicationDAO.getAllSubjectsFromDB() end");
		return subjectEntityList;
	}
	
	@SuppressWarnings("unchecked")
	public static List<BookEntity> getAllBooksFromDB(){
		setSessionFactoryInDao();
		System.out.println("Inside HibernateApplicationDAO.getAllBooksFromDB() start");
		
		String QUERY_GET_ALL_BOOKS = "FROM BookEntity B order by B.bookId asc";
		
		List<BookEntity> bookEntityList = null;
		
		Session session = null;
		
		try {
			session = sessionFactory.getCurrentSession();
			
			session.beginTransaction();
			
			Query query = session.createQuery(QUERY_GET_ALL_BOOKS);
			
			bookEntityList = (List<BookEntity>)query.list();
			
			System.out.println("HibernateApplicationDAO : bookEntityList = " + bookEntityList ); 
			
		} catch (Exception e) {
			System.out.println("HibernateApplicationDAO : Exception occured in getAllBooksFromDB() " + e.getMessage());
			e.printStackTrace();
		} finally {
			session.flush();
            session.clear();
            session.close();
		}

		System.out.println("Inside HibernateApplicationDAO.getAllBooksFromDB() end");
        
		return bookEntityList;
	}

	public static boolean addBookToDB(BookEntity bookEntity) {
		setSessionFactoryInDao();
		System.out.println("Inside HibernateApplicationDAO.addBookToDB() start");
		boolean status = false;
		
		Session session = null;
		
		try {
			
			session = sessionFactory.getCurrentSession();
			
			Transaction transaction = session.beginTransaction();
			
			long id = (Long) session.save(bookEntity);
			
			System.out.println("HibernateApplicationDAO : bookEntity saved to DB with id = " + id );
			
			status = true;
			
			transaction.commit();
		}  catch (Exception e) {
			status = false;
			System.out.println("HibernateApplicationDAO : Exception occured in addBookToDB() " + e.getMessage());
			e.printStackTrace();
		}
		
        System.out.println("Inside HibernateApplicationDAO.addBookToDB() end");
		return status;
	}

	public static boolean addSubjectToDB(SubjectEntity subjectEntity) {
		setSessionFactoryInDao();
		System.out.println("Inside HibernateApplicationDAO.addSubjectToDB() start");
		boolean status = false;
		
		Session session = null;
		
		try {
			
			session = sessionFactory.getCurrentSession();
			
			Transaction transaction = session.beginTransaction();
			
			long id = (Long) session.save(subjectEntity);
			
			System.out.println("HibernateApplicationDAO : subjectEntity saved to DB with id = " + id );
			
			status = true;
			
			transaction.commit();
		}  catch (Exception e) {
			status = false;
			System.out.println("HibernateApplicationDAO : Exception occured in addSubjectToDB() " + e.getMessage());
			e.printStackTrace();
		}
		
		System.out.println("Inside HibernateApplicationDAO.addSubjectToDB() end");
		return status;
	}

	public static boolean deleteSubjectFromDB(SubjectEntity subjectEntity) {
		setSessionFactoryInDao();
		System.out.println("Inside HibernateApplicationDAO.deleteSubjectFromDB() start");
		boolean status = false;
		
		Session session = null;
		
		try {
			
			session = sessionFactory.getCurrentSession();
			
			Transaction transaction = session.beginTransaction();
			
			session.delete(subjectEntity);
			
			System.out.println("HibernateApplicationDAO : subjectEntity deleted from DB " );
			
			status = true;
			
			transaction.commit();
		}  catch (Exception e) {
			status = false;
			System.out.println("HibernateApplicationDAO : Exception occured in deleteSubjectFromDB() " + e.getMessage());
			e.printStackTrace();
		}
		
        System.out.println("Inside HibernateApplicationDAO.deleteSubjectFromDB() end");
		return status;
	}

	public static boolean deleteBookFromDB(BookEntity bookEntity) {
		setSessionFactoryInDao();
		System.out.println("Inside HibernateApplicationDAO.deleteBookFromDB() start");
		boolean status = false;
		
		Session session = null;
		
		try {
			
			session = sessionFactory.getCurrentSession();
			
			Transaction transaction = session.beginTransaction();
			
			session.delete(bookEntity);
			
			System.out.println("HibernateApplicationDAO : bookEntity deleted from DB " );
			
			status = true;
			
			transaction.commit();
		}  catch (Exception e) {
			status = false;
			System.out.println("HibernateApplicationDAO : Exception occured in deleteBookFromDB() " + e.getMessage());
			e.printStackTrace();
		}
        
        System.out.println("Inside HibernateApplicationDAO.deleteBookFromDB() end");
		return status;
	}

	public static List<BookEntity> searchBookByIdFromDB(BookEntity bookEntity) {
		setSessionFactoryInDao();
		System.out.println("Inside HibernateApplicationDAO.searchBookByIdFromDB() start");
		
		List<BookEntity> bookEntityList = null;
		BookEntity bookEntityFromDB = null;
		
		Session session = null;
		
		try {
			bookEntityList = new ArrayList<BookEntity>();
			
			session = sessionFactory.getCurrentSession();
			
			session.beginTransaction();
			
			bookEntityFromDB = (BookEntity) session.get(BookEntity.class , bookEntity.getBookId());
			
			System.out.println("HibernateApplicationDAO : bookEntity from DB with ID::[" + bookEntity.getBookId()  + "]  = " + bookEntityFromDB );
			
			bookEntityList.add( ( null != bookEntityFromDB) ? bookEntityFromDB : new BookEntity() );
			
		}  catch (Exception e) {
			System.out.println("HibernateApplicationDAO : Exception occured in searchBookByIdFromDB() " + e.getMessage());
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		System.out.println("Inside HibernateApplicationDAO.searchBookByIdFromDB() end");
		return bookEntityList;
	}

	@SuppressWarnings("unchecked")
	public static List<BookEntity> searchBookByTitleFromDB(BookEntity bookEntity) {
		setSessionFactoryInDao();
		System.out.println("Inside HibernateApplicationDAO.searchBookByTitleFromDB() start");
		
		List<BookEntity> bookEntityList = null;
		
		String QUERY_GET_BOOKS_BY_TITLE = "FROM BookEntity B where LOWER(B.title) like '%' || LOWER('"+ bookEntity.getTitle() +"') || '%' order by B.bookId asc";

		Session session = null;
		
		try {
			session = sessionFactory.getCurrentSession();
			
			session.beginTransaction();
			
			Query query = session.createQuery(QUERY_GET_BOOKS_BY_TITLE);
			
			bookEntityList = (List<BookEntity>)query.list();
			
			System.out.println("HibernateApplicationDAO : bookEntityList from DB with search string::[" + bookEntity.getTitle()  + "]  = " + bookEntityList );
			
		}  catch (Exception e) {
			System.out.println("HibernateApplicationDAO : Exception occured in searchBookByIdFromDB() " + e.getMessage());
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		System.out.println("Inside HibernateApplicationDAO.searchBookByTitleFromDB() end");
		return bookEntityList;
	}

	public static List<SubjectEntity> searchSubjectByIdFromDB(SubjectEntity subjectEntity) {
		setSessionFactoryInDao();
		System.out.println("Inside HibernateApplicationDAO.searchSubjectByIdFromDB() start");

		List<SubjectEntity> subjectEntityList = null;
		SubjectEntity subjectEntityFromDB = null;
		
		Session session = null;
		
		try {
			subjectEntityList = new ArrayList<SubjectEntity>();
			
			session = sessionFactory.getCurrentSession();
			
			session.beginTransaction();
			
			subjectEntityFromDB = (SubjectEntity) session.get(SubjectEntity.class , subjectEntity.getSubjectId());
			
			System.out.println("HibernateApplicationDAO : bookEntity from DB with ID::[" + subjectEntity.getSubjectId()  + "]  = " + subjectEntityFromDB );
			
			subjectEntityList.add( (null != subjectEntityFromDB) ? subjectEntityFromDB : new SubjectEntity());
			
		}  catch (Exception e) {
			System.out.println("HibernateApplicationDAO : Exception occured in searchBookByIdFromDB() " + e.getMessage());
			e.printStackTrace();
		} finally {
			session.close();
		}
        
		System.out.println("Inside HibernateApplicationDAO.searchSubjectByIdFromDB() end");
		return subjectEntityList;
	}
	
	@SuppressWarnings("unchecked")
	public static List<SubjectEntity> searchSubjectByTitleFromDB(SubjectEntity subjectEntity) {
		setSessionFactoryInDao();
		System.out.println("Inside HibernateApplicationDAO.searchSubjectByTitleFromDB() start");
		
		List<SubjectEntity> subjectEntityList = null;
		
		
		String QUERY_GET_SUBJECT_BY_TITLE = "FROM SubjectEntity S where LOWER(S.subtitle) like '%' || LOWER('"+ subjectEntity.getSubtitle() +"') || '%' order by S.subjectId asc";

		Session session = null;
		
		try {
			session = sessionFactory.getCurrentSession();
			
			session.beginTransaction();
			
			Query query = session.createQuery(QUERY_GET_SUBJECT_BY_TITLE);
			
			subjectEntityList = (List<SubjectEntity>) query.list();
			
			System.out.println("HibernateApplicationDAO : subjectEntityList from DB with search string::[" + subjectEntity.getSubtitle()  + "]  = " + subjectEntityList );
			
		}  catch (Exception e) {
			System.out.println("HibernateApplicationDAO : Exception occured in searchBookByIdFromDB() " + e.getMessage());
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		
		System.out.println("Inside HibernateApplicationDAO.searchSubjectByTitleFromDB() end");
		return subjectEntityList;
	}

}
