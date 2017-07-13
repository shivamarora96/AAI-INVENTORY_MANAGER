package com.aai.inventorymanagement.Others;

public class Constants {

    public final static int USERTYPE_ADMIN = 1;
    public final static int USERTYPE_GENERAL = 2;
    public final static String USERTYPE_KEY = "user";

    public static final int ACTION_DELETE = 100;
    public static final int ACTION_VIEW = 2000;
    public static final int ACTION_UPDATE = 300;
    public static final int ACTION_ALLOCATED = 400;
    public static final int ACTION_ITEMREQUEST = 500;


    public final static String ADMIN_ID = "aai@gmail.com";
    public final static String ADMIN_PASSWORD = "aai12345";

    public final static String GENERAL_ID = "user@gmail.com";
    public final static String GENERAL_PASSWORD = "user12345";

    public final static String PREF_NAME = "AAI_IM";

    public final static String COLUMN_PRODUCTID = "PID";
    public final static String COLUMN_BATCHID = "BID";
    public final static String COLUMN_NAME = "N";
    public final static String COLUMN_QUANTITY = "Q";

    public final static String LOGINTOKEN_KEY = "loginToken";
    public final static boolean USER_LOGINED = true;
    public final static boolean USER_NOTLOGINED = false;

    public final static String BASE_URL = "http://inventorymanagement.pythonanywhere.com/api/";

    public final static String UPDATE_RESPONSE_SUCCESS = "inventory updated";
    public final static String ADD_RESPONSE_SUCCESS = "objects created";
    public final static String ITEMREQUEST_RESPONSE_SUCCESS = "items provided";

    public final static String APPLINK = "https://1drv.ms/u/s!Ar-Bx7RovGGoiHe_6BLVjAyLRj-a";







}
