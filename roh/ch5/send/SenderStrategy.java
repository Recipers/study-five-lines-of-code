package send;

import email.EmailRepository;
import message.MessageRepository;

public class SenderStrategy {

  private final MessageRepository messageRepository;

  private final EmailRepository emailRepository;

  public SenderStrategy(MessageRepository messageRepository, EmailRepository emailRepository) {
    this.messageRepository = messageRepository;
    this.emailRepository = emailRepository;
  }

  public void save(SendType sendType) {
    if (sendType.isMessage()) {
      messageRepository.save();
    } else {
      emailRepository.save();
    }
  }

}
