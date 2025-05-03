package InheritanceAssignment;

public class Main {

    public static void main(String[] args) {
        // Test for getRequest
        GetRequest getRequest = new GetRequest("https://uselessfacts.jsph.pl/api/v2/facts/random");
        String responseBody = getRequest.send().body();
        System.out.println(getRequest.toString());
        assert responseBody != null;

        String catResponse = getRequest.getCatImageFromCode(400);
        assert catResponse != null;

        System.out.println(getRequest.coinFlipSend().body());
    }
}
