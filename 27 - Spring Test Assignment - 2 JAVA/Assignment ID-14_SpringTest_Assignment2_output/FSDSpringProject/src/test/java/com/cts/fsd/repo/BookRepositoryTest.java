package com.cts.fsd.repo;

import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.cts.fsd.entity.BookEntity;
import com.cts.fsd.entity.SubjectEntity;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class BookRepositoryTest {
	
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
    private BookRepository bookRepository;
	

	@Test
	public void testDeleteBookById() {
		
		Long bookId = new Long(33333);
		BookEntity bookEntity = new BookEntity();
		bookEntity.setBookId(bookId);
		
		entityManager.merge(bookEntity);
		
		bookRepository.deleteBookById(bookEntity.getBookId());
		
		Optional<BookEntity> result = bookRepository.findById(bookId);
		Assert.assertNotNull(result);
		
	}

	@Test
	public void testFindBookByTitle() {
		
		String title = "fake_title";
		
		BookEntity bookEntity = new BookEntity();
		bookEntity.setTitle(title);
		
		entityManager.merge(bookEntity);
		
		List<BookEntity> result = bookRepository.findBookByTitle(title);
		Assert.assertNotNull(result);
	}

}
