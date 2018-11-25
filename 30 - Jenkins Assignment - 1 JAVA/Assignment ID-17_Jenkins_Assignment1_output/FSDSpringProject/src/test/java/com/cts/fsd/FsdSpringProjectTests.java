package com.cts.fsd;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.cts.fsd.dao.ApplicationDAOServiceTest;
import com.cts.fsd.repo.BookRepositoryTest;
import com.cts.fsd.repo.SubjectRepositoryTest;
import com.cts.fsd.service.ApplicationServiceTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	ApplicationDAOServiceTest.class,
	ApplicationServiceTest.class,
	SubjectRepositoryTest.class,
	BookRepositoryTest.class
})
public class FsdSpringProjectTests {

	@Test
	public void loadAllTestCases() {
		System.out.println("Executing Test Cases for ApplicationDAOServiceTest");
		System.out.println("Executing Test Cases for ApplicationServiceTest");
		System.out.println("Executing Test Cases for SubjectRepositoryTest");
		System.out.println("Executing Test Cases for BookRepositoryTest");
	}

}
