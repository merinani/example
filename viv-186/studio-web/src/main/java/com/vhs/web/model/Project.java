package com.vhs.web.model;

import java.util.Date;

public class Project {

	private long id;
	
    private String projectName;
    private String version;
    private Date lastUpdated;
    private String lastUpdatedUser;
	private boolean currentUserHasAccess;
	
	public Project(){
		id = 0;
	}
	
	public Project(long id, String projectName, String version, Date lastUpdated, String lastUpdatedUser){
		this.id = id;
		this.projectName = projectName;
		this.version = version;
		this.lastUpdated = lastUpdated;
		this.lastUpdatedUser = lastUpdatedUser;
		this.currentUserHasAccess = currentUserHasAccess;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	
	public String getLastUpdatedUser() {
		return lastUpdatedUser;
	}

	public void setLastUpdatedUser(String lastUpdatedUser) {
		this.lastUpdatedUser = lastUpdatedUser;
	}
	
	public boolean getCurrentUserHasAccess() {
		return currentUserHasAccess;
	}

	public void setCurrentUserHasAccess(boolean currentUserHasAccess) {
		this.currentUserHasAccess = currentUserHasAccess;
	}	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Project))
			return false;
		Project other = (Project) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", projectName=" + projectName + ", version=" + version
				+ ", lastUpdated=" + lastUpdated + ", lastUpdatedUser=" + lastUpdatedUser
				+ ", currentUserHasAccess=" + currentUserHasAccess + "]";
	}
}
