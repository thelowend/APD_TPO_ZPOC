
insert into Articulos values ('Rutini','Caja',300,100,'6 unidades','',200,0,200,0)
insert into Articulos values ('Pepsi','Latas',275,200,'24 unidades','',50,0,50,0)
insert into Articulos values ('Coca-Cola','Latas',350,200,'24 unidades','',150,0,150,0)
insert into Articulos values ('Mirinda','Botellas',175.00,300,'6 unidades','',0,-10,0,300)
insert into Articulos values ('7up','Botellas',200.00,100,'6 unidades','',300,0,300,0)


insert into Proveedores values ('Vi�a Uzal')
insert into Proveedores values ('Pepsico')
insert into Proveedores values ('Sierra')
insert into Proveedores values ('Grupo Infra')

Insert into ArticulosProveedor values (175,10000,1)
Insert into ArticulosProveedor values (175,10100,2)
Insert into ArticulosProveedor values (200,10200,3)
Insert into ArticulosProveedor values (80,10300,4)
Insert into ArticulosProveedor values (80,10400,1)
Insert into ArticulosProveedor values (150,10000,2)

insert into Clientes values('Diego Maximiliano Pablos',22333456,'Rivadavia 7376',0,0,10)
insert into Clientes values('Maria De Los Angeles Ojeda',33444555, 'Boedo 150',1,1,20)
insert into Clientes values('Cristian Alberto Cavallaro',44555666,'Puan 566',0,1,10)
insert into Clientes values('Barbara Daniela Zapatero',33220009,'Directorio 787',1,0,5.7)

Insert into CuentasCorriente values (500,6000,1)
Insert into CuentasCorriente values (1000,6000,2)
Insert into CuentasCorriente values (2000,10000,3)
Insert into CuentasCorriente values (0,6000,4)

insert into Facturas values ('20180220','20180324','A', 200, 1)
insert into Facturas values ('20180221','20180324','A',300, 1)
insert into Facturas values ('20180221','20180324','B',500,2)
insert into Facturas values ('20180222','20180324','B',500,2)
insert into Facturas values ('20180222','20180324','A', 2000,3)
insert into Facturas values ('20180222','20180324','B', 1200,4)


Insert into CuentasCorriente values (0,15000,1)
Insert into CuentasCorriente values (1450,6000,2)
Insert into CuentasCorriente values (2550,10000,3)
Insert into CuentasCorriente values (5150,15000,4)



Insert into Facturas values (20180201, 20180501, 'A', 2925,1)
Insert into Facturas values (20180202, 20180502, 'B', 1450,2)
Insert into Facturas values (20180203, 20180503, 'A', 2550,3)
Insert into Facturas values (20180204, 20180504, 'B', 5150,4)


Insert into ItemsFactura values (5, 1500,10000,1)
Insert into ItemsFactura values (5, 875	,10400,1)
Insert into ItemsFactura values (2, 550	,10100,1)
Insert into ItemsFactura values (2, 350	,10300,2)
Insert into ItemsFactura values (4, 1100,10100,2)
Insert into ItemsFactura values (3, 1050,10200,3)
Insert into ItemsFactura values (5, 1500,10000,3)
Insert into ItemsFactura values (2, 350	,10300,4)
Insert into ItemsFactura values (5, 1375,10100,4)
Insert into ItemsFactura values (5, 1500,10000,4)
Insert into ItemsFactura values (1, 175	,10400,4)
Insert into ItemsFactura values (5, 1750,10200,4)


Insert into PedidosWeb values ('20180201','20180202','20180203', 'Entregado','Rivadavia 7376',1)
Insert into PedidosWeb values ('20180202','20180203','20180204', 'Entregado','Boedo 150',2)
Insert into PedidosWeb values ('20180203','20180204','20180205', 'Entregado','Puan 566',3)
Insert into PedidosWeb values ('20180204','20180205','20180206', 'Entregado','Directorio 787',4)
Insert into PedidosWeb values ('20180228', null,	null, 'Pendiente_Stock','Rivadavia 736',1)
Insert into PedidosWeb values ('20180228', null,	null, 'Pendiente_Despacho','Boedo 150',2)

Insert into ItemsPedido values (5,'Con_Stock',10000,1)
Insert into ItemsPedido values (5,'Con_Stock',10400,1)
Insert into ItemsPedido values (2,'Con_Stock',10100,1)
Insert into ItemsPedido values (2,'Con_Stock',10300,2)
Insert into ItemsPedido values (4,'Con_Stock',10100,2)
Insert into ItemsPedido values (3,'Con_Stock',10200,3)
Insert into ItemsPedido values (5,'Con_Stock',10000,3)
Insert into ItemsPedido values (2,'Con_Stock',10300,4)
Insert into ItemsPedido values (5,'Con_Stock',10100,4)
Insert into ItemsPedido values (5,'Con_Stock',10000,4)
Insert into ItemsPedido values (1,'Con_Stock',10400,4)
Insert into ItemsPedido values (5,'Con_Stock',10200,4)
Insert into ItemsPedido values (10,'Sin_Stock',10300,5)
Insert into ItemsPedido values (5,'Con_Stock',10100,5)
Insert into ItemsPedido values (5,'Con_Stock',10000,6)
Insert into ItemsPedido values (5,'Con_Stock',10200,6)

insert into Lotes values (25647,'20180825',10000)
insert into Lotes values (25648,'20180305',10000)
insert into Lotes values (58787,'20180326',10100)
insert into Lotes values (87547,'20180328',10200)
insert into Lotes values (78787,'20180825',10400)
insert into Lotes values (87877,'20180303',10400)

insert into Ubicaciones  values ( 'A010101','A',01,01,	01,'Completa',0,100,1)
insert into Ubicaciones  values ( 'A010102','A',01, 01, 02,'Completa',0,100,2)
insert into Ubicaciones  values ( 'A010103','A',01, 01, 03,'Con_disponibilidad',50,100,3)
insert into Ubicaciones  values ( 'A010104','A',01, 01, 04,'Con_disponibilidad',50,100,4)
insert into Ubicaciones  values ( 'A010105','A',01, 01, 05,'Completa',0,100,4)
insert into Ubicaciones  values ( 'A010106','A',01, 01, 06,'Completa',0,100,5)
insert into Ubicaciones  values ( 'A010107','A',01, 01, 07,'Completa',0,100,5)
insert into Ubicaciones  values ( 'A010108','A',01, 01, 08,'Completa',0,100,6)
insert into Ubicaciones  values ( 'A010109','A',01, 01, 09,'Libre',100,100,null)
insert into Ubicaciones  values ( 'A010110','A',01, 01, 10,'Libre',100,100,null)
insert into Ubicaciones  values ( 'A010111','A',01, 01, 11,'Libre',100,100,null)
insert into Ubicaciones  values ( 'A010112','A',01, 01, 12,'Libre',100,100,null)
insert into Ubicaciones  values ( 'A010113','A',01, 01, 13,'Libre',100,100,null)
insert into Ubicaciones  values ( 'A010114','A',01, 01, 14,'Libre',100,100,null)
insert into Ubicaciones  values ( 'A010115','A',01, 01, 15,'Libre',100,100,null)
insert into Ubicaciones  values ( 'A010116','A',01, 01, 16,'Libre',100,100,null)
insert into Ubicaciones  values ( 'A010117','A',01, 01, 17,'Libre',100,100,null)
insert into Ubicaciones  values ( 'A010118','A',01, 01, 18,'Libre',100,100,null)
insert into Ubicaciones  values ( 'A010119','A',01, 01, 19,'Libre',100,100,null)
insert into Ubicaciones  values ( 'A010120','A',01, 01, 20,'Libre',100,100,null)
insert into Ubicaciones  values ( 'A010121','A',01, 01, 21,'Libre',100,100,null)
insert into Ubicaciones  values ( 'A010201','A',01, 02, 01,'Libre',100,100,null)


INSERT INTO OrdenesCompra values (300,'Pendiente','20180201',4,null,5,10300);