package ch6_1.data;

import java.io.File;
import ch6_1.config.PathConfiguration;

public class Group {

  private Integer groupSequence;

  public String groupDirectoryPath(PathConfiguration pathConfiguration) {

    return String.join(File.separator, pathConfiguration.getGroupPath(),
        String.valueOf(this.groupSequence));
  }

}
