package controllers;

import com.google.gson.Gson;
import io.javalin.Javalin;
import java.io.IOException;
import java.util.Queue;
import models.GameBoard;
import models.GameBoard.Result;
import models.Message;
import org.eclipse.jetty.websocket.api.Session;

final class PlayGame {

  private PlayGame() {
    //not called
  }

  /** port number. */
  private static final int PORT_NUMBER = 8080;
  /** the Javalin application. */
  private static Javalin app;
  /** the current GameBoard object. */
  private static GameBoard gb = null;
  /** Gson Object used to transfer Object to Json String. */
  private static Gson gson = new Gson();
  /** the error messages. */
  private static String[] messages = {
    "",
    "This position has been taken",
    "It's not your turn",
    "Your opponent hasn't joined",
    "Game is ended"
  };
  /** the type information's index in message. */
  private static final int TYPE_INDEX_IN_MESSAGE = 5;
  /** the default status message code. */
  private static final int DEFAULT_MESSAGE_CODE = 100;

  /** Main method of the application.
   * @param args Command line arguments
   */
  public static void main(final String[] args) {

    app = Javalin.create(config -> {
      config.addStaticFiles("/public");
    }).start(PORT_NUMBER);

    // Test Echo Server
    app.post("/echo", ctx -> {
      ctx.result(ctx.body());
    });

    // newGame
    app.get("/newgame", ctx -> {
      ctx.redirect("/tictactoe.html");
    });

    // startGame
    app.post("/startgame", ctx -> {
      char choice = ctx.body().charAt(TYPE_INDEX_IN_MESSAGE);
      gb = new GameBoard(choice);
      
      ctx.result(gson.toJson(gb));
    });

    // joinGame
    app.get("/joingame", ctx -> {
      if (gb == null) {
        ctx.result("Link is invalid");
        return;
      }
      gb.setGameStarted(true);
      ctx.redirect("/tictactoe.html?p=2");
      sendGameBoardToAllPlayers(gson.toJson(gb));
    });

    // move
    app.post("/move/:playerId", ctx -> {
      String playerId = ctx.pathParam("playerId");
      char c;

      if (playerId.equals("1")) {
        c = gb.getP1().getType();
      } else {
        c = gb.getP2().getType();
      }
      int x = Integer.parseInt(ctx.formParam("x"));
      int y = Integer.parseInt(ctx.formParam("y"));
      Result res = gb.update(x, y, c);
      Message m = new Message(res == Result.VALID, DEFAULT_MESSAGE_CODE,
          messages[res.ordinal()]);
      ctx.result(gson.toJson(m));
      sendGameBoardToAllPlayers(gson.toJson(gb));
    });

    // Web sockets - DO NOT DELETE or CHANGE
    app.ws("/gameboard", new UiWebSocket());
  }

  /** Send message to all players.
   * @param gameBoardJson Gameboard JSON
   * @throws IOException Websocket message send IO Exception
   */
  private static void sendGameBoardToAllPlayers(final String gameBoardJson) {
    Queue<Session> sessions = UiWebSocket.getSessions();
    for (Session sessionPlayer : sessions) {
      try {
        sessionPlayer.getRemote().sendString(gameBoardJson);
      } catch (IOException e) {
        // Add logger here
      }
    }
  }

  public static void stop() {
    app.stop();
  }
}
