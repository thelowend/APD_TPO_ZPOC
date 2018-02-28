(() => {
	const session = window.sessionStorage.getItem('session');
	const currentEpoch = new Date().getTime();
	if (session && session.expires < currentEpoch) {
		window.location.replace('layout.jsp');
	} else {
		window.location.replace('login.jsp');
	}
})();