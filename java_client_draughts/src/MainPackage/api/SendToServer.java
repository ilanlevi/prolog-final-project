package MainPackage.api;

import MainPackage.entities.GameState;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.concurrent.Future;

public class SendToServer {

    public static final int SERVER_PORT = 3000;
    public static final String SERVER_ADDRESS = "http://localhost";


    public static GameState getServerNextMove(GameState currentState) {
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

            System.out.println("blabla");
            String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");


            GameState gameState = GameState.fromJsonArray(currentState, responseString);


            System.out.println(responseString);

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
