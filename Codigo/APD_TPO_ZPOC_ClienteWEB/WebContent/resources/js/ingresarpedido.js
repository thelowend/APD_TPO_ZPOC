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
		
		const carrito = ($this) => {
			return () => {
				
			}
		}
		
		
		

		const doBindings = () => {
			
			$btnPedido.click(ingresarPedido);
			$plusArt = $('.plus-art');
			$minArt = $('.min-art');
			$removeArt = $('.remove-art');
			$('.plus-art').click(carrito.apply($plusArt));
			$('.min-art').click(carrito.apply($minArt));
			$('.remove-art').click(carrito.apply($removeArt));
		}
		
		doBindings();
	});
})();