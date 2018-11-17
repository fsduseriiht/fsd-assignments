package com.cts.fsd.util;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.cts.fsd.Book;
import com.cts.fsd.Subject;
import com.cts.fsd.exception.CustomException;


@SuppressWarnings("unchecked")
public class FSDAppUtil {
	
	private static String realContextPath;
	
	public static String getRealContextPath() {
		return realContextPath;
	}

	public static void setRealContextPath(String realContextPath) {
		FSDAppUtil.realContextPath = realContextPath;
	}
	
	final static String RESOURCE_LOCATION = /*File.separator + */"WEB-INF" + File.separator + "classes" + File.separator;

	@Autowired
	Book newBook;
	
	@Autowired
	Subject newSubject;
	
	private static FileOutputStream FOS;
	private static ObjectOutputStream OBJ_OUT;
	private static FileInputStream FIS;
	private static ObjectInputStream OBJ_IN;

	
	public static void writeObjectToFile(Object objectToWrite , String fileName) {
		try {
			String fileNameWithPath = getRealContextPath() + RESOURCE_LOCATION + fileName + ".ser";
			initializeFileSerializer(fileNameWithPath);
			OBJ_OUT.writeObject(objectToWrite);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				FOS.close();
				OBJ_OUT.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	
	public static Object readObjectFromFile(String fileName) {
		Object existingObjects = null;
		try {
			String fileNameWithPath = getRealContextPath() + RESOURCE_LOCATION + fileName + ".ser";
			initializeFileDeserializer(fileNameWithPath);
			existingObjects = OBJ_IN.readObject();
		} catch(EOFException e){
			existingObjects= null;
		} catch (IOException e) {
			existingObjects= null;
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			existingObjects= null;
			e.printStackTrace();
		} finally {
			try {
				FIS.close();
				OBJ_IN.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return existingObjects;
	}
	
	public static void initializeFileDeserializer(String fileNameWithPath) throws IOException {
		
		File file = new File(fileNameWithPath);
		if (!file.exists() || !file.canRead()) {
			initializeFileSerializer(fileNameWithPath);
			List<Book> allBooks = new ArrayList<Book>();
			List<Subject> allSubjects = new ArrayList<Subject>();
			if(fileNameWithPath.contains("AllSubjects.ser")) {
				writeObjectToFile(allSubjects, "AllSubjects");
			} else if (fileNameWithPath.contains("AllBooks.ser")) {
				writeObjectToFile(allBooks, "AllBooks");
			}
		}
		FIS = new FileInputStream(file);
		OBJ_IN = new ObjectInputStream(FIS);
	}
	
	public static void initializeFileSerializer(String fileNameWithPath) throws IOException {
		
		System.out.println("Alone fileNameWithPath = "  +  fileNameWithPath);
		FOS = new FileOutputStream(fileNameWithPath);
		OBJ_OUT = new ObjectOutputStream(FOS);
	}
}
