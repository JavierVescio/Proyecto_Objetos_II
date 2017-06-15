package datos.sectorPersonal;

public class Login {
	private int idLogin;
	private String usuario;
	private String contrasenia;
	private boolean enLinea;
	
	public Login(){}

	public Login(String usuario, String contrasenia, boolean enLinea) {
		super();
		this.usuario = usuario;
		this.contrasenia = contrasenia;
		this.enLinea = enLinea;
	}

	public int getIdLogin() {
		return idLogin;
	}

	protected void setIdLogin(int idLogin) {
		this.idLogin = idLogin;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public boolean isEnLinea() {
		return enLinea;
	}

	public void setEnLinea(boolean enLinea) {
		this.enLinea = enLinea;
	}

	@Override
	public String toString() {
		return "Login [idLogin=" + idLogin + ", usuario=" + usuario
				+ ", contrasenia=" + contrasenia + ", enLinea=" + enLinea + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idLogin;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Login other = (Login) obj;
		if (idLogin != other.idLogin)
			return false;
		return true;
	}
}