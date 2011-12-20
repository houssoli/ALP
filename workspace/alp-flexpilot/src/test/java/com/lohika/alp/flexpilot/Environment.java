package com.lohika.alp.flexpilot;

public class Environment {

	private String	hubURL = "http://localhost:4444/wd/hub";
	private String	browser = "firefox";
	private String	platform = "WINDOWS";
	private String	EnvUrl = "http://lohika.github.com/ALP/test/fp/index.html";

	public String getEnvUrl() {
		return EnvUrl;
	}
	public void setEnvUrl(String envUrl) {
		EnvUrl = envUrl;
	}
	public String getHubURL() {
		return hubURL;
	}
	public void setHubURL(String hubURL) {
		this.hubURL = hubURL;
	}
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}

}
