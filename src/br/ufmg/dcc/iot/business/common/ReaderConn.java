package br.ufmg.dcc.iot.business.common;

public class ReaderConn {

	private String connectionMethod;
	private String connectionAddress;
	private Integer connectionPort;
	private String username;
	private String password;
	private String description;
	
	public ReaderConn(String connectionMethod, String connectionAddress, Integer connectionPort, String username,
			String password, String description) {
		this.connectionMethod = connectionMethod;
		this.connectionAddress = connectionAddress;
		this.connectionPort = connectionPort;
		this.username = username;
		this.password = password;
		this.description = description;
	}
	
	public String getConnectionMethod() {
		return connectionMethod;
	}

	public String getConnectionAddress() {
		return connectionAddress;
	}

	public Integer getConnectionPort() {
		return connectionPort;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getDescription() {
		return description;
	}
	
	@Override
	public String toString() {
		return getDescription();
	}

	//--- Static creation
	public static ReaderConn createALR9900Conn() {
		return new ReaderConn("COM1", "150.164.10.42", 23, "alien", "password", 
				"Leitor Alien ALR9900 (alien9900.winet.dcc.ufmg.br:23)"); 
	}
	
	public static ReaderConn createALR9650Conn() {
		return new ReaderConn("COM1", "150.164.10.41", 23, "alien", "password", 
				"Leitor Alien ALR9650 (alien9650.winet.dcc.ufmg.br:23)"); 
	}
	
}
