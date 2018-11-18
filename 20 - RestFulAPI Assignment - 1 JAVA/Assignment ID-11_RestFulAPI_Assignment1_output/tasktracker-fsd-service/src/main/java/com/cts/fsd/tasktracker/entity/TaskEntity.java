package com.cts.fsd.tasktracker.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Amitabha Das [420652]
 *
 */
@Entity
@Table(name = "TASK_TABLE")
public class TaskEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "TASK_ID")
	private long taskId;
	
	@OneToOne(fetch = FetchType.LAZY) 
	@JoinColumn(name = "PARENT_ID" )
	private ParentTaskEntity parentTaskEntity;
	
	@Column(name = "TASk")
	private String task;
	
	
	@Column(name = "START_DATE")
	private Date startDate;
	
	
	@Column(name = "END_DATE")
	private Date endDate;
	
	
	@Column(name = "PRIORITY")
	private int priority;


	/**
	 * @return long
	 */
	public long getTaskId() {
		return taskId;
	}


	/**
	 * @param taskId
	 */
	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}


	/**
	 * @return ParentTaskEntity
	 */
	public ParentTaskEntity getParentTaskEntity() {
		return parentTaskEntity;
	}


	/**
	 * @param parentTaskEntity
	 */
	public void setParentTaskEntity(ParentTaskEntity parentTaskEntity) {
		this.parentTaskEntity = parentTaskEntity;
	}


	/**
	 * @return String
	 */
	public String getTask() {
		return task;
	}


	/**
	 * @param task
	 */
	public void setTask(String task) {
		this.task = task;
	}


	/**
	 * @return java.sql.Date
	 */
	public Date getStartDate() {
		return startDate;
	}


	/**
	 * @param startDate
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	/**
	 * @return java.sql.Date
	 */
	public Date getEndDate() {
		return endDate;
	}


	/**
	 * @param endDate
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	/**
	 * @return int
	 */
	public int getPriority() {
		return priority;
	}


	/**
	 * @param priority
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TaskEntity [taskId=" + taskId + ", parentTaskEntity=" + parentTaskEntity + ", task=" + task
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", priority=" + priority + "]";
	}

	

}
