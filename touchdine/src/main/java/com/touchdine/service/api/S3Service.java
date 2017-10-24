package com.touchdine.service.api;

import org.springframework.web.multipart.MultipartFile;

/**
 * API for interacting with S3 service.
 * 
 * @author ravisha
 *
 */
public interface S3Service {
	public Object add(String name, MultipartFile mfile);

	public void delete(String fileName);

	public Object read(String fileName);

	public void setCredentialForBucketAccess(String userName, String accessId, String secAccessId);
}
