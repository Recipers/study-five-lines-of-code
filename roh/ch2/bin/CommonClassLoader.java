package food869.was.bin;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;

public class CommonClassLoader extends URLClassLoader {
	private HashMap<String, AppClassLoader> childMap;

	public CommonClassLoader(ClassLoader parent) {
		this(new URL[0], parent);
		childMap = new HashMap<>();
	}

	public CommonClassLoader(URL[] urls, ClassLoader parent) {
		super(urls, parent);
	}

	public void addURL(URL[] urls) {
		for (int i = 0; i < urls.length; i++) {
			addURL(urls[i]);
		}
	}

	final AppClassLoader makeAppCL(String webApp) {
		AppClassLoader child = new AppClassLoader(this);
		childMap.put(webApp, child);
		return child;
	}

	public final AppClassLoader getChild(String webApp) {
		return childMap.get(webApp);
	}

}
