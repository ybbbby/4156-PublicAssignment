package models;

public class GameBoard {

  /** TicTacToe's board length. */
  private final int boardSideLength = 3;
  /** TicTacToe's board's total position count. */
  private final int posCount = 9;

  /** The possible result for each user's step. */
  public enum Result {
    /** The step is valid. */
    VALID,
    /** Invalid, the selected position is been taken. */
    POSITION_BE_TAKEN,
    /** Invalid, it is not current user's turn. */
    NOT_YOUR_TURN,
    /** Invalid, the game is not started yet. */
    NOT_STARTED,
    /** Invalid, the game is ended. */
    GAME_ENDED
  }

  /** Record the player1's option. */
  private Player p1;

  /** Record the player2's option. */
  private Player p2;

  /** Indicate if the game started. */
  private boolean gameStarted;

  /** Indicate who's turn now. */
  private int turn;

  /** Keep track of the board state. */
  private char[][] boardState;

  /** Indicate if winner is generated and who. */
  private int winner;

  /** Indicate if the game is draw. */
  private boolean isDraw;

  /** Indicate how many blank position are there in the board. */
  private int left;

  /** Constructor for GameBoard.
   * @param choice the user's chosen character
   */
  public GameBoard(final char choice) {
    p1 = new Player(choice, 1);
    if (choice == 'X') {
      p2 = new Player('O', 2);
    } else {
      p2 = new Player('X', 2);
    }
    gameStarted = false;
    turn = 1;
    boardState = new char[boardSideLength][boardSideLength];
    for (int i = 0; i < boardSideLength; i++) {
      for (int j = 0; j < boardSideLength; j++) {
        boardState[i][j] = '\u0000';
      }
    }
    winner = 0;
    isDraw = false;
    left = posCount;
  }

  /** Update the board, the return value has different meaning.
   * @param x index x
   * @param y index y
   * @param c the character the user use
   * @return status code
   */
  public Result update(final int x, final int y, final char c) {
    if (winner > 0 || isDraw) {
      return Result.GAME_ENDED;
    }
    if (boardState[x][y] != '\u0000') {
      return Result.POSITION_BE_TAKEN;
    }
    if (!gameStarted) {
      return Result.NOT_STARTED;
    }

    Player p;
    if (c == p1.getType()) {
      p = p1;
    } else {
      p = p2;
    }
    if ((c == p1.getType() && turn == 2) || (c == p2.getType() && turn == 1)) {
      return Result.NOT_YOUR_TURN;
    }

    boardState[x][y] = c;
    left--;
    if (turn == 1) {
      turn = 2;
    } else {
      turn = 1;
    }
    int i;
    for (i = 0; i < boardSideLength; i++) {
      if (boardState[i][y] != c) {
        break;
      }
    }
    if (i == boardSideLength) {
      winner = p.getId();
      return Result.VALID;
    }
    for (i = 0; i < boardSideLength; i++) {
      if (boardState[x][i] != c) {
        break;
      }
    }
    if (i == boardSideLength) {
      winner = p.getId();
      return Result.VALID;
    }
    if (boardState[0][0] == boardState[1][1]
        && boardState[1][1] == boardState[2][2]
        && boardState[0][0] == c) {
      winner = p.getId();
      return Result.VALID;
    }
    if (boardState[2][0] == boardState[1][1]
        && boardState[1][1] == boardState[0][2]
        && boardState[1][1] == c) {
      winner = p.getId();
      return Result.VALID;
    }

    if (left == 0) {
      isDraw = true;
      return Result.VALID;
    }
    return Result.VALID;
  }

  /** Make sure the variable can be serialized.
   *  @return p1
   */
  public Player getP1() {
    return p1;
  }

  /** Make sure the variable can be serialized.
   *  @param player1 p1
   */
  public void setP1(final Player player1) {
    this.p1 = player1;
  }

  /** Make sure the variable can be serialized.
   *  @return p2
   */
  public Player getP2() {
    return p2;
  }

  /** Make sure the variable can be serialized.
   *  @param player2 p2
   */
  public void setP2(final Player player2) {
    this.p2 = player2;
  }

  /** Make sure the variable can be serialized.
   *  @return gameStarted
   */
  public boolean isGameStarted() {
    return gameStarted;
  }

  /** Make sure the variable can be serialized.
   * @param ifGameStarted gameStarted
   */
  public void setGameStarted(final boolean ifGameStarted) {
    this.gameStarted = ifGameStarted;
  }

  /** Make sure the variable can be serialized.
   *  @return turn
   */
  public int getTurn() {
    return turn;
  }

  /** Make sure the variable can be serialized.
   *  @param val turn
   */
  public void setTurn(final int val) {
    this.turn = val;
  }

  /** Make sure the variable can be serialized.
   *  @return boardState
   */
  public char[][] getBoardState() {
    return boardState;
  }

  /** Make sure the variable can be serialized.
   *  @param newBoardState boardState
   */
  public void setBoardState(final char[][] newBoardState) {
    this.boardState = newBoardState;
  }

  /** Make sure the variable can be serialized.
   * @return winner
   */
  public int getWinner() {
    return winner;
  }

  /** Make sure the variable can be serialized.
   *  @param whoIsWinner winner
   */
  public void setWinner(final int whoIsWinner) {
    this.winner = whoIsWinner;
  }

  /** Make sure the variable can be serialized.
   *  @return isDraw
   */
  public boolean getIsDraw() {
    return isDraw;
  }

  /** Make sure the variable can be serialized.
   *  @param isDrawHappened isDraw
   */
  public void setIsDraw(final boolean isDrawHappened) {
    this.isDraw = isDrawHappened;
  }

}
