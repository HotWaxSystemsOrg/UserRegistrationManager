package com.userRegistrationMgr;
import org.apache.ofbiz.base.util.UtilDateTime;
import org.apache.ofbiz.entity.Delegator;
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

public class PersonHelper{
//    PERSON REGISTRATION HELPER SERVICES
    public static Map<String, Object> createParty(Delegator delegator, Map<String, ? extends Object> context) throws GenericEntityException {
        Map<String, Object> result = ServiceUtil.returnSuccess();
        String partyId = delegator.getNextSeqId("Party");
        GenericValue party = delegator.makeValue("Party");
        party.set("partyId", partyId);
        party.set("partyTypeId", "PERSON");
        delegator.create(party);

        createPerson(delegator,context,result,partyId);
        createUserLogin(delegator,context,partyId);
        return result;
    }

    private static void createPerson(Delegator delegator, Map<String, ? extends Object> context, Map<String, Object> result, String partyId) throws GenericEntityException {
        String firstName = (String) context.get("firstName");
        String lastName = (String) context.get("lastName");

        GenericValue person = delegator.makeValue("Person");
        person.set("partyId", partyId);
        person.set("firstName", firstName);
        person.set("lastName", lastName);
        delegator.create(person);

        result.put("partyId", partyId);
        result.put("firstName", firstName);
        result.put("lastName", lastName);
    }

    private static void createUserLogin(Delegator delegator, Map<String, ? extends Object> context, String partyId)
            throws GenericEntityException {
        String userLoginId = (String) context.get("userLoginId");
        String password = (String) context.get("password");

        GenericValue userLogin = delegator.makeValue("UserLogin");
        userLogin.set("partyId", partyId);
        userLogin.set("userLoginId", userLoginId);
        userLogin.set("currentPassword", password);
        delegator.create(userLogin);
    }
//    ===============================================================================================
//    PERSONAL DETAILS HELPER SERVICES
    public static void createContactMech(Delegator delegator, String partyId, String email, String phoneNumber, String address1,
                                         String city, String postalCode, String purposeType, Timestamp fromDate) throws GenericEntityException {
        String table = "", contactMechId = delegator.getNextSeqId("ContactMech");
        GenericValue contactMech = delegator.makeValue("ContactMech");
        contactMech.set("contactMechId", contactMechId);

        switch(purposeType){
            case "PRIMARY_EMAIL":
                contactMech.set("contactMechTypeId", "EMAIL_ADDRESS");
                contactMech.set("infoString", email); break;
            case "PHONE_HOME": case "PHONE_MOBILE":
                contactMech.set("contactMechTypeId", "TELECOM_NUMBER");
                table = "TelecomNumber"; break;
            case "BILLING_ADDRESS": case "SHIPPING_ADDRESS":
                contactMech.set("contactMechTypeId", "POSTAL_ADDRESS");
                table = "PostalAddress"; break;
            default: System.out.println("LOG: ----partyContactMechPurposeId mismatch");
        }
        delegator.create(contactMech);

        GenericValue entity = delegator.makeValue(table);
        entity.set("contactMechId", contactMechId);
        if(table.equals("TELECOM_NUMBER")){
            entity.set("contactNumber", phoneNumber);
        }
        else if (table.equals("POSTAL_ADDRESS")) {
            entity.set("address1", address1);
            entity.set("city", city);
            entity.set("postalCode", postalCode);
        }
        delegator.create(entity);
        createPartyContactMech(delegator,partyId,contactMechId,purposeType,fromDate);
    }
//    PARTY CONTACT MECH AND PARTY CONTACT MECH PURPOSE ENTITY CREATION
    private static void createPartyContactMech(Delegator delegator, String partyId, String contactMechId, String purposeType,
                                              Timestamp fromDate) throws GenericEntityException {
        GenericValue partyContactMech = delegator.makeValue("PartyContactMech");
        partyContactMech.set("partyId", partyId);
        partyContactMech.set("contactMechId", contactMechId);
        partyContactMech.set("fromDate", fromDate);
        delegator.create(partyContactMech);

        GenericValue partyContactMechPurpose = delegator.makeValue("PartyContactMechPurpose");
        partyContactMechPurpose.set("partyId", partyId);
        partyContactMechPurpose.set("contactMechId", contactMechId);
        partyContactMechPurpose.set("fromDate", fromDate);
        partyContactMechPurpose.set("contactMechPurposeTypeId", purposeType);
        delegator.create(partyContactMechPurpose);
    }
}