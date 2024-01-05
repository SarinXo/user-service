function sortProducts() {
    var csrfToken = $("meta[name='_csrf']").attr("content");
    var csrfHeader = $("meta[name='_csrf_header']").attr("content");

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