<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일일 가계부</title>
<script src='<c:url value="/js/jquery-3.4.1.js" />'></script>
<script>
	$(function()
	{
		$('#delbtn').on('click',dellist);
		$('#regbtn').on('click',reglist);
		$('#inputdate').on('change',setlist);
		$('#type').on('change',typesetlist);
		
		
		
		$('#amount').on('keyup',function()
		{
			var amount =$('#amount').val();
			if(isNaN(amount))
			{
				alert("숫자를 입력하세요");
				$('#amount').val('');
			}
		})
	})
	
	function typesetlist()
	{
		var date = $('#inputdate').val();
		if(date==''||date==null)
		{
			return false;
		} else 
		{
			$('#tbody').remove();
			setlist();
		}
		
	}
	
	function setlist()
	{
		$.ajax
		({
			url:'mylist',
			type:'post',
			data:{'inputdate':$('#inputdate').val(),'type':$('#type').val()},
			success:function(result)
			{
				$('#tbody').remove();
				$('#addpart').remove();
				if(result.length>0)
				{
					$('#listhead').append('<th id="addpart">삭제</th>');
					
					$('#listtable').append('<tbody id="tbody">');			
					$(result).each(function(index,item)
					{
						$('#listtable').append("<tr><td>"+
							result[index].memo+"</td><td>"+	
							result[index].amount+"</td><td><input type='radio' class='delradio' name='delradio' id='delradio' value='"+
							result[index].infonum+"'/></td>"
						)
				})
				$('#listtable').append('</tbody>');
				};
			},
			error:function(){alert("error");}
		})
	}
	
	function reglist()
	{
		var date=$('#inputdate').val();
		var memo=$('#memo').val();
		var amount=$('#amount').val();
		if(date==""||date==null)
		{
			alert("날짜를 입력하세요");
			return null;
		}
		if(memo==""||memo==null)
		{
			alert("메모를 입력하세요");
			return null;
		}
		if(amount==""||amount==null)
		{
			alert("금액을 입력하세요");
			return null;
		}
		
		$.ajax
		({
			url:'reglist',
			type:'post',
			data:$('#reglist').serialize(),
			success:function()
			{
				$('#tbody').remove();
				setlist();
				clear();
			},
			error:function()
			{
				alert("error");
			}
		})
	}
	var sendvalue = "null";
	function dellist()
	{
		var radios = $('.delradio');
		for(var i=0;i<radios.length;i++)
		{
			if(radios[i].checked==true)
			{
				sendvalue = radios[i].value;
			}
		}
		if(sendvalue=="null")
		{
			alert("삭제할 항목을 선택해주세요");
			return false;
		} 
		$.ajax({
			url:'dellist',
			type:'post',
			data:{'infonum':sendvalue},
			success:function()
			{	
				$('#tbody').remove();
				setlist();
				clear();
				sendvalue = "null";
			},
			error:function(){alert("error");}
		})
		
	}
	function clear()
	{
		$('#memo').val("");
		$('#amount').val("");
	}
</script>
<style>
	#listtable
	{
		border: solid;
		width:80%;
		left:10%;
		position:relative;
		margin-bottom: 5%;
	}
	.wrapper
	{
		border:double;
		text-align:center;
		width:40%;
		position:relative;
		left:30%;
	}
</style>
</head>
<body>
<div class="wrapper">
	<form id="reglist" action="reglist" method="post">
		<table id="regtable">
			<tr>
				<td>
					<select id="type" name="type">
						<option >수입</option>
						<option >지출</option>
					</select>
					<input id="inputdate" name="inputdate" type="date">
				</td>
			</tr>
			<tr>
				<th>
					메모
				</th>
				<th>
					금액
				</th>
			</tr>
			<tr>
				<td>
					<input type="text" id="memo" name="memo">
				</td>
				<td>
					<input type="text" id="amount" name="amount">
				</td>
				<td>
					<input type="button" id="regbtn" value="등록">
				</td>
				<td>
					<input type="button" id="delbtn" value="삭제">
				</td>
			</tr>
		</table>
	</form>
	<hr>
	<table id="listtable" style="border:solid;">
		<thead>
			<tr  id="listhead">
				<th>
					메모
				</th>
				<th>
					금액
				</th>
			</tr>
		</thead>
	</table>
</div>
</body>
</html>
