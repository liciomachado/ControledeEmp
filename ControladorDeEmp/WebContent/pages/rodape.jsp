<footer id="contato">
	<address>
		<strong>1º Batalhão Ferroviário - Lages/SC</strong><br> 5ª RM -
		CMS <br> (49) 3251-9500 <br>
	</address>

</footer>
</body>

<script src="../css/jquery-3.4.1.min.js"></script>
<script src="../css/jquery.mask.min.js""></script>
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
	$(document).ready(function(e) { 
		$("#inputCadastro").click(function(e) { 
			if($(this).is(':checked')) { 
				$("#mostraCadastro").slideToggle(170);
			} else {
				$("#mostraCadastro").slideToggle(170);
				console.log("CheckBox desmarcado não faz nada."); 
			}
		}); 
	});
	$(document).ready(function(e) { 
		$("#adicionaObs").click(function(e) { 
			if($(this).is(':checked')) { 
				$("#txtObs").slideToggle(170);
			} else {
				$("#txtObs").slideToggle(170);
				console.log("CheckBox desmarcado não faz nada."); 
			}
		}); 
	});
	$(document).ready(function(e) { 
		$("#adicionaNF").click(function(e) { 
			if($(this).is(':checked')) { 
				$("#txtNF").slideToggle(170);
			} else {
				$("#txtNF").slideToggle(170);
				console.log("CheckBox desmarcado não faz nada."); 
			}
		}); 
	});
	$("#animate").click(function() {

	    $("#contentDetalheEmpenho").animate(

	            {"width": "100%"},

	            1000);
	});
	jQuery(function($) {
		$("#contentDetalheEmpenhoNaoEntregue").animate(
	            {"width": "100%"},
	            1000);
		}
	);
	jQuery(function($) {
		$("#contentDetalheEmpenhoEntregue").animate(
	            {"width": "100%"},
	            1000);
		}
	);
	jQuery(function($) {
		$("#contentDetalheEmpenhoConcluido").animate(
	            {"width": "100%"},
	            1000);
		}
	);
	
	
	$('#HabilitaEmpenho').click(function(){
		if($(this).is(':checked')) { 
			$('#numeroEmpenho').prop('readonly', false);
			$('#valor').prop('readonly', false);
			$('#destinoEmpenho').prop('readonly', false);
		}else {
			$('#numeroEmpenho').prop('readonly', true);
			$('#valor').prop('readonly', true);
			$('#destinoEmpenho').prop('readonly', true);
		}
	});
	$('#habilitaEmpresa').click(function(){
		if($(this).is(':checked')) { 
			$('#nomeEmpresa').prop('readonly', false);
			$('#numTelefone').prop('readonly', false);
			$('#nomeEmail').prop('readonly', false);
		}else {
			$('#nomeEmpresa').prop('readonly', true);
			$('#numTelefone').prop('readonly', true);
			$('#nomeEmail').prop('readonly', true);
		}
	});
	$('#habilitaNF').click(function(){
		if($(this).is(':checked')) { 
			$('#inputChaveAcesso').prop('readonly', false);
			$('#inputNota').prop('readonly', false);
			$('#inputDataEmissao').prop('readonly', false);
			$('#inputPreco').prop('readonly', false);
		}else {
			$('#inputChaveAcesso').prop('readonly', true);
			$('#inputNota').prop('readonly', true);
			$('#inputDataEmissao').prop('readonly', true);
			$('#inputPreco').prop('readonly', true);
		}
	});
	$("#numTelefone").mask("(00) 00000-0000");
	$('#dinheiro').mask('###0.00', {reverse: true});
	
	
	$(document).ready(function(){
	    $('.trEmpresas').click(function(){
	        window.location = $(this).data('url');
	        returnfalse;
	    });
	});
	$(document).ready(function(){
	    $('.trRecebidos').click(function(){
	        window.location = $(this).data('url');
	        returnfalse;
	    });
	});
</script>
</html>