package BaseDatos;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

public class TestBaseDatosEliminarCliente {

	@Test
	public void testEliminarCliente() {
		BaseDatos.initBD("Basedatos.db");
		try {
			int ContAntesdeEliminar = BaseDatos.ContarClientes();
			BaseDatos.eliminarCliente("12345678B");
			int ContDespuesdeELiminar = BaseDatos.ContarClientes();
			assertTrue(ContAntesdeEliminar == ContDespuesdeELiminar + 1);
			BaseDatos.closeBD();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
