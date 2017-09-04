/**
 * @author alexies
 */

package com.alexiesracca.sandbox.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.alexiesracca.sandbox.entity.User;

public interface UserRepository extends MongoRepository<User, String> {

	public List<User> findByLastName(String lastName);
	
	public List<User> findByActiveIsTrue();

}
