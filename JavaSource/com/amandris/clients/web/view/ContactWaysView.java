package com.amandris.clients.web.view;import org.apache.struts.action.ActionForm;public class ContactWaysView extends ActionForm{		private String sellerId;	private String contactId;	private String sellerLogin;	private String allowEmail;	private String allowSms;	private String allowPhoneCall;	private String allowSkype;	private String allowMessenger;	private String email;	private String sms;	private String phoneCall;	private String skype;	private String messenger;		private static final long serialVersionUID = 7526421165623796030L;		public ContactWaysView()	{		sellerId		= "";		contactId		= "";		sellerLogin		= "";		allowEmail 		= "";		allowSms		= "";		allowPhoneCall	= "";		allowSkype		= "";		allowMessenger	= "";		email			= "";		sms				= "";		phoneCall		= "";		skype			= "";		messenger		= "";	}	public String getAllowEmail() {		return allowEmail;	}	public void setAllowEmail(String allowEmail) {		this.allowEmail = allowEmail;	}	public String getAllowMessenger() {		return allowMessenger;	}	public void setAllowMessenger(String allowMessenger) {		this.allowMessenger = allowMessenger;	}	public String getAllowPhoneCall() {		return allowPhoneCall;	}	public void setAllowPhoneCall(String allowPhoneCall) {		this.allowPhoneCall = allowPhoneCall;	}	public String getAllowSkype() {		return allowSkype;	}	public void setAllowSkype(String allowSkype) {		this.allowSkype = allowSkype;	}	public String getAllowSms() {		return allowSms;	}	public void setAllowSms(String allowSms) {		this.allowSms = allowSms;	}	public String getEmail() {		return email;	}	public void setEmail(String email) {		this.email = email;	}	public String getMessenger() {		return messenger;	}	public void setMessenger(String messenger) {		this.messenger = messenger;	}	public String getPhoneCall() {		return phoneCall;	}	public void setPhoneCall(String phoneCall) {		this.phoneCall = phoneCall;	}	public String getSkype() {		return skype;	}	public void setSkype(String skype) {		this.skype = skype;	}	public String getSms() {		return sms;	}	public void setSms(String sms) {		this.sms = sms;	}	public String getSellerId() {		return sellerId;	}	public void setSellerId(String sellerId) {		this.sellerId = sellerId;	}	public String getSellerLogin() {		return sellerLogin;	}	public void setSellerLogin(String sellerLogin) {		this.sellerLogin = sellerLogin;	}	public String getContactId() {		return contactId;	}	public void setContactId(String contactId) {		this.contactId = contactId;	}	}