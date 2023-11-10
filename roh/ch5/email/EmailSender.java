package email;

import send.AgentDeviceInfo;
import send.Receiver;
import send.Relayer;
import send.Sender;
import util.TemplateUtil;

public class EmailSender implements Sender {

  private final EmailRepository emailRepository;

  public EmailSender(EmailRepository emailRepository) {

    this.emailRepository = emailRepository;
  }

  @Override
  public void doSend(Relayer relayer) {

    loadTemplateContent(relayer);
    for (Receiver receiver : relayer.getReceivers()) {
      receiver.setMessage(
          TemplateUtil.replaceTemplateBlock(relayer.getTemplate(), receiver.getTemplateMap()));
    }

    writeAgentTable(relayer);
  }

  @Override
  public void loadTemplateContent(Relayer relayer) {

    // load template contents
  }

  @Override
  public void writeAgentTable(Relayer relayer) {

    AgentDeviceInfo agentDeviceInfo = null;
    for (Receiver receiver : relayer.getReceivers()) {
      agentDeviceInfo = getAgentDeviceInfo();
    }
    emailRepository.save();
  }

  @Override
  public AgentDeviceInfo getAgentDeviceInfo() {

    return new AgentDeviceInfo();
  }

}
