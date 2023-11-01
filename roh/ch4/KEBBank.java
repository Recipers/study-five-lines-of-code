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

}
