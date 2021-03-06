package controladores.sectorProducto;

import java.io.IOException;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.sectorProducto.ProductoABM;
import negocio.sectorProducto.PrecioProductoABM;
import negocio.sectorPersonal.TipoClienteABM;


public class ControladorPlato extends HttpServlet {

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
			ProductoABM abmProducto = new ProductoABM();
			
			String nombreProducto = request.getParameter("nombreProducto");
			String descripcionProducto = request.getParameter("descripcionProducto");
			String notasPlato = request.getParameter("notasPlato");
			String strPrecio = request.getParameter("precio");
			
			
			//Solo el nombre es obligatorio. Si entro a la funcion agregarPlato del ABM se ve claramente que es asi.
			if (nombreProducto.isEmpty() || strPrecio.isEmpty()){
				request.setAttribute("msgError", "El nombre del producto y el precio no pueden quedar en blanco.");
				request.getRequestDispatcher("/productos/cargarplato.jsp").forward(request, response);
			}
			else {
				int idProducto = abmProducto.agregarPlato(nombreProducto, descripcionProducto, "", notasPlato);
				
				double precio = 0;
				try{
					precio = Double.valueOf(strPrecio);
					
				}catch (NumberFormatException n){
					abmProducto.eliminar(idProducto);
					request.setAttribute("msgError", "El precio ingresado es incorrecto");
					request.getRequestDispatcher("/productos/cargarplato.jsp").forward(request, response);
				}
				
				PrecioProductoABM abmPrecioProducto = new PrecioProductoABM();
				TipoClienteABM abmTipoCliente = new TipoClienteABM();
				
				abmPrecioProducto.agregarPrecioProducto(precio, new GregorianCalendar(), abmProducto.traerProductoPorId(idProducto), abmTipoCliente.traerTipoClientePorId(1));
				
				request.setAttribute("msgTodoBien", "Creacion exitosa de plato con nombre " + nombreProducto);
				request.getRequestDispatcher("/productos/cargarplato.jsp").forward(request, response);
			}
		} catch (Exception e) {
			response.sendError(500, "Error Intente de nuevo");
		}
	}

}
