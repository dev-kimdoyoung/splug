var post = {
    init : function () {
        var _this = this;
        $('#save').on('click', function () {
            _this.save();
        });

        $('#update').on('click', function () {
            _this.update();
        });

        $('#delete').on('click', function () {
            _this.delete();
        });
    },

    save : function () {

        var data = {
            title: $('#title').val(),
            postType: $('#type').val(),
            content: CKEDITOR.instances.content.getData()
        };

        if(data.postType == "--게시판--"){
            alert("게시판을 선택해주세요");
            return;
        }
        else if(data.title.length < 1){
            alert("제목을 입력해주세요");
            return;
        }
        else if(data.content.length < 1){
            alert("내용을 입력해주세요");
            return;
        }

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert("글이 등록되었습니다.");
            window.location.href = '/'+ $('#type option:selected').val();
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
            url: '/api/v1/posts/'+idx,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 수정되었습니다.');
            window.location.href = '/'+ data.postType;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    delete : function () {
        var idx = $('#idx').val();
        var postType = $('#type').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/posts/'+idx,
            dataType: 'json',
            contentType:'application/json; charset=utf-8'
        }).done(function() {
            alert('글이 삭제되었습니다.');
            window.location.href = '/' + postType;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
};

post.init();