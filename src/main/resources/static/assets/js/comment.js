var comment = {
    init : function () {
        var _this = this;
        $('#comment_save').on('click', function () {
            _this.save();
        });

        $('#comment_update').on('click', function () {
            _this.update();
        });

        $('#comment_delete').on('click', function () {
            _this.delete();
        });
    },

    save : function () {
        var data = {
            postIdx: $('#post_idx').val(),
            content: $('#post_comment').val()
        };

        if(data.content == ""){
            alert("댓글을 입력해주세요");
            return;
        }

        data.content = data.content.replace("/r/n", "<br>");
        data.content = data.content.replace("/n", "<br>");

        $.ajax({
            type: 'POST',
            url: '/api/v1/comment',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert("댓글이 등록되었습니다.");
            window.location.reload();
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    update : function () {
        var data = {
            postType: $('#type').val(),
            title: $('#title').val(),
            content: editor.getData()
        };

        var idx = $('#idx').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/comment/'+idx,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('수정되었습니다.');
            window.location.href = '/'+ data.postType;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    delete : function () {

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/comment/'+idx,
            dataType: 'json',
            contentType:'application/json; charset=utf-8'
        }).done(function() {
            alert('삭제되었습니다.');
            window.location.reload();
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
};

comment.init();