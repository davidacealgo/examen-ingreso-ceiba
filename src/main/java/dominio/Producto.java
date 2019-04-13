package dominio;

public class Producto {
	
	//Variables that are necessary in the product
	
	private String codigo;
	private String nombre;
	private double precio;

	/**Builder product
	 * @param codigo Code of the product
	 * @param nombre Name of the product
	 * @param precio Price of the product
	 */
	public Producto(String codigo, String nombre, double precio) {

		this.codigo = codigo;
		this.nombre = nombre;
		this.precio = precio;
	}

	//return the code of the product
	public String getCodigo() {
		return codigo;
	}

	//return the name of the product
	public String getNombre() {
		return nombre;
	}

	//return the price of the product
	public double getPrecio() {
		return precio;
	}

}
