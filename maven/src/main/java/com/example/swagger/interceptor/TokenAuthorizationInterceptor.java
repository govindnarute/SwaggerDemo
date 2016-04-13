package com.example.swagger.interceptor;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.example.swagger.dto.UserDTO;
import com.example.swagger.model.ErrorModel;
import com.example.swagger.model.ResponseModel;
import com.example.swagger.service.UserService;

import static com.example.swagger.constants.SwaggerMVCDemoConstants.AUTHTOKEN;
import static com.example.swagger.constants.SwaggerMVCDemoConstants.AUTHTOKEN_KEYWORD;
import static com.example.swagger.constants.SwaggerMVCDemoConstants.SIGN_UP_USER_URL;
import static com.example.swagger.constants.SwaggerMVCDemoConstants.LOGIN_URL;
import static com.example.swagger.constants.SwaggerMVCDemoConstants.FORGOT_PASSWORD;
import static com.example.swagger.constants.SwaggerMVCDemoConstants.SWAGGER_URL;
import static com.example.swagger.constants.SwaggerMVCDemoConstants.FAIL;
import static com.example.swagger.constants.SwaggerMVCDemoConstants.INVALID_USER_TOKEN;
import static com.example.swagger.constants.SwaggerMVCDemoConstants.USER;





@Provider
@Produces(MediaType.APPLICATION_JSON)
public class TokenAuthorizationInterceptor extends AbstractPhaseInterceptor<Message> implements HandlerInterceptor {
	private Logger logger = Logger.getLogger(TokenAuthorizationInterceptor.class);
	@Autowired
	private UserService userService;

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public TokenAuthorizationInterceptor() {
		super(Phase.PRE_INVOKE); // Put this interceptor in this phase
	}

	public void handleMessage(Message message) throws RuntimeException {
		HttpServletRequest httpRequest = (HttpServletRequest) message.get(AbstractHTTPDestination.HTTP_REQUEST);

		
		
		String pathInfo = httpRequest.getPathInfo();
		System.out.println("Method Type " + httpRequest.getMethod());
		
//		if ( !(pathInfo.contains(SWAGGER_URL)) ) {
//			// To check whether deviceType is added in header or not
//			if(deviceType == null){
//				try {
//					throw new UserException(ResourceManager.getMessage(DEVICE_TYPE_SHOULD_NOT_BE_NULL_EXCEPTION, null,
//							DEVICE_TYPE_SHOULD_NOT_BE_NULL_EXCEPTION, null));
//				} catch (Exception ex) {
//					// TODO Auto-generated catch block
//					logger.error(ex.getStackTrace(), ex);
//					throw ex;
//				}
//			}
//
//		}

		logger.info("PATH httpRequest" + pathInfo);
		if (!(pathInfo.equals(SIGN_UP_USER_URL)) && !(pathInfo.contains(LOGIN_URL))
				&& !(pathInfo.contains(FORGOT_PASSWORD)) && !(pathInfo.contains(SWAGGER_URL))) {
			System.out.println("******************** \n" + pathInfo);

			
			// get the authToken value from header
			String authToken = httpRequest.getHeader(AUTHTOKEN);
			logger.info(AUTHTOKEN_KEYWORD + authToken);

			System.out.println("**********************" + authToken);

			UserDTO user = null;
			try {
				user = userService.getUserByAuthToken(authToken);
			} catch (Exception ex) {
				logger.error(ex.getStackTrace(), ex);
			}

			if (user == null) {
				String errorMessage = INVALID_USER_TOKEN;
				logger.info(AUTHTOKEN_KEYWORD + errorMessage);
				ResponseModel responseModel = ResponseModel.getInstance();
				ErrorModel error = new ErrorModel(errorMessage);
				responseModel.setError(error);
				responseModel.setStatus(FAIL);
				String errorResponse = null;
				ObjectMapper mapper = new ObjectMapper();
				try {
					errorResponse = mapper.writeValueAsString(responseModel);
				} catch (Exception e) {
					e.printStackTrace();
				}
				HttpServletResponse response = (HttpServletResponse) message.get(AbstractHTTPDestination.HTTP_RESPONSE);
				response.setStatus(500);
				try {
					response.getWriter().write(errorResponse);
				} catch (IOException e) {
					e.printStackTrace();
				}
				throw new org.apache.cxf.interceptor.security.AccessDeniedException("INVALID_USER_TOKEN");

			}
			httpRequest.setAttribute(USER, user);
			httpRequest.setAttribute("authToken", authToken);

		}
		
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void printMsg(){
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("=========== $$$$$$$$$$$$$$$ =============");

		return true;
	}
}
