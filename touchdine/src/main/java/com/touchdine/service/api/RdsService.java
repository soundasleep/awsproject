package com.touchdine.service.api;

import java.sql.Date;
import java.util.List;

import com.touchdine.model.S3Data;
import com.touchdine.model.User;
import com.touchdine.repo.S3UploadRepo;
import com.touchdine.repo.UserRepository;

/**
 * API for interacting with Rds Service.
 * 
 * @author ravisha
 *
 */
public interface RdsService {

	public void setRespositry(UserRepository userRepository, S3UploadRepo s3UploadRepo);

	public Object add(String userName, String fileName, String fileDescription, String url);

	public Object update(String userName, String fileDescription, String url);

	public List<S3Data> getAllImagesFromBucket(String userName);

	public User getUser(String userName);

	public Object delete(String fileName);

	public Object read(String userFirstname);
}
