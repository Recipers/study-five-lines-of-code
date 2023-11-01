public class KEBBank implements BankCode {

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
    return false;
  }

  @Override
  public boolean isKEB() {
    return true;
  }

  @Override
  public boolean isSuHyup() {
    return false;
  }

  @Override
  public String formatAccountNumber(String accountNumber) {

    if (accountNumber.length() == 11) {
      return BankUtil.addHyphens(accountNumber, new int[]{3, 5, 10});
    }
    return BankUtil.addHyphens(accountNumber, new int[]{3, 9});
  }

}
