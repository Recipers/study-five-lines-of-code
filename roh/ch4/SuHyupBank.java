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

}
