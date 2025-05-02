package InheritanceAssignment;
import java.net.URL;
import java.net.http.HttpResponse;
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

    /**
     * Checks whether the given URL string contains a query string.
     * This method parses the URL and returns true if a non-empty query component is present.
     *
     * @param urlString The URL string to analyze.
     * @return true If the URL contains a query string; false otherwise.
     */
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
	 * Checks if the specified url can be cacheable in memory.
	 * 
	 * @param url The URL to check if it is cacheable.
	 * @return true If the URL string is able to be cached based on structure.
	 */
    public boolean isCacheable(String url) {
		return !hasQueryString(url);
    }

	/**
	 * Sends a HTTP GET request to "https://example.com" for testing purposes.
	 * 
	 * @return HttpResponse The HttpResponse received from "https://example.com".
	 */
	public HttpResponse<String> sendExampleResponse() {
		GetRequest getRequest = new GetRequest("https://example.com");
		return getRequest.send();
	}

	/**
	 * Sends a HTTP GET request to "https://http.cat/{YOUR_HTTP_CODE}" for nice cat images.
	 * 
	 * @param httpCode The HTTP code to get the image for (e.g., "200", "400", etc.)
	 * @return String The String received from the HttpResponse to "https://http.cat/{YOUR_HTTP_CODE}", link to an image.
	 */
	public String getCatImageFromCode(int httpCode) {
		GetRequest getRequest = new GetRequest(String.format("https://http.cat/%d", httpCode));
		HttpResponse<String> response = getRequest.send();
		return response.body();
	}
}
