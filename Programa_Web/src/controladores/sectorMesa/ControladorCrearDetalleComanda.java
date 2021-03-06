package controladores.sectorMesa;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.sectorMesa.ComandaABM;
import negocio.sectorMesa.DetalleComandaABM;
import negocio.sectorMesa.MesaABM;
import negocio.sectorPersonal.PersonaABM;
import negocio.sectorProducto.ProductoABM;
import datos.sectorMesa.Comanda;
import datos.sectorMesa.Mesa;
import datos.sectorMesa.OcupacionMesa;
import datos.sectorPersonal.Cliente;
import datos.sectorPersonal.Personal;
import datos.sectorProducto.Producto;



public class ControladorCrearDetalleComanda extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		procesarPeticion(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		procesarPeticion(request, response);
	}

	private void procesarPeticion(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try {
			ComandaABM abmComanda = new ComandaABM();
			DetalleComandaABM abmDetalleComanda = new DetalleComandaABM();
			ProductoABM abmProducto = new ProductoABM();

			int idcomanda = Integer.valueOf(request.getParameter("idcomanda").toString());
			int idproducto = Integer.valueOf(request.getParameter("idproducto").toString());
			int cantidad = 0;
			
			String strCantidad = request.getParameter("cantidad").toString();
			if (strCantidad.isEmpty() == false)
				cantidad = Integer.valueOf(strCantidad);
			
			System.out.println("CREAR DETALLE COMANDA");
			System.out.println("idcomanda: " + idcomanda);
			System.out.println("idproducto: " + idproducto);
			System.out.println("strCantidad: " + strCantidad);
			
			if (idcomanda == -1 || idproducto == -1 || strCantidad.isEmpty() || cantidad < 1){
				request.setAttribute("msgError", "Debe seleccionar una comanda y un producto, e indicar la cantidad que desea.");
				request.getRequestDispatcher("/mesas/creardetallecomanda.jsp").forward(request, response);
			}
			else {
				Comanda comanda = abmComanda.traerComandaYDetalleComandasPorId(idcomanda);
				Producto producto = abmProducto.traerProductoPorId(idproducto);
				int idDetalleComanda = abmDetalleComanda.agregarDetalleComanda(comanda, producto, cantidad);

				request.setAttribute("msgTodoBien", "Creacion exitosa de detalle comanda con producto: " + producto.getNombre());
				request.getRequestDispatcher("/mesas/creardetallecomanda.jsp").forward(request, response);
			}
			
		} catch (Exception e) {
			response.sendError(500, "ControladorCrearDetalleComanda: "+e.getMessage());
		}
	}

}
