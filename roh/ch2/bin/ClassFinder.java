package food869.was.bin;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;

public class ClassFinder {

	HashSet<URL> findClassInRootLib() {
		String libPath = "../lib/";
		try {
			// add class files in directory
			HashSet<URL> urls = new HashSet<>();
			File libClassPath = new File(libPath);
			urls.add(new URL("file:" + libClassPath.getCanonicalPath() + File.separator));

			// add jar files
			File[] files = libClassPath.listFiles();
			for (File file : files) {
				if (file.isFile() && file.getName().endsWith(".jar")) {
					urls.add(new URL("file:" + file.getCanonicalPath()));
				}
			}
			return urls;
		} catch (IOException e) {
			return null;
		}
	}

	HashSet<File> getWebApps() {
		File rootDir = new File("../webapps/");
		HashSet<File> webApps = new HashSet<File>(Arrays.asList(rootDir.listFiles()));
		return webApps;
	}

}
