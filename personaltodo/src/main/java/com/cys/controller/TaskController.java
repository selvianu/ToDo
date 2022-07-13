package com.cys.controller;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cys.dao.TaskDao;
import com.cys.model.Task;

public class TaskController {

	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	TaskDao taskDao;

	@RequestMapping("/")
	public String home() {
		return "addTask.jsp";
	}

	@GetMapping("/inserttask")
	public String saveTask(@RequestParam("taskName") String taskName) {
		// log.info(" insert {}", taskName);
		System.out.println("saving tas...");
		Task t = new Task();
//		t.taskId = taskId;
		t.setTaskName(taskName);
		t.setStatus("Pending");
		String sDate1 = "12/07/2022";
		java.util.Date date1 = null;
		try {
			date1 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		t.setCreatedDate((Date) date1);	
		System.out.println("Task Name : " + t.getTaskName() + "status: " + t.getStatus() + t.getCreatedDate());
		taskDao.save(t);
		return "success2.jsp";
	}

	@GetMapping("/updatetask")
	public void updateTaskName(@RequestParam("taskId") Integer taskId, @RequestParam("taskName") String taskName) {
		Task t = new Task();
		t.setTaskId(taskId);
		t.setTaskName(taskName);
		taskDao.updateTaskName(t);
		System.out.println("Task name update by id");
	}

	@GetMapping("/updatetaskstatus")
	public void updateTaskStatus(@RequestParam("taskId") Integer taskId, @RequestParam("status") String status) {
		Task t = new Task();
		t.setTaskId(taskId);
		t.setStatus(status);
		taskDao.updateTaskStatus(t);
		System.out.println("Task name update by id");
	}

}
