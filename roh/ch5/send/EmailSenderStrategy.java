package send;

import email.EmailRepository;

public class EmailSenderStrategy implements SenderStrategy {

  private final EmailRepository emailRepository;


  public EmailSenderStrategy(EmailRepository emailRepository) {
    this.emailRepository = emailRepository;
  }

  @Override
  public void save(Relayer relayer) {
    emailRepository.save();
  }
}
