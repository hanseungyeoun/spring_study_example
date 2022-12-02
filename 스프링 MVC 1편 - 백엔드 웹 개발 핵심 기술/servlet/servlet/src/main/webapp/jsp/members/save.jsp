<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="hello.servlet.domain.Member" %>
<%@ page import="hello.servlet.domain.MemberRepository" %>
<%
    //request, reponse 는 사용 사능
    MemberRepository memberRepository = MemberRepository.getInstance();
    String userName = request.getParameter("username");
    int age =  Integer.parseInt(request.getParameter("age"));

    Member member = new Member(userName, age);
    memberRepository.save(member);
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    성공
    <ul>
        <li>id=<%=member.getId()%></li>
        <li>userame=<%=member.getUsername()%></li>
        <li>age=<%=member.getAge()%></li>
    </ul>
    <a href="/index.html">메인</a>
</body>
</html>
