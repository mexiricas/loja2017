function formatarData(mascara, documento) {
    var i = documento.value.length;
    var saida = mascara.substring(0, 1);
    var texto = mascara.substring(i)

    if (texto.substring(0, 1) != saida) {
        documento.value += texto.substring(0, 1);
    }

}

function limpaCampo(x) {
    x.value = "";
}


function scroll() {
    if (document.body.scrollTop > 250 || document.documentElement.scrollTop > 50) {
       $('#goTop').fadeIn('slow');
    } else {
        $('#goTop').fadeOut('slow');
    }
}