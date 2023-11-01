public class SuHyupBank implements BankCode {

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
    return false;
  }

  @Override
  public boolean isSuHyup() {
    return true;
  }

  @Override
  public String formatAccountNumber(String accountNumber) {

    if (accountNumber.length() == 11) {
      return BankUtil.addHyphens(accountNumber, new int[]{3, 5, 10});
    } else if (accountNumber.length() == 12) {
      return BankUtil.addHyphens(accountNumber, new int[]{3, 11});
    }
    return BankUtil.addHyphens(accountNumber, new int[]{3, 5, 13});
  }

}
