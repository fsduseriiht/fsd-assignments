package com.cts.fsd.tasktracker.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Amitabha Das [420652]
 *
 */
@Entity
@Table(name = "PARENT_TASK_TABLE")
public class ParentTaskEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "PARENT_ID")
	private long parentId;
	

	@Column(name = "PARENT_TASK")
	private String parentTask;


	/**
	 * @return long
	 */
	public long getParentId() {
		return parentId;
	}


	/**
	 * @param parentId
	 */
	public void setParentId(long parentId) {
		this.parentId = parentId;
	}


	/**
	 * @return String
	 */
	public String getParentTask() {
		return parentTask;
	}


	/**
	 * @param parentTask
	 */
	public void setParentTask(String parentTask) {
		this.parentTask = parentTask;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ParentTaskEntity [parentId=" + parentId + ", parentTask="
				+ parentTask + "]";
	}

	
}
