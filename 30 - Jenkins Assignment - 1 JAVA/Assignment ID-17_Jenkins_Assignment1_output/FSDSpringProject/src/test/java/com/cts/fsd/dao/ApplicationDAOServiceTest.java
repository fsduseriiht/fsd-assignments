package com.cts.fsd.dao;

import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cts.fsd.entity.BookEntity;
import com.cts.fsd.entity.SubjectEntity;
import com.cts.fsd.repo.BookRepository;
import com.cts.fsd.repo.SubjectRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationDAOServiceTest {

	@MockBean
	protected SubjectRepository subjectRepository;
	
	@MockBean
	protected BookRepository bookRepository;
	
	@Autowired
	ApplicationDAOService applicationDAOService;

	
	
	/*============== Test Cases Start ==============*/
	
	@Test
	public void testSampleService() {
		Assert.assertTrue(this.subjectRepository.getClass().getSimpleName().startsWith("SubjectRepository"));
		Assert.assertTrue(this.bookRepository.getClass().getSimpleName().startsWith("BookRepository"));
	}
	
	@Test
	public void testGetAllSubjectsFromDB() {
		List<SubjectEntity> subjectEntityList = new ArrayList<SubjectEntity>();
		
		Mockito.when(subjectRepository.findAll()).thenReturn(subjectEntityList);
		
		List<SubjectEntity> result = applicationDAOService.getAllSubjectsFromDB();
		Assert.assertNotNull(result);
	}
	
	@Test
	public void testGetAllBooksFromDB() {
		List<BookEntity> bookEntityList = new ArrayList<BookEntity>();
		
		Mockito.when(bookRepository.findAll()).thenReturn(bookEntityList);
		
		List<BookEntity> result = applicationDAOService.getAllBooksFromDB();
		Assert.assertNotNull(result);
	}
	
	@Test
	public void testAddBookToDB() {
		BookEntity bookEntity = new BookEntity();
		List<BookEntity> bookEntityList = new ArrayList<BookEntity>();
		bookEntityList.add(bookEntity);
		Mockito.when(bookRepository.saveAll((List<BookEntity>)any(List.class))).thenReturn(bookEntityList);
		
		boolean result = applicationDAOService.addBookToDB(bookEntity);
		Assert.assertNotNull(Boolean.toString(result));
		Assert.assertTrue(result);
	}
	
	@Test
	public void testAddSubjectToDB() {
		SubjectEntity subjectEntity = new SubjectEntity();
		List<SubjectEntity> subjectEntityList = new ArrayList<SubjectEntity>();
		subjectEntityList.add(subjectEntity);
		Mockito.when(subjectRepository.saveAll((List<SubjectEntity>)any(List.class))).thenReturn(subjectEntityList);
		
		boolean result = applicationDAOService.addSubjectToDB(subjectEntity);
		Assert.assertNotNull(Boolean.toString(result));
		Assert.assertTrue(result);
	}
	
	@Test
	public void testDeleteSubjectFromDB() {
		SubjectEntity subjectEntity = new SubjectEntity();
		subjectEntity.setSubjectId(000000);
		Mockito.doNothing().when(subjectRepository).delete(any(SubjectEntity.class));
		Optional<SubjectEntity> dbResponse = Optional.of(subjectEntity);
		Mockito.when(subjectRepository.findById(any(Long.class))).thenReturn(dbResponse);
		
		boolean result = applicationDAOService.deleteSubjectFromDB(subjectEntity);
		Assert.assertNotNull(Boolean.toString(result));
		Assert.assertFalse(result);
	}

	
	@Test
	public void testDeleteBookFromDB() {
		BookEntity bookEntity = new BookEntity();
		bookEntity.setBookId(000000);
		
		Mockito.doNothing().when(bookRepository).delete(any(BookEntity.class));
		
		Optional<BookEntity> dbResponse = Optional.of(bookEntity);
		Mockito.when(bookRepository.findById(any(Long.class))).thenReturn(dbResponse);
		
		boolean result = applicationDAOService.deleteBookFromDB(bookEntity);
		Assert.assertNotNull(Boolean.toString(result));
		Assert.assertFalse(result);
	}
	
	@Test
	public void testSearchBookByIdFromDB() {
		BookEntity bookEntity = new BookEntity();
		
		Optional<BookEntity> dbResponse = Optional.of(bookEntity);
		Mockito.when(bookRepository.findById(any(Long.class))).thenReturn(dbResponse);
		
		List<BookEntity> result = applicationDAOService.searchBookByIdFromDB(bookEntity);
		Assert.assertNotNull(result);
		Assert.assertTrue(!result.isEmpty());
	}
	
	@Test
	public void testSearchBookByTitleFromDB() {
		BookEntity bookEntity = new BookEntity();
		bookEntity.setTitle("fake_title");
		List<BookEntity> bookEntityList = new ArrayList<BookEntity>();
		bookEntityList.add(bookEntity);
		
		Mockito.when(bookRepository.findBookByTitle(any(String.class))).thenReturn(bookEntityList);
		
		List<BookEntity> result = applicationDAOService.searchBookByTitleFromDB(bookEntity);
		Assert.assertNotNull(result);
		Assert.assertTrue(!result.isEmpty());
	}
	
	@Test
	public void testSearchSubjectByIdFromDB() {
		SubjectEntity subjectEntity = new SubjectEntity();
		
		Optional<SubjectEntity> dbResponse = Optional.of(subjectEntity);
		Mockito.when(subjectRepository.findById(any(Long.class))).thenReturn(dbResponse);
		
		List<SubjectEntity> result = applicationDAOService.searchSubjectByIdFromDB(subjectEntity);
		Assert.assertNotNull(result);
		Assert.assertTrue(!result.isEmpty());
	}
	
	@Test
	public void testSearchSubjectByTitleFromDB() {
		SubjectEntity subjectEntity = new SubjectEntity();
		subjectEntity.setSubtitle("fake_subtitle");
		List<SubjectEntity> subjectEntityList = new ArrayList<SubjectEntity>();
		subjectEntityList.add(subjectEntity);
		
		Mockito.when(subjectRepository.findSubjectByTitle(any(String.class))).thenReturn(subjectEntityList);
		
		List<SubjectEntity> result = applicationDAOService.searchSubjectByTitleFromDB(subjectEntity);
		Assert.assertNotNull(result);
		Assert.assertTrue(!result.isEmpty());
	}
	

}
