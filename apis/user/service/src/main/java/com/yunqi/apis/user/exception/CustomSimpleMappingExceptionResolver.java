package com.yunqi.apis.user.exception;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

//@Component
public class CustomSimpleMappingExceptionResolver extends SimpleMappingExceptionResolver  {

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

		  HandlerMethod handlerMethod = (HandlerMethod) handler;
		  ResponseBody body = handlerMethod.getMethodAnnotation(ResponseBody.class);
		  
		if (body == null) {
			return super.doResolveException(request, response, handlerMethod, ex);
		}
		ModelAndView mv = new ModelAndView();
		// 设置状态码,注意这里不能设置成500，设成500JQuery 不会出错误提示
		// 并且不会有任何反应
		response.setStatus(HttpStatus.OK.value());
		// 设置 ContentType
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		// 避免乱码
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "no-cache, must-revalidate");
		try {
			ex.printStackTrace();
			PrintWriter writer = response.getWriter();
//			writer.write(JSONObject.fromObject(ControllerUtil.newErrorResultMap(ex.getMessage())).toString());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 return mv;
		
//		String viewName = determineViewName(ex, request);
//		if (viewName != null) {
//			if (!(request.getHeader("accept").indexOf("application/json") > -1 || (request.getHeader("X-Requested-With") != null && request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1))) {
//				Integer statusCode = determineStatusCode(request, viewName);
//				if (statusCode != null) {
//					applyStatusCodeIfPossible(request, response, statusCode);
//				}
//				return getModelAndView(viewName, ex, request);
//			} else {
//				try {
//					PrintWriter writer = response.getWriter();
//					writer.write(ex.getMessage());
//					writer.flush();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//				return null;
//			}
//		} else {
//			return null;
//		}
	}
}
