<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="ko">
<%@ include file="/include/header.jspf" %>
<body>
<%@ include file="/include/navigation.jspf" %>

<div class="container" id="main">
    <h2>Q&A</h2>
    <div class="qna-list">
        <ul class="list">
            <c:forEach items="${questions}" var="question">
                <li>
                    <div class="wrap">
                        <div class="main">
                            <strong class="subject">
                                <a href="/qna/show?questionId=${question.questionId}">${question.title}</a>
                            </strong>
                            <div class="auth-info">
                                <i class="icon-add-comment"></i>
                                <span class="time">${question.createdDate}</span>
                                <span clas="author">${question.writer}</span>
                                <!-- <a href="./user/profile.html" class="author">이영선</a> -->
                            </div>
                            <div class="reply" title="댓글">
                                <i class="icon-reply"></i>
                                <span class="point">${question.questionId}</span>
                            </div>
                        </div>
                    </div>
                </li>
            </c:forEach>
        </ul>
        <div class="row">
            <div class="col-md-5"></div>
            <div class="col-md-5">
                <ul class="pagination" style="display:align-items-center;">
                    <li class="page-item disabled">
                        <a class="page-link" href="#" tabindex="-1" aria-disabled="true">Previous</a>
                    </li>
                    <li class="page-item"><a class="page-link" href="#">1</a></li>
                    <li class="page-item active" aria-current="page">
                        <a class="page-link" href="#">2</a>
                    </li>
                    <li class="page-item"><a class="page-link" href="#">3</a></li>
                    <li class="page-item">
                        <a class="page-link" href="#">Next</a>
                    </li>
                </ul>
            </div>
            <div class="col-md-2 qna-write">
                <a href="/qna/form" class="btn btn-primary pull-right" role="button">질문하기</a>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<script src="js/scripts.js"></script>
</body>
</html>