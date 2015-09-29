package com.vhs.web.service;

import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import com.vhs.web.model.Project;

@Service("projectService")
@Transactional
@Repository
public class ProjectServiceImpl implements ProjectService {
	
	private static List<Project> projects;
	@Autowired
	private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
	private String currentUsername = null;	
	
	public List<Project> findAllProjects() {
		currentUsername = null;
		if(null == currentUsername)
		{
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			currentUsername = auth.getName(); //get logged in username
		}
		String sql = "SELECT * FROM PROJECTDETAILS LEFT OUTER JOIN USERACCESS ON PROJECTDETAILS.PROJECTNAME = USERACCESS.PROJECTNAME ";
		projects = jdbcTemplate.query(sql, new RowMapper<Project>() {
 
			@Override
			public Project mapRow(ResultSet rs, int rowNum) throws SQLException {
				Project project = new Project();
				project.setProjectName(rs.getString("PROJECTNAME"));
				project.setVersion(rs.getString("VERSION"));
				project.setLastUpdated(rs.getDate("LASTUPDATED"));
				project.setLastUpdatedUser(rs.getString("LASTUPDATEDUSER"));
				project.setCurrentUserHasAccess(currentUsername == rs.getString("USERNAME"));
				return project;
			}
		});
		
		return projects;
	}
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource); 
   }	
}
