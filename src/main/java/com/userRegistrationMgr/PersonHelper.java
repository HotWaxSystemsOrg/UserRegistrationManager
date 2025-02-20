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

public class PersonHelper{
//    ===============================================================================================
//    PERSON REGISTRATION HELPER SERVICES
    public static String createParty(Delegator delegator,  Map<String, ? extends Object> context) throws GenericEntityException {
        Map<String, Object> result = ServiceUtil.returnSuccess();
        String partyId = delegator.getNextSeqId("Party");
        GenericValue party = delegator.makeValue("Party");
        party.set("partyId", partyId);
        party.set("partyTypeId", "PERSON");
        delegator.create(party);

        createPerson(delegator, context, partyId);
        createUserLogin(delegator, context, partyId);

        result.put("partyId", partyId);
        result.put("firstName", firstName);
        result.put("lastName", lastName);
        return result;
    }

    private static void createPerson(Delegator delegator, Map<String, ? extends Object> context, String partyId) throws GenericEntityException {
        String firstName = (String) context.get("firstName");
        String lastName = (String) context.get("lastName");

        GenericValue person = delegator.makeValue("Person");
        person.set("partyId", partyId);
        person.set("firstName", firstName);
        person.set("lastName", lastName);
        delegator.create(person);
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
        String contactMechId = delegator.getNextSeqId("ContactMech");
        GenericValue contactMech = delegator.makeValue("ContactMech");
        contactMech.set("contactMechId", contactMechId);

        switch(purposeType){
            case "EMAIL_ADDRESS": createEmailContact(delegator,email); break;
            case "PHONE_HOME": case "PHONE_MOBILE": createPhoneContact(delegator,contactMechId,phoneNumber); break;
            case "BILLING_ADDRESS": createAddressContact(delegator,address1,city,postalCode); break;
            case "SHIPPING_ADDRESS": createAddressContact(delegator,address1,city,postalCode); break;
            default:    System.out.println("LOG: ----partyContactMechPurposeId mismatch");
        }
        createPartyContactMech(delegator, partyId, contactMechId, purposeType, fromDate);
    }
//  ============================================================================
//    CONTACT MECH ENTITIES CREATION METHODS
    public static void createEmailContact(Delegator delegator, String email) throws GenericEntityException {
        contactMech.set("contactMechTypeId", "EMAIL_ADDRESS");
        contactMech.set("infoString", email);
        delegator.create(contactMech);
    }

    public static void createPhoneContact(Delegator delegator, String contactMechId, String phoneNumber) throws GenericEntityException {
        contactMech.set("contactMechTypeId", "TELECOM_NUMBER");
        delegator.create(contactMech);

        GenericValue telecomNumber = delegator.makeValue("TelecomNumber");
        telecomNumber.set("contactMechId", contactMechId);
        telecomNumber.set("contactNumber", phoneNumber);
        delegator.create(telecomNumber);
    }

    //    ===============================================================================================
//    PARTY CONTACT MECH ENTITY CREATION METHODS
    public static void createPartyContactMech(Delegator delegator, String partyId, String contactMechId, String purposeType,
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
    public static void createAddressContact(Delegator delegator, String address1, String city, String postalCode, String purposeType,
                                            Timestamp fromDate) throws GenericEntityException {
        contactMech.set("contactMechTypeId", "POSTAL_ADDRESS");
        delegator.create(contactMech);

        GenericValue postalAddress = delegator.makeValue("PostalAddress");
        postalAddress.set("contactMechId", contactMechId);
        postalAddress.set("address1", address1);
        postalAddress.set("city", city);
        postalAddress.set("postalCode", postalCode);
        delegator.create(postalAddress);
    }
}