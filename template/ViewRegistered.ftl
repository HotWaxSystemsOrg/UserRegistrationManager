<form name='PersonInformation' method="get" action="<@ofbizUrl>ViewRegistered</@ofbizUrl>">
    <table cellspacing='0'>
        <tr>
            <td><label>Party ID:  </label></td>
            <td><input type="text" name="partyId" readonly value="${requestAttributes.partyId!}"></td>
        </tr>
        <tr>
            <td><label>First Name:  </label></td>
            <td><input type='text' name='firstName' readonly value='${context.firstName!}'/></td>
        </tr>
        <tr>
            <td><label>Last Name:  </label></td>
            <td><input type='text' name='lastName' readonly value='${context.lastName!}'/></td>
        </tr>
        <tr>
            <td><label>User Login ID:  </label></td>
            <td><input type="text" name="userLoginId" readonly value="${context.userLoginId!}"></td>
        </tr>
        <tr>
            <td><label>Password:  </label></td>
            <td><input type="password" name="password" readonly value="${context.password!}"></td>
        </tr>
        <tr>
            <td><label>E-Mail Address:  </label></td>
            <td><input type="text" name="email" readonly value="${context.email!}"/></td>
        </tr>
        <tr>
            <td><label>Home Phone Number:  </label></td>
            <td><input type="text" name="homePhoneNumber" readonly value="${context.homePhoneNumber!}"/></td>
        </tr>
        <tr>
            <td><label>Mobile Phone Number:  </label></td>
            <td><input type='text' name='mobilePhoneNumber' readonly value="${context.mobilePhoneNumber!}"/></td>
        </tr>
        <tr>
            <td><label>Billing Address:  </label></td>
            <td><input type='text' name='address1B' readonly value="${context.address1B!}"/></td>
            <td><input type='text' name='cityB' readonly value="${context.cityB!}"/></td>
            <td><input type='text' name='postalCodeB' readonly value="${context.postalCodeB!}"/></td>
        </tr>
        <tr>
            <td><label>Shipping Address:  </label></td>
            <td><input type='text' name='address1S' readonly value="${context.address1S!}"/></td>
            <td><input type='text' name='cityS' readonly value="${context.cityS!}"/></td>
            <td><input type='text' name='postalCodeS' readonly value="${context.postalCodeS!}"/></td>
        </tr>
        <tr><td colspan="1" rowspan="1"><hr /></td></tr>
    </table>
</form>
