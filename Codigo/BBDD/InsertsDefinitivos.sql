/****** Object:  Table [dbo].[Articulos]    Script Date: 27/02/2018 11:54:59 a.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Articulos](
	[CodigoBarra] [int] IDENTITY(1,1) NOT NULL,
	[NombreArticulo] [varchar](255) NULL,
	[Descripcion] [varchar](255) NULL,
	[PrecioVenta] [float] NULL,
	[CantidadOC] [int] NULL,
	[Presentacion] [varchar](255) NULL,
	[Tamano] [varchar](255) NULL,
	[StockFisico] [int] NULL,
	[StockVirtual] [int] NULL,
	[StockDisponible] [int] NULL,
	[StockPendienteEntrega] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[CodigoBarra] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[ArticulosProveedor]    Script Date: 27/02/2018 11:54:59 a.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ArticulosProveedor](
	[PrecioCompra] [float] NULL,
	[IdProveedor] [int] NOT NULL,
	[CodigoBarra] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[CodigoBarra] ASC,
	[IdProveedor] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Clientes]    Script Date: 27/02/2018 11:54:59 a.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Clientes](
	[IdCliente] [int] IDENTITY(1,1) NOT NULL,
	[Nombre] [varchar](255) NULL,
	[Documento] [int] NULL,
	[DomicilioFacturacion] [varchar](255) NULL,
	[ResponsableInscripto] [tinyint] NULL,
	[IVAInscripto] [tinyint] NULL,
	[Descuento] [float] NULL,
PRIMARY KEY CLUSTERED 
(
	[IdCliente] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[CuentasCorriente]    Script Date: 27/02/2018 11:54:59 a.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CuentasCorriente](
	[IdCtaCte] [int] IDENTITY(1,1) NOT NULL,
	[SaldoTotal] [float] NULL,
	[LimiteMaximo] [float] NULL,
	[IdCliente] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[IdCtaCte] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Facturas]    Script Date: 27/02/2018 11:54:59 a.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Facturas](
	[NroFactura] [int] IDENTITY(1,1) NOT NULL,
	[FechaEmision] [datetime] NULL,
	[FechaVencimiento] [datetime] NULL,
	[TipoFactura] [varchar](255) NULL,
	[TotalFactura] [float] NULL,
	[IdCliente] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[NroFactura] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[ItemsFactura]    Script Date: 27/02/2018 11:54:59 a.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ItemsFactura](
	[IdItemFactura] [int] IDENTITY(1,1) NOT NULL,
	[Cantidad] [int] NULL,
	[Precio] [float] NULL,
	[CodigoBarra] [int] NULL,
	[NroFactura] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[IdItemFactura] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[ItemsPedido]    Script Date: 27/02/2018 11:54:59 a.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[ItemsPedido](
	[IdItemPedido] [int] IDENTITY(1,1) NOT NULL,
	[Cantidad] [int] NULL,
	[Estado] [varchar](255) NULL,
	[CodigoBarra] [int] NULL,
	[IdPedido] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[IdItemPedido] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[ItemsRemitoAlmacen]    Script Date: 27/02/2018 11:54:59 a.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ItemsRemitoAlmacen](
	[IdItemRemitoAlmacen] [int] IDENTITY(1,1) NOT NULL,
	[Cantidad] [int] NULL,
	[IdUbicacion] [int] NULL,
	[CodigoBarra] [int] NULL,
	[IdRemitoAlmacen] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[IdItemRemitoAlmacen] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Lotes]    Script Date: 27/02/2018 11:54:59 a.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Lotes](
	[NroLote] [int] IDENTITY(1,1) NOT NULL,
	[Vencimiento] [datetime] NULL,
	[CodigoBarraArticulo] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[NroLote] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[MovimientosStock]    Script Date: 27/02/2018 11:54:59 a.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[MovimientosStock](
	[TipoMovimiento] [varchar](31) NOT NULL,
	[IdMStock] [int] IDENTITY(1,1) NOT NULL,
	[Fecha] [datetime] NULL,
	[Cantidad] [int] NULL,
	[Operador] [int] NULL,
	[Autorizante] [int] NULL,
	[Destino] [varchar](255) NULL,
	[SubTipo] [varchar](255) NULL,
	[Nro_Lote] [int] NULL,
	[NroOC] [int] NULL,
	[IdPedido] [int] NULL,
	[CodigoBarra] [int] NULL,
	[NroLote] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[IdMStock] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[OrdenesCompra]    Script Date: 27/02/2018 11:54:59 a.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[OrdenesCompra](
	[NroOC] [int] IDENTITY(1,1) NOT NULL,
	[Cantidad] [int] NULL,
	[EstadoOC] [varchar](255) NULL,
	[Fecha] [datetime] NULL,
	[NroLote] [int] NULL,
	[IdProveedor] [int] NULL,
	[IdPedido] [int] NULL,
	[CodigoBarra] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[NroOC] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[PagosCliente]    Script Date: 27/02/2018 11:54:59 a.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[PagosCliente](
	[IdComprobante] [int] IDENTITY(1,1) NOT NULL,
	[MedioPago] [varchar](255) NULL,
	[FechaPago] [datetime] NULL,
	[Monto] [float] NULL,
	[NroFactura] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[IdComprobante] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[PedidosWEB]    Script Date: 27/02/2018 11:54:59 a.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[PedidosWEB](
	[IdPedido] [int] IDENTITY(1,1) NOT NULL,
	[FechaGeneracion] [datetime] NULL,
	[FechaDespacho] [datetime] NULL,
	[FechaEntrega] [datetime] NULL,
	[EstadoPedido] [varchar](255) NULL,
	[DireccionEnvio] [varchar](255) NULL,
	[IdCliente] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[IdPedido] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Proveedores]    Script Date: 27/02/2018 11:54:59 a.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Proveedores](
	[IdProveedor] [int] IDENTITY(1,1) NOT NULL,
	[Nombre] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[IdProveedor] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[RemitosAlmacen]    Script Date: 27/02/2018 11:54:59 a.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[RemitosAlmacen](
	[IdRemitoAlmacen] [int] IDENTITY(1,1) NOT NULL,
	[Estado] [varchar](255) NULL,
	[TipoRemito] [varchar](255) NULL,
	[Numero] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[IdRemitoAlmacen] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[RemitosTrasporte]    Script Date: 27/02/2018 11:54:59 a.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[RemitosTrasporte](
	[IdRemitoT] [int] IDENTITY(1,1) NOT NULL,
	[Empresa] [varchar](255) NULL,
	[IdPedido] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[IdRemitoT] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Ubicaciones]    Script Date: 27/02/2018 11:54:59 a.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Ubicaciones](
	[IdUbicacion] [int] IDENTITY(1,1) NOT NULL,
	[Nombre] [varchar](255) NULL,
	[Calle] [varchar](255) NULL,
	[Bloque] [int] NULL,
	[Estante] [int] NULL,
	[Posicion] [int] NULL,
	[Estado] [varchar](255) NULL,
	[Capacidad] [int] NULL,
	[CapacidadInicial] [int] NULL,
	[NroLote] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[IdUbicacion] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
ALTER TABLE [dbo].[ArticulosProveedor]  WITH CHECK ADD  CONSTRAINT [FK221D56E61C310E55] FOREIGN KEY([IdProveedor])
REFERENCES [dbo].[Proveedores] ([IdProveedor])
GO
ALTER TABLE [dbo].[ArticulosProveedor] CHECK CONSTRAINT [FK221D56E61C310E55]
GO
ALTER TABLE [dbo].[ArticulosProveedor]  WITH CHECK ADD  CONSTRAINT [FK221D56E6C3383736] FOREIGN KEY([CodigoBarra])
REFERENCES [dbo].[Articulos] ([CodigoBarra])
GO
ALTER TABLE [dbo].[ArticulosProveedor] CHECK CONSTRAINT [FK221D56E6C3383736]
GO
ALTER TABLE [dbo].[CuentasCorriente]  WITH CHECK ADD  CONSTRAINT [FK86F807CC4C078D0D] FOREIGN KEY([IdCliente])
REFERENCES [dbo].[Clientes] ([IdCliente])
GO
ALTER TABLE [dbo].[CuentasCorriente] CHECK CONSTRAINT [FK86F807CC4C078D0D]
GO
ALTER TABLE [dbo].[Facturas]  WITH CHECK ADD  CONSTRAINT [FK22580C1B4C078D0D] FOREIGN KEY([IdCliente])
REFERENCES [dbo].[Clientes] ([IdCliente])
GO
ALTER TABLE [dbo].[Facturas] CHECK CONSTRAINT [FK22580C1B4C078D0D]
GO
ALTER TABLE [dbo].[ItemsFactura]  WITH CHECK ADD  CONSTRAINT [FKF7B113B830A6E979] FOREIGN KEY([NroFactura])
REFERENCES [dbo].[Facturas] ([NroFactura])
GO
ALTER TABLE [dbo].[ItemsFactura] CHECK CONSTRAINT [FKF7B113B830A6E979]
GO
ALTER TABLE [dbo].[ItemsFactura]  WITH CHECK ADD  CONSTRAINT [FKF7B113B8C3383736] FOREIGN KEY([CodigoBarra])
REFERENCES [dbo].[Articulos] ([CodigoBarra])
GO
ALTER TABLE [dbo].[ItemsFactura] CHECK CONSTRAINT [FKF7B113B8C3383736]
GO
ALTER TABLE [dbo].[ItemsPedido]  WITH CHECK ADD  CONSTRAINT [FK320CC305BE1D1C83] FOREIGN KEY([IdPedido])
REFERENCES [dbo].[PedidosWEB] ([IdPedido])
GO
ALTER TABLE [dbo].[ItemsPedido] CHECK CONSTRAINT [FK320CC305BE1D1C83]
GO
ALTER TABLE [dbo].[ItemsPedido]  WITH CHECK ADD  CONSTRAINT [FK320CC305C3383736] FOREIGN KEY([CodigoBarra])
REFERENCES [dbo].[Articulos] ([CodigoBarra])
GO
ALTER TABLE [dbo].[ItemsPedido] CHECK CONSTRAINT [FK320CC305C3383736]
GO
ALTER TABLE [dbo].[ItemsRemitoAlmacen]  WITH CHECK ADD  CONSTRAINT [FKBF4D0C036814457F] FOREIGN KEY([IdRemitoAlmacen])
REFERENCES [dbo].[RemitosAlmacen] ([IdRemitoAlmacen])
GO
ALTER TABLE [dbo].[ItemsRemitoAlmacen] CHECK CONSTRAINT [FKBF4D0C036814457F]
GO
ALTER TABLE [dbo].[ItemsRemitoAlmacen]  WITH CHECK ADD  CONSTRAINT [FKBF4D0C03752C5D17] FOREIGN KEY([IdUbicacion])
REFERENCES [dbo].[Ubicaciones] ([IdUbicacion])
GO
ALTER TABLE [dbo].[ItemsRemitoAlmacen] CHECK CONSTRAINT [FKBF4D0C03752C5D17]
GO
ALTER TABLE [dbo].[ItemsRemitoAlmacen]  WITH CHECK ADD  CONSTRAINT [FKBF4D0C03C3383736] FOREIGN KEY([CodigoBarra])
REFERENCES [dbo].[Articulos] ([CodigoBarra])
GO
ALTER TABLE [dbo].[ItemsRemitoAlmacen] CHECK CONSTRAINT [FKBF4D0C03C3383736]
GO
ALTER TABLE [dbo].[Lotes]  WITH CHECK ADD  CONSTRAINT [FK4632F9FC6B29311] FOREIGN KEY([CodigoBarraArticulo])
REFERENCES [dbo].[Articulos] ([CodigoBarra])
GO
ALTER TABLE [dbo].[Lotes] CHECK CONSTRAINT [FK4632F9FC6B29311]
GO
ALTER TABLE [dbo].[MovimientosStock]  WITH CHECK ADD  CONSTRAINT [FKF8AED358240A5B3B] FOREIGN KEY([NroOC])
REFERENCES [dbo].[OrdenesCompra] ([NroOC])
GO
ALTER TABLE [dbo].[MovimientosStock] CHECK CONSTRAINT [FKF8AED358240A5B3B]
GO
ALTER TABLE [dbo].[MovimientosStock]  WITH CHECK ADD  CONSTRAINT [FKF8AED3583D3BE0A5] FOREIGN KEY([NroLote])
REFERENCES [dbo].[Lotes] ([NroLote])
GO
ALTER TABLE [dbo].[MovimientosStock] CHECK CONSTRAINT [FKF8AED3583D3BE0A5]
GO
ALTER TABLE [dbo].[MovimientosStock]  WITH CHECK ADD  CONSTRAINT [FKF8AED358AD96138E] FOREIGN KEY([Nro_Lote])
REFERENCES [dbo].[Lotes] ([NroLote])
GO
ALTER TABLE [dbo].[MovimientosStock] CHECK CONSTRAINT [FKF8AED358AD96138E]
GO
ALTER TABLE [dbo].[MovimientosStock]  WITH CHECK ADD  CONSTRAINT [FKF8AED358BE1D1C83] FOREIGN KEY([IdPedido])
REFERENCES [dbo].[PedidosWEB] ([IdPedido])
GO
ALTER TABLE [dbo].[MovimientosStock] CHECK CONSTRAINT [FKF8AED358BE1D1C83]
GO
ALTER TABLE [dbo].[MovimientosStock]  WITH CHECK ADD  CONSTRAINT [FKF8AED358C3383736] FOREIGN KEY([CodigoBarra])
REFERENCES [dbo].[Articulos] ([CodigoBarra])
GO
ALTER TABLE [dbo].[MovimientosStock] CHECK CONSTRAINT [FKF8AED358C3383736]
GO
ALTER TABLE [dbo].[OrdenesCompra]  WITH CHECK ADD  CONSTRAINT [FK7F2940F61C310E55] FOREIGN KEY([IdProveedor])
REFERENCES [dbo].[Proveedores] ([IdProveedor])
GO
ALTER TABLE [dbo].[OrdenesCompra] CHECK CONSTRAINT [FK7F2940F61C310E55]
GO
ALTER TABLE [dbo].[OrdenesCompra]  WITH CHECK ADD  CONSTRAINT [FK7F2940F63D3BE0A5] FOREIGN KEY([NroLote])
REFERENCES [dbo].[Lotes] ([NroLote])
GO
ALTER TABLE [dbo].[OrdenesCompra] CHECK CONSTRAINT [FK7F2940F63D3BE0A5]
GO
ALTER TABLE [dbo].[OrdenesCompra]  WITH CHECK ADD  CONSTRAINT [FK7F2940F6BE1D1C83] FOREIGN KEY([IdPedido])
REFERENCES [dbo].[PedidosWEB] ([IdPedido])
GO
ALTER TABLE [dbo].[OrdenesCompra] CHECK CONSTRAINT [FK7F2940F6BE1D1C83]
GO
ALTER TABLE [dbo].[OrdenesCompra]  WITH CHECK ADD  CONSTRAINT [FK7F2940F6C3383736] FOREIGN KEY([CodigoBarra])
REFERENCES [dbo].[Articulos] ([CodigoBarra])
GO
ALTER TABLE [dbo].[OrdenesCompra] CHECK CONSTRAINT [FK7F2940F6C3383736]
GO
ALTER TABLE [dbo].[PagosCliente]  WITH CHECK ADD  CONSTRAINT [FKA60F53C030A6E979] FOREIGN KEY([NroFactura])
REFERENCES [dbo].[Facturas] ([NroFactura])
GO
ALTER TABLE [dbo].[PagosCliente] CHECK CONSTRAINT [FKA60F53C030A6E979]
GO
ALTER TABLE [dbo].[PedidosWEB]  WITH CHECK ADD  CONSTRAINT [FKCD4D5EC64C078D0D] FOREIGN KEY([IdCliente])
REFERENCES [dbo].[Clientes] ([IdCliente])
GO
ALTER TABLE [dbo].[PedidosWEB] CHECK CONSTRAINT [FKCD4D5EC64C078D0D]
GO
ALTER TABLE [dbo].[RemitosTrasporte]  WITH CHECK ADD  CONSTRAINT [FK3AAF650BBE1D1C83] FOREIGN KEY([IdPedido])
REFERENCES [dbo].[PedidosWEB] ([IdPedido])
GO
ALTER TABLE [dbo].[RemitosTrasporte] CHECK CONSTRAINT [FK3AAF650BBE1D1C83]
GO
ALTER TABLE [dbo].[Ubicaciones]  WITH CHECK ADD  CONSTRAINT [FKAA5A856D3D3BE0A5] FOREIGN KEY([NroLote])
REFERENCES [dbo].[Lotes] ([NroLote])
GO
ALTER TABLE [dbo].[Ubicaciones] CHECK CONSTRAINT [FKAA5A856D3D3BE0A5]
GO
USE [master]
GO
ALTER DATABASE [ADVerano_08] SET  READ_WRITE 
GO



insert into Clientes values('Diego Maximiliano Pablos',22333456,'Rivadavia 7376',0,0,10)
insert into Clientes values('Maria De Los Angeles Ojeda',33444555, 'Boedo 150',1,1,20)
insert into Clientes values('Cristian Alberto Cavallaro',44555666,'Puan 566',0,1,10)
insert into Clientes values('Barbara Daniela Zapatero',55666777,'Directorio 787',1,0,5.7)

insert into Facturas values ('20180220','20180324','A', 700, 1)
insert into Facturas values ('20180221','20180324','B',800, 1)
insert into Facturas values ('20180221','20180324','A',900,2)
insert into Facturas values ('20180222','20180324','B',1000,2)
insert into Facturas values ('20180222','20180324','A', 1100,3)
insert into Facturas values ('20180222','20180324','B', 1200,4)


insert into PagosCliente values('TC','20180221',700,6)
insert into PagosCliente values('TC','20180221',700,1)
insert into PagosCliente values('CP','20180222',800,2)
insert into PagosCliente values('TC','20180222',900,3)
insert into PagosCliente values('Efectivo','20180222',700,1)
insert into PagosCliente values('TarjetaCrédito','20180222',800,2)
insert into PagosCliente values('TarjetaCrédito','20180222',900,3)


insert into PedidosWEB values('20180218','20180220','20180221','Completo','Rivadavia 7376',1)
insert into PedidosWEB values('20180218','20180220','20180221','Completo','Boedo 150',2)
insert into PedidosWEB values('20180218','20180220','20180221','Completo', 'Puan 566',2)
insert into PedidosWEB values('20180219','20180222','20180222','Pendiente de Despacho', 'Directorio 787',2)
insert into PedidosWEB values('20180219','20180222','20180222','Pendiente de Despacho','Rivadavia 7376',4)
insert into PedidosWEB values('20180220','20180222','20180222','Pendiente de Despacho','Boedo 150',4)

insert into Articulos values ('Rutini','Caja',300,100,'6 unidades','',0,0,0,0)
insert into Articulos values ('Pepsi','Latas',275,200,'24 unidades','',0,0,0,0)
insert into Articulos values ('Coca-Cola','Latas',350,200,'24 unidades','',0,0,0,0)
insert into Articulos values ('Mirinda','Botellas',175.00,300,'6 unidades','',0,0,0,0)
insert into Articulos values ('7up','Botellas',175.00,100,'6 unidades','',0,0,0,0)


insert into ItemsPedido values (20, 'Con_Stock',1,1)
insert into ItemsPedido values (24, 'Con_Stock',2,1)
insert into ItemsPedido values (16, 'Con_Stock',1,3)
insert into ItemsPedido values (19, 'Con_Stock',3,3)
insert into ItemsPedido values (15, 'Con_Stock',4,3)



insert into Proveedores values ('Viña Uzal')
insert into Proveedores values ('Pepsico')
insert into Proveedores values ('Sierra')
insert into Proveedores values ('Grupo Infra')



insert into ArticulosProveedor values (150.00,1,1)
insert into ArticulosProveedor values (180,2,2)
insert into ArticulosProveedor values (150,2,1)
insert into ArticulosProveedor values (10400,3,1)
insert into ArticulosProveedor values (10600,4,3)
insert into ArticulosProveedor values (10600,4,2)


insert into Ubicaciones  values ( 'A010101','A',01,01,01,'Completa',0,100,1)
insert into Ubicaciones  values ( 'A010102','A',01, 01, 02,'Con_disponibilidad',50,100,1)
insert into Ubicaciones  values ( 'A010107','A',01, 01, 07,'Libre', 100,100,null)
insert into Ubicaciones  values ( 'A010108','A',01, 01, 08,'Libre', 100,100,null)
insert into Ubicaciones  values ( 'A010109','A',01, 01, 09,'Libre', 100,100,null)


insert into Lotes values ('20180825',1)
insert into Lotes values ('20180305',1)
insert into Lotes values ('20180326',1)
insert into Lotes values ('20180328',2)
insert into Lotes values ('20180825',2)
insert into Lotes values ('20180303',3)
insert into Lotes values ('20180326',3)
insert into Lotes values ('20181212',3)
insert into Lotes values ('20181125',3)
insert into Lotes values ('20180622',4)

insert into RemitosAlmacen values ('Procesado','PedidoWeb',1)
insert into RemitosAlmacen values ('Procesado','Compra',1)
insert into RemitosAlmacen values ('Procesado','PedidoWeb',2)
insert into RemitosAlmacen values ('Procesado','Compra',2)
insert into RemitosAlmacen values ('Procesado','PedidoWeb',3)
insert into RemitosAlmacen values ('Procesado','PedidoWeb',4)
insert into RemitosAlmacen values ('Procesado','PedidoWeb',5)
insert into RemitosAlmacen values ('Procesado','PedidoWeb',6)


insert into ItemsRemitoAlmacen values (10000,2,1,1)
insert into ItemsRemitoAlmacen values (10200,2,1,1)


insert into RemitosTrasporte values ('FedEx',1)
insert into RemitosTrasporte values ('FedEx',5)


insert into OrdenesCompra values (10200,'Aceptada','20180220',1,1,1,1)
insert into OrdenesCompra values (10400,'Aceptada','20180220',1,2,1,1)







