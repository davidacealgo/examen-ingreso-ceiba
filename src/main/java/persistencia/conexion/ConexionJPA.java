package persistencia.conexion;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/* JPA connection to make the application persistent, remembering that we are using H2  how the relational database
 * and makes queries and manipulation so fast
 */
public class ConexionJPA {
	
	//Constant to connect with H2, has the same name in persistence.xml
	private static final String TIENDA = "tienda";
	private static EntityManagerFactory entityManagerFactory; //used to open the connection to the database

	//Connecting with the database
	public ConexionJPA() {
		entityManagerFactory = Persistence.createEntityManagerFactory(TIENDA);
	}
	
	/**Can create an entity to manage the database
	 * @return return
	 */
	public EntityManager createEntityManager() {
		return entityManagerFactory.createEntityManager();
	}
}
