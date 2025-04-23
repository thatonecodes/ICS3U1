package InheritanceAssignment;

/**
 * Represents an HTTP GET request.
 */
public class GetRequest extends HttpRequest {

	/**
	 * Creates a new GET request for the specified URL.
	 * 
	 * @param url The URL to send the GET request to
	 */
	public GetRequest(String url) {
		super(url, "GET");
	}

	// Additional GET-specific methods could be added here
}
