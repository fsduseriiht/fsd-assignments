package com.cts.fsd.tasktracker.pojo;


import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * @author Amitabha Das [420652]
 * TaskPOJO Object that interacts with UI and Controller
 */
@Component
public class TaskPOJO {
	
	/**
	 * variable taskId
	 */
	private int taskId;
	
	/**
	 * variable task
	 */
	private String task;
	
	/**
	 * variable parentId
	 */
	private int parentId;

	/**
	 * variable parentTask
	 */
	private String parentTask;
	
	/**
	 * variable startDate
	 */
	private Date startDate;
	
	/**
	 * variable endDate
	 */
	private Date endDate;
	
	/**
	 * variable priority
	 */
	private int priority;

	
	
	
	
	/**
	 * @return int
	 */
	public int getTaskId() {
		return taskId;
	}

	/**
	 * @param taskId
	 */
	public void setTaskId(int taskId) {
		this.taskId = taskId;
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
	 * @return int
	 */
	public int getParentId() {
		return parentId;
	}

	/**
	 * @param parentId
	 */
	public void setParentId(int parentId) {
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

	/**
	 * @return java.util.Date
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
	 * @return java.util.Date
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
		return "TaskPOJO [taskId=" + taskId + ", task=" + task + ", parentId=" + parentId + ", parentTask=" + parentTask
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", priority=" + priority + "]";
	}

	
	
}
