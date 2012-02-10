package com.lohika.alp.utils.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Class EmailValidator.
 */
public class EmailValidator{
 
	  /** The pattern. */
  	private Pattern pattern;
	  
  	/** The matcher. */
  	private Matcher matcher;
 
	  /** Expression used to validate mail. */
  	private static final String EMAIL_PATTERN = 
			  "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
 
	  /**
  	 * Instantiates a new email validator.
  	 */
  	public EmailValidator(){
		  pattern = Pattern.compile(EMAIL_PATTERN);
	  }
 
	  /**
  	 * Validate hex with regular expression.
  	 *
  	 * @param hex hex for validation
  	 * @return true valid hex, false invalid hex
  	 */
	  public boolean validate(final String hex){
 
		  matcher = pattern.matcher(hex);
		  return matcher.matches();
 
	  }
}