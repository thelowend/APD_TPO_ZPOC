Create table Clientes
(	IdCliente				smallint not null identity,
	Nombre					Char(30),
	Documento				int,
	ResponsableInscripto	Tinyint,
	IVAInscripto			Tinyint,
	DomicilioFacturacion	Char(30),
	Descuento				float,

	Primary Key (IdCliente)
	)
GO


create table Facturas
(	NroFactura			smallint not null identity,
	IdCliente			smallint,
	FechaEmision		date,
	FechaVencimiento	date,
	TipoFactura			Char(1),
	TotalFactura		float,
	Constraint PKNro_Factura Primary Key (NroFactura),
	Constraint FKCliente2  Foreign Key (IdCliente) references Clientes (IdCliente))
GO


create table PagosCliente
(	IdComprobante		smallint not null identity,
	MedioPago			varchar(30) not null,
	FechaPago			date,
	Monto				float,
	NroFactura			smallint,
	Constraint PKPagoCliente Primary Key (IdComprobante),
	Constraint FKNroFactura Foreign Key (NroFactura) references Facturas(NroFactura))
GO

create table PedidosWEB
(	IdPedido			smallint not null identity,
	IdCliente			smallint not null,
	FechaGeneracion		date,
	FechaDespacho		date,
	FechaEntrega		date,
	EstadoPedido		varchar(50),
	DireccionEnvio		Char(100),
	Constraint PKId_Pedido  Primary Key (IdPedido),
	Constraint FKCliente Foreign Key (IdCliente) references Clientes(IdCliente)
	)

Create Table Articulos
(	
	CodigoBarra		int identity (10000,200) not null,
	NombreArticulo	Char(50),
	Descripcion		 Char (100),
	PrecioVenta		float,
	CantidadOC		int,
	Presentacion	Char(100),
	Tamano			Char(50),
	StockFisico		int,
	StockVirtual	int,
	StockDisponible int,
	StockPendienteEntrega int,
	Primary Key (CodigoBarra))
GO

create table Proveedores
(	IdProveedor smallint identity not null,
	Nombre Char(100),
	Primary Key (IdProveedor))

	GO

Create Table ArticulosProveedor
(	IdProveedor smallint not null,
	CodigoBarra int not null,
	PrecioCompra float,
	Primary Key (IdProveedor, CodigoBarra),
	Constraint  FKIdProveedor Foreign Key (IdProveedor) references Proveedores(IdProveedor),
	Constraint  FKCodigoBarra Foreign Key (CodigoBarra) references Articulos(CodigoBarra))
GO


Create Table ItemsPedido
(	IdItemPedido smallint identity,
	IdPedido smallint not null,
	CodigoBarra int not null,
	Cantidad smallint,
	Estado char(30),
	
	Primary Key (IdItemPedido),
	Constraint  FKCodigoBarra1 Foreign Key (CodigoBarra) references Articulos(CodigoBarra),
	Constraint  FKIdPedido Foreign Key (IdPedido ) references PedidosWEB(IdPedido))
GO

	
Create table Ubicaciones
(	
	IdUbicacion Char(7), 
	Calle		Char(1), 
	Bloque		smallint, 
	Estante		smallint, 
	Posicion	smallint,
	Capacidad	smallint,
	Estado		varchar(30),
	Primary Key (IdUbicacion)
)

GO

create table Lotes
(	NroLote Char(10) not null,
	Vencimiento date,
	CodigoBarra int not null,
	Primary Key (NroLote),
	Constraint  FKCodigoBarra4 Foreign Key (CodigoBarra) references Articulos(CodigoBarra))
GO

create table UbicacionesLote
(	IdUbicacion Char(7),
	NroLote Char(10) not null,
	Primary Key (NroLote,IdUbicacion),
	Constraint  FKIdUbicacion2 Foreign Key (IdUbicacion) references Ubicaciones(IdUbicacion),
	Constraint  FKLote  Foreign Key (NroLote) references Lotes(NroLote)
	)

GO
Create table RemitosAlmacen
(	IdRemitoAlmacen smallint identity not null,
	IdEstado		varchar(30),
	TipoRemito		varchar(30),
	Numero			int,
	Primary Key (IdRemitoAlmacen),

	)

GO

Create table ItemsRemitoAlmacen
(	IdItemRemitoAlamcen smallint identity,
	IdRemitoAlmacen smallint,
	CodigoBarra	int,
	Cantidad smallint,
	IdUbicacion Char(7),
	Primary Key (IdItemRemitoAlamcen),
	Constraint	FKIdRemitoAlmacen Foreign Key (IdRemitoAlmacen) references RemitosAlmacen (IdRemitoAlmacen),
	Constraint  FKCodigo_Barra3 Foreign Key (CodigoBarra ) references Articulos(CodigoBarra ),
	Constraint  FKIdUbicacion5 Foreign Key (IdUbicacion) references Ubicaciones(IdUbicacion))
GO



Create table RemitosTrasporte
(	IdRemitoT smallint identity not null,
	IdPedido smallint,
    Empresa	Char(100),
	Primary Key (IdRemitoT),
	Constraint  FKIdPedido5 Foreign Key (IdPedido ) references PedidosWEB(IdPedido ))

GO


Create table MovimientosStock
(	IdMStock smallint identity not null,
	Fecha date,
	CodigoBarra int,
	TipoMovimiento varchar(50),
	Cantidad smallint,
	Estado varchar(20),
	Destino varchar(30),
	SubTipo varchar (30),
    Operador Char(100),
    Autorizante Char(100),
	NroOC smallint,
    IdPedido smallint, 
	NroLote Char(10) not null,
	Primary Key (IdMStock),
	Constraint  FKCodigoBarra8 Foreign Key (CodigoBarra ) references Articulos(CodigoBarra ),
	Constraint  FKNroLote4 Foreign Key (NroLote ) references Lotes(NroLote ))

	Go

Create table OrdenesCompra
(	NroOC smallint identity not null,
	IdProveedor smallint,
	CodigoBarra int,
	EstadoOC varchar (30),
	IdPedido smallint,
	NroLote Char(10),
	Cantidad int,
	Fecha date,
	Primary Key (NroOC),
	Constraint  FKIdProveedor1 Foreign Key (IdProveedor ) references Proveedores(IdProveedor),
	Constraint  FKCodigoBarra7 Foreign Key (CodigoBarra ) references Articulos(CodigoBarra),
	Constraint  FKIdPedido Foreign Key (IdPedido ) references PedidosWEB(IdPedido)
	)
	
	GO

Create table CuentasCorriente
(	IdCtaCte smallint identity not null,
	IdCliente smallint,
	SaldoTotal float,
	LimiteMaximo float,
	Primary Key (IdCtaCte),
	Constraint  FKIdCliente Foreign Key (IdCliente ) references Clientes(IdCliente)
	)

		
create table ItemsFactura (
	IdItemFactura smallint not null identity,
	NroFactura smallint,
	CodigoBarra	   int, 
	Cantidad	   int,
	Precio		  float,
	Constraint  FKCodigoBarra Foreign Key (CodigoBarra) references Articulos(CodigoBarra),
	Constraint	FKFactura2   Foreign Key (NroFactura) references Facturas (NroFactura)
)