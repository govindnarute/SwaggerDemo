package com.example.swagger.webservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.swagger.dto.UserDTO;
import com.example.swagger.model.ResponseModel;
import com.example.swagger.model.UserModel;
import com.example.swagger.service.UserService;
import com.example.swagger.util.LocaleConverter;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiImplicitParam;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

//Constants
//import static com.example.swagger.constants.SwaggerMVCDemoConstants.AUTHTOKEN;

import java.util.Locale;

@Component
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })

@Api(value = "/user", description = "User REST for Integration Testing")
@Path("/user")

public class UserWebServiceImpl implements UserWebService {
	private static Logger logger = Logger.getLogger(UserWebServiceImpl.class);

	@Autowired
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@GET
	@Override
	@ApiOperation(value = "Get user details", response = UserDTO.class, notes = "This API returns appropriate user using auth Token.")
	public ResponseModel getUser(@QueryParam("authToken") String authToken) {
		UserDTO userDto = userService.getUserByAuthToken(authToken);
		ResponseModel responseModel = null;
		responseModel = ResponseModel.getInstance();
		responseModel.setObject(userDto);
		responseModel.setMessage("done");
		return responseModel;
	}

	@POST
	@ApiOperation(value = "Save user details", response = UserDTO.class, notes = "This API creates user's account. (SignUp)")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "User Registered Successfully."),
			@ApiResponse(code = 500, message = "Internal server error.") })

	public ResponseModel signUpUser(@Context HttpServletRequest request, @Context HttpServletResponse response,
			@ApiParam(name = "userModel", value = "This body should contain : [ name, emailId and password ]", required = true) UserModel userModel)
			throws Exception {

		ResponseModel responseModel = null;
		responseModel = ResponseModel.getInstance();
		logger.info("<------inside signUpUser start------>");
		@SuppressWarnings("unused")
		Locale locale = LocaleConverter.getLocaleFromRequest(request);
		UserDTO userDTO = userService.saveUser(userModel, request);

		responseModel.setObject(userDTO);
		responseModel.setMessage("User record saved successfully.");
		return responseModel;
	}

	// For getting authorization token from header
	// Used Post http method here just to show how to get entire body as a request parameters.
	@POST
	@Override
	@Path("/getUserById")
	@ApiImplicitParams(value = {
			@ApiImplicitParam(name = "authToken", value = "Authorization token", required = true, dataType = "string", paramType = "header") })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "User details sent Successfully."),
			@ApiResponse(code = 500, message = "Internal server error.") })

	@ApiOperation(value = "Get user details", response = UserDTO.class, notes = "This API returns appropriate user using auth Token.")
	public ResponseModel getUserById(@Context HttpServletRequest request,
			@ApiParam(name = "userModel", value = "This body should contain : [ id ]", required = true) UserModel userModel) throws Exception {
		ResponseModel responseModel = null;
		responseModel = ResponseModel.getInstance();
		UserDTO userDTO = userService.getUserById(userModel);

		responseModel.setObject(userDTO);
		responseModel.setMessage("User details sent successfully");
		return responseModel;
	}

}
