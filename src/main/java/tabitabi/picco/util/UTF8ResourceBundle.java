package tabitabi.picco.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class UTF8ResourceBundle extends ResourceBundle implements Serializable {

	private static final long serialVersionUID = 2331608237039550253L;

	private static final String BUNDLE_EXTENSION = "properties";
	private static final Charset UTF_8 = Charset.forName("UTF-8");
	private static final Control UTF_8_CONTROL = new UTF8Control();

	public UTF8ResourceBundle(final String bundleName, final Locale locale) {
		setParent(ResourceBundle.getBundle(bundleName, locale, UTF_8_CONTROL));
	}

	@Override
	public Enumeration<String> getKeys() {
		return parent.getKeys();
	}

	
	public  Map<String, String> asMap() {
        Map<String, String> map = new HashMap<>();
 
        Enumeration<String> keys = getKeys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            map.put(key, getString(key) );            
        }        
        return map;
    }
	
	@Override
	protected Object handleGetObject(final String key) {
		return parent.getObject(key);
	}

	static class UTF8Control extends Control {
		@Override
		public ResourceBundle newBundle(final String baseName,
				final Locale locale, final String format,
				final ClassLoader loader, final boolean reload)
				throws IllegalAccessException, InstantiationException,
				IOException {

			String bundleName = toBundleName(baseName, locale);
			String resourceName = toResourceName(bundleName, BUNDLE_EXTENSION);
			ResourceBundle bundle = null;
			InputStream inStream = null;

			if (reload) {
				URL url = loader.getResource(resourceName);

				if (url != null) {
					URLConnection connection = url.openConnection();

					if (connection != null) {
						connection.setUseCaches(false);
						inStream = connection.getInputStream();
					}
				}

			} else {
				inStream = loader.getResourceAsStream(resourceName);
			}

			if (inStream != null) {
				try {
					bundle = new PropertyResourceBundle(new InputStreamReader(
							inStream, UTF_8));

				} finally {
					inStream.close();
				}
			}

			return bundle;
		}

	}

}
