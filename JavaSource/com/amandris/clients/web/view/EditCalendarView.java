package com.amandris.clients.web.view;import java.util.ArrayList;import java.util.Date;import org.apache.struts.action.ActionForm;public class EditCalendarView extends ActionForm{		public class DateDate extends ActionForm {		private String hour;		private String status;		private String oddOrEven;				public DateDate()		{			hour 		= "";			status		= "";			oddOrEven	= "";		}				public String getHour() {			return hour;		}		public void setHour(String hour) {			this.hour = hour;		}		public String getOddOrEven() {			return oddOrEven;		}		public void setOddOrEven(String oddOrEven) {			this.oddOrEven = oddOrEven;		}		public String getStatus() {			return status;		}		public void setStatus(String status) {			this.status = status;		}	}		public class HourList extends ActionForm {		private String 		date;		private String 		briefDate;		private	Date		javaDate;		private ArrayList	hourList;				public HourList() 		{			date		= "";			briefDate	= "";			hourList	= new ArrayList();			javaDate	= new Date();		}				public String getDate() {			return date;		}		public void setDate(String date) {			this.date = date;		}		public ArrayList getHourList() {			return hourList;		}		public void setHourList(ArrayList hourList) {			this.hourList = hourList;		}		public Date getJavaDate() {			return javaDate;		}		public void setJavaDate(Date javaDate) {			this.javaDate = javaDate;		}		public String getBriefDate() {			return briefDate;		}		public void setBriefDate(String briefDate) {			this.briefDate = briefDate;		}	}			private ArrayList 	dateList; //Type HourList	private String		nextDate;	private String		previousDate;	private String		startDate;		private static final long serialVersionUID = 8261052357834501272L;		public EditCalendarView()	{		nextDate		= "";		previousDate 	= "";		startDate		= "";		dateList 		= new ArrayList();	}	public ArrayList getDateList() {		return dateList;	}	public void setDateList(ArrayList dateList) {		this.dateList = dateList;	}	public String getNextDate() {		return nextDate;	}	public void setNextDate(String nextDate) {		this.nextDate = nextDate;	}	public String getPreviousDate() {		return previousDate;	}	public void setPreviousDate(String previousDate) {		this.previousDate = previousDate;	}	public String getStartDate() {		return startDate;	}	public void setStartDate(String startDate) {		this.startDate = startDate;	}		}