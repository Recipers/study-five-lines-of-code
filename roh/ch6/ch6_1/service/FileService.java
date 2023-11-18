package ch6_1.service;

import java.io.File;
import ch6_1.config.PathConfiguration;
import ch6_1.data.GroupFile;

public class FileService {

  private final PathConfiguration pathConfiguration;

  public FileService(PathConfiguration pathConfiguration) {

    this.pathConfiguration = pathConfiguration;
  }

  public File findGroupAdminFileInDisk(GroupFile groupFile) {

    String groupFilePath = groupFile.getGroupFilePath(pathConfiguration);
    return new File(groupFilePath);
  }

}
