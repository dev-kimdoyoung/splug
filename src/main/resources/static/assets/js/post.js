var post = {
    init : function () {
        var _this = this;
        $('#save').on('click', function () {
            _this.save();
        });

        $('#post_update').on('click', function () {
            _this.update();
        });

        $('#post_delete').on('click', function () {
            _this.delete();
        });
    },

    save : function () {

        let userRoleType = $('#sessionUser_roleType').val();

        var data = {
            title: $('#title').val(),
            postType: $('#type').val(),
            content: CKEDITOR.instances.content.getData()
        };

        if(userRoleType != "ADMIN" && data.postType == "notice"){
            alert("공지사항은 관리자만 작성 가능합니다.");
            return;
        }

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

        let userRoleType = $('#sessionUser_roleType').val();

        var data = {
            postType: $('#type').val(),
            title: $('#title').val(),
            content: CKEDITOR.instances.content.getData()
        };

        var idx = $('#post_idx').val();

        if(userRoleType != "ADMIN" && data.postType == "notice"){
            alert("공지사항은 관리자만 작성 가능합니다.");
            return;
        }

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
        var idx = $('#post_idx').val();
        var postType = $('#post_type').val();

        if(!confirm("정말로 삭제하시겠습니까?")){
            return;
        }

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