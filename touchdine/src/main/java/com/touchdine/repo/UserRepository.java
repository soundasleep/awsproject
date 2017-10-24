
package com.touchdine.repo;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.touchdine.model.User;

/**
 * Respoitry used for interacting with user information.
 * @author ravisha
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}