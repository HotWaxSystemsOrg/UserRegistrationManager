<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Personal Information Form</title>

    <style>
        form {
            background: #f8f9fa;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
            max-width: 600px;
            margin: 20px auto;
            font-family: Arial, sans-serif;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        td {
            padding: 10px;
        }

        label {
            font-weight: bold;
            color: #333;
            display: block;
            margin-bottom: 5px;
        }

        input[type='text'] {
            width: 100%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 14px;
        }

        input::placeholder {
            color: #888;
            font-style: italic;
        }

        input[type='submit'] {
            background-color: #007bff;
            color: white;
            border: none;
            padding: 10px 15px;
            cursor: pointer;
            border-radius: 4px;
            font-size: 16px;
            transition: background 0.3s ease;
        }

        input[type='submit']:hover {
            background-color: #0056b3;
        }

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

<h2>Personal Information Form</h2>

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
            <td><input type='text' name='email' placeholder="Email address"/></td>
        </tr>
        <tr>
            <td><label>${uiLabelMap.Home_Phone_Number}</label></td>
            <td><input type='text' name='homePhoneNumber' placeholder="Home-phone number"/></td>
        </tr>
        <tr>
            <td><label>${uiLabelMap.Mobile_Phone_Number}</label></td>
            <td><input type='text' name='mobilePhoneNumber' placeholder="Mobile-phone number"/></td>
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
        <tr><td colspan="4"><hr /></td></tr>
        <tr>
            <td></td>
            <td><input type='submit' value='Submit'/></td>
        </tr>
    </table>
</form>

</body>
</html>
