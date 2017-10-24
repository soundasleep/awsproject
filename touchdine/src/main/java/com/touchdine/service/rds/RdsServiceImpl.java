package com.touchdine.service.rds;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import com.touchdine.model.S3Data;
import com.touchdine.model.User;
import com.touchdine.repo.S3UploadRepo;
import com.touchdine.repo.UserRepository;
import com.touchdine.service.api.RdsService;

/**
 * Used for doing operations withs rds.
 * 
 * @author ravisha
 *
 */
public class RdsServiceImpl implements RdsService {

	/**
	 * Used for holding the transaction information of s3 operations.
	 */
	private S3UploadRepo s3UploadRepo;

	/**
	 * Used for holding the user information.
	 */
	private UserRepository userRepository;

	/**
	 * Used for setting the two repos for interacting with both user and s3
	 * transaction information.
	 */
	public void setRespositry(UserRepository userRepository, S3UploadRepo s3UploadRepo) {
		this.userRepository = userRepository;
		this.s3UploadRepo = s3UploadRepo;
	}

	/**
	 * User for adding the s3 transaction information into rds.
	 */
	public Object add(String userName, String fileName, String fileDescription, String url) {
		System.out.println("Trying to add record to rds" + userName);
		User userFromDB = getUser(userName);
		System.out.println("User from DB." + userFromDB);
		if (null != userFromDB) {
			S3Data s3Data = new S3Data();
			s3Data.setFileDesc(fileDescription);
			s3Data.setFileName(fileName);
			s3Data.setLastName(userFromDB.getLastName());
			s3Data.setUserName(userFromDB.getUsername());
			s3Data.setFirstName(userFromDB.getFirstName());
			s3Data.setUrl(url);
			s3Data.setUpdatedTime(new Date(System.currentTimeMillis()));
			s3Data.setUploadTime(new Date(System.currentTimeMillis()));
			System.out.println("Adding to rds repo.." + userName);
			s3UploadRepo.save(s3Data);
		}
		return null;
	}

	/**
	 * Used for performing the delete operation on rds.
	 */
	@Override
	public Object delete(String url) {
		S3Data s3Data = new S3Data();
		s3Data.setUrl(url);
		S3Data s3data = new S3Data();
		s3data.setUrl(url);
		S3Data rdsMatchingRecord = s3UploadRepo.findOne(Example.<S3Data>of(s3data,
				ExampleMatcher.matching().withMatcher("url", ExampleMatcher.GenericPropertyMatchers.ignoreCase())));
		s3UploadRepo.delete(rdsMatchingRecord.getId());
		return null;
	}

	@Override
	public Object read(String userFirstname) {
		return null;
	}

	/**
	 * Used for get the user bean from the given user name.
	 */
	@Override
	public User getUser(String userName) {
		User inputUser = new User();
		inputUser.setUsername(userName);
		User userFromDB = userRepository.findOne(Example.<User>of(inputUser, ExampleMatcher.matching()
				.withMatcher("username", ExampleMatcher.GenericPropertyMatchers.ignoreCase())));
		return userFromDB;
	}

	/**
	 * Used for getting all the objects present in the given bucket.
	 */
	@Override
	public List<S3Data> getAllImagesFromBucket(String userName) {
		S3Data s3data = new S3Data();
		s3data.setUserName(userName);
		java.util.List<S3Data> s3DataList = s3UploadRepo.findAll(Example.<S3Data>of(s3data, ExampleMatcher.matching()
				.withMatcher("username", ExampleMatcher.GenericPropertyMatchers.ignoreCase())));
		return s3DataList;
	}

	/**
	 * Used for doing update operation, we just allow update of description of image
	 * and its description.
	 */
	@Override
	public Object update(String userName, String fileDescription, String url) {
		S3Data s3data = new S3Data();
		s3data.setUrl(url);
		S3Data rdsMatchingRecord = s3UploadRepo.findOne(Example.<S3Data>of(s3data,
				ExampleMatcher.matching().withMatcher("url", ExampleMatcher.GenericPropertyMatchers.ignoreCase())));
		rdsMatchingRecord.setFileDesc(fileDescription);
		rdsMatchingRecord.setUpdatedTime(new Date(System.currentTimeMillis()));
		s3UploadRepo.saveAndFlush(rdsMatchingRecord);
		return null;
	}

}
