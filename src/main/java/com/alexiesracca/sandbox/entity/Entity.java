/**
 * @author alexies
 *
 */

package com.alexiesracca.sandbox.entity;

import java.time.LocalDateTime;

public abstract class Entity {

	private String lastUpdatedBy;

	private LocalDateTime lastUpdate;

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(LocalDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

}
