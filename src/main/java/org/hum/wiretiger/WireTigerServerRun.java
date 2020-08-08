package org.hum.wiretiger;

import org.hum.wiretiger.config.WireTigerConfig;
import org.hum.wiretiger.core.server.ServerFactory;

public class WireTigerServerRun {

	public static void main(String[] args) {
		// config
		WireTigerConfig config = new WireTigerConfig();
		config.setPort(52007);
		config.setConsolePort(8080);
		config.setThreads(Runtime.getRuntime().availableProcessors());
		config.setDebug(false);
		// start
		ServerFactory.create(config).start();
	}
}
