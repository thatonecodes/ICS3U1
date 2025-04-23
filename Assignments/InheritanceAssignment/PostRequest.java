package InheritanceAssignment;

import java.net.http.HttpResponse;
import java.net.http.HttpClient;
import java.net.http.HttpRequest.Builder;
import java.net.URI;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Represents an HTTP POST request.
 */
public class PostRequest extends HttpRequest {

	private String body;

	/**
	 * Creates a new POST request for the specified URL.
	 * 
	 * @param url The URL to send the POST request to
	 */
	public PostRequest(String url) {
		super(url, "POST");
		this.body = "";
	}

	/**
	 * Creates a new POST request with the specified URL and body.
	 * 
	 * @param url  The URL to send the POST request to
	 * @param body The body content to send with the request
	 */
	public PostRequest(String url, String body) {
		super(url, "POST");
		this.body = body;
	}

	/**
	 * Overrides the send method to include the body in POST requests.
	 */
	@Override
	public HttpResponse<String> send() {
		try {
			HttpClient client = HttpClient.newHttpClient();

			URI uri = new URI(getUrl());
			java.net.http.HttpRequest request = java.net.http.HttpRequest.newBuilder()
					.uri(uri)
					.header("Content-Type", "application/json")
					.POST(java.net.http.HttpRequest.BodyPublishers.ofString(body))
					.build();

			return client.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (URISyntaxException | IOException | InterruptedException e) {
			e.printStackTrace();
			return null;
		}
	}

	// Getters and setters for body
	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
}
