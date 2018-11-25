package com.cts.fsd.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "BOOK_TABLE")
public class BookEntity implements Serializable {
	
	/**
	 * Added serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "BOOKID")
	private long bookId;
	
	@Column(name = "TITLE")
	private String title;
	
	@Column(name = "PRICE")
	private double price;
	
	@Column(name = "VOLUME")
	private int volume;
	
	@Column(name = "PUBLISHDATE")
	private Date publishDate;
	
	@ManyToOne
	@JoinColumn(name = "SUBJECTID")
	private SubjectEntity subjectEntity;

	
	
	public BookEntity() { }
	
	public BookEntity(String title, double price, int volume, Date publishDate, long subjectId) {
//		super();
		this.title = title;
		this.price = price;
		this.volume = volume;
		this.publishDate = publishDate;
	}


	public long getBookId() {
		return bookId;
	}
	public void setBookId(long bookId) {
		this.bookId = bookId;
	}


	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}


	public int getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}


	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}



	
	public SubjectEntity getSubjectEntity() {
		return subjectEntity;
	}
	public void setSubjectEntity(SubjectEntity subjectEntity) {
		this.subjectEntity = subjectEntity;
	}

	@Override
	public String toString() {
		return "BookEntity [bookId=" + bookId + ", title=" + title + ", price=" + price + ", volume=" + volume
				+ ", publishDate=" + publishDate + ", subjectEntity=" + subjectEntity + "]";
	}




}
