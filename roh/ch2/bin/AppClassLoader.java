package food869.was.bin;

import java.net.URL;
import java.net.URLClassLoader;

public class AppClassLoader extends URLClassLoader {

  private CommonClassLoader commonClassLoader;

  public AppClassLoader(CommonClassLoader commonClassLoader) {
    super(new URL[0]);
    this.commonClassLoader = commonClassLoader;
  }

  @Override
  public Class<?> loadClass(String name) throws ClassNotFoundException {
    if (this.getURLs() != commonClassLoader.getURLs()) {
      this.addURL(commonClassLoader.getURLs());
    }
    return super.loadClass(name);
  }

  public void addURL(URL[] urls) {
    for (int i = 0; i < urls.length; i++) {
      addURL(urls[i]);
    }
  }

}
