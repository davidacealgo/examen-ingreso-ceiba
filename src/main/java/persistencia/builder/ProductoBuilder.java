package persistencia.builder;

import dominio.Producto;
import persistencia.entitad.ProductoEntity;

public class ProductoBuilder {
	
	private ProductoBuilder() {}
	
	/** Static method to change an entity table into a product object
	 * @param productoEntity Entity of the table product
	 * @return a product object
	 */
	public static Producto convertirADominio(ProductoEntity productoEntity) {
		
		Producto producto = null;
		
		if(productoEntity != null) {
			producto = new Producto(productoEntity.getCodigo(), productoEntity.getNombre(), productoEntity.getPrecio());
		}
		
		return producto;
	}
	
	/** Static method to change an object to an entity
	 * @param producto object product
	 * @return a product entity
	 */
	public static ProductoEntity convertirAEntity(Producto producto) {
		
		ProductoEntity productoEntity = new ProductoEntity();
		
		productoEntity.setCodigo(producto.getCodigo());
		productoEntity.setNombre(producto.getNombre());
		productoEntity.setPrecio(producto.getPrecio());
		
		return productoEntity;
	}
}
