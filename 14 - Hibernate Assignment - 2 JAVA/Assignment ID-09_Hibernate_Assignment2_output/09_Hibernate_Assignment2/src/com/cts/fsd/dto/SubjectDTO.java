package com.cts.fsd.dto;

import java.util.Set;

public class SubjectDTO {
	
	private long subjectId;
	
	private String subtitle;
	
	private int durationInHours;
	
	private Set<BookDTO> references;
	
	
	public long getSubjectId() {
		return subjectId;
	}



	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}



	public String getSubtitle() {
		return subtitle;
	}



	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}



	public int getDurationInHours() {
		return durationInHours;
	}



	public void setDurationInHours(int durationInHours) {
		this.durationInHours = durationInHours;
	}



	public Set<BookDTO> getReferences() {
		return references;
	}



	public void setReferences(Set<BookDTO> references) {
		this.references = references;
	}



	@Override
	public String toString() {
		return "SubjectDTO [subjectId=" + subjectId + ", subtitle=" + subtitle + ", durationInHours=" + durationInHours
				+ ", references=" + references + "]";
	}



	/*@Override
	public String toString() {
//		StringBuffer bookBuffer = new StringBuffer();
//		references.forEach(book -> bookBuffer.append(book.getTitle() + "; "));
//		return  String.format("%6s\t%20s\t%8s\t%s" , subjectId , subtitle , durationInHours , "["+new StringBuffer().append(bookBuffer.toString().substring(0 , bookBuffer.toString().lastIndexOf("; "))).toString()+"]");
//		
		return "Subject [subjectId=" + subjectId + ", subtitle=" + subtitle
				+ ", durationInHours=" + durationInHours + ", references="
				+ references + "]";
	}*/

}
