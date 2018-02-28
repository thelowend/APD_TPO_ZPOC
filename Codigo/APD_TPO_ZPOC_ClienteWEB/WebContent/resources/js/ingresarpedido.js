(() => {
	$(() =>{
		const $btnPedido = $('#submitPedido');
		const $btnAnadir = $('.btn-anadir');
		const $carritoArticulos = $('.carrito-articulos');
		
		let pedidoActual = {};
		
		const validar = () => {
			return true;
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
					setTimeout((data) => { 
						
						//alert(`¡${currentPedido.idPedido} despachado!`);
//						$modalDespachar.one('hidden.bs.modal', function (e) {
//							$main.html(page);
//						});
//						$modalDespachar.modal('hide');
						
					}, 100);
				});				
			}
			
			confirm("confirmar pedido?");
		}
		
		const agregarAlCarrito = (ev) => {
			const articulo = $(ev.currentTarget).data('articulo');
			const model = `
			<div class="row no-gutters" id="artcarr${articulo.id}">
			<div class="carrito-articulo-nombre col-8">${articulo.nombre}</div>
				  <div class="carrito-articulo-trash col-1 text-center text-dark"><button class="btn btn-sm btn-light plus-art"><i class="fas fa-minus-square"></i></button></div>
				  <div class="carrito-articulo-cant col-1 text-center">${articulo.cant}</div>
				  <div class="carrito-articulo-trash col-1 text-center text-dark"><button class="btn btn-sm btn-light min-art"><i class="fas fa-plus-square"></i></button></div>
				  <div class="carrito-articulo-trash col-1 text-center"><button class="btn btn-sm btn-dark remove-art" data-artborrar="${articulo.id}"><i class="fas fa-trash"></i></button></div>
			</div>
			`;
			let articulosEnCarrito = $carritoArticulos.data('articulos');
			if (articulosEnCarrito.length) {
				let modif = $.each(articulosEnCarrito, (idx, art) => {
					if(articulo.id === art.id) {
						art.cant++;
						$carritoArticulos.find(`#artcarr${articulo.id} .carrito-articulo-cant`).html(art.cant);
					} else {
						articulosEnCarrito.push(articulo);
						$carritoArticulos.append(model);
					}
				});	
			} else {
				$carritoArticulos.append(model);
				articulosEnCarrito.push(articulo);
			}
			
			$carritoArticulos.data('articulos', articulosEnCarrito);
		};
		
		const carrito = ($this) => {
			return () => {
				
			}
		}
		
		
		

		const doBindings = () => {
			$btnAnadir.click(agregarAlCarrito);
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