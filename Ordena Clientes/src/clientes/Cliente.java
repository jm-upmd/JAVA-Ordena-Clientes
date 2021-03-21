package clientes;


class Cliente {
	
	private String idCliente;
	private String nombreCompañia;
	private String nombreContacto;
	private int antiguedad;
	private float facturacion;
	private String cargoContacto;
	private String direccion;
	private String ciudad;
	private String region;
	private String codPostal;
	private String pais;
	private String telefono;
	private String fax;
	
	private static final String S_IZQ = "    "; // Sangría izquierda
	
	
	//	Constructor
	
	public Cliente(String idCliente, String nombreCompañia, String nombreContacto, int antiguedad, float facturacion, String cargoContacto, String direccion,
			String ciudad, String region, String codPostal, String pais, String lelefono, String fax) {
		this.idCliente = idCliente;
		this.nombreCompañia = nombreCompañia;
		this.nombreContacto = nombreContacto;
		this.antiguedad = antiguedad;
		this.facturacion = facturacion;
		this.cargoContacto = cargoContacto;
		this.direccion = direccion;
		this.ciudad = ciudad;
		this.region = region;
		this.codPostal = codPostal;
		this.pais = pais;
		this.telefono = lelefono;
		this.fax = fax;
	}

	
	/*
	 * devuelve true si la empresa cliente está ubicada en España; false en otro caso
	 */
	boolean esEspañol() {
		return pais.toUpperCase().compareTo("ESPAÑA") == 0;
	}
	
	// Sobreescribo metod toString para que me debuelva un String con el formato 
	// original de campos separados por punto y coma, tal y como estaba en el 
	// fichero de texto.
	
	public String objetoAString() {
		final char sep = ';'; // Caracter separdor de campo
		// Creo un StringBuilder para componer el registro con los datos
		// del objeto.
		// En estos casos procurar no uar String porque tal y como os comenté
		// en clase las concatenaciones con String son muy costosas...

		StringBuilder registro = new StringBuilder();

		// Fijaos que:
		// - Método append está sobrecargado: admite String y char
		// - Metodo append devuelve una referencia a su propio objeto registro,
		// por lo que podemos concatenar tantos .append como queramos sin necesidad
		// de escribir una instrucción por cada append que hagamos (comodidad).

		registro.append(idCliente).append(sep);
		registro.append(nombreContacto).append(sep);
		registro.append(nombreCompañia).append(sep);
		registro.append(cargoContacto).append(sep);
		registro.append(direccion).append(sep);
		registro.append(ciudad).append(sep);
		registro.append(region).append(sep);
		registro.append(codPostal).append(sep);
		registro.append(pais).append(sep);
		registro.append(telefono).append(sep);
		registro.append(fax);

		// Devolvemos un String con el contenido del StringBuilder
		// Ojo: Antes de usar un método toString de un objeto mirár en la documentación
		// que es lo que hace. Como ya sabéis es un método heredado de Object y cada clase 
		// lo reescribe de una forma diferente, o no lo reescribe. En el caso de
		// StringBuilder está reescrito pra que devuelva un String con su contenido tal cual.
		
		return registro.toString();
		
	}
	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		sb.append(S_IZQ).append("Id. Cliente: ").append(idCliente).append("\n");
		sb.append(S_IZQ).append("Nombre Contacto: ").append(nombreContacto).append("\n");
		sb.append(S_IZQ).append("Antigüedad: ").append(antiguedad).append("\n");
		sb.append(S_IZQ).append("Facturación: ").append(facturacion).append("\n");
		sb.append(S_IZQ).append("Nombre Compañía: ").append(nombreCompañia).append("\n");
		sb.append(S_IZQ).append("Nombre Ciudad: ").append(ciudad).append("\n\n");
		
		return sb.toString();
		
	}
	
	public String getIdCliente() {
		return idCliente;
	}
	
	
	
	public void setIdCliente(String  idCliente) {
		this.idCliente = idCliente;
	}
	
	public String getNombreCompañia() {
		return nombreCompañia;
	}
	
	public void setNombreCompañia(String nombreCompañia) {
		this.nombreCompañia = nombreCompañia;
	}
	
	public String getNombreContacto() {
		return nombreContacto;
	}
	
	public void setNombreContacto(String nombreContacto) {
		this.nombreContacto = nombreContacto;
	}
	
	public String getCargoContacto() {
		return cargoContacto;
	}
	
	public void setCargoContacto(String cargoContacto) {
		this.cargoContacto = cargoContacto;
	}
	
	public String getDireccion() {
		return direccion;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public String getCiudad() {
		return ciudad;
	}
	
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	
	public String getCodPostal() {
		return codPostal;
	}
	
	public void setCodPostal(String codPostal) {
		this.codPostal = codPostal;
	}
	public String getLelefono() {
		return telefono;
	}
	
	public void setLelefono(String lelefono) {
		this.telefono = lelefono;
	}
	
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public int getAntiguedad() {
		return antiguedad;
	}

	public void setAntiguedad(int antiguedad) {
		this.antiguedad = antiguedad;
	}

	public float getFacturacion() {
		return facturacion;
	}

	public void setFacturacion(float facturacion) {
		this.facturacion = facturacion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
}
