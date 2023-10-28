package food869.was.bin;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashSet;

public class Starter {

  private CommonClassLoader commonLoader;

  public Starter() {
    this.commonLoader = new CommonClassLoader(Thread.currentThread().getContextClassLoader());
  }

  private void addClasses() throws IOException {
    ClassFinder classFinder = new ClassFinder();
    HashSet<URL> rootLib = classFinder.findClassInRootLib();
    HashSet<File> webApps = classFinder.getWebApps();

    for (File webApp : webApps) {
      String classPath = "/WEB-INF/classes/";
      File classDirectory = new File(webApp.getCanonicalPath() + classPath);

      AppClassLoader appLoader = commonLoader.makeAppClassLoader("/" + webApp.getName());
      appLoader.addURL(
          new URL[]{new URL("file:" + classDirectory.getCanonicalPath() + File.separator)});
    }

    commonLoader.addURL((URL[]) rootLib.toArray(new URL[rootLib.size()]));
  }

  private void loadContainer() throws Exception {
    Class<?> containerClass = commonLoader.loadClass("food869.was.lib.container.Container");
    Object container = containerClass.newInstance();
    Method startMethod = containerClass.getDeclaredMethod("start", commonLoader.getClass());
    startMethod.invoke(container, commonLoader);
  }

  public static void main(String[] args) {
    Starter starter = new Starter();
    try {
      starter.addClasses(); // throws IOException
      starter.loadContainer(); // throws too many Exception
    } catch (IOException e) {
      System.out.println("No Class Files In WebApp Directory");
      System.exit(0);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
