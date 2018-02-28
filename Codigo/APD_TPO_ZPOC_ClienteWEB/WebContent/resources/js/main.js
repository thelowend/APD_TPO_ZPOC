//Definimos un espacio privado para ofuscar el acceso a las variables
(() => {

	const $main = $('#main');
	const $overlay = $('.loading-overlay');
	
	const loadPage = (ev) => {
		ev.preventDefault();
		$overlay.fadeIn(150);
		$.post(ev.currentTarget.href, {"test" : 'lalala'}, page => {
			setTimeout(() => { //Timeout para simular carga
				$main.html(page);
				$overlay.fadeOut(150);
			}, 500);
		});
	}
	
	$('.ajax-link').click(loadPage);
})();