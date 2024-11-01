<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="ko">
<%@ include file="/include/header.jspf"%>
<body>
<%@ include file="/include/navigation.jspf" %>

    <div class="container" id="main">
            <header class="qna-header">
                <h2 class="qna-title">${question.title}</h2>
            </header>
            <div class="content-main">
                <article class="article">
                    <div class="article-header">
                        <div class="article-header-thumb">
                            <img src="../img/picture.jpeg" class="article-author-thumb" alt="">
                        </div>
                        <div class="article-header-text">
                          <!-- 사용자 프로필 추가 할거면 span -> a 태그로 바꾸고 API 연결 -->
                            <span class="article-author-name">${question.writer}</span>
                            <span class="article-header-time">
                              ${question.createdDate}
                            </span>
                        </div>
                    </div>
                    <div class="article-doc">
                        <p>${question.contents}</p>
                    </div>
                    <div class="article-util">
                        <ul class="article-util-list">
                            <c:if test="${sessionScope.user.userId == question.writer}">
                                <li>
                                    <a class="link-modify-article" href="/qna/updateForm?questionId=${question.questionId}">수정</a>
                                </li>
                                <li>
                                    <form class="form-delete" action="/qna/delete" method="POST">
                                        <input type="hidden" name="_method" value="DELETE">
                                        <button class="link-delete-article" type="submit">삭제</button>
                                    </form>
                                </li>
                            </c:if>
                            <li>
                                <a class="link-modify-article" href="/">목록</a>
                            </li>
                        </ul>
                    </div>
                </article>
  
                <div class="qna-comment">
                    <div class="qna-comment-kuit">
                        <p class="qna-comment-count"><strong>${question.countOfAnswer}</strong>개의 의견</p>
                        <div>
                            <c:forEach items="${answers}" var="answer">
                                <article class="article" id="answer-1405">
                                    <div class="article-header">
                                        <div class="article-header-thumb">
                                            <img src="../img/picture.jpeg" class="article-author-thumb" alt="">
                                        </div>
                                        <div class="article-header-text">
                                            <span class="article-author-name">${answer.writer}</span>
                                            <span class="article-header-time">
                                           ${answer.createdDate}
                                        </span>
                                        </div>
                                    </div>
                                    <div class="article-doc comment-doc">
                                        <p>${answer.contents}</p>
                                    </div>
                                    <div class="article-util">
                                        <ul class="article-util-list">
                                            <li>
                                                <!-- 수정, 삭제 API 연결 필요 -->
                                                <a class="link-modify-article" href="/api/qna/updateAnswer?answerId=${answer.answerId}">수정</a>
                                            </li>
                                            <li>
                                                <!-- 수정, 삭제 API 연결 필요 -->
                                                <form class="delete-answer-form" action="/api/qna/deleteAnswer" method="POST">
                                                    <input type="hidden" name="_method" value="${answer.answerId}">
                                                    <button type="submit" class="delete-answer-button">삭제</button>
                                                </form>
                                            </li>
                                        </ul>
                                    </div>
                                </article>
                            </c:forEach>
                            <div class="answerWrite">
                                <form class="submit-write">
                                    <input type="hidden" name="questionId" value="${question.questionId}">
                                    <div class="form-group col-lg-4" style="padding-top:10px;">
                                        <input class="form-control" id="writer" name="writer" placeholder="이름"
                                               value="${sessionScope.user.name}" readonly>
                                    </div>
                                    <div class="form-group col-lg-12">
                                        <textarea name="contents" id="contents" class="form-control" placeholder=""></textarea>
                                    </div>
                                    <input class="btn btn-success pull-right" type="submit" value="답변하기">
                                    <div class="clearfix"/>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
    </div>

    <script type="text/template" id="answerTemplate">
        <article class="article">
            <div class="article-header">
                <div class="article-header-thumb">
                    <img src="/img/picture.jpeg" class="article-author-thumb" alt="">
                </div>
                <div class="article-header-text">
                    {0}
                    <div class="article-header-time">{1}</div>
                </div>
            </div>
            <div class="article-doc comment-doc">
                {2}
            </div>
            <div class="article-util">
                <ul class="article-util-list">
                    <li>
                        <a class="link-modify-article" href="/api/qna/updateAnswer/{3}">수정</a>
                    </li>
                    <li>
                        <form class="form-delete" action="/api/qna/deleteAnswer" method="POST">
                            <input type="hidden" name="answerId" value="{4}"/>
                            <button type="submit" class="link-delete-article">삭제</button>
                        </form>
                    </li>
                </ul>
            </div>
        </article>
    </script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="/js/scripts.js"></script>
  </body>
</html>