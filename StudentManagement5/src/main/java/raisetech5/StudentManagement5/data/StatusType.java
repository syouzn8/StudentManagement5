package raisetech5.StudentManagement5.data;

public enum StatusType {
  TENTATIVE_APPLICATION("仮申込"),
  CONFIRMED_APPLICATION("本申込"),
  IN_PROGRESS("受講中"),
  COMPLETED("受講終了");

  private final String label;

  StatusType(String label) {
    this.label = label;
  }

  public String getLabel() {
    return label;
  }

  public static StatusType fromString(String input) {
    for (StatusType status : StatusType.values()) {

      if (status.name().equals(input) || status.getLabel().equals(input)) {
        return status;
      }
    }
    throw new IllegalArgumentException("無効なステータス: " + input);
  }
}
