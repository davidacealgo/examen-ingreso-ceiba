package dominio.unitaria;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import dominio.Producto;
import testdatabuilder.ProductoTestDataBuilder;

public class ProductoTest {

	private static final String CODIGO = "S01H1AT51";
	private static final String NOMBRE_PRODUCTO = "Impresora";
	private static final int PRECIO = 550000;
	private static final String CODIGO_1 = "AO93-RT77";
	private static final int PRECIO_1 = 2029000;
	private static final String CODIGO_CON_TRES_VOCALES = "FLI-RO111";

	@Test
	public void crearProductoTest() {
		
		// arrange
		ProductoTestDataBuilder productoTestDataBuilder = new ProductoTestDataBuilder().
				conNombre(NOMBRE_PRODUCTO).
				conCodigo(CODIGO).
				conPrecio(PRECIO);

		// act
		Producto producto = productoTestDataBuilder.build();

		// assert
		assertEquals(NOMBRE_PRODUCTO, producto.getNombre());
		assertEquals(CODIGO, producto.getCodigo());
		assertEquals(PRECIO, producto.getPrecio(),0);
	}

}
