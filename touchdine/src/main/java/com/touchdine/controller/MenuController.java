package com.touchdine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.touchdine.model.S3Data;
import com.touchdine.model.User;
import com.touchdine.repo.S3UploadRepo;
import com.touchdine.repo.UserRepository;
import com.touchdine.service.api.RdsService;
import com.touchdine.service.api.S3Service;
import com.touchdine.service.rds.RdsServiceImpl;
import com.touchdine.service.s3.S3ServiceImpl;
import com.touchdine.util.Util;

/**
 * This is the main controller for the complete menu functionality.
 * 
 * @author ravisha
 *
 */
@Controller
public class MenuController {

	/**
	 * Used for dealing with RDS with user data.
	 */
	@Autowired
	private UserRepository userRepository;

	/**
	 * Used for dealing with transaction data of the image upload in RDS.
	 */
	@Autowired
	private S3UploadRepo s3UploadRepo;

	/**
	 * Service for dealing with RDS service.
	 */
	private RdsService rdsService = new RdsServiceImpl();

	/**
	 * Service for dealing with s3 service.
	 */
	private S3Service s3Service = new S3ServiceImpl();

	public MenuController() {

	}

	/**
	 * User lands on this, on opening the web site, it will then redirect to the
	 * login page.
	 * 
	 * @return
	 */
	@GetMapping("/")
	public String getIndex() {
		return "login";
	}

	/**
	 * On login user will be again redirected to login page for next login.
	 * 
	 * @return
	 */
	@GetMapping("/logout")
	public String getLogout() {
		return "login";
	}

	/**
	 * This Mapping takes care of delete and update operations of the image.
	 * 
	 * @param userName
	 * @param imageUrl
	 * @param actionType
	 * @param modelMap
	 * @return
	 */
	@PostMapping("/deleteUpdate")
	public String getdeleteUpdate(@RequestParam("username") String userName, @RequestParam("imageUrl") String imageUrl,
			@RequestParam("actionType") String actionType, ModelMap modelMap) {
		modelMap.put("username", userName);
		setAWSCredentail(userName);
		if ("update".equals(actionType)) {
			modelMap.put("imageUrl", imageUrl);
			return "update";
		} else {
			modelMap.put("delete", imageUrl);
			s3Service.delete(imageUrl);
			rdsService.setRespositry(userRepository, s3UploadRepo);
			rdsService.delete(imageUrl);
			List<S3Data> s3dataList = (List<S3Data>) getALLImagesFromBucket(userName);
			modelMap.put("username", userName);
			modelMap.put("s3dataList", s3dataList);
			return "viewDetails";
		}

	}

	/**
	 * This mapping takes care of redirecting to upload page for uploading the
	 * required file.
	 * 
	 * @param userName
	 * @param modelMap
	 * @return
	 */
	@GetMapping("/uploadpage")
	public String getUploadPage(@RequestParam("username") String userName, ModelMap modelMap) {
		modelMap.put("username", userName);
		return "upload";
	}

	/**
	 * This mapping is used for redirecting to the home page.
	 * 
	 * @param userName
	 * @param modelMap
	 * @return
	 */
	@GetMapping("/homePage")
	public String getHomePage(@RequestParam("username") String userName, ModelMap modelMap) {
		List<S3Data> s3dataList = (List<S3Data>) getALLImagesFromBucket(userName);
		modelMap.put("username", userName);
		modelMap.put("s3dataList", s3dataList);
		return "viewDetails";
	}

	/**
	 * Once the User enters the credential information, validation happens here and
	 * then redirects to view details page or gallery page basing on the credential
	 * information provided.
	 * 
	 * @param username
	 * @param password
	 * @param modelMap
	 * @return
	 */
	@PostMapping("/login")
	public String processLogin(@RequestParam("email") String username, @RequestParam("password") String password,
			ModelMap modelMap) {
		User inputUser = new User();
		inputUser.setUsername(username);
		inputUser.setPassword(password);
		User userFromDB = userRepository.findOne(Example.<User>of(inputUser, ExampleMatcher.matching()
				.withMatcher("username", ExampleMatcher.GenericPropertyMatchers.ignoreCase())));
		if (null != userFromDB && userFromDB.getPassword().equals(userFromDB.getPassword())) {
			List<S3Data> s3dataList = (List<S3Data>) getALLImagesFromBucket(username);
			modelMap.put("username", userFromDB.getUsername());
			modelMap.put("s3dataList", s3dataList);
			return "viewDetails";
		} else {
			modelMap.put("loginstatus", "Username or Password is wrong!!");
			return "login";
		}
	}

	private List<S3Data> getALLImagesFromBucket(String username) {
		rdsService.setRespositry(userRepository, s3UploadRepo);
		return rdsService.getAllImagesFromBucket(username);
	}

	/**
	 * Used for adding the menu information.
	 * 
	 * @param name
	 * @param file
	 * @param redirectAttributes
	 */
	@PostMapping("/upload")
	public String addMenu(@RequestParam("username") String userName, @RequestParam("fileName") String fileName,
			@RequestParam("description") String fileDescription, @RequestParam("file") MultipartFile file,
			ModelMap modelMap) {
		System.out.println("User name "+userName);
		setAWSCredentail(userName);
		String objectS3Url = (String) s3Service.add(fileName, file);
		System.out.println("object s3 URl.."+objectS3Url);
		String cloudFrontUrl = objectS3Url.replaceAll("s3.amazonaws.com/eztouchmenu", "d1s8csah4zncsp.cloudfront.net");
		rdsService.setRespositry(userRepository, s3UploadRepo);
		rdsService.add(userName, fileName, fileDescription, cloudFrontUrl);
		List<S3Data> s3dataList = (List<S3Data>) getALLImagesFromBucket(userName);
		modelMap.put("username", userName);
		modelMap.put("s3dataList", s3dataList);
		return "viewDetails";

	}

	/**
	 * Used for updating the menu.
	 * 
	 * @param name
	 * @param file
	 * @param redirectAttributes
	 */
	@PostMapping("/update")
	public String updateMenu(@RequestParam("username") String userName, @RequestParam("imageUrl") String imageUrl,
			@RequestParam("description") String fileDescription, @RequestParam("file") MultipartFile file,
			ModelMap modelMap) {
		setAWSCredentail(userName);
		String objectS3Url = (String) s3Service.add(Util.getKeyName(imageUrl), file);
		String cloudFrontUrl = objectS3Url.replaceAll("s3.amazonaws.com/eztouchmenu", "d1s8csah4zncsp.cloudfront.net");
		rdsService.setRespositry(userRepository, s3UploadRepo);
		rdsService.update(userName, fileDescription, cloudFrontUrl);
		List<S3Data> s3dataList = (List<S3Data>) getALLImagesFromBucket(userName);
		modelMap.put("username", userName);
		modelMap.put("s3dataList", s3dataList);
		return "viewDetails";

	}

	/**
	 * Used for setting the aws credentials.
	 * 
	 * @param userName
	 */
	private void setAWSCredentail(String userName) {
		User user = rdsService.getUser(userName);
		s3Service.setCredentialForBucketAccess(userName, user.getAccessKey().trim(), user.getSecAccessKey().trim());
	}

}
