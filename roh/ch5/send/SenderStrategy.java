package send;

import email.EmailRepository;
import message.MessageRepository;

public interface SenderStrategy {

  void save(Relayer relayer);

}
