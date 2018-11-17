package com.cts.fsd.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import com.cts.fsd.controller.ApplicationController;
import com.cts.fsd.controller.UserController;
import com.cts.fsd.dto.BookDTO;
import com.cts.fsd.dto.SubjectDTO;
import com.cts.fsd.exception.CustomException;
import com.cts.fsd.util.ApplicationUtility;


public class Assignment8Main {

	final static String RESOURCES_FOLDER = "./src/resources/requirements/";
	
	static String[] argsToMainMethod = {};

	public static void main(String[] args) {
		BufferedReader buffer = null;
		String inputStr;
		boolean exitProgram = false;
		try {
			System.out.println("==========================================================");
			System.out.println("Please select one of the below options to proceed...");
			System.out.println("==========================================================");
			System.out.println("a.	Add a Subject");
			System.out.println("b.	Add a Book");
			System.out.println("c.	Delete a Subject");
			System.out.println("d.	Delete a book");
			System.out.println("e.	Search for a book");
			System.out.println("f.	Search for a subject");
			System.out.println("g.	Exit");
			System.out.println("==========================================================");
			System.out.print("Value = ");
			buffer = new BufferedReader(new InputStreamReader(System.in));
			inputStr = buffer.readLine();
			
			switch (inputStr) {
			
				case "a":
					ApplicationUtility.displayOptionReceived(inputStr);
					{
						ApplicationUtility.displayHorizontalLine();
						System.out.println("Operation - Add a Subject");
						ApplicationUtility.displayHorizontalLine();
						SubjectDTO subjectDTO = new SubjectDTO();
						subjectDTO = UserController.getSubjectDetailsFromUser(buffer);
						
						ApplicationController.addSubject(subjectDTO);
					}
					break;
	
				case "b":
					ApplicationUtility.displayOptionReceived(inputStr);
					{
						ApplicationUtility.displayHorizontalLine();
						System.out.println("Operation - Add a Book");
						ApplicationUtility.displayHorizontalLine();
						
						BookDTO bookDTO = null;
						bookDTO = UserController.getBookDetailsFromUser(buffer);
						ApplicationController.addBook(bookDTO);
						bookDTO = null;
					}
					break;
	
				case "c":
					ApplicationUtility.displayOptionReceived(inputStr);
					{
						ApplicationUtility.displayHorizontalLine();
						System.out.println("Operation - Delete a Subject");
						ApplicationUtility.displayHorizontalLine();
						
						SubjectDTO subjectDTO = null;
						subjectDTO = UserController.getSubjectToRemoveFromUser(buffer);
						if (null != subjectDTO) {
							ApplicationController.deleteSubject(subjectDTO);
						}
					}
					break;
	
				case "d":
					ApplicationUtility.displayOptionReceived(inputStr);
					{
						ApplicationUtility.displayHorizontalLine();
						System.out.println("Operation - Delete a Book");
						ApplicationUtility.displayHorizontalLine();
						
						BookDTO bookDTO = null;
						bookDTO = UserController.getBookToRemoveFromUser(buffer);
						if (null != bookDTO) {
							ApplicationController.deleteBook(bookDTO);
						}
					}
					break;
	
				case "e":
					ApplicationUtility.displayOptionReceived(inputStr);
					{
						ApplicationUtility.displayHorizontalLine();
						System.out.println("Operation - Search a Book");
						ApplicationUtility.displayHorizontalLine();
						
						Map<String , String> searchMap = UserController.getBookSearchOptionFromUser(buffer);
						if (null != searchMap && !searchMap.isEmpty()) {
							List<BookDTO> bookDTOList = ApplicationController.searchBook(searchMap);
							if (null != bookDTOList && !bookDTOList.isEmpty()) {
								UserController.display(bookDTOList , "book");
							}
						}
						
					}
					break;
	
				case "f":
					ApplicationUtility.displayOptionReceived(inputStr);
					{
						ApplicationUtility.displayHorizontalLine();
						System.out.println("Operation - Search a Subject");
						ApplicationUtility.displayHorizontalLine();
						
						Map<String , String> searchMap = UserController.getSubjectSearchOptionFromUser(buffer);
						
						if (null != searchMap && !searchMap.isEmpty()) {
							List<SubjectDTO> subjectDTOList = ApplicationController.searchSubject(searchMap);
							if (null != subjectDTOList && !subjectDTOList.isEmpty()) {
								UserController.display(subjectDTOList , "subject");
							}
						}
					}
					break;
				
				case "g":
					ApplicationUtility.displayOptionReceived(inputStr);
					System.out.println("\nExiting the program...");
					exitProgram = true;
					break;
				
				default:
					throw new CustomException("Invalid Input... Please try again...\n\n");
			}
			
			
			
			throw new CustomException("--------------------");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CustomException e) {
			System.out.println(e.getMessage());
			if(!exitProgram) {
				main(argsToMainMethod);
			}
		} finally {
			try {
				buffer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
}
