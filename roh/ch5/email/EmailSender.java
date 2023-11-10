package email;

import util.TemplateUtil;

public class EmailSender {

  private final EmailRepository emailRepository;

  public EmailSender(EmailRepository emailRepository) {

    this.emailRepository = emailRepository;
  }

  public void doEmailSend(EmailRelayer relayer) {

    loadTemplateContent(relayer);
    for (EmailReceiver receiver : relayer.getReceivers()) {
      receiver.setMessage(
          TemplateUtil.replaceTemplateBlock(relayer.getTemplate(), receiver.getTemplateMap()));
    }

    writeEmailAgentTables(relayer);
  }

  protected void loadTemplateContent(EmailRelayer relayer) {

    // load template contents
  }

  protected void writeEmailAgentTables(EmailRelayer relayer) {

    EmailDeviceInfo emailDeviceInfo = null;
    for (EmailReceiver receiver : relayer.getReceivers()) {
      emailDeviceInfo = getEmailDeviceInfo();
    }
    emailRepository.save();
  }

  protected EmailDeviceInfo getEmailDeviceInfo() {

    return new EmailDeviceInfo();
  }

}
