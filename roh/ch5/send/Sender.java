package send;

import send.AgentDeviceInfo;
import send.Relayer;

public interface Sender {

  void doSend(Relayer relayer);

  void loadTemplateContent(Relayer relayer);

  void writeAgentTable(Relayer relayer);

  AgentDeviceInfo getAgentDeviceInfo();

}
