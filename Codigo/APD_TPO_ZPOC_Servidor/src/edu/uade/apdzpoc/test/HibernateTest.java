package edu.uade.apdzpoc.test;


import edu.uade.apdzpoc.dao.ArticuloDAO;
import edu.uade.apdzpoc.dao.ClienteDAO;
import edu.uade.apdzpoc.dao.CuentaCorrienteDAO;
import edu.uade.apdzpoc.dao.PedidoWebDAO;
import edu.uade.apdzpoc.enums.EstadoItemPedido;
import edu.uade.apdzpoc.enums.EstadoPedido;
import edu.uade.apdzpoc.negocio.*;

import java.util.ArrayList;
import java.util.List;

public class HibernateTest {

    public static void main(String[] args) throws Exception {
        cuentasCorrientes();
        clientes();
        articulos();
        pedidoWeb();

        //Get Pedido Web
        PedidoWeb pw = PedidoWebDAO.getInstancia().findByCodigo(1);
        System.out.println(pw);
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
}

