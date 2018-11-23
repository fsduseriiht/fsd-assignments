package com.cts.fsd.dto;

import java.time.LocalDate;

public class BookDTO {
	
	private long bookId;
	
	private String title;
	
	private double price;
	
	private int volume;
	
	private LocalDate publishDate;
	
	private SubjectDTO subjectDTO;
	
	
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

	public LocalDate getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(LocalDate publishDate) {
		this.publishDate = publishDate;
	}

	public SubjectDTO getSubjectDTO() {
		return subjectDTO;
	}
	public void setSubjectDTO(SubjectDTO subjectDTO) {
		this.subjectDTO = subjectDTO;
	}
	
	
	@Override
	public String toString() {
		return "BookDTO [bookId=" + bookId + ", title=" + title + ", price=" + price + ", volume=" + volume
				+ ", publishDate=" + publishDate + ", subjectDTO=" + subjectDTO + "]";
	}
	
	
	

	/*@Override
	public String toString() {
//		String bookToString = String.format("%6s\t%20s\t%6s\t%10s\t%11s", bookId , title , price , volume , new SimpleDateFormat("dd-MMM-yyyy").format(publishDate));
//		
//		return bookToString;
//		return "Book [bookId=" + bookId + ", title=" + title + ", price="
//				+ price + ", volume=" + volume + ", publishDate=" + publishDate
//				+ "]";
	}*/
	
}
