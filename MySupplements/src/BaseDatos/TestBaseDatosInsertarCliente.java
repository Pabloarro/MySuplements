package BaseDatos;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

public class TestBaseDatosInsertarCliente {

	@Test
	public void testInsertarCliente() {
		BaseDatos.initBD("Basedatos.db");
		try {
			int ContAntes= BaseDatos.ContarClientes();
			BaseDatos.insertarCliente("ClientePrueba", "ContraseniaPrueba", "12345678B", System.currentTimeMillis());
			int ContDespues=BaseDatos.ContarClientes();
			assertTrue(ContAntes == ContDespues - 1);	
			BaseDatos.closeBD();
			} catch (SQLException e) {
			e.printStackTrace();
		}
		}
}
