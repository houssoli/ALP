package com.lohika.alp.utils.object.reader;

/**
 * The Class ObjectReaderException.
 */
public class ObjectReaderException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5253648746746360191L;

	/**
	 * Instantiates a new object reader exception.
	 *
	 * @param message the message
	 */
	public ObjectReaderException(String message) {
		super(message);
	}
	
	/**
	 * Instantiates a new object reader exception.
	 *
	 * @param message the message
	 * @param throwable the throwable
	 */
	public ObjectReaderException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
