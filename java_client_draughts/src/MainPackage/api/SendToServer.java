package MainPackage.api;

import MainPackage.Main;
import MainPackage.entities.BoardTile;
import MainPackage.entities.Game;
import MainPackage.entities.GameState;
import MainPackage.gui.entities.BoardGui;
import javafx.application.Platform;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.concurrent.*;

public class SendToServer {

    public static final int SERVER_PORT = 3000;
    public static final String SERVER_ADDRESS = "http://localhost";

    private ExecutorService executorService;

    public static SendToServer instance = new SendToServer();

    private SendToServer(){
        executorService =  Executors.newSingleThreadExecutor();
    }

    public void AsyncSendToServer(GameState currentState){
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

    private GameState getServerNextMove(GameState currentState) {
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(SERVER_ADDRESS + ":" + SERVER_PORT);

            httpPost.addHeader("Content-type", "application/json");
            httpPost.addHeader("Accept", "application/json");
            String entity = currentState.toString();
            System.out.println(entity);
            httpPost.setEntity(new StringEntity(entity));


            CloseableHttpResponse response = null;

            response = client.execute(httpPost);

            if (response.getStatusLine().getStatusCode() != 200) {
                System.err.println("Cannot find server, exiting!");
                System.exit(1);
            }

            String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");


            GameState gameState = GameState.fromJsonArray(currentState, responseString);


            client.close();
            return gameState;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Cannot find server, exiting!");
            System.exit(1);
        }
        return null;

    }
}
