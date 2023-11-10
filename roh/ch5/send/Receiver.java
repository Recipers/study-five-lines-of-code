package send;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Receiver {

  private String cell;
  private String email;

  private String name;

  private final List<String> messages;

  private final Map<String, String> templateMap;

  public Receiver(String cell, String email, String name) {

    this.cell = cell;
    this.email = email;
    this.name = name;
    this.messages = new ArrayList<>();
    this.templateMap = new HashMap<>();
  }

  public void putTemplateMap(String key, String value) {

    templateMap.put(key, value);
  }

  public Map<String, String> getTemplateMap() {

    Map<String, String> copyMap = new HashMap<>();

    for (Map.Entry<String, String> entry : templateMap.entrySet()) {
      copyMap.put(entry.getKey(), entry.getValue());
    }
    return copyMap;
  }

  public String getCell() {

    return cell;
  }

  public void setCell(String cell) {

    this.cell = cell;
  }

  public String getEmail() {

    return email;
  }

  public void setEmail(String email) {

    this.email = email;
  }

  public String getName() {

    return name;
  }

  public void setName(String name) {

    this.name = name;
  }

  public void addMessage(String message) {

    messages.add(message);
  }

}
