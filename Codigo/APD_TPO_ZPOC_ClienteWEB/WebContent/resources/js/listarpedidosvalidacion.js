(() => {
	$(() =>{
		const $main = $('#main').removeClass('modal-body');
		const $btnValidarPedido = $('.btn-validar');
		
		const validarPedido = function () {
			$.post('ActionServlet?action=ValidarPedido', {"pedido": JSON.stringify($(this).data('pedido')) }, page => {
				$main.html(page);
			});				
		}

		const doBindings = () => {
			$btnValidarPedido.click(validarPedido);
		}
		
		doBindings();
	});

})();