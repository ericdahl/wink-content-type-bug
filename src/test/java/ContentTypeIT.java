import com.jcabi.http.Request;
import com.jcabi.http.request.JdkRequest;
import com.jcabi.http.response.RestResponse;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.net.HttpURLConnection;

public class ContentTypeIT {

    private Server server = new Server(8080);

    @Before
    public void setup() throws Exception {
        WebAppContext webapp = new WebAppContext();
        webapp.setContextPath("/");
        webapp.setWar("target/hellowink-1.0-SNAPSHOT.war");

        server.setHandler(webapp);

        server.start();
//        server.join();
    }

    @After
    public void shutdown() throws Exception {
        server.stop();
    }

    @Test
    public void basic() throws Exception {
        new JdkRequest("http://localhost:8080/")
            .method(Request.GET)
                .header(HttpHeaders.ACCEPT, MediaType.TEXT_PLAIN)
            .fetch()
            .as(RestResponse.class)
                .assertHeader("Content-Type", "text/plain")
                .assertStatus(HttpURLConnection.HTTP_OK);
    }

    @Test
    public void quality() throws Exception {
        new JdkRequest("http://localhost:8080/")
                .method(Request.GET)
                .header(HttpHeaders.ACCEPT, MediaType.TEXT_PLAIN + ";q=0.5," + MediaType.APPLICATION_JSON + ";q=0.1")
                .fetch()
                .as(RestResponse.class)
                .assertHeader("Content-Type", "text/plain")
                .assertStatus(HttpURLConnection.HTTP_OK);
    }
}
