<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>카테고리 관리자 페이지</title>
<script src="//code.jquery.com/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		$(document).on('click','.deleteBtn',function deleteCategory(event){
			var parent=$(this).parent();
			var id = $(parent).data("id");
			console.log(id);
			$.ajax({
				url:"http://localhost:8080/category/"+id,
				type:"DELETE",
				success : function (){
					console.log($(this));
					$(parent).remove();
				}
			});
		});
		
		$(document).on('click','#createBtn',function createCategory(event){
			if($("#categoryInputName").val()==''){
				alert("카테고리 이름을 입력해주세요 :(");
			}else{
			var queryString=$("form[name=categoryInputForm]").serialize();
			$.ajax({
				type:'Get',
				url:'/category/create',
				data : queryString,
				dataType : 'json',
				success:function(data){
					console.log(data);
					$('#categoryTable').append('<tr data-id="'+data.id+'">'
							+'<td width="30">'+data.id+'</td>'
							+'<td width="300">'
							+'<input type="text" data-id="'+data.id+'" value="'+data.name+'" size="50" name="name"/>'
							+'</td>'
							+'<td width="50" class="deleteBtn"><button>delete</button></td>'
							+'<td width="50" class="updateBtn"><button>update</button></td>'
						+'</tr>');
					$("#categoryInputName").val("");
				},
				error: function(){
	                alert("이미있는 이름입니다:(");
	            }
			});
			}
		});
		$(document).on('click','.updateBtn',function deleteCategory(event){
			var parent=$(this).parent();
			var id = $(parent).data("id");
			var inputTag=$("input[data-id="+id+"]");
			var name=inputTag.val();
			var data=new Object();
			data.name=name;
		
			if(name==''){
				alert("카테고리 이름을 입력해주세요 :(");
			}else{
			$.ajax({
				url:"http://localhost:8080/category/"+id,
				type:"PUT",
				contentType:"application/json",
				data:JSON.stringify(data),
				success : function (){
					alert("수정완료:)");
				},
				error: function(){
	                alert("이미있는 이름입니다:(");
	            }
			});
			}
		});	

	});
	
</script>
</head>
<body>
<center>
	<form name="categoryInputForm">
		<input type="text" name="name" id="categoryInputName"/> <input id="createBtn" type="button"
			value="카테고리 등록" />
	</form>
	<br>
	<br>
	<br> 카테고리 테이블
	<br>
	<table id="categoryTable" border="1" width="450">
	<c:forEach var="list_one" items="${list}">
			<tr data-id="${list_one.id}">
				<td width="30">${list_one.id}</td>
				<td width="300" class="cateName">
					<input data-id="${list_one.id}" value="${list_one.name}" type="text" size="50" name="name" />
				</td>
				<td width="50" class="deleteBtn"><button>delete</button></td>
				<td width="50" class="updateBtn"><button>update</button></td>
			</tr>
	</c:forEach>
	</table></center>
</body>
</html>