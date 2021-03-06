<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ include file="/include/tags.jspf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="/include/header.jspf"%>
</head>
<body>
	<div id="header">
		<div id="title">
			<h2>
				<a href="/list.next">Java Web Programming 실습</a>
			</h2>
		</div>
	</div>

	<div id="main">
		<div class="post">
			<h2 class="post-title">
				<a href="">${question.title}</a>
			</h2>
			<div class="post-metadata">
				<span class="post-author">${question.writer}</span>, <span
					class="post-date"><fmt:formatDate
						pattern="yyyy-MM-dd HH:mm:ss" value="${question.createdDate}" /></span>
			</div>
			<div class="post-content">
				<div class="about">내용 :</div>
				${nf:hbr(question.contents)}
			</div>
		</div>

		<div class="editQuestion">
			<button style="width: 50px; height: 27px; background-color: white;">
				<a href="/edit.next?questionId=${question.questionId}"> 수정 </a>
			</button>
		</div>

		<div class="delQuestion">
			<button style="width: 50px; height: 27px; background-color: pink;">
				<a href="/delete.next?questionId=${question.questionId}"> 삭제 </a>
			</button>
		</div>
		<br /> <a href="/list.next">목록으로</a>

		<h3>답변</h3>
		<div class="answerWrite">
			<form method="post">
				<input type="hidden" name="questionId"
					value="${question.questionId}">
				<p>
					<label for="author">이름: </label> <input type="text" name="writer"
						id="writer" />
				</p>
				<p>
					<label for="content">내용 : </label>
					<textarea name="content" id="content"></textarea>
				</p>
				<p>
					<input type="submit" value="저장" />
				</p>
			</form>
		</div>

		<!-- comments start -->
		<div class="comments">
			<h3>댓글 수 : ${question.countOfComment}</h3>

			<c:forEach var="each" items="${answers}">
				<div class="comment">
					<div class="comment-metadata">
						<span class="comment-author"> ${each.writer}, </span> <span
							class="comment-date"> ${each.createdDate} </span>
					</div>
					<div class="comment-content">
						<div class="about">내용 :</div>
						${each.contents}
					</div>
					<div class="delBtn">
						<a href="#">삭제</a>
					</div>
					<div class="delInfo" style="display:none;">
						<ul>
							<li>${each.answerId}</li>
							<li>${each.questionId}</li>
						</ul>
					</div>
				</div>
			</c:forEach>
		</div>
		<%@ include file="/include/footer.jspf"%>
</body>
</html>