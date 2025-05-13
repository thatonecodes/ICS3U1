package EnumAssignment;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== HTTP STATUS ENUM TESTS ===\n");

        // Test: Print all enum values with all associated methods
        for (HttpStatus status : HttpStatus.values()) {
            System.out.println("Code: " + status.getCode() + " (" + status.getDescription() + ")");
            System.out.println(" → Can Continue: " + status.canContinue());
            System.out.println(" → Is Common Code: " + status.isCommonCode());
            System.out.println(" → Is Cacheable: " + status.isCacheable());
            System.out.println(" → Response Type: " + status.responseType(status.getCode()));
            System.out.println(" → Get Random HTTP Code: " + status.getRandomHttpStatus());
            System.out.println();
        }

        // Test: getStatusFromCode
        System.out.println("=== TEST: getStatusFromCode ===");
        int[] testCodes = {200, 404, 999, -1, 302, 100};
        for (int code : testCodes) {
            HttpStatus status = HttpStatus.getStatusFromCode(code);
            System.out.println("Input Code: " + code + " → Result: " + status.name() + " (" + status.getDescription() + ")");
        }
        System.out.println();

        // Test: responseType edge cases
        System.out.println("=== TEST: responseType (invalid inputs) ===");
        int[] invalidCodes = {-100, 0, 999};
        for (int code : invalidCodes) {
            String type;
            try {
                type = HttpStatus.OK.responseType(code);
            } catch (Exception e) {
                type = "Error: " + e.getMessage();
            }
            System.out.println("Code: " + code + " → Response Type: " + type);
        }

        System.out.println("\n=== DONE ===");
    }
}
