Delete from [dbo].[Articulos]
Delete from [dbo].[ArticulosProveedor]
Delete from [dbo].[Clientes]
Delete from [dbo].[CuentasCorriente]
Delete from [dbo].[Facturas]
Delete from [dbo].[ItemsFactura]
Delete from [dbo].[ItemsPedido]
Delete from [dbo].[ItemsRemitoAlmacen]
Delete from [dbo].[Lotes]
Delete from [dbo].[MovimientosStock]
Delete from [dbo].[OrdenesCompra]
Delete from [dbo].[PagosCliente]
Delete from [dbo].[PedidosWEB]
Delete from [dbo].[Proveedores]
Delete from [dbo].[RemitosAlmacen]
Delete from [dbo].[RemitosTrasporte]
Delete from [dbo].[Ubicaciones]



DBCC CHECKIDENT ([Articulos], RESEED, 0)

DBCC CHECKIDENT ([Articulos], RESEED, 0)
DBCC CHECKIDENT ([ArticulosProveedor], RESEED, 0)
DBCC CHECKIDENT ([Clientes], RESEED, 0)
DBCC CHECKIDENT ([CuentasCorriente], RESEED, 0)
DBCC CHECKIDENT ([Facturas], RESEED, 0)
DBCC CHECKIDENT ([ItemsFactura], RESEED, 0)
DBCC CHECKIDENT ([ItemsPedido], RESEED, 0)
DBCC CHECKIDENT ([ItemsRemitoAlmacen], RESEED, 0)
DBCC CHECKIDENT ([Lotes], RESEED, 0)
DBCC CHECKIDENT ([MovimientosStock], RESEED, 0)
DBCC CHECKIDENT ([OrdenesCompra], RESEED, 0)
DBCC CHECKIDENT ([PagosCliente], RESEED, 0)
DBCC CHECKIDENT ([PedidosWEB], RESEED, 0)
DBCC CHECKIDENT ([Proveedores], RESEED, 0)
DBCC CHECKIDENT ([RemitosAlmacen], RESEED, 0)
DBCC CHECKIDENT ([RemitosTrasporte], RESEED, 0)
DBCC CHECKIDENT ([Ubicaciones], RESEED, 0)












Select * from [dbo].[Articulos]
Select * from [dbo].[ArticulosProveedor]
Select * from [dbo].[Clientes]
Select * from [dbo].[CuentasCorriente]
Select * from [dbo].[Facturas]
Select * from [dbo].[ItemsFactura]
Select * from [dbo].[ItemsPedido]
Select * from [dbo].[ItemsRemitoAlmacen]
Select * from [dbo].[Lotes]
Select * from [dbo].[MovimientosStock]
Select * from [dbo].[OrdenesCompra]
Select * from [dbo].[PagosCliente]
Select * from [dbo].[PedidosWEB]
Select * from [dbo].[Proveedores]
Select * from [dbo].[RemitosAlmacen]
Select * from [dbo].[RemitosTrasporte]
Select * from [dbo].[Ubicaciones]