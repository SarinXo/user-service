$(document).ready(function () {
    $('#commentForm').on('submit', function (e) {
        e.preventDefault();

        var csrfToken = $("meta[name='_csrf']").attr("content");
        var csrfHeader = $("meta[name='_csrf_header']").attr("content");

        var comment = document.getElementById('comment').value;
        var rating = document.getElementById('rating').value;
        var toFarmerWithId = document.getElementById('toFarmerWithId').value;
        var username = document.getElementById('username').value;

        var data = {
            comment: comment,
            rating: rating,
            toFarmerWithId: toFarmerWithId
        };
        $.ajax({
            url: '/users/feedback',
            method: 'POST',
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(data),
            beforeSend: function(xhr) {
                xhr.setRequestHeader(csrfHeader, csrfToken);
            },
            success: function() {
                window.location.href = '/users/page?login=' + username;
            },
            error: function(jqXHR, textStatus, errorThrown) {
                var errorMessage = jqXHR.responseText || 'Произошла ошибка';

                toastr.error(errorMessage);
            }
        });
    });
});
