/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.authentication;

/**
 *
 * @author Dat
 */
public class Constants {
    //Dat
    	public static String GOOGLE_CLIENT_ID = "GOOGLE_CLIENT_ID";

	public static String GOOGLE_CLIENT_SECRET = "GOOGLE_CLIENT_SECRET";

	public static String GOOGLE_REDIRECT_URI = "http://localhost:2069/TheCardWebsite/loginwithgooglehandler";

	public static String GOOGLE_LINK_GET_TOKEN = "https://accounts.google.com/o/oauth2/token";

	public static String GOOGLE_LINK_GET_USER_INFO = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";

	public static String GOOGLE_GRANT_TYPE = "authorization_code";
}
