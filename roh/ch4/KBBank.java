public class KBBank implements BankCode {

  @Override
  public boolean isKDB() {
    return false;
  }

  @Override
  public boolean isIBK() {
    return false;
  }

  @Override
  public boolean isKB() {
    return true;
  }

  @Override
  public boolean isKEB() {
    return false;
  }

  @Override
  public boolean isSuHyup() {
    return false;
  }

  @Override
  public String formatAccountNumber(String accountNumber) {

    if (accountNumber.length() == 12) {
      return BankUtil.addHyphens(accountNumber, new int[]{3, 5, 9});
    }
    return BankUtil.addHyphens(accountNumber, new int[]{6, 8});
  }

}
