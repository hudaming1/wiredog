package org.hum.wiredog.proxy.mock;

import java.util.List;

import org.hum.wiredog.proxy.mock.netty.NettyResponseInterceptor;

import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;

public class MockHandler {
	
	private final String WT_MOCK_SIGN = "_WtMock";
	private List<Mock> mockList;
	
	public MockHandler(List<Mock> mocks) {
		this.mockList = mocks;
	}

	public void mock(FullHttpRequest request) {
		if (mockList == null || mockList.isEmpty()) {
			return ;
		}
		
		for (Mock mock : mockList) {
			if (mock.getRequestInterceptor() != null && mock.getRequestInterceptor().isHit(request)) {
				// TODO mock
				
				// rebuild
				if (mock.getRequestRebuilder() != null) {
					request = mock.getRequestRebuilder().eval(request);
				}
				request.headers().set(WT_MOCK_SIGN, mock.getId());
			}
		}
	}

	public void mock(FullHttpRequest request, FullHttpResponse resp) {
		if (mockList == null || mockList.isEmpty()) {
			return ;
		}
		
		for (Mock mock : mockList) {
			boolean requestMiss = request.headers().contains(WT_MOCK_SIGN) == false || !mock.getId().equals(request.headers().get(WT_MOCK_SIGN));
			NettyResponseInterceptor responseInterceptor = mock.getResponseInterceptor();
			if (requestMiss && (responseInterceptor == null || !responseInterceptor.isHit(resp))) {
				continue;
			}
			if (mock.getResponseRebuild() != null) {
				resp = mock.getResponseRebuild().eval(resp);
				resp.headers().set(WT_MOCK_SIGN, mock.getId());
			}
		}
	}

}