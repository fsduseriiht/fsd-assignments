package com.cts.fsd.controller;

import java.io.BufferedReader;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.fsd.dto.BookDTO;
import com.cts.fsd.dto.SubjectDTO;
import com.cts.fsd.service.ApplicationService;

@Component
public class AppControllerHelper {
	
	@Autowired
	private ApplicationService applicationService;
	
	BufferedReader buffer = null;
	String inputStr = null;
	
	public  void addBook(BookDTO bookDTO) {
		System.out.println("Inside ApplicationController.addBook() start");
		boolean bookAdded = applicationService.addBookToStorage(bookDTO);
		if(bookAdded) {
			System.out.println("Book Added...");
		} else {
			System.out.println("Book Not Added...");
		}
		System.out.println("Inside ApplicationController.addBook() end");
	}

	public  void addSubject(SubjectDTO subjectDTO) {
		System.out.println("Inside ApplicationController.addSubject() start");
		if (null != subjectDTO) {
			boolean subjectAdded = applicationService.addSubjectToStorage(subjectDTO);
			if(subjectAdded) {
				System.out.println("Subject Added...");
			} else {
				System.out.println("Subject Not Added...");
			}
		}
		System.out.println("Inside ApplicationController.addSubject() end");
	}
	
	public  void deleteBook(BookDTO bookDTO) {
		System.out.println("Inside ApplicationController.deleteBook() start");
		if (null != bookDTO) {
			boolean deleteStatus = applicationService.deleteBookFromStorage(bookDTO);
			if(deleteStatus) {
				System.out.println("Book Deleted...");
			} else {
				System.out.println("Book Not Deleted...");
			}
		}
		System.out.println("Inside ApplicationController.deleteBook() end");
	}

	public  void deleteSubject(SubjectDTO subjectDTO) {
		System.out.println("Inside ApplicationController.deleteSubject() start");
		
		if (null != subjectDTO) {
			boolean deleteStatus = applicationService.deleteSubjectFromStorage(subjectDTO);
			if(deleteStatus) {
				System.out.println("Subject Deleted...");
			} else {
				System.out.println("Subject Not Deleted...");
			}
		}
		
		System.out.println("Inside ApplicationController.deleteSubject() end");
	}

	public  List<BookDTO> searchBook(Map<String , String> searchMap) {
		System.out.println("Inside ApplicationController.searchBook() start");
		List<BookDTO> bookDTOList = null;
		
		if (searchMap.containsKey("1")) {
			bookDTOList = applicationService.searchBookByIdFromStorage(searchMap.get("1"));
		} else if (searchMap.containsKey("2")) {
			bookDTOList = applicationService.searchBookByTitleFromStorage(searchMap.get("2"));
		} else if (searchMap.containsKey("3")) {
			bookDTOList = getAllBooks();
		}

		if(null != bookDTOList && !bookDTOList.isEmpty() && !searchMap.containsKey("3")) {
			System.out.println("Book Found...");
		} else if (!searchMap.get("3").equals("allBooks")){
			System.out.println("Book Not Found...");
		}
		
		System.out.println("Inside ApplicationController.searchBook() end");
		return bookDTOList;
	}

	public  List<SubjectDTO> searchSubject(Map<String, String> searchMap) {
		System.out.println("Inside ApplicationController.searchSubject() start");
		
		List<SubjectDTO> subjectDTOList = null;
		
		if (searchMap.containsKey("1")) {
			subjectDTOList = applicationService.searchSubjectByIdFromStorage(searchMap.get("1"));
		} else if (searchMap.containsKey("2")) {
			subjectDTOList = applicationService.searchSubjectByTitleFromStorage(searchMap.get("2"));
		} else if (searchMap.containsKey("3")) {
			subjectDTOList = getAllSubjects();
		}

		if(null != subjectDTOList && !subjectDTOList.isEmpty() && !searchMap.containsKey("3")) {
			System.out.println("Subject Found...");
		} else if (!searchMap.get("3").equals("allSubjects")) {
			System.out.println("Subject Not Found...");
		}
		
		System.out.println("Inside ApplicationController.searchSubject() end");
		return subjectDTOList;
	}
	
	public  List<BookDTO> getAllBooks() {
		System.out.println("Inside ApplicationController.getAllBooks() start");
		
		List<BookDTO> bookDTOList = applicationService.getAllBooksFromStorage();
		
		System.out.println("Inside ApplicationController.getAllBooks() end");
		return bookDTOList;
	}
	
	public  List<SubjectDTO> getAllSubjects() {
		System.out.println("Inside ApplicationController.getAllSubjects() start");
		
		List<SubjectDTO> subjectDTOList = applicationService.getAllSubjectsFromStorage();
		
		System.out.println("Inside ApplicationController.getAllSubjects() end");
		return subjectDTOList;
	}

}
