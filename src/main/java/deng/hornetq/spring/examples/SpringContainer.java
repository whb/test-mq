package deng.hornetq.spring.examples;

import org.springframework.context.support.FileSystemXmlApplicationContext;

public class SpringContainer {
	public static void main(String[] args) {
		SpringContainer main = new SpringContainer();
		main.profileName = args[0];
		main.waitForShutdown = Boolean.parseBoolean(System.getProperty("waitForShutdown", "true"));
		main.run();
	}
	
	private String profileName;
	private boolean waitForShutdown;
	
	public void run() {
		final FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext(profileName);
		context.registerShutdownHook();
		context.start();
				
		if (waitForShutdown) {
			// Put this thread into wait state until shutdown hook is call (eg: CTRL+C).
			synchronized (this) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}
}
