package InheritanceAssignment;

import java.net.http.HttpResponse;
import java.net.http.HttpClient;
import java.net.http.HttpRequest.Builder;
import java.net.URI;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Represents a generic HTTP request. Common to all request types.
 * This class encapsulates the basic properties of an HTTP request, such as URL
 * and method,
 * and provides a method to send the request using Java's built-in HttpClient.
 * 
 * Example usage:
 * 
 * <pre>{@code
 * HttpRequest request = new HttpRequest("https://example.com", "GET");
 * HttpResponse<String> response = request.send();
 * System.out.println(response.body());
 * }</pre>
 * 
 * @author Maher
 * @author ICS3U1
 * @version 1.0.0
 **/
public class HttpRequest {

	private String url;
	private String method;

	/**
	 * Constructs an HttpRequest with the specified URL and method.
	 *
	 * @param url    The URL to which the request will be sent.
	 * @param method The HTTP method to use (e.g., "GET", "POST").
	 */
	public HttpRequest(String url, String method) {
		this.url = url;
		this.method = method;
	}

	/**
	 * Sends the HTTP request using the specified URL and method.
	 * 
	 * @return HttpResponse<String> The response from the server, or null if an
	 *         error occurred.
	 */
	public HttpResponse<String> send() {
		try {
			HttpClient client = HttpClient.newHttpClient();

			URI uri = new URI(url);
			java.net.http.HttpRequest request = java.net.http.HttpRequest.newBuilder()
					.uri(uri)
					.method(method, java.net.http.HttpRequest.BodyPublishers.noBody())
					.build();

			return client.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (URISyntaxException | IOException | InterruptedException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Gets the URL of this HTTP request.
	 *
	 * @return The URL as a string.
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Sets the URL of this HTTP request.
	 *
	 * @param url The URL to set.
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Gets the HTTP method of this request.
	 *
	 * @return The HTTP method as a string.
	 */
	public String getMethod() {
		return method;
	}

	// TODO override ToString methods

	/**
	 * Sets the HTTP method of this request.
	 *
	 * @param method The method to set (e.g., "GET", "POST").
	 */
	public void setMethod(String method) {
		this.method = method;
	}
}
