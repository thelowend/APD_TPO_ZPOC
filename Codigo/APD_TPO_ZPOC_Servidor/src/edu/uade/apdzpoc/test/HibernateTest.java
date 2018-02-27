package edu.uade.apdzpoc.test;


import edu.uade.apdzpoc.dao.*;
import edu.uade.apdzpoc.enums.*;
import edu.uade.apdzpoc.excepciones.ArticuloException;
import edu.uade.apdzpoc.excepciones.ClienteException;
import edu.uade.apdzpoc.negocio.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class HibernateTest {

    public static void main(String[] args) throws Exception {
        cuentasCorrientes();
        clientes();
        articulos();
        pedidoWeb();
        facturas();
        proveedores();
        pagosCliente();
        ubicaciones();
        remitosAlmacen();
        remitosTransporte();
        lotes();
        ordenesCompra();

        movimientosAjuste();
        movimientosCompra();
        movimientosPedido();

        //Get Pedido Web
        PedidoWebDAO.getInstancia().findByCodigo(1);

        //Get Factura
        FacturaDAO.getInstancia().findByCodigo(1);

        //Get Remito Almacen
        RemitoAlmacenDAO.getInstancia().findByNro(1);

        //Get Remito Almacen
        RemitoTransporteDAO.getInstancia().findByCodigo(1);

        //Get Pago Cliente
        PagoClienteDAO.getInstancia().findByNro(1);

        //Get Movimientos
        MovimientoAjusteDAO.getInstancia().findByNro(1);

        MovimientoCompraDAO.getInstancia().findByNro(2);

        MovimientoPedidoDAO.getInstancia().findByNro(3);
    }

    private static void ordenesCompra() throws Exception {
        Proveedor p = ProveedorDAO.getInstancia().findByNro(1);
        Articulo a = ArticuloDAO.getInstancia().findByCodigo(1);
        PedidoWeb pw = PedidoWebDAO.getInstancia().findByCodigo(1);
        new OrdenCompra(p, a, pw).save();
    }

    private static void movimientosAjuste() throws Exception {
        Articulo a = ArticuloDAO.getInstancia().findByCodigo(1);
        Lote l = LoteDAO.getInstancia().findByNro(1);
        new MovimientoAjuste(new Date(), a, 70, CausaAjuste.Rotura, 12345, 54321, DestinoArticulos.Donacion, l).save();
    }

    private static void movimientosPedido() throws Exception {
        Articulo a = ArticuloDAO.getInstancia().findByCodigo(1);
        PedidoWeb pw = PedidoWebDAO.getInstancia().findByCodigo(1);
        new MovimientoPedido(new Date(), a, 99, pw).save();
    }

    private static void movimientosCompra() throws Exception {
        Articulo a = ArticuloDAO.getInstancia().findByCodigo(1);
        OrdenCompra oc = OrdenCompraDAO.getInstancia().findByCodigo(1);
        Lote l = LoteDAO.getInstancia().findByNro(1);
        new MovimientoCompra(new Date(), a, 10, oc, l).save();
    }

    private static void pagosCliente() throws Exception {
        Factura f = FacturaDAO.getInstancia().findByCodigo(1);
        new PagoCliente(MedioPago.Efectivo, new Date(), 100, f).save();
    }

    private static void proveedores() {
        new Proveedor("Proveedor Alfa").save();
        new Proveedor("Proveedor Beta").save();
    }

    private static void cuentasCorrientes() throws Exception {
        new CuentaCorriente(5000).save();
        new CuentaCorriente(10000).save();
        new CuentaCorriente(15000).save();
    }

    private static void clientes() throws Exception {
        CuentaCorriente cc = CuentaCorrienteDAO.getInstancia().findByCodigo(1);
        new Cliente("Cliente Ficticio 1 SA",
                32145687,
                "Esquina de Crotto 1, Buenos Aires, Argentina",
                true,
                true,
                10,
                cc).save();

        CuentaCorriente cc2 = CuentaCorrienteDAO.getInstancia().findByCodigo(2);
        new Cliente("Cliente Ficticio 2 SA",
                85274136,
                "Lima 200, CABA, Argentina",
                false,
                false,
                0,
                cc).save();
    }

    private static void articulos() throws Exception {
        new Articulo("Articulo A", "Descripcion del Articulo A", 100, 25, "Presentacion?", "S").save();
        new Articulo("Articulo B", "Descripcion del Articulo B", 200, 50, "Presentacion?", "M").save();
        new Articulo("Articulo C", "Descripcion del Articulo C", 300, 100, "Presentacion?", "XL").save();
        new Articulo("Articulo D", "Descripcion del Articulo D", 400, 200, "Presentacion?", "XL").save();
    }

    private static void pedidoWeb() throws Exception {
        Cliente c = ClienteDAO.getInstancia().findByCodigo(1);

        Articulo a = ArticuloDAO.getInstancia().findByCodigo(1);
        Articulo b = ArticuloDAO.getInstancia().findByCodigo(2);

        List<ItemPedido> items = new ArrayList<>();
        items.add(new ItemPedido(a, 20, EstadoItemPedido.Con_Stock));
        items.add(new ItemPedido(b, 30, EstadoItemPedido.Con_Stock));

        new PedidoWeb(
                c,
                EstadoPedido.Pendiente_Validacion,
                "Esquina de Crotto 1, Buenos Aires, Argentina",
                items
        ).save();
    }

    private static void facturas() throws Exception {
        PedidoWeb pw = PedidoWebDAO.getInstancia().findByCodigo(1);
        new Factura(pw).save();
    }

    private static void ubicaciones() throws Exception {
        new Ubicacion("Ubicacion 001", "Calle Falsa 123", 1, 1, 1, EstadoUbicacion.Libre, 100).save();
        new Ubicacion("Ubicacion 002", "Calle Falsa 123", 2, 2, 2, EstadoUbicacion.Libre, 200).save();
        new Ubicacion("Ubicacion 003", "Calle Falsa 123", 3, 3, 3, EstadoUbicacion.Libre, 300).save();
    }

    private static void lotes() throws Exception {
        Articulo a = ArticuloDAO.getInstancia().findByCodigo(1);

        Ubicacion u1 = UbicacionDAO.getInstancia().findByNro(1);
        Ubicacion u2 = UbicacionDAO.getInstancia().findByNro(2);

        new Lote(new Date(), a, Arrays.asList(u1, u2)).save();
    }

    private static void remitosAlmacen() throws Exception {
        Cliente c = ClienteDAO.getInstancia().findByCodigo(1);

        Ubicacion u = UbicacionDAO.getInstancia().findByNro(1);

        Articulo a = ArticuloDAO.getInstancia().findByCodigo(1);
        Articulo b = ArticuloDAO.getInstancia().findByCodigo(2);

        List<ItemRemitoAlmacen> items = new ArrayList<>();
        items.add(new ItemRemitoAlmacen(a, 20, u));
        items.add(new ItemRemitoAlmacen(b, 50, u));

        new RemitoAlmacen(EstadoRemito.Pendiente, items, TipoRemitoAlmacen.PedidoWeb, 15).save();

    }


    private static void remitosTransporte() throws Exception {
        PedidoWeb pw = PedidoWebDAO.getInstancia().findByCodigo(1);
        new RemitoTransporte("Transportes Fantasticos SA", pw).save();
    }
	
    public static Articulo recuperarArticulo(int CodigoBarra) throws ArticuloException {

		return ArticuloDAO.getInstancia().findByCodigo(CodigoBarra);
	}
    
    public static Cliente recuperarCliente(int idCliente) throws ClienteException {

		return ClienteDAO.getInstancia().findByCodigo(idCliente);
	}
    public static List <PedidoWeb> mostrarPedidosADespachar (){
    	List <PedidoWeb> lista= new ArrayList<>();
    	lista=PedidoWebDAO.getInstancia().findByEstado(EstadoPedido.Pendiente_Despacho);
    	
    	return lista;
    	
    	
    }
    
}

