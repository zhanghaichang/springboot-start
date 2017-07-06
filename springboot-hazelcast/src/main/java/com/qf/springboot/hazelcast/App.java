package com.qf.springboot.hazelcast;

import java.util.concurrent.TimeUnit;

import com.qf.springboot.hazelcast.common.BigWideWorld;
import com.qf.springboot.hazelcast.common.MyApplication;

public class App {

	public static void main(String[] args) throws InterruptedException {
		BigWideWorld theWorld = new BigWideWorld();
		MyApplication application = new MyApplication();
		while (true) {
			String username = theWorld.nextUser();
			if (application.isLoggedOn(username)) {
				application.logout(username);
			} else {
				application.logon(username);
			}
			application.displayUsers();
			TimeUnit.SECONDS.sleep(2);
		}
	}

}
