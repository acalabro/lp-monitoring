package eu.learnpad.monitoring.exceptions;

public class UnknownEnablerIDException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnknownEnablerIDException()
	{
		System.out.println("Check request ID, may contains errors");
	}
}
