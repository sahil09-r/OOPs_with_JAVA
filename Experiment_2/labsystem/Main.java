package labsystem;

public class Main {
    public static void main(String[] args) {
        Student s1 = new Student("KRG20281", "Sahil", 0, 0);
        Student s2 = new Student("STD10001", "Ankit", 50, 0);
        Student s3 = new Student("STD10002", "Aryan", 0, 2);

        Asset a1 = new Asset("LAB-101", "HDMI Cable", true, 1);
        Asset a2 = new Asset("LAB-102", "Projector", true, 2);
        Asset a3 = new Asset("LAB-103", "Secure Laptop", true, 3);

        AssetStore store = new AssetStore();
        store.addAsset(a1);
        store.addAsset(a2);
        store.addAsset(a3);

        CheckoutService service = new CheckoutService(store, new Student[]{s1, s2, s3});

        CheckoutRequest[] requests = {
            new CheckoutRequest("KRG20281", "LAB-101", 4),   
            new CheckoutRequest("STD10001", "BAD-999", 2),   
            new CheckoutRequest("STD10002", "LAB-102", 2)    
        };

        for (CheckoutRequest req : requests) {
            try {
                String receipt = service.checkout(req);
                System.out.println("Checkout successful: " + receipt);
            } catch (IllegalArgumentException e) {
                AuditLogger.logError(e);
            } catch (NullPointerException e) {
                AuditLogger.logError(e);
            } catch (SecurityException e) {
                AuditLogger.logError(e);
            } catch (IllegalStateException e) {
                AuditLogger.logError(e);
            } finally {
                AuditLogger.log("Attempt finished for UID=" + req.uid + ", asset=" + req.assetId);
            }
        }
    }
}