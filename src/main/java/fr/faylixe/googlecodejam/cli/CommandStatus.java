package fr.faylixe.googlecodejam.cli;

/**
 * Status of command execution.
 * 
 * @author fv
 */
public enum CommandStatus {

	/** Successful command execution. **/
	SUCCESS,
	
	/** Failed command execution. **/
	FAILED,

	/** Indicates wrong parameters set. **/
	INVALID_FORMAT

}
