package InheritanceAssignment;

import java.net.http.HttpResponse;
import java.net.http.HttpClient;
import java.net.URI;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Represents an HTTP POST request.
 * <p>
 * This class extends {@link HttpRequest} and adds support for a request body.
 * It automatically sets the method to "POST" and sends the request body as
 * JSON.
 * </p>
 *
 * <p>
 * Example usage:
 * </p>
 * 
 * <pre>{@code
 * PostRequest postRequest = new PostRequest("https://example.com/api", "{\"key\":\"value\"}");
 * HttpResponse<String> response = postRequest.send();
 * System.out.println(response.body());
 * }</pre>
 *
 * @see HttpRequest
 */
public class PostRequest extends HttpRequest {

	private String body;

	/**
	 * Constructs a new POST request for the specified URL with an empty body.
	 *
	 * @param url The URL to send the POST request to.
	 */
	public PostRequest(String url) {
		super(url, "POST");
		this.body = "";
	}

	/**
	 * Constructs a new POST request for the specified URL and request body.
	 *
	 * @param url  The URL to send the POST request to.
	 * @param body The request body to send as a string (typically JSON).
	 */
	public PostRequest(String url, String body) {
		super(url, "POST");
		this.body = body;
	}

	/**
	 * Sends the POST request with the included body content.
	 * The content type is set to "application/json" by default.
	 *
	 * @return HttpResponse<String> The response from the server, or null if an
	 *         error occurred.
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

	/**
	 * Gets the request body.
	 *
	 * @return The current body content as a string.
	 */
	public String getBody() {
		return body;
	}

	/**
	 * Sets the request body.
	 *
	 * @param body The new body content to send.
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * Sends the PostRequest and returns the response body as a string.
	 *
	 * @return The response body as a string.
	 */
	public String sendReadResponse() {
		return send().body().toString();
	}
}
