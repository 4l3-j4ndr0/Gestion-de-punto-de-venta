# Gestion-de-negocio-de-ventas
gestiona las ventas de los productos de un negocio, cuenta con:
USUARIOS           ----> gestiona los usuarios que pueden entrar, editando el nombre de usuario y contrase;a de los existentes o agregando uno nuevo
ALMACEN            ----> se agregan, editan y eliminan productos o eliminan por tipo de producto; muestra precio compra, precio de venta y ganancia por cada producto
REGISTRO VENTAS    ----> se muestran todas las ventas realizadas con info del producto vendido y la fecha, la fecha, ganancia, inversion; se edita, elimina venta (pasa al almacen);
                         se puede buscar por fecha, mostrar todas las ventas o las del dia nadamas; muestra ganancia total, inversion total y monto total; lista la cantidad, total , inversion y ganancia por 
                         productos agrupados, ademas se puede graficar en tres formas , pastel, barra y linea , para un mejor entendimiento de loas productos mas vendidos. En el reporte se puede mostrar uno o todos los graficos o simplemente ninguno de los tres.
CAJA REGISTRADORA  ----> es donde se le dan salida a los productos, tantos los que se pagan al momento como los que salen como deuda para pagar en un tiempo determinado
                         si se archivan como deudas se debera escribir el nombre de la persona, entidad, etc , a la q se cargara la deuda, antes de darle salida del alamacen 
                         se muestra una lista con todos los productos seleccionados y el precio a pagar, se puede ingresar el dinero que se recibe y muestra automaticamente el cambio
                         que se debe dar al que lo compra, se puede eliminar o cancelar la venta que se este realizando
CUENTAS POR COBRAR ----> igual que el registro de ventas pero con las deudas, a excepcion de que al eliminar una deuda o editarla (para menor cantidad del producto) esta pasa para 
                         el registro de ventas y no para el almacen como es logico, si se elimina da la opcion de eliminar toda la deuda o solo la fila que se selecccione
En todos los anteriores excepto en usuarios se puede hacer reportes para exportalos a excel, pdf, html, etc, o imprimirlos directamente   

                         
Cuenta con una base de datos para almacenar toda esta informacion, la base de datos puede estar hospedada en un servidor de bases de datos online como xampp o embebida 
con la libreria h2-1.4.192.jar
                         
