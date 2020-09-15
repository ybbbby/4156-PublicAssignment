package models;

public class Player {

  /** Store the user's choice. */
  private char type;

  /** Store the user's id. */
  private int id;

  /** Constructor for Player.
   * @param userType user's choice 'X' or 'O'
   * @param userId the user's id
   */
  public Player(final char userType, final int userId) {
    this.type = userType;
    this.id = userId;
  }

  /** Make sure the variable can be serialized.
   *  @return type
   */
  public char getType() {
    return type;
  }

  /** Make sure the variable can be serialized.
   *  @param userType type
   */
  public void setType(final char userType) {
    this.type = userType;
  }

  /** Make sure the variable can be serialized.
   *  @return id
   */
  public int getId() {
    return id;
  }

  /** Make sure the variable can be serialized.
   *  @param userId id
   */
  public void setId(final int userId) {
    this.id = userId;
  }

}
