package com.cts.fsd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.cts.fsd.entity.BookEntity;
import com.cts.fsd.entity.SubjectEntity;
import com.cts.fsd.util.DBUtility;



public class ApplicationDAO {

	public static List<SubjectEntity> getAllSubjectsFromDB() {
		System.out.println("Inside ApplicationDAO.getAllSubjectsFromDB() start");
		
		List<SubjectEntity> subjectEntityList = null;
		SubjectEntity subjectEntity = null;
		BookEntity bookEntity = null;
		Set<BookEntity> referencesEntity = null;
		
		Connection conn = null;
        Statement stmt = null;
        ResultSet resultSet = null;
        
        String queryString = null;
    	
        try {
        	subjectEntityList = new ArrayList<SubjectEntity>();
        	
        	conn = DBUtility.getConnection();
        	stmt = conn.createStatement();
        	queryString = "SELECT * FROM SUBJECT_TABLE S, BOOK_TABLE B where B.subjectId = S.subjectId order by S.subjectId asc";
        	resultSet = stmt.executeQuery(queryString);
        	
        	referencesEntity = new LinkedHashSet<BookEntity>();
        	while (resultSet.next()) {
        		
        		subjectEntity = new SubjectEntity();
    			subjectEntity.setSubjectId(resultSet.getLong(1));
        		subjectEntity.setSubtitle(resultSet.getString(2));
        		subjectEntity.setDurationInHours(resultSet.getInt(3));
        		
        		bookEntity = new BookEntity();
        		bookEntity.setBookId(resultSet.getLong(4));
        		bookEntity.setTitle(resultSet.getString(5));
        		bookEntity.setPrice(resultSet.getDouble(6));
        		bookEntity.setVolume(resultSet.getInt(7));
        		bookEntity.setPublishDate(resultSet.getDate(8));
        		
        		
        		if (!(null != subjectEntityList && !subjectEntityList.isEmpty() && subjectEntityList.contains(subjectEntity))) {
        			referencesEntity = new LinkedHashSet<BookEntity>();
				}
        		referencesEntity.add(bookEntity);
        		subjectEntity.setReferences(referencesEntity);
        		
        		if (null != subjectEntityList && !subjectEntityList.isEmpty() && subjectEntityList.contains(subjectEntity)) {
        			subjectEntityList.remove(subjectEntity);
				}
        		subjectEntityList.add(subjectEntity);
        	}
//        	System.out.println("subjectEntityList = " + subjectEntityList);
        	
		} catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			queryString = null;
			try {
				DBUtility.closeConnection(conn);
				stmt.close();
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("Inside ApplicationDAO.getAllSubjectsFromDB() end");
		return subjectEntityList;
	}
	
	public static List<BookEntity> getAllBooksFromDB(){
		System.out.println("Inside ApplicationDAO.getAllBooksFromDB() start");
		
		List<BookEntity> bookEntityList = null;
		BookEntity bookEntity = null;
		
		Connection conn = null;
        Statement stmt = null;
        ResultSet resultSet = null;
        
        String queryString = null;
        	
        try {
        	bookEntityList = new ArrayList<BookEntity>();
        	conn = DBUtility.getConnection();
        	stmt = conn.createStatement();
        	
        	queryString = "SELECT * FROM BOOK_TABLE";
        	resultSet = stmt.executeQuery(queryString);
        	
        	while (resultSet.next()) {
        		bookEntity = new BookEntity();
        		bookEntity.setBookId(resultSet.getLong("bookId"));
        		bookEntity.setTitle(resultSet.getString("title"));
        		bookEntity.setPrice(resultSet.getDouble("price"));
        		bookEntity.setVolume(resultSet.getInt("volume"));
        		bookEntity.setPublishDate(resultSet.getDate("publishDate"));
        		
        		bookEntityList.add(bookEntity);
        	}
        	
		} catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			queryString = null;
			try {
				DBUtility.closeConnection(conn);
				stmt.close();
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
        System.out.println("Inside ApplicationDAO.getAllBooksFromDB() end");
        
		return bookEntityList;
	}

	public static boolean addBookToDB(BookEntity bookEntity) {
		System.out.println("Inside ApplicationDAO.addBookToDB() start");
		boolean status = false;
		
		Connection conn = null;
        PreparedStatement pstmt = null;
        String queryString = null;
        
        try {
        	conn = DBUtility.getConnection();
        	queryString = "INSERT into BOOK_TABLE (bookId, title, price, volume, publishDate) values (?, ?, ?, ?, ?)";
        	
        	pstmt = conn.prepareStatement(queryString);
        	
        	pstmt.setLong(1, bookEntity.getBookId());
        	pstmt.setString(2, bookEntity.getTitle());
        	pstmt.setDouble(3, bookEntity.getPrice());
        	pstmt.setInt(4, bookEntity.getVolume());
        	pstmt.setDate(5, bookEntity.getPublishDate());
        	
        	int updatedRows = pstmt.executeUpdate();
        	
        	if (updatedRows > 0) {
        		status = true;
			}
		} catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e) {
			status = false;
			e.printStackTrace();
		} catch (Exception e) {
			status = false;
			e.printStackTrace();
		} finally {
			queryString = null;
			try {
				DBUtility.closeConnection(conn);
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
        System.out.println("Inside ApplicationDAO.addBookToDB() end");
		return status;
	}

	public static boolean addSubjectToDB(SubjectEntity subjectEntity) {
		System.out.println("Inside ApplicationDAO.addSubjectToDB() start");
		boolean status = false;
		
		Connection conn = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        
        String queryString = null;
        
        int insertedSubjects = 0;
        int insertedBooksForSubject = 0;
        
        try {
        	conn = DBUtility.getConnection();
        	conn.setAutoCommit(false);
        	
        	// Insert into SUBJECT_TABLE regarding subject details
        	queryString = "INSERT into SUBJECT_TABLE (subjectId, subtitle, durationInHours) values (?, ?, ?)";
        	pstmt1 = conn.prepareStatement(queryString);
        	pstmt1.setLong(1, subjectEntity.getSubjectId());
        	pstmt1.setString(2, subjectEntity.getSubtitle());
        	pstmt1.setInt(3, subjectEntity.getDurationInHours());
        	insertedSubjects = pstmt1.executeUpdate();
        	
        	// INSERT into BOOK_TABLE for the Books that are going to be referred by the corresponding Subjects
        	queryString = "UPDATE BOOK_TABLE SET subjectId=? WHERE bookId=?";
        	if (null != subjectEntity.getReferences() && !subjectEntity.getReferences().isEmpty()) {
				for(BookEntity bookEntity : subjectEntity.getReferences()) {
					// Insert into BOOK_TABLE regarding subject mapping with books
		        	pstmt2 = conn.prepareStatement(queryString);
		        	pstmt2.setLong(1, subjectEntity.getSubjectId());
		        	pstmt2.setLong(2, bookEntity.getBookId());
		        	insertedBooksForSubject += pstmt2.executeUpdate();
				}
			}
        	
        	// Transaction Management in JDBC using commit() and rollback()
        	if (insertedSubjects > 0 && insertedBooksForSubject > 0) {
        		status = true;
        		conn.commit();
			} else {
				status = false;
				conn.rollback();
			}
		} catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e) {
			status = false;
			e.printStackTrace();
		} catch (Exception e) {
			status = false;
			e.printStackTrace();
		} finally {
			queryString = null;
			try {
				DBUtility.closeConnection(conn);
				pstmt1.close();
				pstmt2.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("Inside ApplicationDAO.addSubjectToDB() end");
		return status;
	}

	public static boolean deleteSubjectFromDB(SubjectEntity subjectEntity) {
		System.out.println("Inside ApplicationDAO.deleteSubjectFromDB() start");
		boolean status = false;
		
		Connection conn = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        
        String queryString = null;
        
        int subjectDeleted = 0;
        int booksDeleted = 0;
        
        try {
        	conn = DBUtility.getConnection();
        	conn.setAutoCommit(false);
        	
        	// DELETE FROM SUBJECT_TABLE regarding subject details
        	queryString = "DELETE from SUBJECT_TABLE where subjectId=?";
        	pstmt1 = conn.prepareStatement(queryString);
        	pstmt1.setLong(1, subjectEntity.getSubjectId());
        	subjectDeleted = pstmt1.executeUpdate();
        	
        	
        	// UPDATE BOOK_TABLE regarding with subject id as NULL
        	queryString = "UPDATE BOOK_TABLE set subjectId=NULL where bookId = ?";
        	if (null != subjectEntity.getReferences() && !subjectEntity.getReferences().isEmpty()) {
        		for(BookEntity bookEntity : subjectEntity.getReferences()) {
        			// update BOOK_TABLE by setting NULL to subjectid field for books of various subjects
		        	pstmt2 = conn.prepareStatement(queryString);
		        	pstmt2.setLong(1, bookEntity.getBookId());
		        	booksDeleted += pstmt2.executeUpdate();
        		}
        	}
        	
        	
        	// Transaction Management in JDBC using commit() and rollback()
        	if (subjectDeleted > 0 && booksDeleted > 0) {
        		status = true;
        		conn.commit();
			} else {
				status = false;
				conn.rollback();
			}
		} catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e) {
			status = false;
			e.printStackTrace();
		} catch (Exception e) {
			status = false;
			e.printStackTrace();
		} finally {
			queryString = null;
			try {
				DBUtility.closeConnection(conn);
				pstmt1.close();
				pstmt2.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
        System.out.println("Inside ApplicationDAO.deleteSubjectFromDB() end");
		return status;
	}

	public static boolean deleteBookFromDB(BookEntity bookEntity) {
		System.out.println("Inside ApplicationDAO.deleteBookFromDB() start");
		boolean status = false;
		Connection conn = null;
        PreparedStatement pstmt = null;
        
        String queryString = null;
        int bookDeleted = 0;
        
        try {
        	conn = DBUtility.getConnection();
        	queryString = "DELETE from BOOK_TABLE where bookId=?";
        	pstmt = conn.prepareStatement(queryString);
        	pstmt.setLong(1, bookEntity.getBookId());
        	
        	bookDeleted = pstmt.executeUpdate();
        	
        	if (bookDeleted > 0 ) {
        		status = true;
			} 
        	
        	
		} catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e) {
			status = false;
			e.printStackTrace();
		} catch (Exception e) {
			status = false;
			e.printStackTrace();
		} finally {
			queryString = null;
			try {
				DBUtility.closeConnection(conn);
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
        System.out.println("Inside ApplicationDAO.deleteBookFromDB() end");
		return status;
	}

	public static List<BookEntity> searchBookByIdFromDB(BookEntity bookEntity) {
		System.out.println("Inside ApplicationDAO.searchBookByIdFromDB() start");
		
		List<BookEntity> bookEntityList = null;
		BookEntity bookEntityFromDB = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        String queryString = null;
		
        try {
        	bookEntityList = new ArrayList<BookEntity>();
        	queryString = "SELECT * FROM BOOK_TABLE where bookId=?";
        	conn = DBUtility.getConnection();
        	pstmt = conn.prepareStatement(queryString);
        	pstmt.setLong(1, bookEntity.getBookId());
        	
        	resultSet = pstmt.executeQuery();
        	
        	while (resultSet.next()) {
        		bookEntityFromDB = new BookEntity();
        		bookEntityFromDB.setBookId(resultSet.getLong("bookId"));
        		bookEntityFromDB.setTitle(resultSet.getString("title"));
        		bookEntityFromDB.setPrice(resultSet.getDouble("price"));
        		bookEntityFromDB.setVolume(resultSet.getInt("volume"));
        		bookEntityFromDB.setPublishDate(resultSet.getDate("publishDate"));
        		
        		bookEntityList.add(bookEntityFromDB);
        	}
        	
        	
		} catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			queryString = null;
			try {
				DBUtility.closeConnection(conn);
				pstmt.close();
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("Inside ApplicationDAO.searchBookByIdFromDB() end");
		return bookEntityList;
	}

	public static List<BookEntity> searchBookByTitleFromDB(BookEntity bookEntity) {
		System.out.println("Inside ApplicationDAO.searchBookByTitleFromDB() start");
		
		List<BookEntity> bookEntityList = null;
		BookEntity bookEntityFromDB = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        String queryString = null;
		
        try {
        	bookEntityList = new ArrayList<BookEntity>();
        	queryString = "SELECT * FROM BOOK_TABLE where LOWER(title) like '%' || LOWER(?) || '%'";
        	conn = DBUtility.getConnection();
        	pstmt = conn.prepareStatement(queryString);
        	pstmt.setString(1, bookEntity.getTitle());
        	
        	resultSet = pstmt.executeQuery();
        	
        	while (resultSet.next()) {
        		bookEntityFromDB = new BookEntity();
        		bookEntityFromDB.setBookId(resultSet.getLong("bookId"));
        		bookEntityFromDB.setTitle(resultSet.getString("title"));
        		bookEntityFromDB.setPrice(resultSet.getDouble("price"));
        		bookEntityFromDB.setVolume(resultSet.getInt("volume"));
        		bookEntityFromDB.setPublishDate(resultSet.getDate("publishDate"));
        		
        		bookEntityList.add(bookEntityFromDB);
        	}
        	
        	
		} catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			queryString = null;
			try {
				DBUtility.closeConnection(conn);
				pstmt.close();
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("Inside ApplicationDAO.searchBookByTitleFromDB() end");
		return bookEntityList;
	}

	public static List<SubjectEntity> searchSubjectByIdFromDB(SubjectEntity subjectEntity) {
		System.out.println("Inside ApplicationDAO.searchSubjectByIdFromDB() start");
		

		List<SubjectEntity> subjectEntityList = null;
		SubjectEntity subjectEntityFromDB = null;
		Set<BookEntity> referencesEntity = null;
		BookEntity bookEntity = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        String queryString = null;
        
        try {
        	subjectEntityList = new ArrayList<SubjectEntity>();
        	queryString = "SELECT * FROM SUBJECT_TABLE S, BOOK_TABLE B where B.subjectId = S.subjectId and S.subjectId=? order by S.subjectId asc";
        	conn = DBUtility.getConnection();
        	pstmt = conn.prepareStatement(queryString);
        	pstmt.setLong(1, subjectEntity.getSubjectId());
        	
        	resultSet = pstmt.executeQuery();
        	
        	referencesEntity = new LinkedHashSet<BookEntity>();
        	while (resultSet.next()) {
        		subjectEntityFromDB = new SubjectEntity();
        		subjectEntityFromDB.setSubjectId(resultSet.getLong(1));
        		subjectEntityFromDB.setSubtitle(resultSet.getString(2));
        		subjectEntityFromDB.setDurationInHours(resultSet.getInt(3));
        		
        		bookEntity = new BookEntity();
        		bookEntity.setBookId(resultSet.getLong(4));
        		bookEntity.setTitle(resultSet.getString(5));
        		bookEntity.setPrice(resultSet.getDouble(6));
        		bookEntity.setVolume(resultSet.getInt(7));
        		bookEntity.setPublishDate(resultSet.getDate(8));
        		
        		if (!(null != subjectEntityList && !subjectEntityList.isEmpty() && subjectEntityList.contains(subjectEntityFromDB))) {
        			referencesEntity = new LinkedHashSet<BookEntity>();
				}
        		referencesEntity.add(bookEntity);
        		subjectEntityFromDB.setReferences(referencesEntity);
        		
        		if (null != subjectEntityList && !subjectEntityList.isEmpty() && subjectEntityList.contains(subjectEntityFromDB)) {
        			subjectEntityList.remove(subjectEntityFromDB);
				}
        		subjectEntityList.add(subjectEntityFromDB);
        	}
        	
		} catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			queryString = null;
			try {
				DBUtility.closeConnection(conn);
				pstmt.close();
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
        
		System.out.println("Inside ApplicationDAO.searchSubjectByIdFromDB() end");
		return subjectEntityList;
	}
	
	public static List<SubjectEntity> searchSubjectByTitleFromDB(SubjectEntity subjectEntity) {
		System.out.println("Inside ApplicationDAO.searchSubjectByTitleFromDB() start");
		
		List<SubjectEntity> subjectEntityList = null;
		SubjectEntity subjectEntityFromDB = null;
		Set<BookEntity> referencesEntity = null;
		BookEntity bookEntity = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        String queryString = null;
        
        try {
        	subjectEntityList = new ArrayList<SubjectEntity>();
        	
        	queryString = "SELECT * FROM SUBJECT_TABLE S, BOOK_TABLE B where B.subjectId = S.subjectId and LOWER(S.subtitle) like '%' || LOWER(?) || '%' order by S.subjectId asc";
        	conn = DBUtility.getConnection();
        	pstmt = conn.prepareStatement(queryString);
        	pstmt.setString(1, subjectEntity.getSubtitle());
        	resultSet = pstmt.executeQuery();
        	
        	referencesEntity = new LinkedHashSet<BookEntity>();
        	while (resultSet.next()) {
        		subjectEntityFromDB = new SubjectEntity();
        		subjectEntityFromDB.setSubjectId(resultSet.getLong(1));
        		subjectEntityFromDB.setSubtitle(resultSet.getString(2));
        		subjectEntityFromDB.setDurationInHours(resultSet.getInt(3));
        		
        		bookEntity = new BookEntity();
        		bookEntity.setBookId(resultSet.getLong(4));
        		bookEntity.setTitle(resultSet.getString(5));
        		bookEntity.setPrice(resultSet.getDouble(6));
        		bookEntity.setVolume(resultSet.getInt(7));
        		bookEntity.setPublishDate(resultSet.getDate(8));
        		
        		if (!(null != subjectEntityList && !subjectEntityList.isEmpty() && subjectEntityList.contains(subjectEntityFromDB))) {
        			referencesEntity = new LinkedHashSet<BookEntity>();
				}
        		referencesEntity.add(bookEntity);
        		subjectEntityFromDB.setReferences(referencesEntity);
        		
        		if (null != subjectEntityList && !subjectEntityList.isEmpty() && subjectEntityList.contains(subjectEntityFromDB)) {
        			subjectEntityList.remove(subjectEntityFromDB);
				}
        		subjectEntityList.add(subjectEntityFromDB);
        	}
        	
		} catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			queryString = null;
			try {
				DBUtility.closeConnection(conn);
				pstmt.close();
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Inside ApplicationDAO.searchSubjectByTitleFromDB() end");
		return subjectEntityList;
	}

}