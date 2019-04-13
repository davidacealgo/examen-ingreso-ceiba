package dominio;

import dominio.repositorio.RepositorioProducto;
import dominio.repositorio.RepositorioGarantiaExtendida;
import dominio.excepcion.GarantiaExtendidaException;
import java.util.Calendar;
import java.util.Date;

public class Vendedor {

    public static final String EL_PRODUCTO_TIENE_GARANTIA = "El producto ya cuenta con una garantia extendida";
    public static final String PRODUCTO_NO_EXISTE = "Este producto no se encuentra en nuestra base de datos";
    public static final String PRODUCTO_SIN_GARANTIA = "Este producto no cuenta con garantia extendida"; 
   
    //Variables to access on the database
    private RepositorioProducto repositorioProducto;
    private RepositorioGarantiaExtendida repositorioGarantia;
    /**Constructor builder class
     * @param repositorioProducto Is used to manage the product table
     * @param repositorioGarantia Id used to manage the extended warranty table
     */
    public Vendedor(RepositorioProducto repositorioProducto, RepositorioGarantiaExtendida repositorioGarantia) {
        this.repositorioProducto = repositorioProducto;
        this.repositorioGarantia = repositorioGarantia;

    }
    /**
     * @param codigo Is used to create the extended warranty of the product
     * @param cliente Client name used to involve the extended warranty
     * @throws GarantiaExtendidaException Manage the exceptions when creates the extended warranty
     */
    public void generarGarantia(String codigo, String cliente) throws GarantiaExtendidaException {
    	
    	if(!productoExiste(codigo))
			throw new GarantiaExtendidaException(PRODUCTO_NO_EXISTE);
		if(NumerarVocales(codigo)>=3)
			throw new GarantiaExtendidaException(PRODUCTO_SIN_GARANTIA);
		if(tieneGarantia(codigo)==true)
			throw new GarantiaExtendidaException(EL_PRODUCTO_TIENE_GARANTIA);
		
		//Already exists the product, can be store in the variable product
		Producto producto = repositorioProducto.obtenerPorCodigo(codigo);
		//variables used to the Date of warranty
		int diasGarantia;
		double precioProducto = producto.getPrecio();
		double precioGarantia;

		//Calculating the price and date of extended warranty
		if(precioProducto > 500000) {
			precioGarantia = precioProducto*0.2;
			diasGarantia = 200;
			
		}else {
			precioGarantia = precioProducto*0.1;
			diasGarantia = 100;
		}
		//Starting date of warranty and his method to calculate the end
		Date inicioGarantia = Calendar.getInstance().getTime();
		Date finGarantia = fechaExactaGarantia(inicioGarantia, diasGarantia);
		
		//Creating object of extended warranty
		GarantiaExtendida nuevaGarantia = new GarantiaExtendida(producto, inicioGarantia, finGarantia, precioGarantia, cliente);
		//Adding the object to our Database
		repositorioGarantia.agregar(nuevaGarantia);
    }
    
    /**
     * Check if the code of the product has at least three vocals
     * @param codigo 
     * @return the number of vocals in the code
     */
    public static int NumerarVocales(String codigo){
    	int vocales=0,i=0;
    	char posicion = '\u0000';
    	while(i < codigo.length()){
    		posicion = codigo.toLowerCase().charAt(i);
    		switch(posicion){
    		case 'a':
    			vocales++;
    			i++;
    			break;
    		case 'e':
    			vocales ++;
    			i++;
    			break;
    		case 'i':
    			vocales++;
    			i++;
    			break;
    		case 'o':
    			vocales++;
    			i++;
    			break;
    		case 'u':
    			vocales++;
    			i++;
    			break;
    		default:
    			i++;
    			// no es una vocal
    		}
    	}
    	return vocales;
    }
    
    /**
     * Estimate the exact date of the warranty
     * @param fechaInicial Starting date of the warranty
     * @param dias Number of days, can be 200 or 100
     * @return the final date of the warranty 
     */
    public static Date fechaExactaGarantia(Date fechaInicial, int dias) {
    	int i=0;
    	Calendar calendario = Calendar.getInstance();
    	calendario.setTime(fechaInicial);
    	
    	while(i<dias) {
    		if(calendario.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY)
    			i++;
    		calendario.add(Calendar.DAY_OF_MONTH,1);
    	}
    	
    	if(calendario.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
    		calendario.add(Calendar.DAY_OF_MONTH,1);
    	
    	return calendario.getTime();
    }

    /** Check if the product exists in the Database
     * @param codigo Is used to search the product
     * @return Is True if the product exists otherwise shall be False
     */
    public boolean productoExiste(String codigo) {
    	if(repositorioProducto.obtenerPorCodigo(codigo) == null) {
    		return false;
    	}
        return true;
    }
    
    /** Check if the Product has previous warranties in the Database
     * @param codigo Is used to search the product
     * @return True if the product & the warranty exists in the Database otherwise shall be F
     */
    public boolean tieneGarantia(String codigo) {
    	if(repositorioGarantia.obtenerProductoConGarantiaPorCodigo(codigo) == null) {
    		return false;
    	}
        return true;
    }
}
