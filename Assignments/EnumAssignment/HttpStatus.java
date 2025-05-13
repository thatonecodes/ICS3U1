package EnumAssignment;

import java.util.Random;

public enum HttpStatus {

    CONTINUE(100, "Continue"),
    SWITCHING_PROTOCOLS(101, "Switching Protocols"),
    OK(200, "OK"),
    CREATED(201, "Created"),
    ACCEPTED(202, "Accepted"),
    MULTIPLE_CHOICES(300, "Multiple Choices"),
    MOVED_PERMANENTLY(301, "Moved Permanently"),
    FOUND(302, "Found"),
    BAD_REQUEST(400, "Bad Request"),
    UNAUTHORIZED(401, "Unauthorized"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "Not Found"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    NOT_IMPLEMENTED(501, "Not Implemented"),
    BAD_GATEWAY(502, "Bad Gateway"),
    UNKNOWN(-1, "Unknown Status");

    private int code;
    private String description;

    HttpStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static HttpStatus getStatusFromCode(int code) {
        for (HttpStatus status : HttpStatus.values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        return UNKNOWN;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public boolean canContinue(){
        // returns if the HTTP request a fail or success
        int[] validHttpCodes = new int[] {200, 201, 100, 101, 202, 302};
        for (int validCode : validHttpCodes) {
            if (getCode() == validCode) {
                return true;
            }
        }
        return false;
    }

    public String responseType(int code) {
        // based on RFC 2616 section 6 - Response Types
        String[] contentTypes = new String[]{"Informational", "Success", "Redirection", "Client Error", "Server Error"};
        int index = Character.getNumericValue(String.valueOf(code).charAt(0)) - 1;
        if (index >= 0 && index < contentTypes.length){
            return contentTypes[index];
        }
        return UNKNOWN.getDescription();
    }

    public boolean isCommonCode() {
        // from top 10 most common HTTP request codes article
        int[] commonCodes = new int[] {200, 301, 302, 400, 401, 403, 404, 500, 502, 503};
        for (int commonCode : commonCodes) {
            if (getCode() == commonCode) {
                return true;
            }
        }
        return false;
    }

    public boolean isCacheable() {
        // based on RFC 2616 - Cacheable HTTP Request Codes
        int[] cachableCodes = new int[] {200, 203, 300, 301, 302, 404, 410};
        for (int cachableCode : cachableCodes) {
            if (getCode() == cachableCode) {
                return true;
            }
        }
        return false;
    }

    public HttpStatus getRandomHttpStatus() {
        // return a random HTTPStatus enum
        HttpStatus[] array = HttpStatus.values();
        return array[new Random().nextInt(array.length)];
    }
}