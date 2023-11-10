package message;

import email.EmailRepository;
import send.AgentDeviceInfo;
import send.Receiver;
import send.Relayer;
import send.SendType;
import send.Sender;
import send.SenderStrategy;
import util.TemplateUtil;

public class MessageSender implements Sender {

  private final SenderStrategy senderStrategy;

  private final SendType sendType;

  public MessageSender(SendType sendType, SenderStrategy senderStrategy) {

    this.sendType = sendType;
    this.senderStrategy = senderStrategy;
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
    senderStrategy.save(this.sendType);
  }

  public AgentDeviceInfo getAgentDeviceInfo() {

    return new AgentDeviceInfo();
  }

}
