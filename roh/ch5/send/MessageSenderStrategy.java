package send;

import message.MessageRepository;

public class MessageSenderStrategy implements SenderStrategy {

  private final MessageRepository messageRepository;


  public MessageSenderStrategy(MessageRepository messageRepository) {
    this.messageRepository = messageRepository;
  }

  @Override
  public void save(Relayer relayer) {
    messageRepository.save();
  }
}
