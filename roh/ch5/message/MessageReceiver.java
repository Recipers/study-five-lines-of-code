package message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageReceiver {

  private String cell;

  private String name;

  private List<String> messages;

  private final Map<String, String> templateMap;

  public MessageReceiver(String cell, String name) {

    this.cell = cell.replaceAll("-", "");
    this.name = name;
    this.messages = new ArrayList<>();
    this.templateMap = new HashMap<>();
  }

  public void putTemplateMap(String key, String value) {

    templateMap.put(key, value);
  }

  public String getCell() {

    return cell;
  }

  public void setCell(String cell) {

    this.cell = cell;
  }

  public String getName() {

    return name;
  }

  public void setName(String name) {

    this.name = name;
  }

  public Map<String, String> getTemplateMap() {

    Map<String, String> copyMap = new HashMap<>();

    for (Map.Entry<String, String> entry : templateMap.entrySet()) {
      copyMap.put(entry.getKey(), entry.getValue());
    }
    return copyMap;
  }

  public void addMessage(String message) {

    messages.add(message);
  }

}
