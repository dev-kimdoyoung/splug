var regist = {
    init : function () {
        var _this = this;
        $('#regist').on('click', function () {
            _this.regist();
        });
    },

    regist : function () {
        var data = {
            id: $('#id').val(),
            password: $('#pswd1').val(),
            studentId: $('#studentId').val(),
            name: $('#name').val(),
            dateOfBirth: $('#birthday').val(),
            phoneNumber: $('#phoneNumber').val(),
            department: $('#department').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/requestRegist',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert("가입이 완료되었습니다.");
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
};

regist.init();


function authorize(idx){

    $.ajax({
        type: 'POST',
        url: '/api/v1/authorize/' + idx
    }).done(function() {
        alert("가입이 승인되었습니다.");
        window.location.reload();
    }).fail(function (error) {
        alert(JSON.stringify(error));
    });
}

function unauthorize(idx){

    $.ajax({
        type: 'DELETE',
        url: '/api/v1/unauthorize/' + idx
    }).done(function() {
        alert("가입이 거절되었습니다.");
        window.location.reload();
    }).fail(function (error) {
        alert(JSON.stringify(error));
    });
}