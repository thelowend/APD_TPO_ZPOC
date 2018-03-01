(() => {
	$(() =>{
		const $main = $('#main');
		const $btnModalPedido = $('#hacerPedido');
		const $btnPedido = $('#submitPedido');
		const $btnAnadir = $('.btn-anadir');
		const $carritoArticulos = $('.carrito-articulos');
		const $validationFields = $('.needs-validation');
		const $modalPedido = $('#submitPedidoModal');
		const invalidClass = 'is-invalid';
		
		
		const modalPedido = ev => {
			ev.preventDefault();
			if (validar()) {
				popularModal();
				$modalPedido.modal('show');
			}
		}
		
		const popularModal = () => {
			const articulos = $carritoArticulos.data('articulos');
			const $body = $modalPedido.find('.modal-body').empty();
			$.each(articulos, (i, art) => { $modalPedido.find('.modal-body').append(`<div class="row no-gutters"><div class="col-10">${art.nombre} - ${art.desc}</div><div class="col-2 text-center">${art.cant}</div></div>`); });
		}
		
		const validar = () => {

			$validationFields.each((index, item) => {
				switch (item.type) {
					case 'text':
							$(item).toggleClass(invalidClass, !item.value);
						break;
					case 'select-one':
							$(item).toggleClass(invalidClass, item.selectedIndex < 1);
						break;
				}
			});
			
			return  $carritoArticulos.data('articulos').length > 0 && $(`.needs-validation.${invalidClass}`).length < 1;
		}
		
		
		const ingresarPedido = (ev) => {
			ev.preventDefault();
			let articulosComprados = $carritoArticulos.data('articulos');
			
			if(validar()) {
				const nuevopedido = $.extend({}, {
					articulos: JSON.stringify(articulosComprados),
					cliente: $('#cliente').val(),
					direccion: $('#direccion').val()
				});

				$.post('ActionServlet?action=EnviarPedido', nuevopedido, page => {
						$modalPedido.one('hidden.bs.modal', function (e) {
							$main.html(page);
						});
						$modalPedido.modal('hide');
						
				});				
			}
		}
		
		const agregarAlCarrito = (ev) => {
			let articulo = $(ev.currentTarget).data('articulo');
			debugger;
			const model = `
			<div class="row no-gutters" id="artcarr${articulo.codigoBarra}" data-artid="${articulo.codigoBarra}">
				  <div class="carrito-articulo-nombre col-8">${articulo.nombre} - ${articulo.desc}</div>
				  <div class="carrito-articulo-trash col-1 text-center text-dark"><button class="btn btn-sm btn-light" data-action="min"><i class="fas fa-minus-square"></i></button></div>
				  <div class="carrito-articulo-cant col-1 text-center">${articulo.cant}</div>
				  <div class="carrito-articulo-trash col-1 text-center text-dark"><button class="btn btn-sm btn-light" data-action="plus"><i class="fas fa-plus-square"></i></button></div>
				  <div class="carrito-articulo-trash col-1 text-center"><button class="btn btn-sm btn-dark" data-action="remove"><i class="fas fa-trash"></i></button></div>
			</div>
			`;
			let articulosEnCarrito = $carritoArticulos.data('articulos');
			if (articulosEnCarrito.length) {
				let found = false;
				for (let i = 0; !found && i < articulosEnCarrito.length; i++) {
					if(articulo.codigoBarra === articulosEnCarrito[i].codigoBarra) {
						$carritoArticulos.find(`#artcarr${articulosEnCarrito[i].codigoBarra} .carrito-articulo-cant`).html(++articulosEnCarrito[i].cant);
						found = true;
					}
				}
				if(!found) {
					$carritoArticulos.append(model);
					articulosEnCarrito.push(articulo);
				} else {
					
				}
			} else {
				$carritoArticulos.append(model);
				articulosEnCarrito.push(articulo);
			}
			$carritoArticulos.data('articulos', articulosEnCarrito);
		};
		
		
		const carritoAction = function () { // ¡Al fin un lugar en donde es relevante la diferencia entre las arrow function y las expresiones function!
			const $this = $(this);
			let articulosEnCarrito = $carritoArticulos.data('articulos');
			const $artRow = $this.parents('[data-artID]');
			
			let found = false, foundIndex = 0;
			for (foundIndex = 0; !found && foundIndex < articulosEnCarrito.length; foundIndex++) {
				if($artRow.data('artid') == articulosEnCarrito[foundIndex].codigoBarra) {
					found = true;
					foundIndex--;
				}
			}
			
			switch ($this.data('action')) {
				case 'plus':
					$artRow.find('.carrito-articulo-cant').html(++articulosEnCarrito[foundIndex].cant);
					break;
				case 'min':
					if(articulosEnCarrito[foundIndex].cant > 1) 
						$artRow.find('.carrito-articulo-cant').html(--articulosEnCarrito[foundIndex].cant);
					break;
				case 'remove':
					articulosEnCarrito.splice(foundIndex, 1);
					$artRow.remove();
					break;
			}
		}

		const doBindings = () => {
			$btnModalPedido.click(modalPedido);
			$btnAnadir.click(agregarAlCarrito);
			$btnPedido.click(ingresarPedido);
			$carritoArticulos.on('click', '[data-action]', carritoAction);
		}
		
		doBindings();
	});
})();