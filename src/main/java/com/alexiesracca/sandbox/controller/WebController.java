/**
 * @author Alexies Racca
 */


package com.alexiesracca.sandbox.controller;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {
	Logger log = Logger.getLogger(APIController.class);	
	

	@Value("${app.name}")
	private String appName = "Sandbox Application";


	@RequestMapping("/")
	public String root(Map<String, Object> model) {
		initModel(model);
		model.put("appName", this.appName);
	return "index";
	}
	
	private void initModel(Map<String, Object> model){
		model.put("appName", this.appName);
	}
}
