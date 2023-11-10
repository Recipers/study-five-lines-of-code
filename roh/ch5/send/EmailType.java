package send;

public class EmailType implements SendType {

  @Override
  public boolean isMessage() {
    return false;
  }

  @Override
  public boolean isEmail() {
    return true;
  }

}
