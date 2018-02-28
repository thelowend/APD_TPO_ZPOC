(() => {
	$(() =>{
		const $btnPedido = $('#submitPedido');
		
		const ingresarPedido = (ev) => {
			ev.preventDefault();
			debugger;
			const cliente = $('#cliente').value(); //Chequear contra lista de clientes
			const direccion = $('#direccion').value();
			
			confirm("confirmar pedido?");
		}
		
		$btnPedido.click(ingresarPedido);
	});
})();