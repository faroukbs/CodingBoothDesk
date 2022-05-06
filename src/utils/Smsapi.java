///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package utils;
//
//import com.twilio.Twilio;
//import com.twilio.rest.api.v2010.account.Message;
//import com.twilio.type.PhoneNumber;
//
///**
// *
// * @author bouss
// */
//public class Smsapi {
//    
//    
//     public static final String ACCOUNT_SID = "AC11a55670aa37b622eb17e124f3789c62";
//    public static final String AUTH_TOKEN = "375afaa1838c65d540583fe8aad0dc80";
//
//    public static void sendSMS( String msg) {
//        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//        Message message = Message.creator(new PhoneNumber("+21628608927"),new PhoneNumber("+13526772152"), msg).create();
//
//        System.out.println(message.getSid());
//
//    }
//}
//    
//
