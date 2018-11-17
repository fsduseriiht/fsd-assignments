package com.cts.fsd.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.sql.Date;

public class BookEntity implements Serializable {
	/**
	 * Added serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private long bookId;
	
	private String title;
	
	private double price;
	
	private int volume;
	
	private Date publishDate;
	
	private long subjectId;
	

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


	public long getSubjectId() {
		return subjectId;
	}


	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}


	@Override
	public String toString() {
		return "BookEntity [bookId=" + bookId + ", title=" + title + ", price=" + price + ", volume=" + volume
				+ ", publishDate=" + publishDate + ", subjectId=" + subjectId + "]";
	}


//	@Override
//	public String toString() {
//		return String.format("%6s\t%20s\t%6s\t%10s\t%11s\t%6s", bookId , title , price , volume , 
//				new SimpleDateFormat("dd-MMM-yyyy").format(publishDate) , subjectId);
//	}
	
}
