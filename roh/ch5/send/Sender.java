package send;

import send.AgentDeviceInfo;
import send.MessageSenderStrategy;
import send.Receiver;
import send.Relayer;
import send.SenderStrategy;
import util.TemplateUtil;

public class Sender {

  private final SenderStrategy senderStrategy;

  public Sender(SenderStrategy senderStrategy) {

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
    senderStrategy.save(relayer);
  }

  public AgentDeviceInfo getAgentDeviceInfo() {

    return new AgentDeviceInfo();
  }

}
