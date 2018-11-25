package com.cts.fsd.repo;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cts.fsd.entity.BookEntity;


/**
 * @author Amitabha Das [420652]
 *
 */
@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long>{
	
	
	/**
	 * Deletes a specific ParentTask Record from the Task Table based on a parent id
	 * @param parentId
	 */
	@Transactional
    @Modifying
    @Query("delete from BookEntity b where b.bookId=:id")
    public void deleteBookById(@Param("id") Long bookId);
	
	
	
	@Transactional
    @Modifying
    @Query("FROM BookEntity B where LOWER(B.title) like '%' || LOWER(:title) || '%' order by B.bookId asc")
    public List<BookEntity> findBookByTitle(@Param("title") String title);
	
}
