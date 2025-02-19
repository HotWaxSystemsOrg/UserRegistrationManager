/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

// Retrieve partyId from parameters
partyId = parameters.partyId ?: request.getAttribute("partyId")
if (!partyId) {
    println("Missing required parameter: partyId")
    return
}
println("LOG: -----------------------partyId found = " + partyId)

// Search for Person
person = from("Person").where("partyId", partyId).queryOne()
if (!person) {
    println("LOG: person record not found")
    return
}
println("LOG: person record found")

// Store directly in context
context.firstName = person.firstName
context.lastName = person.lastName

// Search for UserLogin
userLogin = from("UserLogin").where("partyId", partyId).queryOne()
if (userLogin) {
    println("LOG: user login record found")
    context.userLoginId = userLogin.userLoginId
    context.password = userLogin.currentPassword
}

//================================================================================
// Search for PartyContactMechPurpose with PRIMARY_EMAIL
partyContactMechPurpose = from("PartyContactMechPurpose").where("partyId", partyId).queryList()

if (partyContactMechPurpose) {
    println("LOG: partyContactMechPurpose records found")
    partyContactMechPurpose.each { value ->
        switch (value.contactMechPurposeTypeId) {
            case "PRIMARY_EMAIL":
                contactMech = from("ContactMech").where("contactMechId", value.contactMechId).queryOne()
                if (contactMech) {
                    println("LOG: email contact mech found")
                    context.email = contactMech.infoString
                }
                break

            case "PHONE_HOME":
                contactMech = from("TelecomNumber").where("contactMechId", value.contactMechId).queryOne()
                if (contactMech) {
                    println("LOG: phone-home contact mech records found")
                    context.homePhoneNumber = contactMech.contactNumber
                }
                break

            case "PHONE_MOBILE":
                contactMech = from("TelecomNumber").where("contactMechId", value.contactMechId).queryOne()
                if (contactMech) {
                    println("LOG: phone-mobile contact mech records found")
                    context.mobilePhoneNumber = contactMech.contactNumber
                }
                break

            case "BILLING_LOCATION":
                contactMech = from("PostalAddress").where("contactMechId", value.contactMechId).queryOne()
                if (contactMech) {
                    println("LOG: billing contact mech records found")
                    context.address1B = contactMech.address1
                    context.cityB = contactMech.city
                    context.postalCodeB = contactMech.postalCode
                }
                break

            case "SHIPPING_LOCATION":
                contactMech = from("PostalAddress").where("contactMechId", value.contactMechId).queryOne()
                if (contactMech) {
                    println("LOG: shipping contact mech records found")
                    context.address1S = contactMech.address1
                    context.cityS = contactMech.city
                    context.postalCodeS = contactMech.postalCode
                }
                break

            default:
                println("LOG: partyContactMechPurposeId mismatch")
        }
    }
}

return "success"
//================================================================================
