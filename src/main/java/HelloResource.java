import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/")
public class HelloResource {

    @GET
    @Produces("text/plain")
    public String getMessage() {
        return "hello";
    }
}
