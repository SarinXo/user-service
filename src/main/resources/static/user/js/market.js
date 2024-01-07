function getFormData($form){
    var unindexed_array = $form.serializeArray();
    var indexed_array = {};

    $.map(unindexed_array, function(n, i){
        indexed_array[n['name']] = n['value'];
    });

    return indexed_array;
}

function sortProducts() {

    var sortByName = document.getElementById('sortByName').checked;
    var sortByPrice = document.getElementById('sortByPrice').checked;

    $.ajax({
        type: "GET",
        url: "/users/products",
        data: { sortByName: sortByName, sortByPrice: sortByPrice },
        beforeSend: function(xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
        success: function(response) {
            // Обработка успешного ответа
        },
        error: function(error) {
            // Обработка ошибки
        }
    });
}

function sortingRequest(sortByUser, sortByPrice) {
    var csrfToken = $("meta[name='_csrf']").attr("content");
    var csrfHeader = $("meta[name='_csrf_header']").attr("content");

    $.ajax({
        type: "GET",
        //`${greeting}, ${firstName}!`
        url: `/users/market?sortByName=${sortByUser}&sortByPrice=${sortByPrice}`,
        beforeSend: function(xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
        success: function(response) {
            // Обработка успешного ответа
        },
        error: function(error) {
            // Обработка ошибки
        }
    });
}

$(document).ready(function () {

    var csrfToken = $("meta[name='_csrf']").attr("content");
    var csrfHeader = $("meta[name='_csrf_header']").attr("content");
    var sortByUser = false;
    var sortByPrice = false;

    $('.buy').click(function () {
        var id = $(this).data('id');
        var name = $(this).data('name');
        Swal.fire({
            title: 'Вы уверены?',
            text: `Вы уверены, что хотите купить ${name}?`,
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#bf30fc',
            cancelButtonColor: '#c50404',
            confirmButtonText: 'Да!',
            cancelButtonText: 'Отмена'
        }).then((result) => {
            if (result.isConfirmed) {
                buyItem(id, csrfHeader, csrfToken);
            }
        });
    });
});

function buyItem(id, csrfHeader, csrfToken) {

    $.ajax({
        type: 'POST',
        url: '/users/buy-pig',
        contentType: "application/json",
        data: JSON.stringify({id: id}),
        beforeSend: function(xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
        success: function () {
            toastr.success('Вы успешно купили предмет');
            var element = document.getElementById('row'+id);
            element.remove();
        },
        error: function () {
            toastr.error('Произошла ошибка при покупке, проверьте баланс и соединение с сервером');
        }
    });
}