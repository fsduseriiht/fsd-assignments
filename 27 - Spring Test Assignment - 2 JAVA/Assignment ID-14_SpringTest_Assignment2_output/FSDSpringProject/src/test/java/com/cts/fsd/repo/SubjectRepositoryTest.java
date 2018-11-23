package com.cts.fsd.repo;

import static org.junit.Assert.fail;

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

import com.cts.fsd.entity.SubjectEntity;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class SubjectRepositoryTest {
	
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
    private SubjectRepository subjectRepository;
	

	@Test
	public void testDeleteSubjectById() {
		Long subjectId = new Long(1111);
		
		SubjectEntity subjectEntity = new SubjectEntity();
		subjectEntity.setSubjectId(subjectId);
		
		entityManager.merge(subjectEntity);
		
		subjectRepository.deleteSubjectById(subjectEntity.getSubjectId());

		Optional<SubjectEntity> result = subjectRepository.findById(subjectId);
		Assert.assertNotNull(result);
	}

	@Test
	public void testFindSubjectByTitle() {
		String subtitle = "fake_subtitle";
		
		SubjectEntity subjectEntity = new SubjectEntity();
		subjectEntity.setSubtitle(subtitle);
		
		entityManager.merge(subjectEntity);
		
		List<SubjectEntity> result = subjectRepository.findSubjectByTitle(subtitle);
		Assert.assertNotNull(result);
	}

}
