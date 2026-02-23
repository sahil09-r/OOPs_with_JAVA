package labsystem;

public class Student {
    String uid;
    String name;
    int fineAmount;
    int currentBorrowCount;

    public Student(String uid, String name, int fineAmount, int currentBorrowCount) {
        this.uid = uid;
        this.name = name;
        this.fineAmount = fineAmount;
        this.currentBorrowCount = currentBorrowCount;
    }

    public void checkPolicy() throws IllegalStateException {
        if (fineAmount > 0) {
            throw new IllegalStateException("Student has pending fine.");
        }
        if (currentBorrowCount >= 2) {
            throw new IllegalStateException("Borrow limit reached.");
        }
    }
}