$(".answerWrite input[type=submit]").click(addAnswer);

function addAnswer(e) {
    e.preventDefault();
    var queryString = $(".submit-write").serialize();

    $.ajax({
        type : 'post',
        url : '/api/qna/addAnswer',
        data : queryString,
        dataType : 'json', // 서버가 클라이언트에게 JSON으로 반환
        error: onError,
        success : onSuccess,
    });
}

function onSuccess(json, status){
    var answerTemplate = $("#answerTemplate").html();
    // json data를 동적으로 화면생성
    // answer로 한번 더 감싼다 (6주차 ppt참고, view 도입)
    var template = answerTemplate.format(json.answer.writer, new Date(json.answer.createdDate), json.answer.contents, json.answer.answerId, json.answer.answerId);
    // 새로 추가된 답변을 새로고침없이 사용자에게 보여준다.
    $(".qna-comment-kuit-articles").prepend(template);
    var countOfAnswer = document.getElementsByTagName("strong").item(0);
    let number = parseInt(countOfAnswer.innerText,10);
    number += 1;
    countOfAnswer.textContent = number.toString();
}

function onError(xhr, status) {
    alert("error");
}

String.prototype.format = function() {
    var args = arguments;
    return this.replace(/{(\d+)}/g, function(match, number) {
        return typeof args[number] != 'undefined'
            ? args[number]
            : match
            ;
    });
};