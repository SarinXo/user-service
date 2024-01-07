$(document).ready(function () {

    var csrfToken = $("meta[name='_csrf']").attr("content");
    var csrfHeader = $("meta[name='_csrf_header']").attr("content");

    $('.farm-info').click(function () {
        var farmCode=    $(this).data('farmcode');
        var email=       $(this).data('email');
        var description= $(this).data('description');
        var address=     $(this).data('address');
        var license=     $(this).data('license');
        var money=       $(this).data('money');
        $('#editFarmCode').val(farmCode);
        $('#editEmail').val(email);
        $('#editDescription').val(description);
        $('#editAddress').val(address);
        $('#editLicense').val(license);
        $('#editMoney').val(money);
    });

    $('#modify-farm').submit(function(e) {
        e.preventDefault(); // Предотвращение стандартного поведения формы

        var formData = {
            farmCode: $('#editFarmCode').val(),
            email: $('#editEmail').val(),
            description: $('#editDescription').val(),
            address: $('#editAddress').val(),
            license: $('#editLicense').val(),
            money: $('#editMoney').val()
        };

        $.ajax({
            type: 'POST',
            url: '/users/farms',
            contentType: 'application/json',
            data: JSON.stringify(formData),
            beforeSend: function(xhr) {
                xhr.setRequestHeader(csrfHeader, csrfToken);
            },
            success: function(response) {
                $('#modify-farm').modal('hide');
                toastr["success"]("Успешно обновлено! Для просмотра изменений обновите страницу");
            },
            error: function(error) {
                $('#modify-farm').modal('hide');
                toastr["error"]("Ошибка при отправке формы");
            }
        });
    });

    $('#add-stern').submit(function(e) {
        e.preventDefault(); // Предотвращение стандартного поведения формы

        var formData = {
            type: $('#type').val(),
            weight: $('#sternWeight').val(),
            farmerId: $('#farmerId').val()
        };

        $.ajax({
            type: 'POST',
            url: '/users/sterns',
            contentType: 'application/json',
            data: JSON.stringify(formData),
            beforeSend: function(xhr) {
                xhr.setRequestHeader(csrfHeader, csrfToken);
            },
            success: function(response) {
                $('#add-stern').modal('hide');
                toastr["success"]("Успешно обновлено! Для просмотра изменений обновите страницу");
            },
            error: function(error) {
                $('#add-stern').modal('hide');
                toastr["error"]("Ошибка при отправке формы");
            }
        });

    });

    $('#add-pig').submit(function(e) {
        e.preventDefault(); // Предотвращение стандартного поведения формы

        var formData = {
            breed: $('#breed').val(),
            dateOfBirthday: $('#dateOfBirthday').val(),
            gender: $('#gender').val(),
            farmerId: $('#ownerId').val()
        };

        $.ajax({
            type: 'POST',
            url: '/users/pigs',
            contentType: 'application/json',
            data: JSON.stringify(formData),
            beforeSend: function(xhr) {
                xhr.setRequestHeader(csrfHeader, csrfToken);
            },
            success: function(response) {
                $('#add-pig').modal('hide');
                toastr["success"]("Успешно обновлено! Для просмотра изменений обновите страницу");
            },
            error: function(error) {
                $('#add-pig').modal('hide');
                toastr["error"]("Ошибка при отправке формы");
            }
        });
        $('#add-pig').modal('hide');
    });

    $('#add-weight').submit(function(e) {
        e.preventDefault(); // Предотвращение стандартного поведения формы

        var formData = {
            pigId: $('#selectedPig').val(),
            weight: $('#weightInput').val()
        };

        $.ajax({
            type: 'POST',
            url: '/users/weights',
            contentType: 'application/json',
            data: JSON.stringify(formData),
            beforeSend: function(xhr) {
                xhr.setRequestHeader(csrfHeader, csrfToken);
            },
            success: function(response) {
                $('#add-weight').modal('hide');
                toastr["success"]("Успешно обновлено! Для просмотра изменений обновите страницу");
            },
            error: function(error) {
                $('#add-weight').modal('hide');
                toastr["error"]("Ошибка при отправке формы");
            }
        });

    });

    $('#addFatteningDay').submit(function(e) {
        e.preventDefault(); // Предотвращение стандартного поведения формы

        var formData = {
            dateStart: $('#dateStart').val(),
            dateEnd: $('#dateEnd').val(),
            farmCode: $('#farm-code').val()
        };

        $.ajax({
            type: 'POST',
            url: '/users/fat-day',
            contentType: 'application/json',
            data: JSON.stringify(formData),
            beforeSend: function(xhr) {
                xhr.setRequestHeader(csrfHeader, csrfToken);
            },
            success: function(response) {
                $('#addFatteningDay').modal('hide');
                toastr["success"]("Успешно обновлено! Для просмотра изменений обновите страницу");
            },
            error: function(error) {
                $('#addFatteningDay').modal('hide');
                toastr["error"]("Ошибка при отправке формы");
            }
        });

    });

    $('#pigSaleForm').submit(function(e) {
        e.preventDefault(); // Предотвращение стандартного поведения формы

        var formData = {
            pigId: $('#myPig').val(),
            cost: $('#salePriceInput').val(),
            farmerId: $('#sellerId').val()
        };

        $.ajax({
            type: 'POST',
            url: '/users/sell/pig',
            contentType: 'application/json',
            data: JSON.stringify(formData),
            beforeSend: function(xhr) {
                xhr.setRequestHeader(csrfHeader, csrfToken);
            },
            success: function(response) {
                $('#pigSaleForm').modal('hide');
                toastr["success"]("Успешно выставлено на торговую площадку! Для просмотра изменений обновите страницу");
            },
            error: function(error) {
                $('#pigSaleForm').modal('hide');
                toastr["error"]("Ошибка при отправке формы");
            }
        });

    });

    $('#sternSaleForm').submit(function(e) {
        e.preventDefault(); // Предотвращение стандартного поведения формы

        var formData = {
            weight: $('#sternWeightInput').val(),
            costPerKilo: $('#costPerKiloInput').val(),
            sternId: $('#selectedStern').val(),
            farmerId: $('#sellerSId').val()
        };

        $.ajax({
            type: 'POST',
            url: '/users/sell/stern',
            contentType: 'application/json',
            data: JSON.stringify(formData),
            beforeSend: function(xhr) {
                xhr.setRequestHeader(csrfHeader, csrfToken);
            },
            success: function(response) {
                $('#sternSaleForm').modal('hide');
                toastr["success"]("Успешно выставлено на торговую площадку! Для просмотра изменений обновите страницу");
            },
            error: function(error) {
                $('#sternSaleForm').modal('hide');
                toastr["error"]("Ошибка при отправке формы");
            }
        });
    });

    $('#sternOutForm').submit(function(e) {
        e.preventDefault(); // Предотвращение стандартного поведения формы

        var formData = {
            id: $('#selectedSternOnMarket').val()
        };

        $.ajax({
            type: 'POST',
            url: '/users/sell/out-stern',
            contentType: 'application/json',
            data: JSON.stringify(formData),
            beforeSend: function(xhr) {
                xhr.setRequestHeader(csrfHeader, csrfToken);
            },
            success: function(response) {
                $('#sternOutForm').modal('hide');
                toastr["success"]("Успешно снято! Для просмотра изменений обновите страницу");
            },
            error: function(error) {
                $('#sternOutForm').modal('hide');
                toastr["error"]("Ошибка при отправке формы");
            }
        });
    });

    $('#pigOutForm').submit(function(e) {
        e.preventDefault(); // Предотвращение стандартного поведения формы

        var formData = {
            id: $('#selectedPigOnMarket').val()
        };

        $.ajax({
            type: 'POST',
            url: '/users/sell/out-pig',
            contentType: 'application/json',
            data: JSON.stringify(formData),
            beforeSend: function(xhr) {
                xhr.setRequestHeader(csrfHeader, csrfToken);
            },
            success: function(response) {
                $('#pigOutForm').modal('hide');
                toastr["success"]("Успешно снято! Для просмотра изменений обновите страницу");
            },
            error: function(error) {
                $('#pigOutForm').modal('hide');
                toastr["error"]("Ошибка при отправке формы");
            }
        });
    });

});