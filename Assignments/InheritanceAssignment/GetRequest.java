package InheritanceAssignment;
import java.net.URL;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException; 
/**
 * Represents an HTTP GET request.
 * <p>
 * This class is a specialized type of {@link HttpRequest} that always uses the
 * "GET" method.
 * It can be used to retrieve data from the specified URL.
 * </p>
 * 
 * <p>
 * Example usage:
 * </p>
 * 
 * <pre>{@code
 * GetRequest getRequest = new GetRequest("https://example.com/data");
 * HttpResponse<String> response = getRequest.send();
 * System.out.println(response.body());
 * }</pre>
 * 
 * @see HttpRequest
 */
public class GetRequest extends HttpRequest {

	/**
	 * Constructs a new GET request for the specified URL.
	 * 
	 * @param url The URL to send the GET request to.
	 */
	public GetRequest(String url) {
		super(url, "GET");
	}

	// methods to add:
	// - Adding query parameters
	// - Customizing headers
	// - Caching logic

    private static boolean hasQueryString(String urlString) {
        try {
            URL url = new URI(urlString).toURL();
            String query = url.getQuery();
            return query != null && !query.isEmpty();
        } catch (URISyntaxException e) {
            System.err.println("Malformed URI: " + e.getMessage());
            return false;
        } catch (MalformedURLException e) {
            System.err.println("Malformed URL: " + e.getMessage());
            return false;
		}
    } 

	/**
	 * Checks if the specified url can be cacheable in localstorage.
	 * 
	 * @param url The URL to check if it is cacheable.
	 */
    public boolean isCacheable(String url) {
		return hasQueryString(url);
    }
}
