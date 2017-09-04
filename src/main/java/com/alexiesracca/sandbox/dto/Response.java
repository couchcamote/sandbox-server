/**
 * @author Alexies Racca
 */

package com.alexiesracca.sandbox.dto;

import java.util.List;

import com.alexiesracca.sandbox.entity.Entity;

public class Response {

	public enum Status {
		Success, Failed
	}

	private Status status;
	private String message;
	private Entity content;
	private List listContent;

	public List getListContent() {
		return listContent;
	}

	public void setListContent(List listContent) {
		this.listContent = listContent;
	}

	public Response() {
		this.status = Status.Success;
	}

	public Response(Status status) {
		this.status = status;
	}

	public Response(Status status, String message) {
		this.status = status;
		this.message = message;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public Entity getContent() {
		return content;
	}

	public void setContent(Entity content) {
		this.content = content;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
