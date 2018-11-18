package com.cts.fsd.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.fsd.dao.ApplicationDAOService;
import com.cts.fsd.dto.BookDTO;
import com.cts.fsd.dto.SubjectDTO;
import com.cts.fsd.entity.BookEntity;
import com.cts.fsd.entity.SubjectEntity;
import com.cts.fsd.mapper.ObjectMappper;

@Service
public class ApplicationService {
	
	@Autowired
	private ApplicationDAOService applicationDAOService;
	
	static ObjectMappper objectMappper = new ObjectMappper();

	public  List<SubjectDTO> getAllSubjectsFromStorage() {
		System.out.println("Inside ApplicationService.getAllSubjectsFromStorage() start");
		
		List<SubjectDTO> subjectDTOList = new ArrayList<SubjectDTO>();
		SubjectDTO subjectDTO = null;
		
		List<SubjectEntity> subjectEntityList = applicationDAOService.getAllSubjectsFromDB();
		
		for(SubjectEntity subjectEntity  : subjectEntityList) {
			subjectDTO = objectMappper.mapEntityToPojo(subjectEntity);
			subjectDTOList.add(subjectDTO);
		}
		
		System.out.println("Inside ApplicationService.getAllSubjectsFromStorage() end");
		return subjectDTOList;
	}
	
	public  List<BookDTO> getAllBooksFromStorage() {
		System.out.println("Inside ApplicationService.getAllBooksFromStorage() start");
		
		List<BookDTO> bookDTOList = new ArrayList<BookDTO>();
		BookDTO bookDTO = null;
		
		List<BookEntity> bookEntityList = applicationDAOService.getAllBooksFromDB();
		
		for(BookEntity bookEntity : bookEntityList) {
			bookDTO = objectMappper.mapEntityToPojo(bookEntity);
			bookDTOList.add(bookDTO);
		}
		
		
		System.out.println("Inside ApplicationService.getAllBooksFromStorage() end");
		return bookDTOList;
	}

	public  boolean addBookToStorage(BookDTO bookDTO) {
		System.out.println("Inside ApplicationService.addBookToStorage() start");
		
		BookEntity bookEntity = objectMappper.mapPojoToEntity(bookDTO);
		
		boolean status = applicationDAOService.addBookToDB(bookEntity);
		
		System.out.println("Inside ApplicationService.addBookToStorage() end");
		return status;
	}

	public boolean addSubjectToStorage(SubjectDTO subjectDTO) {
		System.out.println("Inside ApplicationService.addSubjectToStorage() start");
		
		SubjectEntity subjectEntity = objectMappper.mapPojoToEntity(subjectDTO);
		
		boolean status = applicationDAOService.addSubjectToDB(subjectEntity);
		
		System.out.println("Inside ApplicationService.addSubjectToStorage() end");
		return status;
	}

	public boolean deleteSubjectFromStorage(SubjectDTO subjectDTO) {
		System.out.println("Inside ApplicationService.deleteSubjectFromStorage() start");
		
		SubjectEntity subjectEntity = objectMappper.mapPojoToEntity(subjectDTO);
		boolean deleteStatus = applicationDAOService.deleteSubjectFromDB(subjectEntity);
		
		System.out.println("Inside ApplicationService.deleteSubjectFromStorage() end");
		return deleteStatus;
	}

	public boolean deleteBookFromStorage(BookDTO bookDTO) {
		System.out.println("Inside ApplicationService.deleteBookFromStorage() start");
		
		BookEntity bookEntity = objectMappper.mapPojoToEntity(bookDTO);
		boolean deleteStatus = applicationDAOService.deleteBookFromDB(bookEntity);
		
		System.out.println("Inside ApplicationService.deleteBookFromStorage() end");
		return deleteStatus;
	}

	
	public List<BookDTO> searchBookByIdFromStorage(String bookId) {
		System.out.println("Inside ApplicationService.searchBookById() start");
		
		List<BookDTO> bookDTOList = new ArrayList<BookDTO>();
		BookDTO bookDTO = null;
		BookEntity bookEntity = new BookEntity();
		bookEntity.setBookId(Long.parseLong(bookId));
		List<BookEntity> bookEntityList = applicationDAOService.searchBookByIdFromDB(bookEntity);
		
		for(BookEntity bookEntityFromDB : bookEntityList) {
			bookDTO = objectMappper.mapEntityToPojo(bookEntityFromDB);
			bookDTOList.add(bookDTO);
		}
		
		System.out.println("Inside ApplicationService.searchBookById() end");
		return bookDTOList;
	}

	public List<BookDTO> searchBookByTitleFromStorage(String title) {
		System.out.println("Inside ApplicationService.searchBookByTitleFromStorage() start");
		
		List<BookDTO> bookDTOList = new ArrayList<BookDTO>();
		BookDTO bookDTO = null;
		BookEntity bookEntity = new BookEntity();
		bookEntity.setTitle(title);
		List<BookEntity> bookEntityList = applicationDAOService.searchBookByTitleFromDB(bookEntity);
		
		for(BookEntity bookEntityFromDB : bookEntityList) {
			bookDTO = objectMappper.mapEntityToPojo(bookEntityFromDB);
			bookDTOList.add(bookDTO);
		}
		
		System.out.println("Inside ApplicationService.searchBookByTitleFromStorage() end");
		return bookDTOList;
	}

	public List<SubjectDTO> searchSubjectByIdFromStorage(String subjectId) {
		System.out.println("Inside ApplicationService.searchSubjectByIdFromStorage() start");
		
		List<SubjectDTO> subjectDTOList = new ArrayList<SubjectDTO>();
		SubjectDTO subjectDTO = null;
		SubjectEntity subjectEntity = new SubjectEntity();
		subjectEntity.setSubjectId(Long.parseLong(subjectId));
		List<SubjectEntity> subjectEntityList = applicationDAOService.searchSubjectByIdFromDB(subjectEntity);
		if (null != subjectEntityList && !subjectEntityList.isEmpty()) {
			for(SubjectEntity subjectEntityFromDB : subjectEntityList) {
				subjectDTO = objectMappper.mapEntityToPojo(subjectEntityFromDB);
				subjectDTOList.add(subjectDTO);
			}
		}
		
		System.out.println("Inside ApplicationService.searchSubjectByIdFromStorage() end");
		return subjectDTOList;
	}

	public List<SubjectDTO> searchSubjectByTitleFromStorage(String subjectTitle) {
		System.out.println("Inside ApplicationService.searchSubjectByTitleFromStorage() start");
		
		List<SubjectDTO> subjectDTOList = new ArrayList<SubjectDTO>();
		SubjectDTO subjectDTO = null;
		SubjectEntity subjectEntity = new SubjectEntity();
		subjectEntity.setSubtitle(subjectTitle);
		
		List<SubjectEntity> subjectEntityList = applicationDAOService.searchSubjectByTitleFromDB(subjectEntity);
		if (null != subjectEntityList && !subjectEntityList.isEmpty()) {
			for(SubjectEntity subjectEntityFromDB : subjectEntityList) {
				subjectDTO = objectMappper.mapEntityToPojo(subjectEntityFromDB);
				subjectDTOList.add(subjectDTO);
			}
		}
		
		System.out.println("Inside ApplicationService.searchSubjectByTitleFromStorage() end");
		return subjectDTOList;
	}
	
}
