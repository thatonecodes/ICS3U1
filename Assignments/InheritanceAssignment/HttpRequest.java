package InheritanceAssignment;

import java.net.http.HttpResponse;
import java.net.http.HttpClient;
import java.net.http.HttpRequest.Builder;
import java.net.URI;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Represents a generic HTTP request. Common to all request types.
 *
 * @author Maher
 * @author ICS3U1
 * @version 1.0.0
 **/
public class HttpRequest {

	private String url;
	private String method;

	public HttpRequest(String url, String method) {
		this.url = url;
		this.method = method;
	}

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
}
