<footer id="contato">
    <address>
        <strong>1º Batalhão Ferroviário - Lages/SC</strong><br>
        5ª RM - CMS <br>
        (49) 3251-9500 <br>
    </address>

</footer>
</body>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script src="../css/select2/dist/js/select2.min.js"></script>

<script>
    (function () {
        // Executa quando o mouse estiver sobre
        jQuery("#whatever-gallery > div ").hover(function () {
            // com o mouse sobre
            jQuery(this).children("form").show();
        }, function () {
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
</script>
</html>