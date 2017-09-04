package com.alexiesracca.sandbox.utilities;

import com.alexiesracca.sandbox.entity.Entity;

public class EntityUtility {
	
	public static void preSave(Entity e) {
		e.setLastUpdate(DateTimeUtility.now());
		e.setLastUpdatedBy(UserUtility.getSystemUser());
	}
	
	public static void preSave(Entity e, String userName) {
		e.setLastUpdate(DateTimeUtility.now());
		e.setLastUpdatedBy(userName);
	}

}
