package org.hum.wiretiger.console.http.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hum.wiretiger.common.constant.HttpConstant;
import org.hum.wiretiger.console.common.WtSession;
import org.hum.wiretiger.console.http.service.SessionService;
import org.hum.wiretiger.proxy.util.HttpRequestCodec;

/**
 * http://localhost:8080/session/getHttpRequest
 */
public class CopyHttpRequestServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final SessionService sessionService = new SessionService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setHeader("Content-Type", "text/plain");
		WtSession wtSession = sessionService.getWtSessionById(Long.parseLong(req.getParameter("id")));
		resp.getWriter().print(HttpRequestCodec.encodeWithoutBody(wtSession.getRequest(), HttpConstant.RETURN_LINE));
		resp.getWriter().print(HttpConstant.RETURN_LINE);
		if (wtSession.getRequestBytes() != null) {
			resp.getWriter().print(new String(wtSession.getRequestBytes()));
		}
		resp.getWriter().flush();
		resp.getWriter().close();
	}
}