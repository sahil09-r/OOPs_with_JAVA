package labsystem;

public class Asset {
    String assetId;
    String assetName;
    boolean available;
    int securityLevel;

    public Asset(String assetId, String assetName, boolean available, int securityLevel) {
        this.assetId = assetId;
        this.assetName = assetName;
        this.available = available;
        this.securityLevel = securityLevel;
    }

    public void checkPolicy(String uid) throws IllegalStateException, SecurityException {
        if (!available) {
            throw new IllegalStateException("Asset not available.");
        }
        if (securityLevel == 3 && !uid.startsWith("KRG")) {
            throw new SecurityException("Restricted asset. UID not authorized.");
        }
    }
}
