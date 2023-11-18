package ch6_1.data;

import java.io.File;
import ch6_1.config.PathConfiguration;

public class GroupFile {

  private Integer fileSequence;

  private String filePath;

  private Group group;

  public String getGroupFilePath(PathConfiguration pathConfiguration) {

    return String.join(File.separator, group.groupDirectoryPath(pathConfiguration), this.filePath);
  }

}
