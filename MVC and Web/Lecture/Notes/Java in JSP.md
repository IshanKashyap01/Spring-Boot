# Java in JSP

```jsp
<% String a = "Hello world!"%>
<!-- Hello world! will be displayed in the page -->
<%= a%>
<%
    ArrayList<User> users = (ArrayList<User>) request.getAttribute("users");
    for(User user : users)
    {
        <% user: <%=user.getName()%> %>
    }
%>
```

| Syntax       | Name            | Purpose                                                            |
| ------------ | --------------- | ------------------------------------------------------------------ |
| `<% ... %>`  | **Scriptlet**   | Execute Java statements                                            |
| `<%= ... %>` | **Expression**  | Output the value of a Java expression                              |
| `<%! ... %>` | **Declaration** | Declare fields or methods in the generated servlet                 |
| `<%@ ... %>` | **Directive**   | Provide instructions to the JSP container (e.g., `page`, `taglib`) |
