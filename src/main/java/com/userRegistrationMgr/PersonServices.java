package com.userRegistrationMgr;

import org.apache.ofbiz.base.util.UtilDateTime;
import java.sql.Timestamp;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.service.DispatchContext;
import org.apache.ofbiz.service.GenericServiceException;
import org.apache.ofbiz.service.ServiceUtil;
import org.apache.ofbiz.entity.GenericValue;
import com.userRegistrationMgr.*;

import java.util.Map;
import java.util.HashMap;
import java.util.List;



public class PersonServices{

//    =============================================================================================
//    REGISTER PERSON METHOD
    public static Map<String, Object> registerPerson(DispatchContext dispatchContext, Map<String, ? extends Object> context) {
        Map<String, Object> result = ServiceUtil.returnSuccess();
        Delegator delegator = dispatchContext.getDelegator();

        try {
            String firstName = (String) context.get("firstName");
            String lastName = (String) context.get("lastName");
            String userLoginId = (String) context.get("userLoginId");
            String password = (String) context.get("password");

            String partyId = PersonHelper.createParty(delegator);
            PersonHelper.createPerson(delegator, partyId, firstName, lastName);
            PersonHelper.createUserLogin(delegator, partyId, userLoginId, password);

            result.put("partyId", partyId);
            result.put("firstName", firstName);
            result.put("lastName", lastName);

        } catch (GenericEntityException e) {
            return ServiceUtil.returnError("Error registering person: " + e.getMessage());
        }
        return result;
    }

//    =============================================================================================
    public static Map<String, Object> setPersonalDetails(DispatchContext dispatchContext, Map<String, ? extends Object> context) {
        Map<String, Object> result = ServiceUtil.returnSuccess();
        Delegator delegator = dispatchContext.getDelegator();
        Timestamp fromDate = UtilDateTime.nowTimestamp();

        try {
            String partyId = (String) context.get("partyId");

            PersonHelper.createEmailContact(delegator, partyId, (String) context.get("email"), fromDate);
            PersonHelper.createPhoneContact(delegator, partyId, (String) context.get("homePhoneNumber"), "PHONE_HOME", fromDate);
            PersonHelper.createPhoneContact(delegator, partyId, (String) context.get("mobilePhoneNumber"), "PHONE_MOBILE", fromDate);
            PersonHelper.createAddressContact(delegator, partyId, (String) context.get("address1B"), (String) context.get("cityB"), (String) context.get("postalCodeB"), "BILLING_LOCATION", fromDate);
            PersonHelper.createAddressContact(delegator, partyId, (String) context.get("address1S"), (String) context.get("cityS"), (String) context.get("postalCodeS"), "SHIPPING_LOCATION", fromDate);

            result.put("partyId", partyId);
            return result;
        } catch (GenericEntityException e) {
            return ServiceUtil.returnError("Error creating person or user login: " + e.getMessage());
        }
    }
}