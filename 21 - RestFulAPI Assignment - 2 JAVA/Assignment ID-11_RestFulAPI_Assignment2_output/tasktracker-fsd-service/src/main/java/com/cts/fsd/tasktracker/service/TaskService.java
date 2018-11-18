package com.cts.fsd.tasktracker.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.fsd.tasktracker.entity.ParentTaskEntity;
import com.cts.fsd.tasktracker.entity.TaskEntity;
import com.cts.fsd.tasktracker.exception.ResourceNotFoundException;
import com.cts.fsd.tasktracker.mapper.TaskTrackerMapper;
import com.cts.fsd.tasktracker.pojo.TaskPOJO;
import com.cts.fsd.tasktracker.repo.TaskRepository;

/**
 * @author Amitabha Das [420652]
 * TaskService interacts between the controller and the datasources using jpa repository
 */
@Service
public class TaskService {
	
	/**
	 * Object TaskTrackerMapper mapper
	 */
	@Autowired
	protected TaskTrackerMapper mapper;
	
	/**
	 * Object TaskRepository taskRepository
	 */
	@Autowired
	protected TaskRepository taskRepository;
	
	/**
	 * Object ParentTaskService parentTaskService
	 */
	@Autowired
	protected ParentTaskService parentTaskService;
	
	/**
	 * createTasks() is used to create a task record in the task table sent in the request
	 * @param taskPOJOList
	 * @return List<TaskPOJO>
	 */
	public List<TaskPOJO> createTasks(List<TaskPOJO> taskPOJOList){
		List<TaskEntity> taskEntityList = new ArrayList<TaskEntity>();
		List<TaskPOJO> returnPojoList = new ArrayList<TaskPOJO>();
		
		if (null != taskPOJOList && !taskPOJOList.isEmpty()) {
			for(TaskPOJO taskPOJO :  taskPOJOList ) {
				
				ParentTaskEntity parentTaskEntityFromDB = parentTaskService.getParentTaskById(taskPOJO.getParentId());
				System.out.println("parentTaskEntityFromDB = " + parentTaskEntityFromDB.toString());
				
				TaskEntity taskEntity = mapper.mapTaskPojoToEntity(taskPOJO);
				if(taskEntity != null ) {
					taskEntity.setParentTaskEntity(parentTaskEntityFromDB);
				}
				taskEntityList.add(taskEntity);
			}
		}
		
		List<TaskEntity> dbResponse = taskRepository.saveAll(taskEntityList);
		System.out.println("createTaskDump() dbResponse = " + dbResponse);
		
		if (null != dbResponse && !dbResponse.isEmpty()) {
			for(TaskEntity taskEntity :  dbResponse ) {
				TaskPOJO taskPOJO = mapper.mapTaskEntityToPojo(taskEntity);
				returnPojoList.add(taskPOJO);
			}
		}
		
		return returnPojoList;
	}


	/**
	 * getAllTasks() is used to get all the records in task table 
	 * @return List<TaskPOJO>
	 */
	public List<TaskPOJO> getAllTasks() {
		
		List<TaskEntity> dbResponse = taskRepository.findAll();
		System.out.println("getAllTasks() dbResponse = " + dbResponse);
		
		List<TaskPOJO> returnPojoList = new ArrayList<TaskPOJO>();
		
		if (null != dbResponse && !dbResponse.isEmpty()) {
			for(TaskEntity taskEntity :  dbResponse ) {
				TaskPOJO parentTaskPOJO = mapper.mapTaskEntityToPojo(taskEntity);
				returnPojoList.add(parentTaskPOJO);
			}
		}
		return returnPojoList;
	}


	/**
	 * editTaskById() is used to update a task record in db for a particular task id
	 * @param taskId
	 * @param taskPOJO
	 * @return TaskPOJO
	 */
	public TaskPOJO editTaskById(int taskId, TaskPOJO taskPOJO) {
		String editResponse = "";
		TaskEntity taskFromDB = null ;
		TaskPOJO returnPOJO = null;
		try {
			taskFromDB = getTaskById(taskId);
			System.out.println("Updating taskFromDB = " + taskFromDB.toString());
			
			taskFromDB.setTask(taskPOJO.getTask());
			taskFromDB.setStartDate(new java.sql.Date(taskPOJO.getStartDate().getTime()));
			taskFromDB.setEndDate(new java.sql.Date(taskPOJO.getEndDate().getTime()));
			taskFromDB.setPriority(taskPOJO.getPriority());
			
			ParentTaskEntity parentTaskEntityFromDB = parentTaskService.getParentTaskById(taskPOJO.getParentId());
			taskFromDB.setParentTaskEntity(parentTaskEntityFromDB);
			
			taskFromDB =  taskRepository.save(taskFromDB);
			
			editResponse = "Task ID("+taskId+") updated, " + taskFromDB.toString();
			
			returnPOJO = mapper.mapTaskEntityToPojo(taskFromDB);
			
		} catch(ResourceNotFoundException e ) {
			System.out.println("ResourceNotFoundException encountered..." + e);
			
			if(e.getResourceName().equals("ParentTaskEntity")) {
				editResponse = "Things are not updated as ParentTaskEntity record does not exist... ";
			} else {
				editResponse = "Things are not updated as record does not exist... ";
			}
			
			taskFromDB = null;
			returnPOJO = null;
		} catch(Exception e ) {
			System.out.println("Exception encountered..." + e);
			editResponse = "Things are not updated due to Exception... " + e.getMessage();
			taskFromDB = null;
			returnPOJO = null;
		}
		System.out.println("After Task Update :: " + editResponse);
		return returnPOJO;
	}


	/**
	 * getTaskById() is used to get a record from the db based on the task id
	 * @param taskId
	 * @return TaskEntity
	 */
	private TaskEntity getTaskById(int taskId) {
		TaskEntity taskFromDB = null;
		
		try {
			taskFromDB = taskRepository.findById(Long.valueOf(taskId)).get();
			System.out.println("getTaskById successfully returned TaskEntity from DB :: " + taskFromDB.toString());
		} catch (NoSuchElementException e) {
			taskFromDB = null;
			System.out.println("getParentById NOT successfull...\nNoSuchElementException encountered... ResourceNotFoundException thrown" + e);
			throw new ResourceNotFoundException("TaskEntity" , "taskId" , taskId);
		} catch (Exception e ) {
			taskFromDB = null;
			System.out.println("Exception encountered..." + e);
		}
		return taskFromDB;
	}


	/**
	 * removeTaskById() is used to delete a record based on task id from the db
	 * @param taskId
	 * @return boolean
	 */
	public boolean removeTaskById(int taskId) {
		String deleteResponse = "";
		TaskEntity taskFromDB = null;
		boolean returnResponse = false;
		System.out.println("Before Delete Task By Id("+taskId+")");
		
		try {
			taskFromDB =  getTaskById(taskId);
			System.out.println("Deleting taskFromDB = " + taskFromDB.toString());
			
			taskRepository.deleteTaskById(Long.valueOf(taskFromDB.getTaskId()));
			deleteResponse = "Task ID("+taskId+") Deleted, Record No More exists,";
			returnResponse = true;
			
		} catch (ResourceNotFoundException e ) {
			System.out.println("ResourceNotFoundException encountered..." + e);
			deleteResponse = "Things are not deleted as record does not exist... ";
			taskFromDB = null;
			returnResponse = false;
		} catch (Exception e ) {
			System.out.println("Exception encountered..." + e);
			deleteResponse = "Things are not deleted due to Exception... " + e.getMessage();
			taskFromDB = null;
			returnResponse = false;
		}
		
		System.out.println("After Delete :: " + deleteResponse);
		return returnResponse;
	}
}
