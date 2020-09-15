package models;

public class Message {

  /** Indicate if the current move is valid. */
  private boolean moveValidity;

  /** The status code of the current move. */
  private int code;

  /** The message to show of this move. */
  private String message;

  /** Constructor for Message.
   * @param ifValid if the move is validated
   * @param statusCode the status code
   * @param messageToShow the error notification to show
   */
  public Message(final boolean ifValid, final int statusCode,
      final String messageToShow) {
    this.moveValidity = ifValid;
    this.code = statusCode;
    this.message = messageToShow;
  }

  /** Make sure the variable can be serialized.
   *  @return moveValidity
   */
  public boolean isMoveValidity() {
    return moveValidity;
  }

  /** Make sure the variable can be serialized.
   *  @param ifValid moveValidity
   */
  public void setMoveValidity(final boolean ifValid) {
    this.moveValidity = ifValid;
  }

  /** Make sure the variable can be serialized.
   *  @return code
   */
  public int getCode() {
    return code;
  }

  /** Make sure the variable can be serialized.
   *  @param statusCode code
   */
  public void setCode(final int statusCode) {
    this.code = statusCode;
  }

  /** Make sure the variable can be serialized.
   *  @return message
   */
  public String getMessage() {
    return message;
  }

  /** Make sure the variable can be serialized.
   *  @param messageToShow message
   */
  public void setMessage(final String messageToShow) {
    this.message = messageToShow;
  }

}
