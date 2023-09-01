package br.com.alura.tienda.tests;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.tienda.dao.CategoriaDao;
import br.com.alura.tienda.dao.ProductoDao;
import br.com.alura.tienda.modelo.Categoria;
import br.com.alura.tienda.modelo.Producto;
import br.com.alura.tienda.utils.JPAUtils;

public class RegistroDeProducto {

	public static void main(String[] args) {
		registrarProducto();
		 EntityManager em = JPAUtils.getEntityManager();
		 ProductoDao productoDao = new ProductoDao(em);
		 Producto producto =  productoDao.consultaPorId(1l);
		 System.out.println(producto.getNombre());
		 
		 BigDecimal precio = productoDao.consultarPrecioPorNombreDeProducto("Xiaomi Redmi");
		 System.out.println(precio);
	}

	private static void registrarProducto() {
		Categoria celulares = new Categoria ("CELULARES");
		
		Producto celular =  new Producto ("Xiaomi Redmi", "Muy rata", new  BigDecimal("800"),celulares);
			
	    EntityManager em = JPAUtils.getEntityManager();
	    ProductoDao productoDao = new ProductoDao(em);
	    CategoriaDao categoriaDao = new CategoriaDao(em);
	    
	    em.getTransaction().begin();
	    
	    categoriaDao.guardar(celulares);
	    productoDao.guardar(celular);
	    
	    em.getTransaction().commit();
	    em.close();
	}

}
