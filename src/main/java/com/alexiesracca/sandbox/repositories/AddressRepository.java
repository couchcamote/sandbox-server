/**
 * @author alexies
 */

package com.alexiesracca.sandbox.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.alexiesracca.sandbox.entity.Address;

public interface AddressRepository extends MongoRepository<Address, String> {


}
