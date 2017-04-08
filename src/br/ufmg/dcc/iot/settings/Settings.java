package br.ufmg.dcc.iot.settings;

public class Settings {

	private static final Settings instance = new Settings();
	
	private Settings() { }
	
	public static Settings getInstance() {
		return instance;
	}
}
