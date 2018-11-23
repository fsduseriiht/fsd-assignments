package com.cts.fsd.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.fsd.entity.BookEntity;
import com.cts.fsd.entity.SubjectEntity;
import com.cts.fsd.repo.BookRepository;
import com.cts.fsd.repo.SubjectRepository;


@Service
public class ApplicationDAOService {
	
	@Autowired
	protected SubjectRepository subjectRepository;
	
	@Autowired
	protected BookRepository bookRepository;
	

	public List<SubjectEntity> getAllSubjectsFromDB() {
		System.out.println("Inside ApplicationDAOService.getAllSubjectsFromDB() start");
		
		List<SubjectEntity> subjectEntityList = subjectRepository.findAll();
		
		
		System.out.println("Inside ApplicationDAOService.getAllSubjectsFromDB() end");
		return subjectEntityList;
	}
	
	public List<BookEntity> getAllBooksFromDB(){
		System.out.println("Inside ApplicationDAOService.getAllBooksFromDB() start");
		
		List<BookEntity> bookEntityList = bookRepository.findAll();

		System.out.println("Inside ApplicationDAOService.getAllBooksFromDB() end");
        
		return bookEntityList;
	}

	public boolean addBookToDB(BookEntity bookEntity) {
		System.out.println("Inside ApplicationDAOService.addBookToDB() start");
		boolean status = false;
		
		List<BookEntity> bookEntityList = new ArrayList<BookEntity>();
		bookEntityList.add(bookEntity);
		
		List<BookEntity> dbResponse = bookRepository.saveAll(bookEntityList);
		
		
		if (null != dbResponse && !dbResponse.isEmpty()) {
			status = true;
		}
		
        System.out.println("Inside ApplicationDAOService.addBookToDB() end");
		return status;
	}

	public boolean addSubjectToDB(SubjectEntity subjectEntity) {
		System.out.println("Inside ApplicationDAOService.addSubjectToDB() start");
		boolean status = false;
		
		List<SubjectEntity> subjectEntityList = new ArrayList<SubjectEntity>();
		subjectEntityList.add(subjectEntity);
		
		List<SubjectEntity> dbResponse = subjectRepository.saveAll(subjectEntityList);
		
		if (null != dbResponse && !dbResponse.isEmpty()) {
			status = true;
		}
		
		System.out.println("Inside ApplicationDAOService.addSubjectToDB() end");
		return status;
	}

	public boolean deleteSubjectFromDB(SubjectEntity subjectEntity) {
		System.out.println("Inside ApplicationDAOService.deleteSubjectFromDB() start");
		boolean status = true;
		
		subjectRepository.delete(subjectEntity);
		
		Optional<SubjectEntity> dbResponse = subjectRepository.findById(subjectEntity.getSubjectId());
		
		if (dbResponse.isPresent()) {
			status = false;
		}
		
        System.out.println("Inside ApplicationDAOService.deleteSubjectFromDB() end");
		return status;
	}

	public boolean deleteBookFromDB(BookEntity bookEntity) {
		System.out.println("Inside ApplicationDAOService.deleteBookFromDB() start");
		boolean status = true;
		
		bookRepository.delete(bookEntity);
		
		Optional<BookEntity> dbResponse = bookRepository.findById(bookEntity.getBookId());
		
		if (dbResponse.isPresent()) {
			status = false;
		}
		
        System.out.println("Inside ApplicationDAOService.deleteBookFromDB() end");
		return status;
	}

	public List<BookEntity> searchBookByIdFromDB(BookEntity bookEntity) {
		System.out.println("Inside ApplicationDAOService.searchBookByIdFromDB() start");
		
		List<BookEntity> bookEntityList = new ArrayList<BookEntity>();
		Optional<BookEntity> dbResponse = bookRepository.findById(bookEntity.getBookId());
		
		if (dbResponse.isPresent()) {
			bookEntityList.add( ( null != dbResponse.get()) ? dbResponse.get() : new BookEntity() );
		}
		
		System.out.println("Inside ApplicationDAOService.searchBookByIdFromDB() end");
		return bookEntityList;
	}

	
	public List<BookEntity> searchBookByTitleFromDB(BookEntity bookEntity) {
		System.out.println("Inside ApplicationDAOService.searchBookByTitleFromDB() start");
		
		List<BookEntity> bookEntityList = bookRepository.findBookByTitle(bookEntity.getTitle());
		
		System.out.println("Inside ApplicationDAOService.searchBookByTitleFromDB() end");
		return bookEntityList;
	}

	public List<SubjectEntity> searchSubjectByIdFromDB(SubjectEntity subjectEntity) {
		System.out.println("Inside ApplicationDAOService.searchSubjectByIdFromDB() start");

		List<SubjectEntity> subjectEntityList = new ArrayList<SubjectEntity>();
		Optional<SubjectEntity> dbResponse = subjectRepository.findById(subjectEntity.getSubjectId());
		
		if (dbResponse.isPresent()) {
			subjectEntityList.add( ( null != dbResponse.get()) ? dbResponse.get() : new SubjectEntity() );
		}
		
		System.out.println("Inside ApplicationDAOService.searchSubjectByIdFromDB() end");
		return subjectEntityList;
	}
	
	public List<SubjectEntity> searchSubjectByTitleFromDB(SubjectEntity subjectEntity) {
		System.out.println("Inside ApplicationDAOService.searchSubjectByTitleFromDB() start");
		
		List<SubjectEntity> subjectEntityList = subjectRepository.findSubjectByTitle(subjectEntity.getSubtitle());
		
		System.out.println("Inside ApplicationDAOService.searchSubjectByTitleFromDB() end");
		return subjectEntityList;
	}

}
