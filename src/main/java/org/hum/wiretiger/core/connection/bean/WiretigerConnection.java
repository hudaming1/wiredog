package org.hum.wiretiger.core.connection.bean;

import io.netty.handler.codec.http.DefaultHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import lombok.Getter;

@Getter
public class WiretigerConnection {

	private long id;
	private Integer pipeId;
	private DefaultHttpRequest request;
	private long requestTime;
	private FullHttpResponse response;
	private byte[] responseBytes;
	private long responseTime;
	
	public WiretigerConnection(Integer pipeId, DefaultHttpRequest request, long requestTime) {
		this.id = System.nanoTime();
		this.pipeId = pipeId;
		this.request = request;
		this.requestTime = requestTime;
	}
	
	public void setResponse(FullHttpResponse response, byte[] responseBytes, long time) {
		this.response = response;
		this.responseBytes = responseBytes;
		this.responseTime = time;
	}
}