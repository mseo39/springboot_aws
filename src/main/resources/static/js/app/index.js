var main = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });

        $('#btn-update').on('click', function () {
            _this.update();
        });

        $('#btn-delete').on('click', function () {
            _this.delete();
        });
    },
    save : function () {
        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val(),
            age: $('#age').val(),
            gender: $("input[type=radio][name=gender]:checked").val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('등록 되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update : function () {
        var data = {
            title: $('#title').val(),
            content: $('#content').val()
        };

        var id = $('#id').val();
        
        
        //원래 저장되어있던 데이터를 가져옴
        var origin_data;

        $.ajax({
            type: "GET",
            url:'/api/v1/posts/'+id,
            dataType: 'json'
        }).done(function(data) {

            //원본 내용과 같다면 예외를 발생
            if (data['title']==$('#title').val() && data['content']==$('#content').val()) {
                alert('제목과 내용이 같습니다.');
                window.location.href = '/posts/update/'+id;
            }else{
                $.ajax({
                    type: 'PUT',
                    url: '/api/v1/posts/' + id,
                    dataType: 'json',
                    contentType: 'application/json; charset=utf-8',
                    data: JSON.stringify(data)
                }).done(function () {
                    alert('수정 되었습니다.');
                    window.location.href = '/';
                }).fail(function (error) {
                    alert(JSON.stringify(error));
                });
            }

        }).fail(function (error) {

            alert(JSON.stringify(error));

        });

        //alert(origin_data + " / " + $('#content').val())



    },
    delete : function () {
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8'
        }).done(function() {
            alert('삭제 되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }

};

main.init();