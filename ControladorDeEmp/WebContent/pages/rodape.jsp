<footer id="contato">
	<address>
		<strong>1º Batalhão Ferroviário - Lages/SC</strong><br> 5ª RM -
		CMS <br> (49) 3251-9500 <br>
	</address>

</footer>
</body>

<script src="../css/jquery-3.4.1.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>
<script src="../css/bootstrap4/js/bootstrap.min.js""></script>
<script src="../css/select2/dist/js/select2.min.js"></script>

<script language="JavaScript" type="text/javascript">

	(function() {
		// Executa quando o mouse estiver sobre
		jQuery("#whatever-gallery > div ").hover(function() {
			// com o mouse sobre
			jQuery(this).children("form").show();
		}, function() {
			// quando o mouse sai de cima
			jQuery(this).children("form").hide();
		});

	})(jQuery);

	function carregaUser(adm, user) {
		var elemento = document.getElementById(adm);
		var elementox1 = document.getElementById(user);
		elemento.style.display = 'block';
		elementox1.style.display = 'none';
	}

	function carregaAdm(adm, user) {
		var elemento = document.getElementById(adm);
		var elementox1 = document.getElementById(user);
		elemento.style.display = 'block';
		elementox1.style.display = 'none';
	}

	$(document).ready(function() {
		$('.js-example-basic-single').select2();
	});

	$(document).ready(function() {
		$("select[name='inputEmp']").change(function() {

			txtid = $("#inputEmp").val();

			$.post("../functionJSON", {
				id : txtid
			}, function(data, status) {
				var valores = data.split('/');
				console.log(valores);

				$("#setEmpresa").val(valores[0]);
				$("#setValor").val(valores[1]);
			});
		});
	});
</script>
</html>