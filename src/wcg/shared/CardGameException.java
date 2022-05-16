/**
 *
 */
package wcg.shared;

import java.io.Serializable;

/**
 * @author spila
 *
 */
public class CardGameException extends Exception implements Serializable {

	private static final long serialVersionUID = 2673116250311434164L;
	private java.lang.String message;
	private java.lang.Throwable cause;
	private boolean enableSuppression;
	private boolean writableStackTrace;
	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public CardGameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super();
		this.message = message;
		this.cause = cause;
		this.enableSuppression = enableSuppression;
		this.writableStackTrace = writableStackTrace;
	}
	/**
	 * @param message
	 * @param cause
	 */
	public CardGameException(String message, Throwable cause) {
		super();
		this.message = message;
		this.cause = cause;
	}
	/**
	 * @param message
	 */
	public CardGameException(String message) {
		super();
		this.message = message;
	}
	/**
	 * @param cause
	 */
	public CardGameException(Throwable cause) {
		super();
		this.cause = cause;
	}
	public CardGameException() {
		super();
	}
	/**
	 * @return the cause
	 */
	@Override
	public java.lang.Throwable getCause() {
		return cause;
	}
	/**
	 * @return the enableSuppression
	 */
	public boolean isEnableSuppression() {
		return enableSuppression;
	}
	/**
	 * @return the writableStackTrace
	 */
	public boolean isWritableStackTrace() {
		return writableStackTrace;
	}
	/**
	 * @return the message
	 */
	@Override
	public java.lang.String getMessage() {
		return message;
	}
}
