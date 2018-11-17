package com.cts.fsd.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cts.fsd.dto.BookDTO;
import com.cts.fsd.dto.SubjectDTO;
import com.cts.fsd.exception.CustomException;
import com.cts.fsd.util.ApplicationUtility;

public class UserController {

	public static BookDTO getBookToRemoveFromUser(BufferedReader buffer) {
		BookDTO bookDTOToBeDeleted = null;
		List<BookDTO>  bookDTOList = null;
		String inputStr = null;
		boolean bookFound = false;
		long bookId = 0;
		
		
		try {
			// Get All books and Display them
			bookDTOToBeDeleted = new BookDTO();
			bookDTOList = ApplicationController.getAllBooks();
			if (null != bookDTOList) {
				System.out.println("Showing all existing Books...");
				System.out.println("Book Details : ");
				System.out.println(String.format("%6s\t%40s\t%6s\t%10s\t%s", "ID" , "TITLE" , "PRICE" , "VOLUME" , "PUBLISH DATE"));
				System.out.println(String.format("%6s\t%40s\t%6s\t%10s\t%s", "------" , "--------------------" , "------" , "----------" , "------------"));
				for(BookDTO bookDTOTemp : bookDTOList) {
					System.out.println(String.format("%6s\t%40s\t%6s\t%10s\t%s" , 
							bookDTOTemp.getBookId() , 
							bookDTOTemp.getTitle() , 
							bookDTOTemp.getPrice() , 
							bookDTOTemp.getVolume() , 
							bookDTOTemp.getPublishDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))));
				}
				
				do {
					System.out.print("Select the Book Id to delete (only numbers) ::");
					inputStr = buffer.readLine();
				} while (!inputStr.matches("[0-9]{1,}"));
				bookId = Long.parseLong(inputStr);
				
				
				for(BookDTO bookDTOTemp : bookDTOList) {
					if (bookDTOTemp.getBookId() == bookId) {
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
			} else {
				bookDTOToBeDeleted = null;
				System.out.println("Book with subject Id ["+bookId+"] will not be deleted as subject is not found...");
			}
		} catch (NumberFormatException e) {
			// TODO: handle exception
		} catch (IOException e) {
			// TODO: handle exception
		}
		
		return bookDTOToBeDeleted;
	}
	
	public static SubjectDTO getSubjectToRemoveFromUser(BufferedReader buffer) {
		SubjectDTO subjectDTOToBeDeleted = null;
		List<SubjectDTO>  subjectDTOList = null;
		String inputStr = null;
		String booksToBeReferred = null; 
		boolean subjectFound = false;
		long subjectId = 0;
		
		try {
			// Get All subjects and display them
			subjectDTOToBeDeleted = new SubjectDTO();
			subjectDTOList = ApplicationController.getAllSubjects();
			if (null != subjectDTOList) {
				System.out.println("Showing all existing Subjects...");
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
					System.out.println(String.format("%6s\t%20s\t%8s\t%s" , subjectDTOTemp.getSubjectId() , subjectDTOTemp.getSubtitle() , subjectDTOTemp.getDurationInHours() , booksToBeReferred));
				}
				
				do {
					System.out.print("Select the Subject Id to delete (only numbers) ::");
					inputStr = buffer.readLine();
					inputStr = inputStr.trim();
				} while (!inputStr.matches("[0-9]{1,}"));
				subjectId = Long.parseLong(inputStr);
				
				
				for(SubjectDTO subjectDTOTemp : subjectDTOList) {
					if (subjectDTOTemp.getSubjectId() == subjectId) {
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
			} else {
				subjectDTOToBeDeleted = null;
				System.out.println("Subject with subject Id ["+subjectId+"] will not be deleted as subject is not found...");
			}
			
		} catch (NumberFormatException e) {
			// TODO: handle exception
		} catch (IOException e) {
			// TODO: handle exception
		}
		
		return subjectDTOToBeDeleted;
	}
	
	public static SubjectDTO getSubjectDetailsFromUserNew(BufferedReader buffer) throws IOException , NumberFormatException {
		SubjectDTO subjectDTO = null;
		String inputStr = null;
		
		try {
			subjectDTO = new SubjectDTO();

			// 2. Enter the Subject sub-title :: 
			System.out.print("Enter the Subject sub-title :: ");
			inputStr = buffer.readLine();
			inputStr = inputStr.trim();
			subjectDTO.setSubtitle(inputStr);
			
			// 3. Enter the Subject Duration in Hours (only numbers) :: 
			do {
				System.out.print("Enter the Subject Duration in Hours (only numbers) :: ");
				inputStr = buffer.readLine();
				inputStr = inputStr.trim();
			} while (!inputStr.matches("[0-9]{1,}"));
			subjectDTO.setDurationInHours(Integer.parseInt(inputStr));
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			inputStr = null;
		}
		
		return subjectDTO;
	}
	
	
	
	
	
	// Not Needed
	public static SubjectDTO getSubjectDetailsFromUser(BufferedReader buffer) throws IOException , NumberFormatException {
		SubjectDTO subjectDTO = null;
		String inputStr = null;
		
		List<BookDTO> bookDTOList = null;
		Set<BookDTO> references = null;
		
		String[] bookIdArray = null;
		String booksNotAvailable = "";
		boolean booksFound = false;
		
		try {
			subjectDTO = new SubjectDTO();
			
			// 1. Enter the Subject details :: 
			System.out.println("Enter the Subject details :: ");
			do {
				System.out.print("Enter the Subject Id (only numbers) :: ");
				inputStr = buffer.readLine();
				inputStr = inputStr.trim();
			} while (!inputStr.matches("[0-9]{1,}"));
			subjectDTO.setSubjectId(Long.parseLong(inputStr));

			// 2. Enter the Subject sub-title :: 
			System.out.print("Enter the Subject sub-title :: ");
			inputStr = buffer.readLine();
			inputStr = inputStr.trim();
			subjectDTO.setSubtitle(inputStr);
			
			// 3. Enter the Subject Duration in Hours (only numbers) :: 
			do {
				System.out.print("Enter the Subject Duration in Hours (only numbers) :: ");
				inputStr = buffer.readLine();
				inputStr = inputStr.trim();
			} while (!inputStr.matches("[0-9]{1,}"));
			subjectDTO.setDurationInHours(Integer.parseInt(inputStr));
			
			
			System.out.println("Select the Book references(Book Id) from the menu below (Seperated by semi-colon \";\") :: ");
			bookDTOList = ApplicationController.getAllBooks();
			if (bookDTOList != null) {
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
				
				do {
					System.out.print("Enter Book ID (only numbers seperated by semi-colon \";\") :- ");
					inputStr = buffer.readLine();
					inputStr = inputStr.trim();
					
					if (inputStr.matches("[0-9;]{1,}")) {
						booksNotAvailable = "";
						bookIdArray = inputStr.split(";");
						for(int i=0; i < bookIdArray.length; i++){
							booksFound = false;
							for(BookDTO bookDTO : bookDTOList) {
								if(Long.parseLong(bookIdArray[i]) == bookDTO.getBookId()) {
									booksFound = true;
								}
							}
							if (!booksFound) {
								booksNotAvailable += ""+bookIdArray[i] + "  " ;
							}
						}
						if (!booksNotAvailable.equals("")) {
							System.out.println("Book id's that are not found = [ "+booksNotAvailable+"], please select correct books again...");
							inputStr = "loopAgain";
						}
					}
					
				} while (!inputStr.matches("[0-9;]{1,}"));
				
				
				references = new LinkedHashSet<BookDTO>();
				
				for(int i=0; i < bookIdArray.length; i++){
					for(BookDTO bookDTO : bookDTOList) {
						if(Long.parseLong(bookIdArray[i]) == bookDTO.getBookId()) {
							references.add(bookDTO);
						}
					}
				}
			} else {
				references = null;
			}
			subjectDTO.setReferences(references);
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			inputStr = null;
		}
		
		return subjectDTO;
	}
	
	public static BookDTO getBookDetailsFromUserNew(BufferedReader buffer) throws IOException , NumberFormatException {
		List<SubjectDTO> subjectDTOList = null;		
		BookDTO bookDTO = null;
		String inputStr = null;
		
		try {
			bookDTO = new BookDTO();
			System.out.println("Enter details of Book to be added...");
			/*// 1. Enter the Book Id (only numbers) :: 
			do {
				System.out.print("Enter the Book Id (only numbers) :: ");
				inputStr = buffer.readLine();
				inputStr = inputStr.trim();
			} while (!inputStr.matches("[0-9]{1,}"));
			bookDTO.setBookId(Long.parseLong(inputStr));*/
			
			// 2. Enter the Book title :: 
			System.out.print("Enter the Book title :: ");
			inputStr = buffer.readLine();
			inputStr = inputStr.trim();
			bookDTO.setTitle(inputStr);
			
			// 3. Enter the Book price (only numbers [decimal]) :: 
			do {
				System.out.print("Enter the Book price (only numbers [decimal]) :: ");
				inputStr = buffer.readLine();
				inputStr = inputStr.trim();
			} while (!inputStr.matches("^[0-9]+(\\.[0-9]+)?$"));
			bookDTO.setPrice(Double.parseDouble(inputStr));
			
			// 4. Enter the Book Volume (only numbers) :: 
			do {
				System.out.print("Enter the Book Volume (only numbers) :: ");
				inputStr = buffer.readLine();
				inputStr = inputStr.trim();
			} while (!inputStr.matches("[0-9]{1,}"));
			bookDTO.setVolume(Integer.parseInt(inputStr));
			
			// 5. Enter the Book Publish Date (format = \"dd-MM-yyyy\") :: 
			do {
				System.out.print("Enter the Book Publish Date (format = \"dd-MM-yyyy\") :: ");
				inputStr = buffer.readLine();
				inputStr = inputStr.trim();
				
				if (inputStr.matches("[0-3][0-9]-[0-1][0-9]-[0-9][0-9][0-9][0-9]")) {
					String[] temp = inputStr.split("-");
					if (!ApplicationUtility.isDateValid(temp[2], temp[1], temp[0])) {
						inputStr = "loopAgain";
					}
				}
				
			} while (!inputStr.matches("[0-3][0-9]-[0-1][0-9]-[0-9][0-9][0-9][0-9]") );
			bookDTO.setPublishDate(LocalDate.parse(inputStr , DateTimeFormatter.ofPattern("dd-MM-yyyy")));
			
			
			// 6. Enter the Subject Details 
			SubjectDTO subjectDTOToBeSelected = null;
			String booksToBeReferred = null;
			boolean subjectFound = false;
			long subjectId = 0;
			
			System.out.println("Select the Subject Id that the Book belongs to from the menu below :: ");
			subjectDTOList = ApplicationController.getAllSubjects();
			if (null != subjectDTOList) {
				System.out.println("Showing all existing Books...");
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
					System.out.println(String.format("%6s\t%20s\t%8s\t%s" , subjectDTOTemp.getSubjectId() , subjectDTOTemp.getSubtitle() , subjectDTOTemp.getDurationInHours() , (null != booksToBeReferred) ? booksToBeReferred : "No Books Added"));
				}
				
				do {
					System.out.print("Select the Subject Id related to the Book (only numbers) ::");
					inputStr = buffer.readLine();
					inputStr = inputStr.trim();
				} while (!inputStr.matches("[0-9]{1,}"));
				subjectId = Long.parseLong(inputStr);
				
				
				for(SubjectDTO subjectDTOTemp : subjectDTOList) {
					if (subjectDTOTemp.getSubjectId() == subjectId) {
						subjectFound = true;
						subjectDTOToBeSelected = subjectDTOTemp;
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
				bookDTO.setSubjectDTO(subjectDTOToBeSelected);
				System.out.println("Subject with subject Id ["+subjectDTOToBeSelected.getSubjectId()+"] is selected for the Book = " + bookDTO );
			} else {
				System.out.println("Subject with subject Id ["+subjectId+"] will not be added as subject is not found...");
			}
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			inputStr = null;
		}
		return bookDTO;
	}
	
	
	
	
	
	// Not Needed
	public static BookDTO getBookDetailsFromUser(BufferedReader buffer) throws IOException , NumberFormatException {
		BookDTO bookDTO = null;
		String inputStr = null;
		
		try {
			bookDTO = new BookDTO();
			System.out.println("Enter details of Book to be added...");
			// 1. Enter the Book Id (only numbers) :: 
			do {
				System.out.print("Enter the Book Id (only numbers) :: ");
				inputStr = buffer.readLine();
				inputStr = inputStr.trim();
			} while (!inputStr.matches("[0-9]{1,}"));
			bookDTO.setBookId(Long.parseLong(inputStr));
			
			// 2. Enter the Book title :: 
			System.out.print("Enter the Book title :: ");
			inputStr = buffer.readLine();
			inputStr = inputStr.trim();
			bookDTO.setTitle(inputStr);
			
			// 3. Enter the Book price (only numbers [decimal]) :: 
			do {
				System.out.print("Enter the Book price (only numbers [decimal]) :: ");
				inputStr = buffer.readLine();
				inputStr = inputStr.trim();
			} while (!inputStr.matches("^[0-9]+(\\.[0-9]+)?$"));
			bookDTO.setPrice(Double.parseDouble(inputStr));
			
			// 4. Enter the Book Volume (only numbers) :: 
			do {
				System.out.print("Enter the Book Volume (only numbers) :: ");
				inputStr = buffer.readLine();
				inputStr = inputStr.trim();
			} while (!inputStr.matches("[0-9]{1,}"));
			bookDTO.setVolume(Integer.parseInt(inputStr));
			
			// 5. Enter the Book Publish Date (format = \"dd-MM-yyyy\") :: 
			do {
				System.out.print("Enter the Book Publish Date (format = \"dd-MM-yyyy\") :: ");
				inputStr = buffer.readLine();
				inputStr = inputStr.trim();
				
				if (inputStr.matches("[0-3][0-9]-[0-1][0-9]-[0-9][0-9][0-9][0-9]")) {
					String[] temp = inputStr.split("-");
					if (!ApplicationUtility.isDateValid(temp[2], temp[1], temp[0])) {
						inputStr = "loopAgain";
					}
				}
				
			} while (!inputStr.matches("[0-3][0-9]-[0-1][0-9]-[0-9][0-9][0-9][0-9]") );
			bookDTO.setPublishDate(LocalDate.parse(inputStr , DateTimeFormatter.ofPattern("dd-MM-yyyy")));
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			inputStr = null;
		}
		return bookDTO;
	}

	public static Map<String , String> getBookSearchOptionFromUser(BufferedReader buffer) {
		String inputStr = null;
		String searchOption = null;
		String searchString = null;
		
		Map<String , String> searchMap = null;
		
		List<String> inputCheckList = new ArrayList<String>();
		inputCheckList.add("1");
		inputCheckList.add("2");
		inputCheckList.add("3");
		inputCheckList.add("4");
		try {
			
			do {
				System.out.println("Select [1] to search by book id");
				System.out.println("Select [2] to search by book title");
				System.out.println("Select [3] to display all books");
				System.out.println("Select [4] to exit search...");
				inputStr = buffer.readLine();
				inputStr = inputStr.trim();
				
				if (!inputCheckList.contains(inputStr)) {
					System.out.println("\n\nPlease select correct option...\n\n");
					inputStr = "loopAgain";
				}
			} while(!inputStr.matches("[1-4]{1}"));
			
			searchOption = inputStr;
			
			if (searchOption.equals("1")) {						// to search by book id
				
				do {
					System.out.print("Enter the Book Id to search (only numbers) ::");
					inputStr = buffer.readLine();
					inputStr = inputStr.trim();
				} while (!inputStr.matches("[0-9]{1,}"));
				searchString = inputStr;
				
			} else if (searchOption.equals("2")) {				// to search by book title
				
				System.out.print("Enter the Book title :: ");
				inputStr = buffer.readLine();
				inputStr = inputStr.trim();
				searchString = inputStr;
				
			} else if (searchOption.equals("3")) {				// to display all books
				
				System.out.println("Displaying All Books...");
				searchString = "allBooks";
			
			} else if (searchOption.equals("4")) {				// to exit search
				
				throw new CustomException("Exiting Search Books...\n\n");
				
			}
			
			searchMap = new HashMap<String , String>();
			searchMap.put(searchOption , searchString);
			
		} catch (CustomException | IOException e) {
			
		}
		
		
		return searchMap;
	}
	

	public static Map<String, String> getSubjectSearchOptionFromUser(BufferedReader buffer) {
		String inputStr = null;
		String searchOption = null;
		String searchString = null;
		
		Map<String , String> searchMap = null;
		
		List<String> inputCheckList = new ArrayList<String>();
		inputCheckList.add("1");
		inputCheckList.add("2");
		inputCheckList.add("3");
		inputCheckList.add("4");
		
		try {
			do {
				System.out.println("Select [1] to search by subject id");
				System.out.println("Select [2] to search by subject title");
				System.out.println("Select [3] to display all subjects");
				System.out.println("Select [4] to exit search...");
				inputStr = buffer.readLine();
				inputStr = inputStr.trim();
				
				if (!inputCheckList.contains(inputStr)) {
					System.out.println("\n\nPlease select correct option...\n\n");
					inputStr = "loopAgain";
				}
			} while(!inputStr.matches("[1-4]{1}"));
			
			searchOption = inputStr;
			
			if (searchOption.equals("1")) {						// to search by book id
				
				do {
					System.out.print("Enter the Subject Id to search (only numbers) ::");
					inputStr = buffer.readLine();
					inputStr = inputStr.trim();
				} while (!inputStr.matches("[0-9]{1,}"));
				searchString = inputStr;
				
			} else if (searchOption.equals("2")) {				// to search by book title
				
				System.out.print("Enter the Subject title :: ");
				inputStr = buffer.readLine();
				inputStr = inputStr.trim();
				searchString = inputStr;
				
			} else if (searchOption.equals("3")) {				// to display all books
				
				System.out.println("Displaying All Subjects...");
				searchString = "allSubjects";
			
			} else if (searchOption.equals("4")) {				// to exit search
				
				throw new CustomException("Exiting Search Subjects...\n\n");
				
			}
			
			searchMap = new HashMap<String , String>();
			searchMap.put(searchOption , searchString);
			
			
			
		} catch (CustomException | IOException e) {
			System.out.println("exception in getSubjectSearchOptionFromUser :: " + e.getMessage());
		}
		return searchMap;
	}
	
	
	@SuppressWarnings("unchecked")
	public static void display(Object dtoList , String listType) {
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

	public static Map<String, String> getSearchMapToSortBooksByTitle(BufferedReader buffer) {
		Map<String , String> searchMap = new HashMap<String , String>();
		searchMap.put("3" , "allBooks");
		return searchMap;
	}

	public static Map<String, String> getSearchMapToSortSubjectsByTitle(BufferedReader buffer) {
		Map<String , String> searchMap = new HashMap<String , String>();
		searchMap.put("3" , "allSubjects");
		return searchMap;
	}

}
