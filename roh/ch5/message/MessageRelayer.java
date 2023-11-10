package message;


import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class MessageRelayer {

  private String template;

  private LocalDateTime sendTime;

  private String title;

  private final Set<MessageReceiver> receivers;

  public MessageRelayer(LocalDateTime sendTime, String template) {

    this.sendTime = sendTime;
    this.template = template;
    this.receivers = new HashSet<>();
  }

  public MessageRelayer(LocalDateTime sendTime, String title, String template) {

    this(sendTime, template);
    this.title = title;
  }


  public void addReceivers(MessageReceiver receiver) {

    this.receivers.add(receiver);
  }

  public void removeReceivers(MessageReceiver receiver) {

    this.receivers.remove(receiver);
  }

  public String getTemplate() {

    return template;
  }

  public void setTemplate(String template) {

    this.template = template;
  }

  public LocalDateTime getSendTime() {

    return sendTime;
  }

  public void setSendTime(LocalDateTime sendTime) {

    this.sendTime = sendTime;
  }

  public String getTitle() {

    return title;
  }

  public void setTitle(String title) {

    this.title = title;
  }

  public Set<MessageReceiver> getReceivers() {

    return this.receivers;
  }

}
