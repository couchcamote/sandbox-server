/**
 * @author alexies
 */

package com.alexiesracca.sandbox.repositories;

import java.util.List;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.alexiesracca.sandbox.entity.Location;

public interface LocationRepository extends MongoRepository<Location, String> {

	List<Location> findByLocationNear(Point point, Distance d);

	List<Location> findByCategoryAndLocationNear(Location.LocationCategory category, Point point, Distance d);

	List<Location> findBySubjectAndLocationNear(String subject, Point p, Distance d);

	List<Location> findByCategory(Location.LocationCategory category);

}
