public interface BankCode {

  boolean isKDB();

  boolean isIBK();

  boolean isKB();

  boolean isKEB();

  boolean isSuHyup();

  String formatAccountNumber(String accountNumber);

}
