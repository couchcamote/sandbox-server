/**
 * @author Alexies Racca
 */

package com.alexiesracca.sandbox.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.alexiesracca.sandbox.dto.Response;
import com.alexiesracca.sandbox.entity.Address;
import com.alexiesracca.sandbox.entity.Location;
import com.alexiesracca.sandbox.entity.User;
import com.alexiesracca.sandbox.repositories.AddressRepository;
import com.alexiesracca.sandbox.repositories.LocationRepository;
import com.alexiesracca.sandbox.repositories.UserRepository;
import com.alexiesracca.sandbox.utilities.EntityUtility;
import com.alexiesracca.sandbox.utilities.StringUtility;


@RestController
public class APIController {
	Logger log = Logger.getLogger(APIController.class);

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private LocationRepository locationRepository;


	@RequestMapping(value = "/api", method = RequestMethod.GET)
	public ResponseEntity<Response> getServices() {
		log.info("/api/");

		List<String> apiMethodsList = new ArrayList<String>();
		apiMethodsList.add("/api/-- api root, list available services");
		apiMethodsList.add("/api/activeusers/ -- list all active users");
		Response res = new Response(Response.Status.Success, "Retrieved Succesfully");
		res.setListContent(apiMethodsList);
		return new ResponseEntity<Response>(res, HttpStatus.OK);

	}

	// Users Entity

	@RequestMapping(value = "/api/user", method = RequestMethod.POST)
	public ResponseEntity<Response> createNewUser(@RequestBody User newUser,
		UriComponentsBuilder ucBuilder) {
		log.info("New User");
		
		User savedUser = null;

		if(newUser!=null){
			Response res = new Response(Response.Status.Success, "User Successfully Registered");
			
			EntityUtility.preSave(newUser);
			savedUser = userRepository.save(newUser);
			
			res.setContent(savedUser);
			return new ResponseEntity<Response>(res, HttpStatus.OK);
		}
		else
		{
			Response res = new Response(Response.Status.Failed, "User Registration Failed");
			return new ResponseEntity<Response>(res, HttpStatus.OK);
		}

	}

	@RequestMapping(value = "/api/activeusers", method = RequestMethod.GET)
	public ResponseEntity<Response> getActiveUsers() {
		log.info("List active users");

		List<User> findList = userRepository.findByActiveIsTrue();
		log.info("Query results size " + findList.size());
		/// check success
		if (findList.size() > 0) {

			Response res = new Response(Response.Status.Success, "Retrieved Succesfully");
			res.setListContent(findList);
			return new ResponseEntity<Response>(res, HttpStatus.OK);
		} else {
			Response res = new Response(Response.Status.Failed, "No Users found");
			return new ResponseEntity<Response>(res, HttpStatus.OK);
		}

	}
	
	@RequestMapping(value = "/api/user/{id}", method = RequestMethod.GET)
	public ResponseEntity<Response> getUser(@PathVariable(value = "id") String id) {
		log.info("Get User");
		
		User user = userRepository.findOne(id);

		if(user!=null){
			Response res = new Response(Response.Status.Success,  "Successfully Registered");
			res.setContent(user);
			return new ResponseEntity<Response>(res, HttpStatus.OK);
		}
		else
		{
			Response res = new Response(Response.Status.Failed,  "Creation Failed");
			return new ResponseEntity<Response>(res, HttpStatus.OK);
		}

	}
	
	@RequestMapping(value = "/api/address", method = RequestMethod.POST)
	public ResponseEntity<Response> createNewUser(@RequestBody Address newAddress,
		UriComponentsBuilder ucBuilder) {
		log.info("New Address");
		
		Address savedAdress = null;

		if(newAddress!=null){
			Response res = new Response(Response.Status.Success,  "Successfully Registered");
			EntityUtility.preSave(newAddress);
			savedAdress = addressRepository.save(newAddress);
			
			res.setContent(savedAdress);
			return new ResponseEntity<Response>(res, HttpStatus.OK);
		}
		else
		{
			Response res = new Response(Response.Status.Failed,  "Creation Failed");
			return new ResponseEntity<Response>(res, HttpStatus.OK);
		}

	}
	
	@RequestMapping(value = "/api/address/{id}", method = RequestMethod.GET)
	public ResponseEntity<Response> getAddress(@PathVariable(value = "id") String id) {
		log.info("Get Address");
		
		Address address = addressRepository.findOne(id);

		if(address!=null){
			Response res = new Response(Response.Status.Success,  "Successfully Retrieved");
			res.setContent(address);
			return new ResponseEntity<Response>(res, HttpStatus.OK);
		}
		else
		{
			Response res = new Response(Response.Status.Failed,  "No such address on the record");
			return new ResponseEntity<Response>(res, HttpStatus.OK);
		}

	}
	
	//Location
	@RequestMapping(value = "/api/location", method = RequestMethod.POST)
	public ResponseEntity<Response> newLocation(@RequestBody LocationRequest locationRequest,
		UriComponentsBuilder ucBuilder) {
		log.info("New Location");
		
		Location savedLocation = null;
		
		Double lon = StringUtility.parseDoubleDigit(locationRequest.getLongitude());
		Double lat = StringUtility.parseDoubleDigit(locationRequest.getLatitude());
		
		log.info(lon + " - " + lat + " "+ locationRequest.getCategory()+ " "+ locationRequest.getSubject());
		
		Location.LocationCategory categoryEnum = Location.LocationCategory.valueOf(locationRequest.getCategory());

		if(lon!=null && lat!=null && categoryEnum!=null){
			Location newLocation =  new Location(locationRequest.getSubject(), 
					new GeoJsonPoint(lon.doubleValue(), 
							lat.doubleValue()),categoryEnum);
			
			Response res = new Response(Response.Status.Success,  "Successfully Saved");
			EntityUtility.preSave(newLocation);
			savedLocation = locationRepository.save(newLocation);
			
			res.setContent(savedLocation);
			return new ResponseEntity<Response>(res, HttpStatus.OK);
		}
		else
		{
			Response res = new Response(Response.Status.Failed,  "Saving Failed");
			return new ResponseEntity<Response>(res, HttpStatus.OK);
		}

	}
	
	@RequestMapping(value = "/api/location/{id}", method = RequestMethod.GET)
	public ResponseEntity<Response> getLocation(@PathVariable(value = "id") String id) {
		log.info("Get Location By ID");
		
		Location location = locationRepository.findOne(id);

		if(location!=null){
			Response res = new Response(Response.Status.Success,  "Successfully Retrieved");
			res.setContent(location);
			return new ResponseEntity<Response>(res, HttpStatus.OK);
		}
		else
		{
			Response res = new Response(Response.Status.Failed,  "No such record");
			return new ResponseEntity<Response>(res, HttpStatus.OK);
		}

	}
	
	@RequestMapping(value = "/api/location", method = RequestMethod.GET)
	public ResponseEntity<Response> getLocation(
			@RequestParam(value = "lat", required=true) String latitude,
			@RequestParam(value = "long", required=true) String longitude,
			@RequestParam(value = "subject", required=false, defaultValue = "") String subject,
			@RequestParam(value = "d", required=false, defaultValue = "10") double distance
			) {
		log.info("Get Location");
		
		Double lon = null;
		Double lat = null;
		
		if(!latitude.isEmpty() && !longitude.isEmpty()) {
			lon = StringUtility.parseDoubleDigit(longitude);
			lat = StringUtility.parseDoubleDigit(latitude);
		}

		List<Location> list = null;
		
		if(lon!=null && lat!=null) {
			 Distance d =  new Distance(distance, Metrics.KILOMETERS);
			 Point point =  new Point(lon.doubleValue(), lat.doubleValue());
			 if(!subject.isEmpty()) {
				 list = locationRepository.findBySubjectAndLocationNear(subject, point, d);
			 }else {
				 list = locationRepository.findByLocationNear(point, d);
			 }
				 
			 
		}

		if(list!=null && list.size() > 0){
			Response res = new Response(Response.Status.Success,  "Successfully Retrieved");
			res.setListContent(list);
			return new ResponseEntity<Response>(res, HttpStatus.OK);
		}
		else
		{
			Response res = new Response(Response.Status.Failed,  "No such record");
			return new ResponseEntity<Response>(res, HttpStatus.OK);
		}

	}
	
	@RequestMapping(value = "/api/location/category/{category}", method = RequestMethod.GET)
	public ResponseEntity<Response> getLocationByCategory(
			@PathVariable(value = "category") Location.LocationCategory category,
			@RequestParam(value = "lat", required=false, defaultValue = "0") String latitude,
			@RequestParam(value = "long", required=false, defaultValue = "0") String longitude,
			@RequestParam(value = "d", required=false, defaultValue = "10") double distance
			) {
		log.info("Get Location");
		
		
		Double lon = null;
		Double lat = null;
		
		if(!latitude.isEmpty() && !longitude.isEmpty()) {
			lon = StringUtility.parseDoubleDigit(longitude);
			lat = StringUtility.parseDoubleDigit(latitude);
		}

		List<Location> list = null;
		
		if(lon!=null && lat!=null) {
			 Distance d =  new Distance(distance, Metrics.KILOMETERS);
			 Point point =  new Point(lon.doubleValue(), lat.doubleValue());
			 list = locationRepository.findByCategoryAndLocationNear(category, point, d);
		}

		if(list!=null && list.size() > 0){
			Response res = new Response(Response.Status.Success,  "Successfully Retrieved");
			res.setListContent(list);
			return new ResponseEntity<Response>(res, HttpStatus.OK);
		}
		else
		{
			Response res = new Response(Response.Status.Failed,  "No such record");
			return new ResponseEntity<Response>(res, HttpStatus.OK);
		}

	}
	
	
	//Location

}

class LocationRequest{
	
	private String subject;
	private String category;
	private String latitude;
	private String longitude;
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
}
