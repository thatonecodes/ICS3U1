package InheritanceAssignment;

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
}
