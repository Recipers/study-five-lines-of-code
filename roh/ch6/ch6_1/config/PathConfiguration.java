package ch6_1.config;

import java.io.File;

public class PathConfiguration {

  private final ApplicationConfiguration applicationConfiguration;

  public PathConfiguration(ApplicationConfiguration applicationConfiguration) {

    this.applicationConfiguration = applicationConfiguration;
  }

  private String groupPath;

  public String getGroupPath() {

    return this.groupPath;
  }

  // @PostConstruct
  public void init() {

    this.groupPath = String.join(File.separator, applicationConfiguration.getRoot(),
        applicationConfiguration.getGroup());
  }

}
