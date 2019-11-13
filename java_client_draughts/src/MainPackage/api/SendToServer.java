package MainPackage.api;

import MainPackage.entities.Game;
import MainPackage.entities.GameSettings;
import MainPackage.entities.GameState;
import javafx.application.Platform;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class SendToServer {

    /**
     * Send current state to server, wait for response and update gui and current game state
     *
     * @param currentState for server to do next move
     */
    public static void AsyncSendToServer(GameState currentState) {
        Platform.runLater(() -> {
            GameState gameState = getServerNextMove(currentState);
            if (gameState != null) {
                gameState.markNewQueensIfNeeded();
                System.err.println(gameState);
                Game.instance().addNewState(gameState);
                Game.instance().getBoardGui().markMove();
            }
        });

    }

    /**
     * Send to server http request with the current game state and game settings level to compute next move
     *
     * @param currentState the game state
     * @return new game state after PC move, if cannot reach server, show message
     */
    private static GameState getServerNextMove(GameState currentState) {
        try {
            GameSettings gameSettings = Game.instance().getSettings();
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(gameSettings.getServerFullUrl());

            httpPost.addHeader("Content-type", "application/json");
            httpPost.addHeader("Accept", "application/json");
            String entity = currentState.toString();
            System.out.println(entity);
            httpPost.setEntity(new StringEntity(entity));

            CloseableHttpResponse response = null;

            response = client.execute(httpPost);
            String responseString = null;
            GameState gameState = null;

            if (response.getStatusLine().getStatusCode() != 200) {
                System.err.println("Cannot find server!");
            } else {
                responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
                gameState = GameState.fromJsonArray(currentState, responseString);
            }

            client.close();
            return gameState;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Cannot find server!");
        }
        return null;

    }
}
