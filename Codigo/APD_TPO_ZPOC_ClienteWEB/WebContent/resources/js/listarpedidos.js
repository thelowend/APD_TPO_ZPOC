(() => {
	$(() =>{
		const $btnPedido = $('.btn-despachar');
		
		const despacharPedido = (ev) => {
			ev.preventDefault();
			// Me guardaría el pedido en la data del botón, y acá la obtengo:
			const pedido = $(ev.currentTarget).data('pedido');
			alert(`¡${pedido} despachado!`);
		}
		
		$btnPedido.click(despacharPedido);
	});
})();