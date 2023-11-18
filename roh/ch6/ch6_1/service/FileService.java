package ch6_1.service;

import java.io.File;
import ch6_1.config.PathConfiguration;
import ch6_1.data.Group;
import ch6_1.data.GroupFile;

public class FileService {

  private final PathConfiguration pathConfiguration;

  public FileService(PathConfiguration pathConfiguration) {

    this.pathConfiguration = pathConfiguration;
  }

  public File findGroupAdminFileInDisk(GroupFile groupFile) {

    String groupFilePath = getGroupAdminFilePath(groupFile);
    return new File(groupFilePath);
  }

  private String getGroupAdminFilePath(GroupFile groupFile) {

    Group group = groupFile.getGroup();
    return String.join(File.separator, pathConfiguration.getGroupPath(),
        String.valueOf(group.getGroupSequence()), groupFile.getFilePath());
  }

}
