package email;

import java.util.HashMap;
import java.util.Map;

public class EmailReceiver {

  private String email;

  private String name;

  private String message;

  private final Map<String, String> templateMap;

  public EmailReceiver(String email, String name) {

    this.email = email;
    this.name = name;
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

  public String getMessage() {

    return message;
  }

  public void setMessage(String message) {

    this.message = message;
  }

}
