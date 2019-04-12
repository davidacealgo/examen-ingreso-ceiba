package dominio.unitaria;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import dominio.Vendedor;
import dominio.Producto;
import dominio.repositorio.RepositorioProducto;
import dominio.repositorio.RepositorioGarantiaExtendida;
import testdatabuilder.ProductoTestDataBuilder;

public class VendedorTest {

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
		int cprueba1 = Vendedor.NumerarVocales(prueba1);
		int cprueba2 = Vendedor.NumerarVocales(prueba2);
		int cprueba3 = Vendedor.NumerarVocales(prueba3);
		int cprueba4 = Vendedor.NumerarVocales(prueba4);
		
		//assert
		assertEquals(cprueba1,5);
		assertEquals(cprueba2,3);
		assertEquals(cprueba3, 10);
		assertEquals(cprueba4,0);
		
	}
	
	@Test
	public void fechaExactaGarantiaTest() {
		
		//arrange
		
	}
}
