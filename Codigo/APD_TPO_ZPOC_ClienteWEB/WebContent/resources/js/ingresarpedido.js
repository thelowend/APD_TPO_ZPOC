(() => {
	$(() =>{
		const $btnPedido = $('#submitPedido');
		
		let pedidoActual = {};
		
		const ingresarPedido = (ev) => {
			ev.preventDefault();
			debugger;
			const cliente = $('#cliente').value(); //Chequear contra lista de clientes
			const direccion = $('#direccion').value();
			
			confirm("confirmar pedido?");
		}
		
		
		

		const doBindings = () => {
			
			$btnPedido.click(ingresarPedido);
			
			plus-art
			min-art
			remove-art
		}
		
		doBindings();
	});
})();