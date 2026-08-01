<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
    <h1>This is the signup page</h1>
    <form:form action="done" modelAttribute="car">
        Car Number: <form:input path="registerationNumber"></form:input>
        <br>
        Car Name: 
        <form:select path="carName">
            <form:option value="Seltos">Seltos</form:option>
        </form:select>
        <br>
        Covered In Warranty:
        <form:select path="carDetails">
            <form:option value="YES">YES</form:option>
            <form:option value="NO">NO</form:option>
        </form:select>
        <br>
        Any remarks: <form:input path="carWork"></form:input>
        <input type="submit">
    </form:form>
</html>
