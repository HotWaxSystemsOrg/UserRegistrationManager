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


public class PersonHelper{
//    ===============================================================================================
//    Person Registration Helper Methods
    public static String createParty(Delegator delegator) throws GenericEntityException {
        String partyId = delegator.getNextSeqId("Party");
        GenericValue party = delegator.makeValue("Party");
        party.set("partyId", partyId);
        party.set("partyTypeId", "PERSON");
        delegator.create(party);
        return partyId;
    }

    public static void createPerson
            (Delegator delegator, String partyId, String firstName, String lastName) throws GenericEntityException {
        GenericValue person = delegator.makeValue("Person");
        person.set("partyId", partyId);
        person.set("firstName", firstName);
        person.set("lastName", lastName);
        delegator.create(person);
    }

    public static void createUserLogin
            (Delegator delegator, String partyId, String userLoginId, String password) throws GenericEntityException {
        GenericValue userLogin = delegator.makeValue("UserLogin");
        userLogin.set("partyId", partyId);
        userLogin.set("userLoginId", userLoginId);
        userLogin.set("currentPassword", password);
        delegator.create(userLogin);
    }


//    ===============================================================================================
//    Personal Details Helper Methods
    public static void createEmailContact(Delegator delegator, String partyId, String email, Timestamp fromDate) throws GenericEntityException {
        String contactMechId = delegator.getNextSeqId("ContactMech");
        GenericValue contactMech = delegator.makeValue("ContactMech");
        contactMech.set("contactMechId", contactMechId);
        contactMech.set("contactMechTypeId", "EMAIL_ADDRESS");
        contactMech.set("infoString", email);
        delegator.create(contactMech);

        createPartyContactMech(delegator, partyId, contactMechId, "PRIMARY_EMAIL", fromDate);
    }

    public static void createPhoneContact(Delegator delegator, String partyId, String phoneNumber, String purposeType, Timestamp fromDate) throws GenericEntityException {
        String contactMechId = delegator.getNextSeqId("ContactMech");
        GenericValue contactMech = delegator.makeValue("ContactMech");
        contactMech.set("contactMechId", contactMechId);
        contactMech.set("contactMechTypeId", "TELECOM_NUMBER");
        delegator.create(contactMech);

        GenericValue telecomNumber = delegator.makeValue("TelecomNumber");
        telecomNumber.set("contactMechId", contactMechId);
        telecomNumber.set("contactNumber", phoneNumber);
        delegator.create(telecomNumber);

        createPartyContactMech(delegator, partyId, contactMechId, purposeType, fromDate);
    }

    public static void createAddressContact(Delegator delegator, String partyId, String address1, String city, String postalCode, String purposeType, Timestamp fromDate) throws GenericEntityException {
        String contactMechId = delegator.getNextSeqId("ContactMech");
        GenericValue contactMech = delegator.makeValue("ContactMech");
        contactMech.set("contactMechId", contactMechId);
        contactMech.set("contactMechTypeId", "POSTAL_ADDRESS");
        delegator.create(contactMech);

        GenericValue postalAddress = delegator.makeValue("PostalAddress");
        postalAddress.set("contactMechId", contactMechId);
        postalAddress.set("address1", address1);
        postalAddress.set("city", city);
        postalAddress.set("postalCode", postalCode);
        delegator.create(postalAddress);

        createPartyContactMech(delegator, partyId, contactMechId, purposeType, fromDate);
    }

    public static void createPartyContactMech(Delegator delegator, String partyId, String contactMechId, String purposeType, Timestamp fromDate) throws GenericEntityException {
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