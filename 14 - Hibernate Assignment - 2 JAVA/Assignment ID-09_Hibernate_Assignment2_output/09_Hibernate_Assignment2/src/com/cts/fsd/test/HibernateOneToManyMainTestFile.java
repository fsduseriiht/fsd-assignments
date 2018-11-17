package com.cts.fsd.test;

import java.text.SimpleDateFormat;
import java.util.LinkedHashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.cts.fsd.entity.BookEntity;
import com.cts.fsd.entity.SubjectEntity;
import com.cts.fsd.hibernate.util.HibernateUtil;

public class HibernateOneToManyMainTestFile {
	static SessionFactory sessionFactory = null;
	
	public void myMethod() throws Exception {
		Set<BookEntity> setHindiLiteratureBooks = new LinkedHashSet<BookEntity>();
		Set<BookEntity> setGeographyPrimaryBooks = new LinkedHashSet<BookEntity>();
		
		BookEntity hindiBookEntity1 = new BookEntity();
		hindiBookEntity1.setTitle("Hindi Prose-II");
		hindiBookEntity1.setPrice(88);
		hindiBookEntity1.setVolume(1);
		hindiBookEntity1.setPublishDate(new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse("1999-05-04").getTime()));
		
		BookEntity hindiBookEntity2 = new BookEntity();
		hindiBookEntity2.setTitle("Hindi Prose-I");
		hindiBookEntity2.setPrice(77);
		hindiBookEntity2.setVolume(2);
		hindiBookEntity2.setPublishDate(new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse("1987-07-01").getTime()));
		
		setHindiLiteratureBooks.add(hindiBookEntity1);
		setHindiLiteratureBooks.add(hindiBookEntity2);
		
		BookEntity geographyBookEntity3 = new BookEntity();
		geographyBookEntity3.setTitle("Geography for Newbies Vol.I");
		geographyBookEntity3.setPrice(113);
		geographyBookEntity3.setVolume(1);
		geographyBookEntity3.setPublishDate(new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse("1998-09-01").getTime()));
		
		BookEntity geographyBookEntity4 = new BookEntity();
		geographyBookEntity4.setTitle("Geography for Beginers vol2");
		geographyBookEntity4.setPrice(79);
		geographyBookEntity4.setVolume(2);
		geographyBookEntity4.setPublishDate(new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse("1999-07-01").getTime()));
		

		setGeographyPrimaryBooks.add(geographyBookEntity3);
		setGeographyPrimaryBooks.add(geographyBookEntity4);
		
		SubjectEntity hindiSubjectEntity = new SubjectEntity();
		hindiSubjectEntity.setSubtitle("Hindi-Literature");
		hindiSubjectEntity.setDurationInHours(100);
		hindiSubjectEntity.setReferences(setHindiLiteratureBooks);

		SubjectEntity geographySubjectEntity = new SubjectEntity();
		geographySubjectEntity.setSubtitle("Geography-Primary");
		geographySubjectEntity.setDurationInHours(50);
		geographySubjectEntity.setReferences(setGeographyPrimaryBooks);
	}
	
	public static void main(String[] args) throws Exception {
		
		
		Session session = null;
	    Transaction tx = null;
	    try {
	    	if (null == sessionFactory) {
				sessionFactory = HibernateUtil.getSessionFactory();
			}
	    	
	    	session = sessionFactory.openSession();

	        tx = session.beginTransaction();    
	        
	        SubjectEntity hindiSubjectEntity = new SubjectEntity();
			hindiSubjectEntity.setSubtitle("Hindi-Literature");
			hindiSubjectEntity.setDurationInHours(100);

			SubjectEntity geographySubjectEntity = new SubjectEntity();
			geographySubjectEntity.setSubtitle("Geography-Primary");
			geographySubjectEntity.setDurationInHours(50);
	        
	        long id1 = (Long) session.save(hindiSubjectEntity);
	        long id2 = (Long) session.save(geographySubjectEntity);
	        
	        /****************************************************************/
	        
	        
	        BookEntity hindiBookEntity1 = new BookEntity();
			hindiBookEntity1.setTitle("Hindi Prose-II");
			hindiBookEntity1.setPrice(88);
			hindiBookEntity1.setVolume(1);
			hindiBookEntity1.setPublishDate(new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse("1999-05-04").getTime()));
			hindiBookEntity1.setSubjectEntity(hindiSubjectEntity);
			
			BookEntity hindiBookEntity2 = new BookEntity();
			hindiBookEntity2.setTitle("Hindi Prose-I");
			hindiBookEntity2.setPrice(77);
			hindiBookEntity2.setVolume(2);
			hindiBookEntity2.setPublishDate(new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse("1987-07-01").getTime()));
			hindiBookEntity2.setSubjectEntity(hindiSubjectEntity);

			
			BookEntity geographyBookEntity3 = new BookEntity();
			geographyBookEntity3.setTitle("Geography for Newbies Vol.I");
			geographyBookEntity3.setPrice(113);
			geographyBookEntity3.setVolume(1);
			geographyBookEntity3.setPublishDate(new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse("1998-09-01").getTime()));
			geographyBookEntity3.setSubjectEntity(geographySubjectEntity);
			
			BookEntity geographyBookEntity4 = new BookEntity();
			geographyBookEntity4.setTitle("Geography for Beginers vol2");
			geographyBookEntity4.setPrice(79);
			geographyBookEntity4.setVolume(2);
			geographyBookEntity4.setPublishDate(new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse("1999-07-01").getTime()));
			geographyBookEntity4.setSubjectEntity(geographySubjectEntity);
			
			long id3 = (Long) session.save(hindiBookEntity1);
			long id4 = (Long) session.save(hindiBookEntity2);

			long id5 = (Long) session.save(geographyBookEntity3);
			long id6 = (Long) session.save(geographyBookEntity4);
			
			
			BookEntity geographyBookEntity5 = new BookEntity();
			geographyBookEntity5.setTitle("Geography for NULL SUBJECT");
			geographyBookEntity5.setPrice(100);
			geographyBookEntity5.setVolume(3);
			geographyBookEntity5.setPublishDate(new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse("2018-12-01").getTime()));
//			geographyBookEntity5.setSubjectEntity(null);
			
			
			long id7 = (Long) session.save(geographyBookEntity5);
			
//	        session.flush();
//            session.clear();
	        
//	        Query query = session.createQuery("FROM SubjectEntity");
//	        List<SubjectEntity> subjectEntityList = query.list();
//	        
//	        for(SubjectEntity subjectEntity : subjectEntityList) {
//	        	System.out.println("subjectEntity = " + subjectEntity.getSubtitle());
//	        	System.out.println(" --------------------------- ");
//	        }
            
            tx.commit();

	    } catch (Exception e) {
			System.out.println("HibernateOneToManyMain Exception occured. "+e.getMessage());
			e.printStackTrace();
		
	    } finally {
	    	if (null != sessionFactory && !sessionFactory.isClosed()) {
				
				sessionFactory.close();
				
				System.out.println("SessionFactory is now closed...");
			}
		}
	}
}
