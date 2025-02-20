<form name='PersonInformation' method="post" action="<@ofbizUrl>SetPersonalDetails</@ofbizUrl>">
    <table cellspacing='0'>
        <tr>
            <td><input type="hidden" name="partyId" value="${requestAttributes.partyId!}"></td>
        </tr>
        <tr>
            <td><label>${uiLabelMap.First_Name}</label></td>
            <td><input type='text' name='firstName' value='${requestAttributes.firstName!}'/></td>
        </tr>
        <tr>
            <td><label>${uiLabelMap.Last_Name}</label></td>
            <td><input type='text' name='lastName' value='${requestAttributes.lastName!}'/></td>
        </tr>
        <tr>
            <td><label>${uiLabelMap.Email_Address}</label></td>
            <td><input type='text' name='email' placeholder="Enter email address here"/></td>
        </tr>
        <tr>
            <td><label>${uiLabelMap.Home_Phone_Number}</label></td>
            <td><input type='text' name='homePhoneNumber' placeholder="Enter home phone number here"/></td>
        </tr>
        <tr>
            <td><label>${uiLabelMap.Mobile_Phone_Number}</label></td>
            <td><input type='text' name='mobilePhoneNumber' placeholder="Enter home phone number here"/></td>
        </tr>
        <tr>
            <td><label>${uiLabelMap.Billing_Address}</label></td>
            <td><input type='text' name='address1B' placeholder="Address"/></td>
            <td><input type='text' name='cityB' placeholder="City"/></td>
            <td><input type='text' name='postalCodeB' placeholder="Postal Code"/></td>
        </tr>
        <tr>
            <td><label>${uiLabelMap.Shipping_Address}</label></td>
            <td><input type='text' name='address1S' placeholder="Address"/></td>
            <td><input type='text' name='cityS' placeholder="City"/></td>
            <td><input type='text' name='postalCodeS' placeholder="Postal Code"/></td>
        </tr>
        <tr><td colspan="1" rowspan="1"><hr /></td></tr>
        <tr>
            <td></td>
            <td>
                <input type='submit' value='Submit'/>
            </td>
        </tr>
    </table>
</form>
