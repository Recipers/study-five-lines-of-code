package food869.was.bin;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

public class CommonClassLoader extends URLClassLoader {
	private Map<String, AppClassLoader> appClassLoaderMap;

	public CommonClassLoader(ClassLoader parent) {
		this(new URL[0], parent);
		appClassLoaderMap = new HashMap<>();
	}

	public CommonClassLoader(URL[] urls, ClassLoader parent) {
		super(urls, parent);
	}

	public void addURL(URL[] urls) {
		for (int i = 0; i < urls.length; i++) {
			addURL(urls[i]);
		}
	}

	final AppClassLoader makeAppClassLoader(String webApp) {
		AppClassLoader appClassLoader = new AppClassLoader(this);
		appClassLoaderMap.put(webApp, appClassLoader);
		return appClassLoader;
	}

	public final AppClassLoader getChild(String webApp) {
		return appClassLoaderMap.get(webApp);
	}

}
