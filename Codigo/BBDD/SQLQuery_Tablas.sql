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
	Fecha_Generacion	date,
	Fecha_Despacho		date,
	Fecha_Entrega		date,
	EstadoPedido		varchar(50),
	DireccionEnvio		Char(100),
	Constraint PKId_Pedido  Primary Key (Id_Pedido),
	Constraint FKCliente Foreign Key (IdCliente) references Clientes(IdCliente)
	)

Create Table Articulo
(	
	Codigo_Barra int identity (10000,200) not null,
	Nombre_Articulo Char(50),
	Precio_Venta float,
	Cantidad_OC int,
	Presentacion Char(100),
	Tamano Char(50),
	Primary Key (Codigo_Barra))
GO

create table Proveedor
(	Id_Proveedor smallint identity not null,
	Nombre Char(100),
	Primary Key (Id_Proveedor))

	GO

Create Table Articulo_Proveedor
(	Id_Proveedor smallint not null,
	Codigo_Barra int not null,
	Precio_Compra float,
	Primary Key (Id_Proveedor, Codigo_Barra),
	Constraint  FKId_Proveedor Foreign Key (Id_Proveedor) references Proveedor(Id_Proveedor),
	Constraint  FKCodigo_Barra Foreign Key (Codigo_Barra) references Articulo(Codigo_Barra))
GO


Create Table Item_Pedido
(	Id_ItemPedido smallint identity,
	Id_Pedido smallint not null,
	Cantidad smallint,
	Estado char(30),
	Codigo_Barra int not null,
	Primary Key (Id_ItemPedido),
	Constraint  FKCodigo_Barra1 Foreign Key (Codigo_Barra ) references Articulo(Codigo_Barra),
	Constraint  FKId_Pedido Foreign Key (Id_Pedido ) references Pedido_WEB(Id_Pedido  ))
GO

	
Create table Ubicacion
(	
	Id_Ubicacion Char(7), 
	Calle Char(1), 
	Bloque	smallint, 
	Estante smallint, 
	Posicion smallint,
	Capacidad smallint,
	Estado varchar(30),
	Primary Key (Id_Ubicacion))

GO

create table Lote
(	Nro_Lote Char(10) not null,
	Vencimiento date,
	Codigo_Barra int not null,
	Primary Key (Nro_Lote),
	Constraint  FKCodigo_Barra4 Foreign Key (Codigo_Barra ) references Articulo(Codigo_Barra))
GO

create table Ubicacion_Lote
(	Nro_Lote Char(10) not null,
	Id_Ubicacion Char(7),
	Primary Key (Nro_Lote,Id_Ubicacion),
	Constraint  FKId_Ubicacion2 Foreign Key (Id_Ubicacion) references Ubicacion(Id_Ubicacion),
	Constraint  FKLote  Foreign Key (Nro_Lote) references Lote(Nro_Lote)
	)

GO
Create table Remito_Almacen
(	Id_RemitoAlmacen smallint identity not null,
	IdEstado	varchar(30),
	Tipo_Remito varchar(30),
	numero int,
	Primary Key (Id_RemitoAlmacen),

	)

GO

Create table Item_RemitoAlmacen
(	Id_ItemRemitoAlamcen smallint identity,
	Id_RemitoAlmacen smallint,
	Codigo_Barra	int,
	Cantidad smallint,
	Id_Ubicacion Char(7),
	Primary Key (Id_ItemRemitoAlamcen),
	Constraint	FKId_RemitoAlmacen Foreign Key (Id_RemitoAlmacen) references Remito_Almacen (Id_RemitoAlmacen),
	Constraint  FKCodigo_Barra3 Foreign Key (Codigo_Barra ) references Articulo(Codigo_Barra ),
	Constraint  FKId_Ubicacion5 Foreign Key (Id_Ubicacion) references Ubicacion(Id_Ubicacion))
GO



Create table Remito_Trasporte
(	Id_RemitoT smallint identity not null,
	Id_Pedido smallint,
    Empresa	Char(100),
	Primary Key (Id_RemitoT),
	Constraint  FKId_Pedido5 Foreign Key (Id_Pedido ) references Pedido_WEB(Id_Pedido ))

GO


Create table Movimiento_Stock
(	Id_MStock smallint identity not null,
	Fecha date,
	Codigo_Barra int,
	Tipo_Movimiento varchar(50),
	Cantidad smallint,
	Estado varchar(20),
	Destino varchar(30),
	SubTipo varchar (30),
    Operador Char(100),
    Autorizante Char(100),
	Nro_OC smallint,
    Id_Pedido smallint, 
	Nro_Lote Char(10) not null,
	Primary Key (Id_MStock),
	Constraint  FKCodigo_Barra8 Foreign Key (Codigo_Barra ) references Articulo(Codigo_Barra ),
	Constraint  FKNro_Lote4 Foreign Key (Nro_Lote ) references Lote(Nro_Lote ))

	Go

Create table Orden_Compra
(	Nro_OC smallint identity not null,
	Id_Proveedor smallint,
	Codigo_Barra int,
	EstadoOC varchar (30),
	Nro_Lote Char(10),
	Cantidad int,
	Fecha date,
	Primary Key (Nro_OC),
	Constraint  FKId_Proveedor1 Foreign Key (Id_Proveedor ) references Proveedor(Id_Proveedor),
	Constraint  FKCodigo_Barra7 Foreign Key (Codigo_Barra ) references Articulo(Codigo_Barra),
	
	)
	GO

Create table Cuenta_Corriente
(	IdCtaCte smallint identity not null,
	IdCliente smallint,
	Saldo_Total float,
	Limite_Maximo float,
	Primary Key (IdCtaCte),
	Constraint  FKIdCliente Foreign Key (IdCliente ) references Cliente(IdCliente)
	)

	select * from Proveedor