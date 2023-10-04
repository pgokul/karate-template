package examples.users;


import com.intuit.karate.junit5.Karate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class UsersRunner {

    private final static Logger logger = LoggerFactory.getLogger(UsersRunner.class);
    @Karate.Test
    Karate testUsers() {
        return Karate.run("users").relativeTo(getClass());
    }

    public static void makeApiCall(String url){
        var t= new Thread(()->{
            try {
                logger.info("Running thread");
                Thread.sleep(500);
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(url))
                        .POST(HttpRequest.BodyPublishers.ofString("hello"))
                        .build();
                logger.info("Hitting Post on url {}",url);
                HttpResponse<String> response = client.send(request,
                        HttpResponse.BodyHandlers.ofString());
                logger.info("Got response {}",response);

            }catch (Exception e)
            {
                logger.error("Got exception in thread",e);
            }
        });
        t.start();
    }

}
