package com.cts.fsd.controller;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cts.fsd.dto.BookDTO;
import com.cts.fsd.dto.SubjectDTO;
import com.cts.fsd.util.FSDAppUtil;

@RestController
@RequestMapping(value="/fsd")
@CrossOrigin("*")
public class ResfulAPIController {
	
	
	@Autowired
	private AppControllerHelper appControllerHelper;
	
	
	@Autowired
	private HttpServletRequest httpServletRequest;

 
	/************************* REST-API METHODS START *************************/
	
	@RequestMapping(value = "/subject/search", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<SubjectDTO>> restDisplayAllSubjects(
			@RequestParam(value = "criteria", required = false, defaultValue = "all")
			String criteria
			) {
		
		System.out.println("ApplicationController :: inside restDisplayAllSubjects()");
		buildRealContextPathOfApplication();
		
		List<SubjectDTO> subjectsFromDB = restfullDisplaySubjects(null , criteria);
		
		return new ResponseEntity<List<SubjectDTO>>(subjectsFromDB , HttpStatus.OK);
    }
	
	@RequestMapping(value = "/subject/search/{searchparam}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<SubjectDTO>> restSearchSubject(
			
			@PathVariable(value = "searchparam", required = false)
			String searchParam,
			
			@RequestParam(value = "criteria", required = false, defaultValue = "all")
			String criteria
			) {
		
		System.out.println("ApplicationController :: inside restSearchSubject()");
		buildRealContextPathOfApplication();
		
		List<SubjectDTO>  subjectsFromDB = restfullDisplaySubjects(searchParam , criteria);
		
		return new ResponseEntity<List<SubjectDTO>>(subjectsFromDB , HttpStatus.OK);
	}
	
	@RequestMapping(value = "/book/search", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<BookDTO>> restDisplayAllBooks(
			@RequestParam(value = "criteria", required = false, defaultValue = "all")
			String criteria) {
		
		System.out.println("ApplicationController :: inside restDisplayAllBooks()");
		buildRealContextPathOfApplication();

		List<BookDTO> booksFromDB = restfullDisplayBooks(null , criteria);

		return new ResponseEntity<List<BookDTO>>(booksFromDB , HttpStatus.OK);
	}
	
	@RequestMapping(value = "/book/search/{searchparam}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<BookDTO>> restSearchBook(
			
			@PathVariable(value = "searchparam", required = false)
			String searchParam,
			
			@RequestParam(value = "criteria", required = false, defaultValue = "all")
			String criteria) {
		
		System.out.println("ApplicationController :: inside searchBook()");
		buildRealContextPathOfApplication();

		List<BookDTO>  booksFromDB = restfullDisplayBooks(searchParam , criteria);

		return new ResponseEntity<List<BookDTO>>(booksFromDB , HttpStatus.OK);
	}
	
	
	
	
	
	
	
	/************************* REST-API HELPER METHODS START *************************/
	
	private List<BookDTO> restfullDisplayBooks( String searchParam , String criteria ) {
		
		System.out.println("ApplicationController :: inside restfullDisplayBooks()");
		List<BookDTO> bookDTOList = new ArrayList<BookDTO>();
		String searchOption = "3";
		String searchString = "allBooks";
		Map<String , String> searchMap = new HashMap<String , String>();
		String searchValue = null;

		if (null != searchParam && !searchParam.equals("")) {
			searchValue = searchParam;
		}
		
		if (null != searchValue) {
			
			if ( criteria.equalsIgnoreCase("byId") ) {
				searchOption = "1";
				searchString = searchValue;
				searchMap.put(searchOption , searchString);
				
				System.out.println("SearchMap Built = searchMap = " + searchMap);
			}
			
			if ( criteria.equalsIgnoreCase("byTitle") ) {
				searchOption = "2";
				searchString = searchValue;
				searchMap.put(searchOption , searchString);
				
				System.out.println("SearchMap Built = searchMap = " + searchMap);
			}
			
			
		} else {
			System.out.println("ApplicationController :: inside restfullDisplayBooks()");
			searchOption = "3";
			searchString = "allBooks";
			searchMap.put(searchOption , searchString);
		}
		
		if (null != searchMap && !searchMap.isEmpty()) {
			bookDTOList = appControllerHelper.searchBook(searchMap);
			if (null != bookDTOList && !bookDTOList.isEmpty()) {
				displayInServerConsole(bookDTOList , "book");
			}
		}
		
		System.out.println("ApplicationController :: restfullDisplayBooks() work finished.................");
		return bookDTOList;
	}
	
	private List<SubjectDTO> restfullDisplaySubjects( String searchParam , String criteria ) {
		
		List<SubjectDTO> subjectDTOList = new ArrayList<SubjectDTO>();
		

		System.out.println("ApplicationController :: inside displaySubjects()");
		
		String searchOption = "3";
		String searchString = "allSubjects";
		Map<String , String> searchMap = new HashMap<String , String>();
		
		String searchValue = null;

		if (null != searchParam && !searchParam.equals("")) {
			searchValue = searchParam;
		}
		
		if (null != searchValue) {
			
			if ( criteria.equalsIgnoreCase("byId") ) {
				searchOption = "1";
				searchString = searchValue;
				searchMap.put(searchOption , searchString);
				System.out.println("SearchMap Built = searchMap = " + searchMap);
			}
			
			if ( criteria.equalsIgnoreCase("byTitle") ) {
				searchOption = "2";
				searchString = searchValue;
				searchMap.put(searchOption , searchString);
				System.out.println("SearchMap Built = searchMap = " + searchMap);
			}
			
		} else {
			
			searchOption = "3";
			searchString = "allSubjects";
			searchMap.put(searchOption , searchString);
			
			System.out.println("SearchMap Built = searchMap = " + searchMap);
		}
		
		if (null != searchMap && !searchMap.isEmpty()) {
			
			subjectDTOList = appControllerHelper.searchSubject(searchMap);
			if (null != subjectDTOList && !subjectDTOList.isEmpty()) {
				displayInServerConsole(subjectDTOList , "subject");
				
			}
		}
		System.out.println("ApplicationController :: displaySubjects() work finished.................");
		return subjectDTOList;
	}

	/************************* REST-API HELPER METHODS START *************************/


	/************************* REST-API METHODS END *************************/
	
	
	/************************* API METHODS START *************************/
	/*@RequestMapping(value="/subject/search")
	public ModelAndView displayAllSubjects(
			@RequestParam(value = "criteria", required = false, defaultValue = "all")
			String criteria
			) {
		System.out.println("ApplicationController :: inside displayAllSubjects()");
		buildRealContextPathOfApplication();
		
		ModelAndView modelViewObject = displaySubjects(null , criteria);
		return modelViewObject;
	}*/
	
	
	/*@RequestMapping("/subject/search/{searchparam}")
	public ModelAndView searchSubject(
			
			@PathVariable(value = "searchparam", required = false)
			String searchParam,
			
			@RequestParam(value = "criteria", required = false, defaultValue = "all")
			String criteria
			) {
		
		System.out.println("ApplicationController :: inside searchSubject()");
		buildRealContextPathOfApplication();
		
		ModelAndView modelViewObject = displaySubjects(searchParam , criteria);
		return modelViewObject;
	}*/
	
	
	
	/*@RequestMapping("/book/search")
	public ModelAndView displayAllBooks(
			@RequestParam(value = "criteria", required = false, defaultValue = "all")
			String criteria) {
		
		System.out.println("ApplicationController :: inside displayAllBooks()");
		buildRealContextPathOfApplication();

		ModelAndView modelViewObject = displayBooks(null , criteria);

		return modelViewObject;
	}*/
	

	/*@RequestMapping("/book/search/{searchparam}")
	public ModelAndView searchBook(
			
			@PathVariable(value = "searchparam", required = false)
			String searchParam,
			
			@RequestParam(value = "criteria", required = false, defaultValue = "all")
			String criteria) {
		
		System.out.println("ApplicationController :: inside searchBook()");
		buildRealContextPathOfApplication();

		ModelAndView modelViewObject = displayBooks(searchParam , criteria);

		return modelViewObject;
	}*/
	
	@RequestMapping("/book/delete/{bookid}")
	public ModelAndView deleteBook(
			@PathVariable(value = "bookid", required = false)
			String bookId 
			) {
		
		System.out.println("ApplicationController :: inside deleteBook()");
		buildRealContextPathOfApplication();
		
		ModelAndView modelViewObject = new ModelAndView("showBooks");
		
		BookDTO bookDTOToBeDeleted = null;
		List<BookDTO>  bookDTOList = null;
		boolean bookFound = false;
		
		try {
			
			bookDTOToBeDeleted = new BookDTO();
			bookDTOList = appControllerHelper.getAllBooks();
			
			if (null != bookDTOList && !bookDTOList.isEmpty()) {
				for(BookDTO bookDTOTemp : bookDTOList) {
					if (Long.valueOf(bookDTOTemp.getBookId()).equals(Long.valueOf(bookId))) {
						bookFound = true;
						bookDTOToBeDeleted = bookDTOTemp;
						break;
					} else {
						bookFound = false;
					}
				}
			} else {
				System.out.println("No Subjects, Database is empty...");
				bookFound = false;
			}
			
			if (bookFound) {
				System.out.println("Book with subject Id ["+bookId+"] going to be deleted...");
				if (null != bookDTOToBeDeleted) {
					appControllerHelper.deleteBook(bookDTOToBeDeleted);
				}
			} else {
				bookDTOToBeDeleted = null;
				System.out.println("Book with subject Id ["+bookId+"] will not be deleted as subject is not found...");
			}
			
			
		} catch (NumberFormatException e) {
			// TODO: handle exception
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		bookDTOList = appControllerHelper.getAllBooks();
		
		modelViewObject.addObject("bookDTOListFromDB", bookDTOList);
		modelViewObject.addObject("operation", "delete");
		modelViewObject.addObject("id", bookId);
		
		return modelViewObject;
	}
	
	
	
	@RequestMapping("/subject/delete/{subjectid}")
	public ModelAndView deleteSubject(
			@PathVariable(value = "subjectid", required = false)
			String subjectId
			) {
		
		System.out.println("ApplicationController :: inside deleteSubject()");
		buildRealContextPathOfApplication();
		
		ModelAndView modelViewObject = new ModelAndView("showSubjects");

		SubjectDTO subjectDTOToBeDeleted = null;
		List<SubjectDTO>  subjectDTOList = null; 
		boolean subjectFound = false;
		
		try {
			// Get All subjects and display them
			subjectDTOToBeDeleted = new SubjectDTO();
			subjectDTOList = appControllerHelper.getAllSubjects();
			if (null != subjectDTOList && !subjectDTOList.isEmpty()) {
				
				for(SubjectDTO subjectDTOTemp : subjectDTOList) {
					if (Long.valueOf(subjectDTOTemp.getSubjectId()).equals(Long.valueOf(subjectId))) {
						subjectFound = true;
						subjectDTOToBeDeleted = subjectDTOTemp;
						break;
					} else {
						subjectFound = false;
					}
				}
				
			} else {
				System.out.println("No Subjects, Database is empty...");
				subjectFound = false;
			}
			
			if (subjectFound) {
				System.out.println("Subject with subject Id ["+subjectDTOToBeDeleted.getSubjectId()+"] is going to be deleted...");
				if (null != subjectDTOToBeDeleted) {
					appControllerHelper.deleteSubject(subjectDTOToBeDeleted);
				}
			} else {
				subjectDTOToBeDeleted = null;
				System.out.println("Subject with subject Id ["+subjectId+"] will not be deleted as subject is not found...");
			}
			
		} catch (NumberFormatException e) {
			System.out.println("ApplicationController : NumberFormatException occured in deleteSubject() " + e.getMessage());
		} catch (Exception e) {
			System.out.println("ApplicationController : Exception occured in deleteSubject() " + e.getMessage());
			e.printStackTrace();
		}
		
		subjectDTOList = appControllerHelper.getAllSubjects();
		
		modelViewObject.addObject("subjectDTOListFromDB", subjectDTOList);
		modelViewObject.addObject("operation", "delete");
		modelViewObject.addObject("id", subjectId);
		
		return modelViewObject;
	}
	
	
	@RequestMapping("/subject/add")
	public ModelAndView addSubject() {

		ModelAndView modelViewObject = new ModelAndView("addSubject");
		buildRealContextPathOfApplication();
		
		List<BookDTO> bookDTOList = appControllerHelper.getAllBooks();
		
		modelViewObject.addObject("bookDTOListFromDB", bookDTOList);

		return modelViewObject;

	}
	
	@RequestMapping("/subject/add/content")
	public ModelAndView addSubjectContent(
									@RequestParam(value = "subjectId", required = true, defaultValue = "all")
									String subjectId,
									
									@RequestParam(value = "subtitle", required = true, defaultValue = "all")
									String subtitle,
									
									@RequestParam(value = "durationInHours", required = true, defaultValue = "all")
									String durationInHours,
									
									@RequestParam(value = "booklist", required = true, defaultValue = "all")
									String booklist
										) {

		ModelAndView modelViewObject = new ModelAndView("showSubjects");
		buildRealContextPathOfApplication();
		SubjectDTO subjectDTO = new SubjectDTO();
		
		subjectDTO.setSubtitle(subtitle);
		subjectDTO.setDurationInHours(Integer.valueOf(durationInHours));
		
		List<BookDTO> allBooks = appControllerHelper.getAllBooks();
		String[] bookIdArray = booklist.split(";");
		Set<BookDTO> references = new LinkedHashSet<BookDTO>();
		for(int i=0; i < bookIdArray.length; i++){
			for(BookDTO book : allBooks) {
				if(Long.valueOf(bookIdArray[i]).equals(Long.valueOf(book.getBookId()))) {
					references.add(book);
				}
			}
		}
		
		subjectDTO.setReferences(references);
		
		appControllerHelper.addSubject(subjectDTO);
		
//		Retrieve All Subjects from Database and view them in showsubjects page
		String searchOption = "3";
		String searchString = "allSubjects";
		Map<String , String> searchMap = new HashMap<String , String>();
		searchMap.put(searchOption , searchString);
		
		List<SubjectDTO> subjectDTOList = appControllerHelper.searchSubject(searchMap);
		
		modelViewObject.addObject("subjectDTOListFromDB", subjectDTOList);

		return modelViewObject;

	}
	
	@RequestMapping("/book/add")
	public ModelAndView addBook() {
		
		ModelAndView modelViewObject = new ModelAndView("addBook");
		buildRealContextPathOfApplication();
		
		
		return modelViewObject;
	}
	
	@RequestMapping("/book/add/content")
	public ModelAndView addBookContent (
									@RequestParam(value = "bookId", required = true, defaultValue = "all")
									String bookId,
									
									@RequestParam(value = "title", required = true, defaultValue = "all")
									String title,
									
									@RequestParam(value = "price", required = true, defaultValue = "all")
									String price,
									
									@RequestParam(value = "volume", required = true, defaultValue = "all")
									String volume,
									
									@RequestParam(value = "publishDate", required = true, defaultValue = "all")
									String publishDate
										) {

		ModelAndView modelViewObject = new ModelAndView("showBooks");
		buildRealContextPathOfApplication();
		BookDTO bookDTO = new BookDTO();
		
		try {
			bookDTO.setTitle(title);
			bookDTO.setPrice(Double.valueOf(price));
			bookDTO.setVolume(Integer.valueOf(volume));
			bookDTO.setPublishDate(new SimpleDateFormat("dd-MM-yyyy").parse(publishDate).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		} catch (ParseException e) {
			System.out.println("ApplicationController : ParseException occured in addBookContent() " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("ApplicationController : Exception occured in addBookContent() " + e.getMessage());
			e.printStackTrace();
		}
		
		
		appControllerHelper.addBook(bookDTO);
		
		List<BookDTO> allBooks = appControllerHelper.getAllBooks();
		
		modelViewObject.addObject("bookDTOListFromDB", allBooks);

		return modelViewObject;

	}
	
	
	/************************** API METHODS END **************************/
	
	
	/**************************  All Helper Methods are given below **************************/
	
	@SuppressWarnings("unchecked")
	public void displayInServerConsole(Object dtoList , String listType) {

		if (null != dtoList) {
			if (listType.equalsIgnoreCase("book")) {
				
				List<BookDTO> bookDTOList = (List<BookDTO>) dtoList;
				
				if (!bookDTOList.isEmpty()) {
					System.out.println("Book Details : ");
					System.out.println(String.format("%6s\t%40s\t%6s\t%10s\t%s", "ID" , "TITLE" , "PRICE" , "VOLUME" , "PUBLISH DATE"));
					System.out.println(String.format("%6s\t%40s\t%6s\t%10s\t%s", "------" , "--------------------" , "------" , "----------" , "------------"));
					
					bookDTOList.forEach(bookDTO -> System.out.println(
							String.format("%6s\t%40s\t%6s\t%10s\t%s", 
							bookDTO.getBookId(),
							bookDTO.getTitle(),
							bookDTO.getPrice(),
							bookDTO.getVolume(),
							bookDTO.getPublishDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")))
							));
				}
				
			} else if (listType.equalsIgnoreCase("subject")) {
				
				List<SubjectDTO> subjectDTOList = (List<SubjectDTO>) dtoList;
				String booksToBeReferred = null;
				
				if(!subjectDTOList.isEmpty()) {
					System.out.println("Subject Details : ");
					System.out.println(String.format("%6s\t%20s\t%8s\t%s" , "ID" , "TITLE" , "Duration" , "Books To Refer"));
					System.out.println(String.format("%6s\t%20s\t%8s\t%s" , "------" , "--------------------" , "--------" , "--------------"));
					for(SubjectDTO subjectDTOTemp : subjectDTOList) {
						booksToBeReferred = null;
						
						if(null != subjectDTOTemp.getReferences() && !subjectDTOTemp.getReferences().isEmpty()) {
							StringBuffer bookBuffer = new StringBuffer();
							subjectDTOTemp.getReferences().forEach(book -> bookBuffer.append(book.getTitle() + "; "));
							booksToBeReferred = "["+new StringBuffer().append(bookBuffer.toString().substring(0 , bookBuffer.toString().lastIndexOf("; "))).toString()+"]";
						}
						System.out.println(String.format("%6s\t%20s\t%8s\t%s" , 
								subjectDTOTemp.getSubjectId() , 
								subjectDTOTemp.getSubtitle() , 
								subjectDTOTemp.getDurationInHours() , 
								(null != booksToBeReferred) ? booksToBeReferred : "No Books Selected"));
					}
					
				}
				
			}
		}
	
	}
	
	private ModelAndView displaySubjects( String searchParam , String criteria ) {
		System.out.println("ApplicationController :: inside displaySubjects()");
		
		ModelAndView modelViewObject = new ModelAndView("showSubjects");
		List<SubjectDTO> subjectDTOList = new ArrayList<SubjectDTO>();
		String searchOption = "3";
		String searchString = "allSubjects";
		Map<String , String> searchMap = new HashMap<String , String>();
		
		String searchValue = null;
		modelViewObject.addObject(subjectDTOList);

		if (null != searchParam && !searchParam.equals("")) {
			searchValue = searchParam;
		}
		
		if (null != searchValue) {
			
			if ( criteria.equalsIgnoreCase("byId") ) {
				searchOption = "1";
				searchString = searchValue;
				searchMap.put(searchOption , searchString);
				System.out.println("SearchMap Built = searchMap = " + searchMap);
			}
			
			if ( criteria.equalsIgnoreCase("byTitle") ) {
				searchOption = "2";
				searchString = searchValue;
				searchMap.put(searchOption , searchString);
				System.out.println("SearchMap Built = searchMap = " + searchMap);
			}
			
			String category = criteria.equalsIgnoreCase("byId") ? "in ID" : (criteria.equalsIgnoreCase("byTitle") ? "in TITLE" : "");
			modelViewObject.addObject("criteria", "For Showing Subjects containing \"" + searchValue + "\" text " + category);
		} else {
			
			searchOption = "3";
			searchString = "allSubjects";
			searchMap.put(searchOption , searchString);
			
			System.out.println("SearchMap Built = searchMap = " + searchMap);
			modelViewObject.addObject("criteria", "For Showing All Subjects");
			modelViewObject.addObject("subjectDTOListFromDB", subjectDTOList);
		}
		
		if (null != searchMap && !searchMap.isEmpty()) {
			
			subjectDTOList = appControllerHelper.searchSubject(searchMap);
			if (null != subjectDTOList && !subjectDTOList.isEmpty()) {
				displayInServerConsole(subjectDTOList , "subject");
				
			}
		}
		
		modelViewObject.addObject("subjectDTOListFromDB", subjectDTOList);
		
		System.out.println("ApplicationController :: displaySubjects() work finished.................");
		
		return modelViewObject;
	}

	private ModelAndView displayBooks( String searchParam , String criteria ) {
		
		System.out.println("ApplicationController :: inside displayBooks()");
		
		ModelAndView modelViewObject = new ModelAndView("showBooks");
		
		List<BookDTO> bookDTOList = new ArrayList<BookDTO>();
		String searchOption = "3";
		String searchString = "allBooks";
		Map<String , String> searchMap = new HashMap<String , String>();
		
		String searchValue = null;

		if (null != searchParam && !searchParam.equals("")) {
			searchValue = searchParam;
		}
		
		if (null != searchValue) {
			
			if ( criteria.equalsIgnoreCase("byId") ) {
				searchOption = "1";
				searchString = searchValue;
				searchMap.put(searchOption , searchString);
				
				System.out.println("SearchMap Built = searchMap = " + searchMap);
			}
			
			if ( criteria.equalsIgnoreCase("byTitle") ) {
				searchOption = "2";
				searchString = searchValue;
				searchMap.put(searchOption , searchString);
				
				System.out.println("SearchMap Built = searchMap = " + searchMap);
			}
			
			String category = criteria.equalsIgnoreCase("byId") ? "in ID" : (criteria.equalsIgnoreCase("byTitle") ? "in TITLE" : "");
			
			modelViewObject.addObject("criteria", "For Showing Books containing \"" + searchValue + "\" text " + category);
		} else {
			System.out.println("ApplicationController :: inside displayBooks()");
			searchOption = "3";
			searchString = "allBooks";
			searchMap.put(searchOption , searchString);
			modelViewObject.addObject("criteria", "For Showing All Books");
		}
		
		if (null != searchMap && !searchMap.isEmpty()) {
			bookDTOList = appControllerHelper.searchBook(searchMap);
			if (null != bookDTOList && !bookDTOList.isEmpty()) {
				displayInServerConsole(bookDTOList , "book");
			}
		}
		
		modelViewObject.addObject("bookDTOListFromDB", bookDTOList);
		System.out.println("ApplicationController :: displayBooks() work finished.................");
		return modelViewObject;
	}
	
	
	
	private String buildRealContextPathOfApplication() {
		
		System.out.println("ApplicationController :: inside getRealContextPathOfApplication()");

		ServletContext context = httpServletRequest.getSession().getServletContext();
		String realContextPath = context.getRealPath("/");
		
		if (null == FSDAppUtil.getRealContextPath() || FSDAppUtil.getRealContextPath().equals("")) {
			FSDAppUtil.setRealContextPath(realContextPath);
		}
		
		return FSDAppUtil.getRealContextPath();
	}
}