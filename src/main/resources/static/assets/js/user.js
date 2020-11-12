var user = {
    init : function () {
        var _this = this;
        $('#user').on('click', function () {
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

user.init();

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

function signOut(idx){

    if(confirm("정말로 탈퇴시키겠습니까?") == true) {
        $.ajax({
            type: 'DELETE',
            url: '/api/v1/signout/' + idx
        }).done(function () {
            alert("탈퇴시켰습니다.");
            window.location.reload();
        }).fail(function (error) {
            alert("작성한 모든 게시글을 삭제해야 탈퇴가 가능합니다.");
            window.location.reload();
        })
    }
}