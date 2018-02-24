Create table Cliente
(	IdCliente				smallint not null identity,
	Nombre					Char(30),
	Domicilio_Facturacion	Char(30),
	Responsable_Inscripto	Tinyint,
	Descuento				float,
	IVA_Inscripto			Tinyint,
	Primary Key (IdCliente)
	)

GO

create table Factura
(	Nro_Factura			smallint not null identity,
	Fecha_Emision		date,
	Fecha_Vencimiento	date,
	Tipo_Factura		Char(1),
	Estado				Char(30),
	Total				float,
	IdCliente			smallint,
	Constraint PKNro_Factura Primary Key (Nro_Factura),
	Constraint FKCliente2  Foreign Key (IdCliente) references Cliente (IdCliente))
GO

create table Pago_Cliente
(	IdComprobante		smallint not null identity,
	Medio_Pago			varchar(30) not null,
	Fecha_Pago			date,
	Monto				float,
	Nro_Factura			smallint,
	Constraint PKPago_Cliente Primary Key (IdComprobante),
	Constraint FKNro_Factura Foreign Key (Nro_Factura) references Factura(Nro_Factura))
GO

create table Pedido_WEB
(	Id_Pedido			smallint not null identity,
	IdCliente			smallint not null,
	Fecha_Generacion	date,
	Fecha_Despacho		date,
	Fecha_Entrega		date,
	EstadoPedido		varchar(50),
	DireccionEnvio		Char(100),
	Constraint PKId_Pedido  Primary Key (Id_Pedido),
	Constraint FKCliente Foreign Key (IdCliente) references Cliente(IdCliente)
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
	Primary Key (Id_Ubicacion),
	Constraint CK_Calle CHECK (Calle like '[A-G]'),
	Constraint CK_Bloque CHECK (Calle like '[1-5]'),
	Constraint CK_Estante CHECK (Calle like '[1-6]'),
	Constraint CK_Posicion CHECK (Calle like '[1-21]'))
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
	Codigo_Barra int not null,
	Id_Ubicacion Char(7),
	Primary Key (Nro_Lote,Codigo_Barra),
	Constraint  FKId_Ubicacion2 Foreign Key (Id_Ubicacion) references Ubicacion(Id_Ubicacion),
	Constraint  FKCodigo_Barra2 Foreign Key (Codigo_Barra) references Articulo(Codigo_Barra),
	Constraint  FKLote  Foreign Key (Nro_Lote) references Lote(Nro_Lote)
	)

GO

Create table Item_RemitoAlmacen
(	Id_ItemRemitoAlamcen smallint identity,
	Codigo_Barra	int,
	Cantidad smallint,
	Id_Ubicacion Char(7),
	Primary Key (Id_ItemRemitoAlamcen),
	Constraint  FKCodigo_Barra3 Foreign Key (Codigo_Barra ) references Articulo(Codigo_Barra ),
	Constraint  FKId_Ubicacion5 Foreign Key (Id_Ubicacion) references Ubicacion(Id_Ubicacion))
GO

Create table Remito_Almacen
(	Id_RemitoAlmacen smallint identity not null,
	IdEstado	varchar(30),
	Id_ItemRemitoAlamcen smallint,
	Tipo_Documento varchar(30),
	Nro_Documento int,
	Primary Key (Id_RemitoAlmacen),
	Constraint  FKId_ItemRemitoAlamcen Foreign Key (Id_ItemRemitoAlamcen) references Item_RemitoAlmacen(Id_ItemRemitoAlamcen),
	)

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