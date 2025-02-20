package com.userRegistrationMgr;
import org.apache.ofbiz.base.util.UtilDateTime;
import java.sql.Timestamp;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.service.DispatchContext;
import org.apache.ofbiz.service.GenericServiceException;
import org.apache.ofbiz.service.ServiceUtil;
import org.apache.ofbiz.entity.GenericValue;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class PersonServices{
//    =============================================================================================
//    REGISTER PERSON WORKER SERVICE
    public static Map<String, Object> registerPerson(DispatchContext dispatchContext, Map<String, ? extends Object> context) {
        Map<String, Object> result = ServiceUtil.returnSuccess();
        Delegator delegator = dispatchContext.getDelegator();
        try {
            return PersonHelper.createParty(delegator, context);
        } catch (GenericEntityException e) {
            return ServiceUtil.returnError("Error registering person: " + e.getMessage());
        }
    }
//    =============================================================================================
//    PERSONAL DETAILS WORKER SERVICE
    public static Map<String, Object> setPersonalDetails(DispatchContext dispatchContext, Map<String, ? extends Object> context) {
        Map<String, Object> result = ServiceUtil.returnSuccess();
        Delegator delegator = dispatchContext.getDelegator();
        Timestamp fromDate = UtilDateTime.nowTimestamp();
        try {
            String partyId = (String) context.get("partyId");
            PersonHelper.createContactMech(delegator, partyId, (String) context.get("email"), null, null, null, null,"PRIMARY_EMAIL",fromDate);
            PersonHelper.createContactMech(delegator, partyId, null, (String) context.get("homePhoneNumber"), null, null, null,"PHONE_HOME",fromDate);
            PersonHelper.createContactMech(delegator, partyId, (String) context.get("mobilePhoneNumber"), null, null, null,"PHONE_MOBILE",fromDate);
            PersonHelper.createContactMech(delegator, partyId, null, null, (String) context.get("address1B"), (String) context.get("cityB"),(String) context.get("postalCodeB"), "BILLING_LOCATION", fromDate);
            PersonHelper.createContactMech(delegator, partyId, null, null, (String) context.get("address1S"), (String) context.get("cityS"),(String) context.get("postalCodeS"), "SHIPPING_LOCATION", fromDate);
            result.put("partyId", partyId);
            return result;
        } catch (GenericEntityException e) {
            return ServiceUtil.returnError("Error creating person or user login: " + e.getMessage());
        }
    }
}