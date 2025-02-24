<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Registered Information</title>

    <!-- Internal CSS -->
    <style>
        /* General form styling */
        form {
            background: #f8f9fa;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
            max-width: 600px;
            margin: 20px auto;
            font-family: Arial, sans-serif;
        }

        /* Table styles */
        table {
            width: 100%;
            border-collapse: collapse;
        }

        td {
            padding: 10px;
        }

        /* Label styling */
        label {
            font-weight: bold;
            color: #333;
            display: block;
            margin-bottom: 5px;
        }

        /* Input fields styling */
        input[type='text'], input[type='password'] {
            width: 100%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 14px;
        }

        /* Readonly input styling */
        input[readonly] {
            background-color: #e9ecef;
            color: #6c757d;
            border: 1px solid #ced4da;
            cursor: not-allowed;
        }

        /* Placeholder styling */
        input::placeholder {
            color: #888;
            font-style: italic;
        }

        /* Responsive Design */
        @media (max-width: 600px) {
            form {
                padding: 15px;
            }
            td {
                display: block;
                width: 100%;
            }
        }
    </style>
</head>
<body>

<h2>View Registered Information</h2>

<form name='PersonInformation' method="get" action="<@ofbizUrl>ViewRegistered</@ofbizUrl>">
    <table cellspacing='0'>
        <tr>
            <td><label>${uiLabelMap.Party_ID}</label></td>
            <td><input type="text" name="partyId" readonly value="${requestAttributes.partyId!}"></td>
        </tr>
        <tr>
            <td><label>${uiLabelMap.First_Name}</label></td>
            <td><input type='text' name='firstName' readonly value='${firstName!}'/></td>
        </tr>
        <tr>
            <td><label>${uiLabelMap.Last_Name}</label></td>
            <td><input type='text' name='lastName' readonly value='${lastName!}'/></td>
        </tr>
        <tr>
            <td><label>${uiLabelMap.User_Login_ID}</label></td>
            <td><input type="text" name="userLoginId" readonly value="${userLoginId!}"></td>
        </tr>
        <tr>
            <td><label>${uiLabelMap.Password}</label></td>
            <td><input type="password" name="password" readonly value="${password!}"></td>
        </tr>
        <tr>
            <td><label>${uiLabelMap.Email_Address}</label></td>
            <td><input type="text" name="email" readonly value="${email!}"/></td>
        </tr>
        <tr>
            <td><label>${uiLabelMap.Home_Phone_Number}</label></td>
            <td><input type="text" name="homePhoneNumber" readonly value="${homePhoneNumber!}"/></td>
        </tr>
        <tr>
            <td><label>${uiLabelMap.Mobile_Phone_Number}</label></td>
            <td><input type='text' name='mobilePhoneNumber' readonly value="${mobilePhoneNumber!}"/></td>
        </tr>
        <tr>
            <td><label>${uiLabelMap.Billing_Address}</label></td>
            <td><input type='text' name='address1B' readonly value="${address1B!}"/></td>
            <td><input type='text' name='cityB' readonly value="${cityB!}"/></td>
            <td><input type='text' name='postalCodeB' readonly value="${postalCodeB!}"/></td>
        </tr>
        <tr>
            <td><label>${uiLabelMap.Shipping_Address}</label></td>
            <td><input type='text' name='address1S' readonly value="${address1S!}"/></td>
            <td><input type='text' name='cityS' readonly value="${cityS!}"/></td>
            <td><input type='text' name='postalCodeS' readonly value="${postalCodeS!}"/></td>
        </tr>
        <tr><td colspan="4"><hr /></td></tr>
    </table>
</form>

</body>
</html>
