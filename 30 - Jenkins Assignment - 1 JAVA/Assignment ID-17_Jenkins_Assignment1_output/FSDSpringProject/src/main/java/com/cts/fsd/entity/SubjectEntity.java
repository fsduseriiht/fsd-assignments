package com.cts.fsd.entity;


import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "SUBJECT_TABLE")
public class SubjectEntity implements Serializable {
	
	/**
	 * Added serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "SUBJECTID")
	private long subjectId = 0L;
	
	@Column(name = "SUBTITLE")
	private String subtitle;
	
	@Column(name = "DURATIONINHOURS")
	private int durationInHours;
	
	@OneToMany(mappedBy = "subjectEntity")
	private Set<BookEntity> references;
	

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

	public Set<BookEntity> getReferences() {
		return references;
	}

	public void setReferences(Set<BookEntity> references) {
		this.references = references;
	}

	
	@Override
	public String toString() {
		return "SubjectEntity [subjectId=" + subjectId + ", subtitle=" + subtitle + ", durationInHours="
				+ durationInHours + ", references=" + references + "]";
	}

	@Override
	public boolean equals(Object obj) {
	    if (obj == null) return false;
	    if (!(obj instanceof SubjectEntity))
	        return false;
	    if (obj == this)
	        return true;
	    return this.getSubjectId() == ((SubjectEntity) obj).getSubjectId();
	}
}
