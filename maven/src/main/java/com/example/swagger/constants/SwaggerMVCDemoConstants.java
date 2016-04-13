package com.example.swagger.constants;

public class SwaggerMVCDemoConstants {
	public static final boolean TRUE = true;
	public static final boolean FALSE = false;
	public static final String EMIAL_FROM_ID = "email.from.id" ;
	public static final String POST = "POST";
	public static final String AUTHTOKEN = "authToken";
	public static final String DEVICETYPE = "deviceType";
	public static final String SIGN_UP_USER_URL = "/user";// "/signUpUser";//
															// "/user";
	public static final String LOGIN_URL = "/user/logIn";
	public static final String SWAGGER_URL = "/sw";

	public static final String FORGOT_PASSWORD = "/user/forgot_password";
	public static final String AUTHTOKEN_KEYWORD = "authToken= ";
	public static final String DEVICE_TYPE_KEYWORD = "deviceType= ";

	public static final String FAIL = "fail";
	public static final String USER = "user";
	public static final String DAILY_CUP = "daily.cup";
	public static final String GUIDED_MEDITATIONS = "guided.meditations";


	// Paths
	public static final String REGISTRATION_CONFIRMATION_EMAIL_PATH = "message.registration.confirmation.email.path";
	public static final String FORGOT_PASSWORD_EMAIL_PATH = "message.forgot.password.email.path";


	// Success Messages
	public static final String REGISTRATION_CONFIRMATION_EMAIL_BODY = "message.registration.confirmation.email.body";
	public static final String FORGOT_PASSWORD_EMAIL_BODY = "message.forgot.password.email.body";
	public static final String USER_REGISTERED_SUCCESSFULLY_MESSAGE = "message.user.successfully.registered";
	public static final String USER_DETAILS_MESSAGE = "message.user.details.sent";
	public static final String DAILY_CUP_RECORD_SENT_SUCCESSFULLY= "message.daily.cup.sent.successfully";
	public static final String DAILY_CUP_REMAINDER_SAVED_SUCCESSFULLY_MESSAGE = "daily.cup.remainder.saved.successfully";
	public static final String GUIDED_MEDITATION_RECORDS_SENT_SUCCESSFULLY = "message.guided.meditation.records.sent.successfully";
	public static final String AUTH_TOKEN_CHANGED_SUCCESSFULLY = "message.auth.token.changed.successfully";

	// Exception Messages
	public static final String NOT_FOUND = "not.found";
	public static final String REGISTER_FAILED_EXCEPTION = "exception.message.register.fail";
	public static final String INVALID_USER_TOKEN = "Invalid User Token.";
	public static final String USER_NOT_REGISTERED = "exception.message.email.not.registered";
	public static final String LOG_IN_FAILED = "exception.message.user.login.fail";
	public static final String PASSWORD_MISMATCH = "exception.message.password.mismatch";
	public static final String USER_LOGGED_IN_SUCCESSFULLY = "message.user.login.successfully";
	public static final String PASSWORD_SENT_SUCCESSFULLY = "message.password.sent.successfully";
	public static final String USER_ALREADY_EXIST_EXCEPTION = "exception.message.email.already.exists";
	public static final String PASSWORD_SHOULD_NOT_BE_NULL_EXCEPTION = "exception.password.should.not.be.null";
	public static final String EAMIL_SHOULD_NOT_BE_NULL_EXCEPTION = "exception.email.should.not.be.null";
	public static final String DEVICE_TOKEN_SHOULD_NOT_BE_NULL_EXCEPTION = "exception.device.token.should.not.be.null";
	public static final String DEVICE_TYPE_SHOULD_NOT_BE_NULL_EXCEPTION = "exception.device.type.should.not.be.null";
	public static final String FACEBOOK_TOKEN_SHOULD_NOT_BE_NULL_EXCEPTION = "exception.facebook.token.should.not.be.null";
	public static final String DAILY_CUP_DOES_NOT_EXIST_FOR_TODAYS_DATE_EXCEPTION = "exception.daily.cup.does.not.exist.for.todays.date";
	public static final String NO_GUIDED_MEDITATIONS_AVAILABLE_EXCEPTION = "exception.no.guided.meditations.are.available";

	
}
