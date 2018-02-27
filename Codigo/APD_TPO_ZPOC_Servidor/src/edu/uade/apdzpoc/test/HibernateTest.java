package edu.uade.apdzpoc.test;


import edu.uade.apdzpoc.dao.*;
import edu.uade.apdzpoc.enums.*;
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

        //Get Pedido Web
        PedidoWebDAO.getInstancia().findByCodigo(1);

        //Get Factura
        FacturaDAO.getInstancia().findByCodigo(1);

        //Get Remito Almacen
        RemitoAlmacenDAO.getInstancia().findrecuperadoByNro(1);

        //Get Remito Almacen
        RemitoTransporteDAO.getInstancia().findByCodigo(1);

        //Get Pago Cliente
        PagoClienteDAO.getInstancia().findrecuperadoByNro(1);
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

        Articulo a = ArticuloDAO.getInstancia().findrecuperadoByCodigo(1);
        Articulo b = ArticuloDAO.getInstancia().findrecuperadoByCodigo(2);

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
        Articulo a = ArticuloDAO.getInstancia().findrecuperadoByCodigo(1);

        Ubicacion u1 = UbicacionDAO.getInstancia().findrecuperadoByNro(1);
        Ubicacion u2 = UbicacionDAO.getInstancia().findrecuperadoByNro(2);

        new Lote(new Date(), a, Arrays.asList(u1, u2)).save();
    }

    private static void remitosAlmacen() throws Exception {
        Cliente c = ClienteDAO.getInstancia().findByCodigo(1);

        Ubicacion u = UbicacionDAO.getInstancia().findrecuperadoByNro(1);

        Articulo a = ArticuloDAO.getInstancia().findrecuperadoByCodigo(1);
        Articulo b = ArticuloDAO.getInstancia().findrecuperadoByCodigo(2);

        List<ItemRemitoAlmacen> items = new ArrayList<>();
        items.add(new ItemRemitoAlmacen(a, 20, u));
        items.add(new ItemRemitoAlmacen(b, 50, u));

        new RemitoAlmacen(EstadoRemito.Pendiente, items, TipoRemitoAlmacen.PedidoWeb, 15).save();

    }


    private static void remitosTransporte() throws Exception {
        PedidoWeb pw = PedidoWebDAO.getInstancia().findByCodigo(1);
        new RemitoTransporte("Transportes Fantasticos SA", pw).save();
    }
}

