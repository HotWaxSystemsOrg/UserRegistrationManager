<form name='Registration' method="post" action="<@ofbizUrl>RegisterUser</@ofbizUrl>">
    <table cellspacing='0' >
        <tr>
            <td><label>${uiLabelMap.First_Name}</label></td>
            <td><input type='text' name='firstName' placeholder="Enter first name here"/></td>
        </tr>
        <tr>
            <td><label> ${uiLabelMap.Last_Name}</label></td>
            <td><input type='text' name='lastName' placeholder="Enter last name here"/></td>
        </tr>
        <tr>
            <td><label>${uiLabelMap.User_Login_Id}</label></td>
            <td><input type='text' name='userLoginId' placeholder="Enter login id here"/></td>
        </tr>
        <tr>
            <td><label>${uiLabelMap.Password}</label></td>
            <td><input type='password' name='password' placeholder="Enter password here"/></td>
        </tr>
        <tr><td colspan="4" rowspan="1"><hr /></td></tr>
        <tr>
            <td></td>
            <td>
                <input type='submit' value='Submit'/>
            </td>
        </tr>
    </table>
</form>
