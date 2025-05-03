package InheritanceAssignment;

import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.net.http.HttpClient;
import java.net.URI;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Represents a generic HTTP request. Common to all request types.
 * This class encapsulates the basic properties of an HTTP request, such as URL
 * and method,
 * and provides a method to send the request using Java's built-in HttpClient.
 * 
 * Basic Example usage:
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
	private Map<String, String> headers = new HashMap<>();

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
	 * Constructs an HttpRequest with the specified URL, method and headers.
	 *
	 * @param url     The URL to which the request will be sent.
	 * @param method  The HTTP method to use (e.g., "GET", "POST").
	 * @param headers The headers in key value format, (e.g "Content-Type":
	 *                "application/json").
	 */
	public HttpRequest(String url, String method, Map<String, String> headers) {
		this.url = url;
		this.method = method;
		this.headers = headers;
	}

	/**
	 * Sends the HTTP request using the specified URL and method.
	 * 
	 * @return HttpResponse The response from the server, or null if an
	 *         error occurred.
	 */
	public HttpResponse<String> send() {
		try {
			HttpClient client = HttpClient.newHttpClient();

			URI uri = new URI(url);
			java.net.http.HttpRequest.Builder builder = java.net.http.HttpRequest.newBuilder()
					.uri(uri)
					.method(method, java.net.http.HttpRequest.BodyPublishers.noBody());

			for (Map.Entry<String, String> header : headers.entrySet()) {
				builder.header(header.getKey(), header.getValue());
			}

			java.net.http.HttpRequest request = builder.build();

			return client.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (URISyntaxException | IOException | InterruptedException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Sends the HTTP request using the specified URL and method only 50% of the
	 * time!
	 * 
	 * @return HttpResponse | null If you get heads, you get the response from the
	 *         server, but if you get tails, the program stops.
	 */
	public HttpResponse<String> coinFlipSend() {
		if (Math.random() < 0.5) {
			System.out.println("🪙 Coin flip: HEADS - sending request!");
			return send();
		} else {
			System.out.println("🪙 Coin flip: TAILS - ending program.");
			System.exit(1);
			return null;
		}
	}

	/**
	 * Adds a header to this HTTP request.
	 *
	 * @param key   The name of the header (e.g., "Content-Type").
	 * @param value The value of the header (e.g., "application/json").
	 */
	public void addHeader(String key, String value) {
		headers.put(key, value);
	}

	/**
	 * Removes the specified header from this HTTP request.
	 *
	 * @param key The name of the header to remove.
	 */
	public void removeHeader(String key) {
		headers.remove(key);
	}

	/**
	 * Returns all headers currently set on this HTTP request.
	 *
	 * @return A map of header names to their corresponding values.
	 */
	public Map<String, String> getHeaders() {
		return headers;
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

	/**
	 * Sets the HTTP method of this request.
	 *
	 * @param method The method to set (e.g., "GET", "POST").
	 */
	public void setMethod(String method) {
		this.method = method;
	}

	/**
	 * Adds a randomly selected User-Agent header to this HTTP request.
	 * <p>
	 * This can be useful for testing or simulating requests from different clients.
	 * The selected User-Agent is printed to the console for reference.
	 * </p>
	 *
	 * @see #addHeader(String, String)
	 */
	public void setRandomUserAgent() {
		String[] userAgents = {
				"Mozilla/5.0 (compatible; MSIE 6.0; Windows NT 5.1)",
				"curl/7.64.1",
				"PostmanRuntime/7.28.0",
				"BananaBot/1.0",
				"MaherBrowser/1.0",
				"FrogFetch/2.2"
		};
		String random = userAgents[(int) (Math.random() * userAgents.length)];
		addHeader("User-Agent", random);
		System.out.printf("Random User-Agent selected was: %s%n", random);
	}

	/**
	 * Returns the toString representation of this object.
	 * 
	 * @return HTTP method and url as a string.
	 */
	public String toString() {
		return String.format("%s HTTP Request for url: %s", method, url);
	}
}