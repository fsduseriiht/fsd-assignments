package com.cts.fsd.service;

import static org.mockito.ArgumentMatchers.any;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cts.fsd.dao.ApplicationDAOService;
import com.cts.fsd.dto.BookDTO;
import com.cts.fsd.dto.SubjectDTO;
import com.cts.fsd.entity.BookEntity;
import com.cts.fsd.entity.SubjectEntity;
import com.cts.fsd.mapper.ObjectMappper;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationServiceTest {

	@MockBean
	private ApplicationDAOService applicationDAOService;

	@MockBean
	private ObjectMappper objectMappper;

	@Autowired
	ApplicationService applicationService;

	/*============== Test Cases Start ==============*/
	
	@Test
	public void testSampleService() {
		Assert.assertTrue(this.applicationDAOService.getClass().getSimpleName().startsWith("ApplicationDAOService"));
	}

	@Test
	public void testGetAllSubjectsFromStorage() {
		List<SubjectEntity> subjectEntityList = new ArrayList<SubjectEntity>();
		SubjectEntity subjectEntity = new SubjectEntity();
		SubjectDTO subjectDTO  = new SubjectDTO();
		
		subjectEntityList.add(subjectEntity);
		Mockito.when(applicationDAOService.getAllSubjectsFromDB()).thenReturn(subjectEntityList);
		Mockito.when(objectMappper.mapEntityToPojo(any(SubjectEntity.class))).thenReturn(subjectDTO);
		
		List<SubjectDTO> result = applicationService.getAllSubjectsFromStorage();
		Assert.assertNotNull(result);
	}

	@Test
	public void testGetAllBooksFromStorage() {
		List<BookEntity> bookEntityList = new ArrayList<BookEntity>();
		BookEntity bookEntity = new BookEntity();
		BookDTO bookDTO  = new BookDTO();
		
		bookEntityList.add(bookEntity);
		Mockito.when(applicationDAOService.getAllBooksFromDB()).thenReturn(bookEntityList);
		Mockito.when(objectMappper.mapEntityToPojo(any(BookEntity.class))).thenReturn(bookDTO);
		
		List<SubjectDTO> result = applicationService.getAllSubjectsFromStorage();
		Assert.assertNotNull(result);
	}

	@Test
	public void testAddBookToStorage() {
		BookEntity bookEntity = new BookEntity();
		boolean status = true;
		BookDTO bookDTO = new BookDTO();
		bookDTO.setPublishDate(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		
		Mockito.when(applicationDAOService.addBookToDB(any(BookEntity.class))).thenReturn(status);
		Mockito.when(objectMappper.mapPojoToEntity(any(BookDTO.class))).thenReturn(bookEntity);
		
		boolean result = applicationService.addBookToStorage(bookDTO);
		Assert.assertNotNull(Boolean.toString(result));
		Assert.assertTrue(result);
	}

	@Test
	public void testAddSubjectToStorage() {
		SubjectEntity subjectEntity = new SubjectEntity();
		SubjectDTO subjectDTO = new SubjectDTO();
		boolean status = true;
		
		Mockito.when(applicationDAOService.addSubjectToDB(any(SubjectEntity.class))).thenReturn(status);
		Mockito.when(objectMappper.mapPojoToEntity(any(SubjectDTO.class))).thenReturn(subjectEntity);
		
		boolean result = applicationService.addSubjectToStorage(subjectDTO);
		Assert.assertNotNull(Boolean.toString(result));
		Assert.assertTrue(result);
	}

	@Test
	public void testDeleteSubjectFromStorage() {
		SubjectEntity subjectEntity = new SubjectEntity();
		SubjectDTO subjectDTO = new SubjectDTO();
		boolean deleteStatus = true;
		
		Mockito.when(applicationDAOService.deleteSubjectFromDB(any(SubjectEntity.class))).thenReturn(deleteStatus);
		Mockito.when(objectMappper.mapPojoToEntity(any(SubjectDTO.class))).thenReturn(subjectEntity);
		
		boolean result = applicationService.deleteSubjectFromStorage(subjectDTO);
		Assert.assertNotNull(Boolean.toString(result));
		Assert.assertTrue(result);
	}

	@Test
	public void testDeleteBookFromStorage() {
		BookEntity bookEntity = new BookEntity();
		BookDTO bookDTO = new BookDTO();
		boolean deleteStatus = true;
		
		bookDTO.setPublishDate(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		
		Mockito.when(applicationDAOService.deleteBookFromDB(any(BookEntity.class))).thenReturn(deleteStatus);
		Mockito.when(objectMappper.mapPojoToEntity(any(BookDTO.class))).thenReturn(bookEntity);
		
		boolean result = applicationService.deleteBookFromStorage(bookDTO);
		Assert.assertNotNull(Boolean.toString(result));
		Assert.assertTrue(result);
	}

	@Test
	public void testSearchBookByIdFromStorage() {
		String bookId = "0000";
		List<BookEntity> bookEntityList = new ArrayList<BookEntity>();
		BookDTO bookDTO = new BookDTO();
		
		Mockito.when(applicationDAOService.searchBookByIdFromDB(any(BookEntity.class))).thenReturn(bookEntityList);
		Mockito.when(objectMappper.mapEntityToPojo(any(BookEntity.class))).thenReturn(bookDTO);
		
		List<BookDTO> result = applicationService.searchBookByIdFromStorage(bookId);
		Assert.assertNotNull(result);
	}
	
	@Test
	public void testSearchBookByTitleFromStorage() {
		String title = "fake_title";
		List<BookEntity> bookEntityList = new ArrayList<BookEntity>();
		BookDTO bookDTO = new BookDTO();
		
		Mockito.when(applicationDAOService.searchBookByTitleFromDB(any(BookEntity.class))).thenReturn(bookEntityList);
		Mockito.when(objectMappper.mapEntityToPojo(any(BookEntity.class))).thenReturn(bookDTO);
		
		List<BookDTO> result = applicationService.searchBookByTitleFromStorage(title);
		Assert.assertNotNull(result);
	}
	
	
	@Test
	public void testSearchSubjectByIdFromStorage() {
		String subjectId = "0000";
		List<SubjectEntity> subjectEntityList = new ArrayList<SubjectEntity>();
		SubjectDTO subjectDTO = new SubjectDTO();
		
		Mockito.when(applicationDAOService.searchSubjectByIdFromDB(any(SubjectEntity.class))).thenReturn(subjectEntityList);
		Mockito.when(objectMappper.mapEntityToPojo(any(SubjectEntity.class))).thenReturn(subjectDTO);
		
		List<SubjectDTO> result = applicationService.searchSubjectByIdFromStorage(subjectId);
		Assert.assertNotNull(result);
	}
	
	@Test
	public void testSearchSubjectByTitleFromStorage() {
		String subjectTitle = "fake_subjectTitle";
		List<SubjectEntity> subjectEntityList = new ArrayList<SubjectEntity>();
		SubjectDTO subjectDTO = new SubjectDTO();
		
		Mockito.when(applicationDAOService.searchSubjectByIdFromDB(any(SubjectEntity.class))).thenReturn(subjectEntityList);
		Mockito.when(objectMappper.mapEntityToPojo(any(SubjectEntity.class))).thenReturn(subjectDTO);
		
		List<SubjectDTO> result = applicationService.searchSubjectByTitleFromStorage(subjectTitle);
		Assert.assertNotNull(result);
	}

}
