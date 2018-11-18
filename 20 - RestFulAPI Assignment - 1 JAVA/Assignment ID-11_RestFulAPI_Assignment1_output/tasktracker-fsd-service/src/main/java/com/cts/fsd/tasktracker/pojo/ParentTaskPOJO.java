package com.cts.fsd.tasktracker.pojo;

import org.springframework.stereotype.Component;

/**
 * @author Amitabha Das [420652]
 * ParentTaskPOJO Object that interacts with UI and Controller
 */
@Component
public class ParentTaskPOJO {
	
	/**
	 * parentId variable
	 */
	private int parentId;

	/**
	 * parentTask variable
	 */
	private String parentTask;

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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ParentTaskPOJO [parentId=" + parentId + ", parentTask=" + parentTask + "]";
	}
	
	
}
