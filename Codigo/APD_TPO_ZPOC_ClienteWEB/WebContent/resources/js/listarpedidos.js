(() => {
	$(() =>{
		const $btnPedido = $('.btn-despachar');
		const $modalDespachar = $('#dispatchPedidoModal');
		const $submitPedido = $('#submitPedido');
		
		let currentPedido = '';
		
		const despacharPedido = (ev) => {
			ev.preventDefault();
			// Me guardaría el pedido en la data del botón, y acá la obtengo:
			const pedido = $(ev.currentTarget).data('pedido');
			
			populateModal(pedido);
			$modalDespachar.modal('show');
			
		}
		
		const submitPedido = (ev) => {
			// Valido los campos
			alert(`¡${currentPedido.nombre} despachado!`);
		}
		
		const populateModal = pedido => {
			currentPedido = { nombre: pedido }; //Despues voy a hacer que pedido sea un JSON
			$modalDespachar.find('#dispatchPedidoModalLabel').text(`¿Despachar ${currentPedido.nombre}?`);
			console.log(pedido);
		}
		
		const doBindings = () => {
			$btnPedido.click(despacharPedido);
			$submitPedido.click(submitPedido);
		}
		
		doBindings();
	});
	

})();