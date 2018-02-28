(() => {
	$(() =>{
		const $btnPedido = $('.btn-despachar');
		const $modalDespachar = $('#dispatchPedidoModal');
		const $submitPedido = $('#submitPedido');
		
		const invalidClass = 'is-invalid';
		
		let currentPedido = '';
		
		const despacharPedido = (ev) => {
			ev.preventDefault();
			populateModal($(ev.currentTarget).data('pedido'));
			$modalDespachar.modal('show');
		}
		
		const validar = () => {
			const fechaGen = new Date(currentPedido.fechagen);
			$modalDespachar.find('.needs-validation').each((index, item) => {
				switch (item.type) {
					case 'date':
							let invalid = true;
							if (!!item.valueAsDate) {
								invalid = fechaGen.getTime() > item.valueAsDate.getTime();
							}
								$(item).toggleClass(invalidClass, invalid);
						break;
					case 'select-one':
							$(item).toggleClass(invalidClass, item.selectedIndex < 1);
						break;
				}
			});
			return $modalDespachar.find(`.needs-validation.${invalidClass}`).length < 1;
		}
		
		const submitPedido = () => {
			if(validar()) {
				$.extend(currentPedido, {
					fechaEntrega: $modalDespachar.find('#fechaentrega').val(),
					empresatransporte: $modalDespachar.find('#empresaTransporteSelect').val()
				});
				
				$.post('ActionServlet?action=DespacharPedido', currentPedido, page => {
					setTimeout((data) => { //Timeout para simular carga
						alert(`¡${currentPedido.nombre} despachado!`);
						$modalDespachar.modal('hide');
					}, 200);
				});				
			}
		}
		
		const populateModal = pedido => {
			currentPedido = pedido; //Despues voy a hacer que pedido sea un JSON
			$modalDespachar.find('#dispatchPedidoModalLabel').text(`¿Despachar pedido ${currentPedido.id}?`);
			$modalDespachar.find('.needs-validation').removeClass('is-invalid');
			console.log(pedido);
		}
		
		const doBindings = () => {
			$btnPedido.click(despacharPedido);
			$submitPedido.click(submitPedido);
		}
		
		doBindings();
	});
	

})();