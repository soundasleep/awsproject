package com.touchdine.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.touchdine.model.S3Data;
 
/**
 * Repositry   for interacting with s3 transaction  information in rds.
 * @author ravisha
 *
 */
@Repository
public interface S3UploadRepo extends JpaRepository<S3Data, Long> {
}

