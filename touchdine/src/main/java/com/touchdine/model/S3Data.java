package com.touchdine.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Bean class used for holding s3 transaction information.
 * @author ravisha
 *
 */
@Entity
@Table(name = "s3data")
public class S3Data {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    @Column(name = "id")
	    private Long id;
	 
	    @Column(name = "user_name")
	    private String userName;
	 
	    @Column(name = "first_name")
	    private String firstName;
	 
	    @Column(name = "last_name")
	    private String lastName;
	    
	    @Column(name = "file_name")
	    private String fileName;
	    
	    @Column(name = "file_desc")
	    private String fileDesc;
	    
	    @Column(name = "url")
	    private String url;
	    
	    @Column(name = "upload_time")
	    private Date uploadTime;
	    
	    @Column(name = "updated_time")
	    private Date updatedTime;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getFileName() {
			return fileName;
		}

		public void setFileName(String fileName) {
			this.fileName = fileName;
		}

		public String getFileDesc() {
			return fileDesc;
		}

		public void setFileDesc(String fileDesc) {
			this.fileDesc = fileDesc;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public Date getUploadTime() {
			return uploadTime;
		}

		public void setUploadTime(Date uploadTime) {
			this.uploadTime = uploadTime;
		}

		public Date getUpdatedTime() {
			return updatedTime;
		}

		public void setUpdatedTime(Date updatedTime) {
			this.updatedTime = updatedTime;
		}
	    
	    
	   
}
