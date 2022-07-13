package com.cys.dao;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cys.model.Task;

@Repository
public class TaskDao {
	@Autowired
	JdbcTemplate jdbcTemplate;

	public void save(Task task) {
		String sql = "insert into tasks(task_name, created_date, status) values (?,?,?)";
		Date cdate = task.getCreatedDate();
		System.out.println(cdate);
		Object[] params = { task.getTaskName(), cdate, task.getStatus() };
		int insertTask = jdbcTemplate.update(sql, params);
		System.out.println(insertTask + "rows inserted..");
	}

	public void updateTaskName(Task task) {
		String updatesql = "update task set taskname=? where taskid=?";
		Object[] params = { task.getTaskName(), task.getTaskId() };
		int updateTaskName = jdbcTemplate.update(updatesql, params);
		System.out.println("No of task updated..." + updateTaskName);
	}

	public void updateTaskStatus(Task task) {
		String updatesql = "update task set status=? where taskid=?";
		Object[] params = { task.getStatus(), task.getTaskId() };
		int updateTaskStatus = jdbcTemplate.update(updatesql, params);
		System.out.println("No of task updated..." + updateTaskStatus);
	}

}
