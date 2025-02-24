<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration Form</title>

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
        input[type='text'],
        input[type='password'] {
            width: 100%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 14px;
        }

        /* Placeholder styling */
        input::placeholder {
            color: #888;
            font-style: italic;
        }

        /* Submit button styling */
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

<h2>Registration Form</h2>

<form name='Registration' method="post" action="<@ofbizUrl>RegisterUser</@ofbizUrl>">
    <table cellspacing='0'>
        <tr>
            <td><label>${uiLabelMap.First_Name}</label></td>
            <td><input type='text' name='firstName' placeholder="Enter first name here"/></td>
        </tr>
        <tr>
            <td><label>${uiLabelMap.Last_Name}</label></td>
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
        <tr><td colspan="2"><hr /></td></tr>
        <tr>
            <td></td>
            <td><input type='submit' value='Submit'/></td>
        </tr>
    </table>
</form>

</body>
</html>