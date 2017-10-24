package com.touchdine.service.s3;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.S3ClientOptions;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.touchdine.service.api.S3Service;

/**
 * This is a service class which is used for doing s3 CRUD operations.
 * 
 * @author ravisha
 *
 */
public class S3ServiceImpl implements S3Service {

	/**
	 * Test user1 who can access the bucket folder ravi, once the access ids are
	 * present, code will fetch the credentials at runtime from db
	 */
	private static String RAVI_USER_NAME = "ravi@gmail.com";

	/**
	 * Test user1 who can access the bucket folder sunder, once the access ids are
	 * present, code will fetch the credentials at runtime from db.
	 */
	private static String SUNDER_USER_NAME = "sunder@gmail.com";

	/**
	 * Bucket names.
	 */
	private static String RAVI_BUCKET_NAME = "eztouchmenu/ravi";
	private static String SUNDER_BUCKET_NAME = "eztouchmenu/sunder";

	/**
	 * S3 client used for interacting with S3 storage.
	 */
	private AmazonS3 amazonS3 = null;
	private String bucketName = null;;

	/**
	 * Used for setting the credentials at runtime while the user tries to interact
	 * with s3 to do CRUD operations.
	 */
	@Override
	public void setCredentialForBucketAccess(String userName, String accessId, String secAccessId) {
		if (RAVI_USER_NAME.equals(userName)) {
			AWSCredentials raviAwsCredentialsFromDB = new BasicAWSCredentials(accessId, secAccessId);
			this.amazonS3 = new AmazonS3Client(raviAwsCredentialsFromDB);
			this.bucketName = RAVI_BUCKET_NAME;
		} else if (SUNDER_USER_NAME.equals(userName)) {
			AWSCredentials sunderAwsCredentialsFromDB = new BasicAWSCredentials(accessId, secAccessId);
			System.out.println("Sunder credential info"+accessId);
			System.out.println("Sunder credential info"+secAccessId);
			this.amazonS3 = new AmazonS3Client(sunderAwsCredentialsFromDB);
			this.bucketName = SUNDER_BUCKET_NAME;
			System.out.println("Sunder credential info"+secAccessId);
		}
	}

	/**
	 * Used for adding the records to s3 bucket
	 */
	public Object add(String name, MultipartFile mFile) {
		InputStream inputStream = null;
		File fileToUpload = null;
		try {
			fileToUpload = convertFromMultiPart(mFile);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			inputStream = mFile.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			System.out.println("bucketName..."+bucketName);
			System.out.println("name..."+bucketName);
			System.out.println("fileToUpload..."+fileToUpload);
			amazonS3.putObject(new PutObjectRequest(bucketName, name, fileToUpload));
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return read(name);

	}

	/**
	 * Convert MultiPartFile to ordinary File
	 * 
	 * @param multipartFile
	 * @return
	 * @throws IOException
	 */
	private File convertFromMultiPart(MultipartFile multipartFile) throws IOException {
		String temporaryFolder = System.getProperty("java.io.tmpdir");
		File file = new File(temporaryFolder + "/" + multipartFile.getOriginalFilename());
		file.createNewFile();
		FileOutputStream fos = new FileOutputStream(file); 
		fos.write(multipartFile.getBytes());
		fos.close(); 
		return file;
	}

	/**
	 * Used for performing delete operation.
	 */
	@Override
	public void delete(String url) {
		amazonS3.deleteObject(new DeleteObjectRequest(bucketName, getKeyName(url)));
	}

	/**
	 * Used for getting the key name.
	 * 
	 * @param url
	 * @return
	 */
	private String getKeyName(String url) {
		int index = url.lastIndexOf("/");
		String keyName = url.substring(index + 1);
		return keyName;
	}

	/**
	 * Used for doing read operation of the s3 object path.
	 */
	@Override
	public Object read(String fileName) {
		S3Object s3Object = amazonS3.getObject(new GetObjectRequest(bucketName, fileName));
		String path = s3Object.getObjectContent().getHttpRequest().getURI().toString();
		return path;

	}

	/**
	 * Used for getting the list of objects in a bucket.
	 */
	public void bucketList() {
		ObjectListing ol = amazonS3.listObjects("");
		List<S3ObjectSummary> objects = ol.getObjectSummaries();
		for (S3ObjectSummary os : objects) {
			System.out.println("* " + os.getKey());
		}

	}

}
