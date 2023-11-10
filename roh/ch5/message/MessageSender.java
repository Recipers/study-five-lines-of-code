package message;

import util.TemplateUtil;

public class MessageSender {

  private final MessageRepository messageRepository;

  public MessageSender(MessageRepository messageRepository) {

    this.messageRepository = messageRepository;
  }

  public void doSend(MessageRelayer relayer) {

    loadTemplateContent(relayer);
    for (MessageReceiver receiver : relayer.getReceivers()) {
      receiver.addMessage(
          TemplateUtil.replaceTemplateBlock(relayer.getTemplate(), receiver.getTemplateMap()));
    }
    writeMessageAgentTable(relayer);
  }


  protected void loadTemplateContent(MessageRelayer relayer) {

  }

  protected void writeMessageAgentTable(MessageRelayer relayer) {


    MessageDeviceInfo messageDeviceInfo = null;
    for (MessageReceiver receiver : relayer.getReceivers()) {
      messageDeviceInfo = getMessageDeviceInfo();
    }
    messageRepository.save();
  }

  protected MessageDeviceInfo getMessageDeviceInfo() {

    return new MessageDeviceInfo();
  }

}
