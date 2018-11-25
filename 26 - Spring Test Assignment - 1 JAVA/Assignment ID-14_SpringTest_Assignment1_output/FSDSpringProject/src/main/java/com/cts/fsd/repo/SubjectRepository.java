package com.cts.fsd.repo;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cts.fsd.entity.BookEntity;
import com.cts.fsd.entity.SubjectEntity;


/**
 * @author Amitabha Das [420652]
 *
 */
@Repository
public interface SubjectRepository extends JpaRepository<SubjectEntity, Long>{
	
	
	/**
	 * Deletes a specific ParentTask Record from the Task Table based on a parent id
	 * @param parentId
	 */
	@Transactional
    @Modifying
    @Query("delete from SubjectEntity s where s.subjectId=:id")
    public void deleteSubjectById(@Param("id") Long subjectId);
	
	

	
	@Transactional
    @Modifying
//    @Query(value = "SELECT * FROM USERS WHERE EMAIL_ADDRESS = ?1", nativeQuery = true)
//	@Query(value = "SELECT * FROM SubjectEntity S where LOWER(S.subtitle) like '%' || LOWER(:title) || '%' order by S.subjectId asc" , nativeQuery = true)
    @Query("FROM SubjectEntity S where LOWER(S.subtitle) like '%' || LOWER(:title) || '%' order by S.subjectId asc")
    public List<SubjectEntity> findSubjectByTitle(@Param("title") String title);
}
