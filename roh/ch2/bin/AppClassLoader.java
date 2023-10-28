package food869.was.bin;

public class AppClassLoader extends CommonClassLoader {
	private CommonClassLoader parent;

	public AppClassLoader(ClassLoader parent) {
		super(parent);
		this.parent = (CommonClassLoader) parent;
	}

	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		if (this.getURLs() != parent.getURLs()) {
			this.addURL(parent.getURLs());
		}
		return super.loadClass(name);
	}

}
