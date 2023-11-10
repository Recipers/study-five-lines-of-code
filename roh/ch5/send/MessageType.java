package send;

public class MessageType implements SendType {

  @Override
  public boolean isMessage() {
    return true;
  }

  @Override
  public boolean isEmail() {
    return false;
  }

}
