package com.cts.fsd.mapper;

import java.sql.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import com.cts.fsd.dto.BookDTO;
import com.cts.fsd.dto.SubjectDTO;
import com.cts.fsd.entity.BookEntity;
import com.cts.fsd.entity.SubjectEntity;



public class ObjectMappper {

	public SubjectDTO mapEntityToPojo(SubjectEntity subjectEntity) {
		SubjectDTO subjectDTO = null;
		Set<BookDTO> referencesDTO = null;
		BookDTO bookDTO = null;
		if (null != subjectEntity) {
			subjectDTO = new SubjectDTO();
			subjectDTO.setSubjectId(subjectEntity.getSubjectId());
			subjectDTO.setSubtitle(subjectEntity.getSubtitle());
			subjectDTO.setDurationInHours(subjectEntity.getDurationInHours());
			
			referencesDTO = new LinkedHashSet<BookDTO>();
			
			if (null != subjectEntity.getReferences() && !subjectEntity.getReferences().isEmpty()) {
				for(BookEntity bookEntity : subjectEntity.getReferences()) {
					bookDTO = mapEntityToPojo(bookEntity);
					referencesDTO.add(bookDTO);
				}
			}
			subjectDTO.setReferences(referencesDTO);
		}
		
		return subjectDTO;
	}
	
	public SubjectEntity mapPojoToEntity(SubjectDTO subjectDTO ) {
		SubjectEntity subjectEntity = null;
		Set<BookEntity> referencesEntity = null;
		BookEntity bookEntity = null;
		if (null != subjectDTO) {
			subjectEntity = new SubjectEntity();
			subjectEntity.setSubjectId(subjectDTO.getSubjectId());
			subjectEntity.setSubtitle(subjectDTO.getSubtitle());
			subjectEntity.setDurationInHours(subjectDTO.getDurationInHours());
			
			referencesEntity = new LinkedHashSet<BookEntity>();
			
			if (null != subjectDTO.getReferences() && !subjectDTO.getReferences().isEmpty()) {
				
				for(BookDTO bookDTO : subjectDTO.getReferences()) {
					bookEntity = mapPojoToEntity(bookDTO);
					referencesEntity.add(bookEntity);
				}
				
			}
			
			subjectEntity.setReferences(referencesEntity);
		}
		
		return subjectEntity;
	}
	
	public BookDTO mapEntityToPojo(BookEntity bookEntity) {
		BookDTO bookDTO = null;
		
		if(null != bookEntity) {
			bookDTO = new BookDTO();
			bookDTO.setBookId(bookEntity.getBookId());
			bookDTO.setTitle(bookEntity.getTitle());
			bookDTO.setPrice(bookEntity.getPrice());
			bookDTO.setVolume(bookEntity.getVolume());
			bookDTO.setPublishDate(bookEntity.getPublishDate().toLocalDate());
		}
		return bookDTO;
	}

	public BookEntity mapPojoToEntity(BookDTO bookDTO) {
		BookEntity bookEntity = null;
		
		if(null != bookDTO) {
			bookEntity = new BookEntity();
			bookEntity.setBookId(bookDTO.getBookId());
			bookEntity.setTitle(bookDTO.getTitle());
			bookEntity.setPrice(bookDTO.getPrice());
			bookEntity.setVolume(bookDTO.getVolume());
			bookEntity.setPublishDate(Date.valueOf(bookDTO.getPublishDate()));
			
			SubjectEntity subjectEntity = mapPojoToEntity(bookDTO.getSubjectDTO());
			
			bookEntity.setSubjectEntity(subjectEntity);
		}
		return bookEntity;
		
	}

}
