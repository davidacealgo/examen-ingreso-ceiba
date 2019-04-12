package dominio.unitaria;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import dominio.Vendedor;
import dominio.Producto;
import dominio.repositorio.RepositorioProducto;
import dominio.repositorio.RepositorioGarantiaExtendida;
import testdatabuilder.ProductoTestDataBuilder;

public class VendedorTest {

	private static final String CLIENTE_PRUEBA = "DAVID";
	private static final String COMPUTADOR_LENOVO = "Computador Lenovo";
	private static final String CODIGO = "AO93-RT77";
	private static final int PRECIO = 2029000;
	private static final String CODIGO_CON_TRES_VOCALES = "FLI-RO111";
	//Test to check that the product already has warranty 
	@Test
	public void productoYaTieneGarantiaTest() {
		
		// arrange
		ProductoTestDataBuilder productoTestDataBuilder = new ProductoTestDataBuilder();
		
		Producto producto = productoTestDataBuilder.build(); 
		
		RepositorioGarantiaExtendida repositorioGarantia = mock(RepositorioGarantiaExtendida.class);
		RepositorioProducto repositorioProducto = mock(RepositorioProducto.class);
		
		when(repositorioGarantia.obtenerProductoConGarantiaPorCodigo(producto.getCodigo())).thenReturn(producto);
		
		Vendedor vendedor = new Vendedor(repositorioProducto, repositorioGarantia);
		
		// act 
		boolean existeProducto = vendedor.tieneGarantia(producto.getCodigo());
		
		//assert
		assertTrue(existeProducto);
	}
	
	//Test to check that the product don't has warranty
	@Test
	public void productoNoTieneGarantiaTest() {
		
		// arrange
		ProductoTestDataBuilder productoestDataBuilder = new ProductoTestDataBuilder();
		
		Producto producto = productoestDataBuilder.build(); 
		
		RepositorioGarantiaExtendida repositorioGarantia = mock(RepositorioGarantiaExtendida.class);
		RepositorioProducto repositorioProducto = mock(RepositorioProducto.class);
		
		when(repositorioGarantia.obtenerProductoConGarantiaPorCodigo(producto.getCodigo())).thenReturn(null);
		
		Vendedor vendedor = new Vendedor(repositorioProducto, repositorioGarantia);
		
		// act 
		boolean existeProducto =  vendedor.tieneGarantia(producto.getCodigo());
		
		//assert
		assertFalse(existeProducto);
	}
	
	//Test to check the counter of vowels
	@Test
	public void NumerarVocalesTest() {
		//Se supone que los codigos de los productos no tienen acentuación
		
		//arrange
		String prueba1 = "Murci1e3la4go0";
		String prueba2 = "azeyxwu";
		String prueba3 = "AbaEceIdiofoUgu";
		String prueba4 = "zXcVbNm";
		
		//act
		int cPrueba1 = Vendedor.NumerarVocales(prueba1);
		int cPrueba2 = Vendedor.NumerarVocales(prueba2);
		int cPrueba3 = Vendedor.NumerarVocales(prueba3);
		int cPrueba4 = Vendedor.NumerarVocales(prueba4);
		
		//assert
		assertEquals(cPrueba1,5);
		assertEquals(cPrueba2,3);
		assertEquals(cPrueba3, 10);
		assertEquals(cPrueba4,0);
		
	}
	
	@Test
	public void fechaExactaGarantiaTest() {
		
		//arrange
		Calendar calendario = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		calendario.set(2018, calendario.SEPTEMBER, 4);	//Cómo utilizar Calendar en java https://bit.ly/2UanmDe
		Date iFecha1 = calendario.getTime();
		calendario.set(2018, calendario.OCTOBER, 15);
		Date iFecha2 = calendario.getTime();
		calendario.set(2018, calendario.NOVEMBER, 16);
		Date iFecha3 = calendario.getTime();
		calendario.set(2019, calendario.APRIL, 12);
		Date iFecha4 = calendario.getTime();
		calendario.set(2018, calendario.AUGUST, 16);
		Date iFecha5 = calendario.getTime();
		
		//act
		Date fFecha1 = Vendedor.fechaExactaGarantia(iFecha1,100);
		Date fFecha2 = Vendedor.fechaExactaGarantia(iFecha2,100);
		Date fFecha3 = Vendedor.fechaExactaGarantia(iFecha3,200);
		Date fFecha4 = Vendedor.fechaExactaGarantia(iFecha4,9); //Testing that the warranty can't count the Monday
		Date fFecha5 = Vendedor.fechaExactaGarantia(iFecha4,8); //Testing that the warranty can't be a Sunday
		Date fFecha6 = Vendedor.fechaExactaGarantia(iFecha5,200);
		
		//assert
		assertEquals(sdf.format(fFecha1), "2018-12-29");
		assertEquals(sdf.format(fFecha2), "2019-02-09");
		assertEquals(sdf.format(fFecha3), "2019-07-08");
		assertEquals(sdf.format(fFecha4), "2019-04-22");
		assertEquals(sdf.format(fFecha5), "2019-04-22");
		assertEquals(sdf.format(fFecha6), "2019-04-06");
	}
	
	//Testing that an added product will exist with the method productoExiste 
	@Test
	public void productoExisteTest() {
		//arrange
		Producto producto = new ProductoTestDataBuilder().conNombre(COMPUTADOR_LENOVO).conNombre(CLIENTE_PRUEBA).conPrecio(PRECIO).conCodigo(CODIGO).build();
		RepositorioGarantiaExtendida repositorioGarantia = mock(RepositorioGarantiaExtendida.class);
		RepositorioProducto repositorioProducto = mock(RepositorioProducto.class); //how to use mock https://bit.ly/2X8wgmx
		//We can simulate the add of the product to make tests 
		when(repositorioProducto.obtenerPorCodigo(CODIGO)).thenReturn(producto);
		Vendedor vendedor = new Vendedor(repositorioProducto, repositorioGarantia);
		
		//act
		boolean productoExiste = vendedor.productoExiste(CODIGO);
		System.out.println(productoExiste);
		//assert
		assertTrue(productoExiste);
	}
}
