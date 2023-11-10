package message;

import send.AgentDeviceInfo;
import send.Receiver;
import send.Relayer;
import send.Sender;
import util.TemplateUtil;

public class MessageSender implements Sender {

  private final MessageRepository messageRepository;

  public MessageSender(MessageRepository messageRepository) {

    this.messageRepository = messageRepository;
  }

  public void doSend(Relayer relayer) {

    loadTemplateContent(relayer);
    for (Receiver receiver : relayer.getReceivers()) {
      receiver.addMessage(
          TemplateUtil.replaceTemplateBlock(relayer.getTemplate(), receiver.getTemplateMap()));
    }
    writeAgentTable(relayer);
  }


  public void loadTemplateContent(Relayer relayer) {

  }

  public void writeAgentTable(Relayer relayer) {
    
    AgentDeviceInfo agentDeviceInfo = null;
    for (Receiver receiver : relayer.getReceivers()) {
      agentDeviceInfo = getAgentDeviceInfo();
    }
    messageRepository.save();
  }

  public AgentDeviceInfo getAgentDeviceInfo() {

    return new AgentDeviceInfo();
  }

}
