package com.amandris.clients.service.facade;import java.util.ArrayList;import java.util.Collection;import java.util.Date;import java.util.GregorianCalendar;import java.util.Iterator;import java.util.Locale;import com.amandris.clients.service.dao.CalendarDAO;import com.amandris.clients.service.dao.SellerDAO;import com.amandris.clients.service.dao.SellerServiceDAO;import com.amandris.clients.service.vo.Calendar;import com.amandris.clients.service.vo.Contact;import com.amandris.clients.service.vo.Seller;import com.amandris.clients.util.ConfigurationParameterManager;import com.amandris.clients.util.Constant;import com.amandris.clients.util.DBUtils;import com.amandris.clients.util.exception.DataAccessErrorException;import com.amandris.clients.util.exception.DataDeleteErrorException;import com.amandris.clients.util.exception.DataUpdateErrorException;import com.amandris.clients.util.exception.TranslateValueObjectErrorException;import com.amandris.clients.util.translator.SellerToSellerViewListTranslator;import com.amandris.clients.util.translator.TranslatorFactory;import com.amandris.clients.web.form.BuyerSellerAvailabilitySearchForm;import com.amandris.clients.web.form.CalendarForm;import com.amandris.clients.web.util.SellerSession;import com.amandris.clients.web.view.EditCalendarView;import com.amandris.clients.web.view.SellerView;import com.amandris.clients.web.view.SellerViewList;public class CalendarFacade {		public boolean setCalendar( CalendarForm calendarForm, SellerSession sellerSession, Locale locale) throws DataAccessErrorException, DataUpdateErrorException, DataDeleteErrorException	{		CalendarDAO 		calendarDAO 		= new CalendarDAO();		SellerDAO			sellerDAO			= new SellerDAO();		Collection			calendarToDelete	= null;		ArrayList			calendarToAdd		= new ArrayList();		Iterator			deleteIterator		= null;		Seller				seller				= null;		int					calendarSize 		= ConfigurationParameterManager.getParameterInt( "calendarSize");		Calendar			calendar			= null;		Calendar			calendarToCheck		= null;		java.util.Calendar 	today 				= java.util.Calendar.getInstance( locale);		java.util.Calendar	mainDate			= new GregorianCalendar( today.get( java.util.Calendar.YEAR), today.get( java.util.Calendar.MONTH), today.get( java.util.Calendar.DATE));		int					pattern				= DBUtils.parseInteger( calendarForm.getPattern(), 0).intValue();		int					startHour			= 0;		int					endHour				= 23;		int					startDay			= 2;		int					endDay				= 1; 				seller = sellerDAO.getSellerById( sellerSession.getId());				if( seller == null)			return false;				calendarToDelete = calendarDAO.getCalendarBySellerId( sellerSession.getId());				for( int i = 0; i < calendarSize; i++) {			for( int j = 0; j < 24; j++) {								calendarToCheck = null;				calendar 		= new Calendar();								calendar.setSeller( seller);				calendar.setSellerId( seller.getId());				calendar.setDate( mainDate.getTime());								calendarToCheck = calendarDAO.getCalendarBySellerIdAndDate( seller.getId(), mainDate.getTime());								if( calendarToCheck != null) {					if( calendarToCheck.getHourStatusConst().intValue() == Constant.HOUR_STATUS_DATE)						calendar.setHourStatusConst( new Integer( Constant.HOUR_STATUS_DATE));				}								if( calendar.getHourStatusConst() == null) {									switch( pattern) {						case Constant.CALENDAR_PATTERN_1:							calendar.setHourStatusConst( new Integer( Constant.HOUR_STATUS_FREE));							break;												case Constant.CALENDAR_PATTERN_2:							startHour	= DBUtils.parseInteger( calendarForm.getStartHour2(), 0).intValue();							endHour		= DBUtils.parseInteger( calendarForm.getEndHour2(), 23).intValue();														if( startHour <= endHour){								if( ( mainDate.get( java.util.Calendar.HOUR_OF_DAY) >= startHour) && ( mainDate.get( java.util.Calendar.HOUR_OF_DAY) <= endHour))									calendar.setHourStatusConst( new Integer( Constant.HOUR_STATUS_FREE));								else									calendar.setHourStatusConst( new Integer( Constant.HOUR_STATUS_NOT_FREE));							} else {								if( ( mainDate.get( java.util.Calendar.HOUR_OF_DAY) >= startHour) || ( mainDate.get( java.util.Calendar.HOUR_OF_DAY) <= endHour))									calendar.setHourStatusConst( new Integer( Constant.HOUR_STATUS_FREE));								else									calendar.setHourStatusConst( new Integer( Constant.HOUR_STATUS_NOT_FREE));							}														seller.setCalendarPatternStartHour	( new Integer( startHour));							seller.setCalendarPatternEndHour	( new Integer( endHour));							break;													case Constant.CALENDAR_PATTERN_3:							if( ( mainDate.get( java.util.Calendar.DAY_OF_WEEK) >= java.util.Calendar.MONDAY) && ( mainDate.get( java.util.Calendar.DAY_OF_WEEK) <= java.util.Calendar.FRIDAY))								calendar.setHourStatusConst( new Integer( Constant.HOUR_STATUS_FREE));							else								calendar.setHourStatusConst( new Integer( Constant.HOUR_STATUS_NOT_FREE));							break;												case Constant.CALENDAR_PATTERN_4:							startHour	= DBUtils.parseInteger( calendarForm.getStartHour4(), 0).intValue();							endHour		= DBUtils.parseInteger( calendarForm.getEndHour4(), 23).intValue();														if( startHour <= endHour){								if( ( mainDate.get( java.util.Calendar.HOUR_OF_DAY) >= startHour) && ( mainDate.get( java.util.Calendar.HOUR_OF_DAY) <= endHour))									calendar.setHourStatusConst( new Integer( Constant.HOUR_STATUS_FREE));								else									calendar.setHourStatusConst( new Integer( Constant.HOUR_STATUS_NOT_FREE));							} else {								if( ( mainDate.get( java.util.Calendar.HOUR_OF_DAY) >= startHour) || ( mainDate.get( java.util.Calendar.HOUR_OF_DAY) <= endHour))									calendar.setHourStatusConst( new Integer( Constant.HOUR_STATUS_FREE));								else									calendar.setHourStatusConst( new Integer( Constant.HOUR_STATUS_NOT_FREE));							}							seller.setCalendarPatternStartHour	( new Integer( startHour));							seller.setCalendarPatternEndHour	( new Integer( endHour));							break;													case Constant.CALENDAR_PATTERN_5:							if( ( mainDate.get( java.util.Calendar.DAY_OF_WEEK) == java.util.Calendar.SATURDAY) || ( mainDate.get( java.util.Calendar.DAY_OF_WEEK) == java.util.Calendar.SUNDAY))								calendar.setHourStatusConst( new Integer( Constant.HOUR_STATUS_FREE));							else								calendar.setHourStatusConst( new Integer( Constant.HOUR_STATUS_NOT_FREE));							break;													case Constant.CALENDAR_PATTERN_6:							startHour	= DBUtils.parseInteger( calendarForm.getStartHour6(), 0).intValue();							endHour		= DBUtils.parseInteger( calendarForm.getEndHour6(), 23).intValue();														if( ( mainDate.get( java.util.Calendar.DAY_OF_WEEK) == java.util.Calendar.SATURDAY) || ( mainDate.get( java.util.Calendar.DAY_OF_WEEK) == java.util.Calendar.SUNDAY)) {								if( startHour <= endHour){									if( ( mainDate.get( java.util.Calendar.HOUR_OF_DAY) >= startHour) && ( mainDate.get( java.util.Calendar.HOUR_OF_DAY) <= endHour))										calendar.setHourStatusConst( new Integer( Constant.HOUR_STATUS_FREE));									else										calendar.setHourStatusConst( new Integer( Constant.HOUR_STATUS_NOT_FREE));								} else {									if( ( mainDate.get( java.util.Calendar.HOUR_OF_DAY) >= startHour) || ( mainDate.get( java.util.Calendar.HOUR_OF_DAY) <= endHour))										calendar.setHourStatusConst( new Integer( Constant.HOUR_STATUS_FREE));									else										calendar.setHourStatusConst( new Integer( Constant.HOUR_STATUS_NOT_FREE));								}							} else								calendar.setHourStatusConst( new Integer( Constant.HOUR_STATUS_NOT_FREE));														seller.setCalendarPatternStartHour	( new Integer( startHour));							seller.setCalendarPatternEndHour	( new Integer( endHour));							break;												case Constant.CALENDAR_PATTERN_7:							startDay	= DBUtils.parseInteger( calendarForm.getStartDay7(), 2).intValue();							endDay		= DBUtils.parseInteger( calendarForm.getEndDay7(), 1).intValue();														if( startDay <= endDay) {								if( ( mainDate.get( java.util.Calendar.DAY_OF_WEEK) >= startDay) && ( mainDate.get( java.util.Calendar.DAY_OF_WEEK) <= endDay))									calendar.setHourStatusConst( new Integer( Constant.HOUR_STATUS_FREE));								else									calendar.setHourStatusConst( new Integer( Constant.HOUR_STATUS_NOT_FREE));							} else {								if( ( mainDate.get( java.util.Calendar.DAY_OF_WEEK) >= startDay) || ( mainDate.get( java.util.Calendar.DAY_OF_WEEK) <= endDay))									calendar.setHourStatusConst( new Integer( Constant.HOUR_STATUS_FREE));								else									calendar.setHourStatusConst( new Integer( Constant.HOUR_STATUS_NOT_FREE));							}														seller.setCalendarPatternStartDay	( new Integer( startDay));							seller.setCalendarPatternEndDay		( new Integer( endDay));							break;													case Constant.CALENDAR_PATTERN_8:							startDay	= DBUtils.parseInteger( calendarForm.getStartDay8(), 2).intValue();							endDay		= DBUtils.parseInteger( calendarForm.getEndDay8(), 1).intValue();							startHour	= DBUtils.parseInteger( calendarForm.getStartHour8(), 0).intValue();							endHour		= DBUtils.parseInteger( calendarForm.getEndHour8(), 23).intValue();																			if( startDay <= endDay) {								if( ( mainDate.get( java.util.Calendar.DAY_OF_WEEK) >= startDay) && ( mainDate.get( java.util.Calendar.DAY_OF_WEEK) <= endDay)) {									if( startHour <= endHour){										if( ( mainDate.get( java.util.Calendar.HOUR_OF_DAY) >= startHour) && ( mainDate.get( java.util.Calendar.HOUR_OF_DAY) <= endHour)){											calendar.setHourStatusConst( new Integer( Constant.HOUR_STATUS_FREE));										}else {											calendar.setHourStatusConst( new Integer( Constant.HOUR_STATUS_NOT_FREE));										}									} else {										if( ( mainDate.get( java.util.Calendar.HOUR_OF_DAY) >= startHour) || ( mainDate.get( java.util.Calendar.HOUR_OF_DAY) <= endHour)){											calendar.setHourStatusConst( new Integer( Constant.HOUR_STATUS_FREE));										} else {											calendar.setHourStatusConst( new Integer( Constant.HOUR_STATUS_NOT_FREE));										}									}								} else									calendar.setHourStatusConst( new Integer( Constant.HOUR_STATUS_NOT_FREE));							} else {								if( ( mainDate.get( java.util.Calendar.DAY_OF_WEEK) >= startDay) || ( mainDate.get( java.util.Calendar.DAY_OF_WEEK) <= endDay)) {									if( startHour <= endHour){										if( ( mainDate.get( java.util.Calendar.HOUR_OF_DAY) >= startHour) && ( mainDate.get( java.util.Calendar.HOUR_OF_DAY) <= endHour)) {											calendar.setHourStatusConst( new Integer( Constant.HOUR_STATUS_FREE));										} else {											calendar.setHourStatusConst( new Integer( Constant.HOUR_STATUS_NOT_FREE));										}									} else {										if( ( mainDate.get( java.util.Calendar.HOUR_OF_DAY) >= startHour) || ( mainDate.get( java.util.Calendar.HOUR_OF_DAY) <= endHour)) {											calendar.setHourStatusConst( new Integer( Constant.HOUR_STATUS_FREE));										} else {											calendar.setHourStatusConst( new Integer( Constant.HOUR_STATUS_NOT_FREE));										}									}								} else									calendar.setHourStatusConst( new Integer( Constant.HOUR_STATUS_NOT_FREE));							}														seller.setCalendarPatternStartHour	( new Integer( startHour));							seller.setCalendarPatternEndHour	( new Integer( endHour));							seller.setCalendarPatternStartDay	( new Integer( startDay));							seller.setCalendarPatternEndDay		( new Integer( endDay));							break;												case Constant.CALENDAR_PATTERN_9:							calendar.setHourStatusConst( new Integer( Constant.HOUR_STATUS_NOT_FREE));							break;													default:							calendar.setHourStatusConst( new Integer( Constant.HOUR_STATUS_FREE));					}				}								calendarToAdd.add( calendar);								mainDate.add( java.util.Calendar.HOUR, 1);			}		}				calendarDAO.createCalendar( calendarToAdd, calendarToDelete);				seller.setIsCalendarGenerated	( new Integer( 1));		seller.setCalendarPattern		( new Integer( pattern));				sellerDAO.setSeller( seller);				return true;	}		public boolean setCalendarHourStatus( Integer sellerId, Date selectedDate, int hour, int status, Locale locale) throws DataAccessErrorException, DataUpdateErrorException, DataDeleteErrorException	{		CalendarDAO 		calendarDAO 		= new CalendarDAO();		SellerDAO			sellerDAO			= new SellerDAO();		Seller				seller				= null;		Calendar			calendar			= null;		GregorianCalendar 	gregorianCalendar = new GregorianCalendar();				seller = sellerDAO.getSellerById( sellerId);				if( seller == null)			return false;			gregorianCalendar.setTime( selectedDate);		gregorianCalendar.add( GregorianCalendar.HOUR_OF_DAY, hour);				calendar = calendarDAO.getCalendarBySellerIdAndDate( sellerId, gregorianCalendar.getTime());				if( calendar == null)			return false;				if( calendar.getHourStatusConst().intValue() != Constant.HOUR_STATUS_DATE) {					if( status == 1)				calendar.setHourStatusConst( new Integer( Constant.HOUR_STATUS_FREE));						if( status == 2)				calendar.setHourStatusConst( new Integer( Constant.HOUR_STATUS_NOT_FREE));					calendarDAO.setCalendar( calendar);		}				return true;	}		public boolean isFreeHours( Integer sellerId, Date date, int length, Locale locale) throws DataAccessErrorException	{		CalendarDAO calendarDAO = new CalendarDAO();		Collection	collection	= null;		Iterator	iterator	= null;		Calendar	calendar	= null;				if( date.before( new Date( GregorianCalendar.getInstance( locale).getTime().getTime() + 7200000))) //two hours			return false;				collection = calendarDAO.getCalendarBySellerIdDateAndLength( sellerId, date, length);				if( ( collection == null) || ( collection.size() == 0)) 			return false;				iterator = collection.iterator();				while( iterator.hasNext()) {			calendar = ( Calendar) iterator.next();						if( calendar.getHourStatusConst().intValue() != Constant.HOUR_STATUS_FREE)				return false;		}				return true;	}		public EditCalendarView getCalendarCollectionBySellerId( Integer id, Date startDate, Date endDate, Locale locale) throws DataAccessErrorException, TranslateValueObjectErrorException	{		SellerDAO 					sellerDAO 			= new SellerDAO();		SellerServiceDAO 			sellerServiceDAO 	= new SellerServiceDAO();		CalendarDAO					calendarDAO			= new CalendarDAO();		EditCalendarView			editCalendarView 	= null;		Seller						seller				= null;		Contact						contact				= null;		Calendar					calendar			= null;		Collection					calendarCollection	= null;		Iterator					calendarIterator	= null;		EditCalendarView.DateDate	dateDate			= null;		EditCalendarView.HourList	hourList			= null;		GregorianCalendar			date				= null;				seller = sellerDAO.getSellerById( id);				if( seller == null)			return null;				editCalendarView = new EditCalendarView();				calendarCollection = calendarDAO.getCalendarBySellerId( id, startDate, endDate, "date", true, 0, ( ConfigurationParameterManager.getParameterInt( "editCalendarDayNumber") + 1)* 24);			calendarIterator = calendarCollection.iterator();				while( calendarIterator.hasNext()) {			calendar = ( Calendar) calendarIterator.next();			dateDate = editCalendarView.new DateDate();						date = new GregorianCalendar();			date.setTime( calendar.getDate());					if( hourList == null) {				hourList = editCalendarView.new HourList();								hourList.setJavaDate	( calendar.getDate());				hourList.setDate		( DBUtils.getDate( calendar.getDate(), 4));				hourList.setBriefDate	( DBUtils.getDate( calendar.getDate(), 1));			}						if( ! DBUtils.getDate( hourList.getJavaDate(), 1).equals( DBUtils.getDate( calendar.getDate(), 1))) {								if( hourList.getHourList().size() >= 23) {					editCalendarView.getDateList().add( hourList);				} 								hourList = editCalendarView.new HourList();				hourList.setJavaDate	( calendar.getDate());				hourList.setDate		( DBUtils.getDate( calendar.getDate(), 4));				hourList.setBriefDate	( DBUtils.getDate( calendar.getDate(), 1));			}						dateDate.setHour( DBUtils.controlNull( new Integer( date.get( GregorianCalendar.HOUR_OF_DAY)), 2));						dateDate.setStatus( DBUtils.controlNull( calendar.getHourStatusConst()));						dateDate.setOddOrEven((( date.get( GregorianCalendar.HOUR_OF_DAY) % 2 == 0)? "odd": "even"));						hourList.getHourList().add( dateDate);		}					return editCalendarView;	}				public Collection getSeller( BuyerSellerAvailabilitySearchForm form, boolean firstPlaceInSearch, int startIndex, int offset, Locale locale) throws DataAccessErrorException, TranslateValueObjectErrorException	{		ArrayList 			Result 				= new ArrayList();		Iterator			sellerIterator 		= null;						CalendarDAO 		calendarDAO			= new CalendarDAO();		Seller				seller				= null;		SellerViewList		sellerViewList		= null;		boolean				odd					= true;		Date 				birthDateLow;		Date				birthDateHigh;		Date				date;		GregorianCalendar 	calendar; 		int 				ageHight 			= 99;		int 				ageLow 				= 18;		int					breastSize 			= 0;		int					educationLevel		= 0;		int					eyeColor			= 0;		int					hairColor			= 0;		int					hairLength			= 0;		int					height				= 0;		int					language			= 0;		int					orientation			= 0;		int					origin				= 0;		int					pennisSize			= 0;		int					physique			= 0;		int					sex					= 0;		int					skinColor			= 0;		int					smoker				= 0;		boolean				isFreeContact		= false;		boolean				isOnline			= false;		boolean				hasAlbum			= false;				if( form.getBreastSize_big().booleanValue())			breastSize = breastSize | Constant.BREAST_SIZE_BIG;		if( form.getBreastSize_normal().booleanValue())			breastSize = breastSize | Constant.BREAST_SIZE_NORMAL;		if( form.getBreastSize_small().booleanValue())			breastSize = breastSize | Constant.BREAST_SIZE_SMALL;		if( form.getBreastSize_veryBig().booleanValue())			breastSize = breastSize | Constant.BREAST_SIZE_VERY_BIG;				if( form.getEducationLevel_postDegree().booleanValue())			educationLevel = educationLevel | Constant.EDUCATION_LEVEL_POSTDEGREE;		if( form.getEducationLevel_primary().booleanValue())			educationLevel = educationLevel | Constant.EDUCATION_LEVEL_PRIMARY;		if( form.getEducationLevel_seconday().booleanValue())			educationLevel = educationLevel | Constant.EDUCATION_LEVEL_SECONDARY;		if( form.getEducationLevel_universitary().booleanValue())			educationLevel = educationLevel | Constant.EDUCATION_LEVEL_UNIVERSITARY;				if( form.getEyeColor_black().booleanValue())			eyeColor = eyeColor | Constant.EYE_COLOR_BLACK;		if( form.getEyeColor_blue().booleanValue())			eyeColor = eyeColor | Constant.EYE_COLOR_BLUE;		if( form.getEyeColor_brown().booleanValue())			eyeColor = eyeColor | Constant.EYE_COLOR_BROWN;		if( form.getEyeColor_green().booleanValue())			eyeColor = eyeColor | Constant.EYE_COLOR_GREEN;				if( form.getHairColor_blonde().booleanValue())			hairColor = hairColor | Constant.HAIR_COLOR_BLONDE;		if( form.getHairColor_brown().booleanValue())			hairColor = hairColor | Constant.HAIR_COLOR_BROWN;		if( form.getHairColor_dark().booleanValue())			hairColor = hairColor | Constant.HAIR_COLOR_DARK;		if( form.getHairColor_red().booleanValue())			hairColor = hairColor | Constant.HAIR_COLOR_RED;		if( form.getHairColor_white().booleanValue())			hairColor = hairColor | Constant.HAIR_COLOR_WHITE;				if( form.getHairLength_beyondShoulders().booleanValue())			hairLength = hairLength | Constant.HAIR_LENGTH_BEYOND_SHOULDERS;		if( form.getHairLength_noHair().booleanValue())			hairLength = hairLength | Constant.HAIR_LENGTH_NOT_HAIR;		if( form.getHairLength_short().booleanValue())			hairLength = hairLength | Constant.HAIR_LENGTH_SHORT;		if( form.getHairLength_untilNeck().booleanValue())			hairLength = hairLength | Constant.HAIR_LENGTH_UNTIL_NECK;		if( form.getHairLength_untilShoulders().booleanValue())			hairLength = hairLength | Constant.HAIR_LENGTH_UNTIL_SHOULDERS;		if( form.getHeight_between150And159().booleanValue())			height = height | Constant.HEIGHT_BETWEEN_150_AND_159;		if( form.getHeight_between160And169().booleanValue())			height = height | Constant.HEIGHT_BETWEEN_160_AND_169;		if( form.getHeight_between170And179().booleanValue())			height = height | Constant.HEIGHT_BETWEEN_170_AND_179;		if( form.getHeight_between180And189().booleanValue())			height = height | Constant.HEIGHT_BETWEEN_180_AND_189;		if( form.getHeight_between190And200().booleanValue())			height = height | Constant.HEIGHT_BETWEEN_190_AND_200;		if( form.getHeight_lessThan150().booleanValue())			height = height | Constant.HEIGHT_LESS_THAN_150;		if( form.getHeight_moreThan200().booleanValue())			height = height | Constant.HEIGHT_MORE_THAN_200;				if( form.getLanguage_arabian().booleanValue())			language = language | Constant.LANGUAGE_ARABIAN;		if( form.getLanguage_chinesse().booleanValue())			language = language | Constant.LANGUAGE_CHINESSE;		if( form.getLanguage_english().booleanValue())			language = language | Constant.LANGUAGE_ENGLISH;		if( form.getLanguage_french().booleanValue())			language = language | Constant.LANGUAGE_FRENCH;		if( form.getLanguage_german().booleanValue())			language = language | Constant.LANGUAGE_GERMAN;		if( form.getLanguage_italian().booleanValue())			language = language | Constant.LANGUAGE_ITALIAN;		if( form.getLanguage_japanesse().booleanValue())			language = language | Constant.LANGUAGE_JAPANESSE;		if( form.getLanguage_portuguese().booleanValue())			language = language | Constant.LANGUAGE_PORTUGUESE;		if( form.getLanguage_spanish().booleanValue())			language = language | Constant.LANGUAGE_SPANISH;				if( form.getOrientation_bicurious().booleanValue())			orientation = orientation | Constant.ORIENTATION_BICURIOUS;		if( form.getOrientation_bisexual().booleanValue())			orientation = orientation | Constant.ORIENTATION_BISEXUAL;		if( form.getOrientation_heterosexual().booleanValue())			orientation = orientation | Constant.ORIENTATION_HETEROSEXUAL;		if( form.getOrientation_homosexual().booleanValue())			orientation = orientation | Constant.ORIENTATION_HOMOSEXUAL;				if( form.getOrigin_african().booleanValue())			origin = origin | Constant.ORIGIN_AFRICAN;		if( form.getOrigin_arabian().booleanValue())			origin = origin | Constant.ORIGIN_ARABIAN;		if( form.getOrigin_caucasic().booleanValue())			origin = origin | Constant.ORIGIN_CAUCASIC;		if( form.getOrigin_hispanic().booleanValue())			origin = origin | Constant.ORIGIN_HISPANIC;		if( form.getOrigin_indian().booleanValue())			origin = origin | Constant.ORIGIN_INDIAN;		if( form.getOrigin_mediterranean().booleanValue())			origin = origin | Constant.ORIGIN_MEDITERRANEAN;		if( form.getOrigin_oriental().booleanValue())			origin = origin | Constant.ORIGIN_ORIENTAL;				if( form.getPennisSize_big().booleanValue())			pennisSize = pennisSize | Constant.PENNIS_SIZE_BIG;		if( form.getPennisSize_biggerThanNormal().booleanValue())			pennisSize = pennisSize | Constant.PENNIS_SIZE_BIGGER_THAN_NORMAL;		if( form.getPennisSize_normal().booleanValue())			pennisSize = pennisSize | Constant.PENNIS_SIZE_NORMAL;		if( form.getPennisSize_veryBig().booleanValue())			pennisSize = pennisSize | Constant.PENNIS_SIZE_VERY_BIG;				if( form.getPhysique_atlethic().booleanValue())			physique = physique | Constant.PHYSIQUE_ATLETHIC;		if( form.getPhysique_fat().booleanValue())			physique = physique | Constant.PHYSIQUE_FAT;		if( form.getPhysique_muscled().booleanValue())			physique = physique | Constant.PHYSIQUE_MUSCLED;		if( form.getPhysique_normal().booleanValue())			physique = physique | Constant.PHYSIQUE_NORMAL;		if( form.getPhysique_thin().booleanValue())			physique = physique | Constant.PHYSIQUE_THIN;		if( form.getSex_boy().booleanValue())			sex = sex | Constant.SEX_BOY;		if( form.getSex_boyAndGirlCouple().booleanValue())			sex = sex | Constant.SEX_BOY_AND_GIRL_COUPLE;		if( form.getSex_boysCouple().booleanValue())			sex = sex | Constant.SEX_BOYS_COUPLE;		if( form.getSex_girl().booleanValue())			sex = sex | Constant.SEX_GIRL;		if( form.getSex_girlsCouple().booleanValue())			sex = sex | Constant.SEX_GIRLS_COUPLE;		if( form.getSex_group().booleanValue())			sex = sex | Constant.SEX_GROUP;		if( form.getSex_shemale().booleanValue())			sex = sex | Constant.SEX_SHEMALE;				if( form.getSkinColor_black().booleanValue())			skinColor = skinColor | Constant.SKIN_COLOR_BLACK;		if( form.getSkinColor_dark().booleanValue())			skinColor = skinColor | Constant.SKIN_COLOR_DARK;		if( form.getSkinColor_veryDark().booleanValue())			skinColor = skinColor | Constant.SKIN_COLOR_VERY_DARK;		if( form.getSkinColor_white().booleanValue())			skinColor = skinColor | Constant.SKIN_COLOR_WHITE;				if( form.getSmoker_eventuallySmoker().booleanValue())			smoker = smoker | Constant.SMOKER_EVENTUALLY;		if( form.getSmoker_noSmoker().booleanValue())			smoker = smoker | Constant.SMOKER_NO;		if( form.getSmoker_smoker().booleanValue())			smoker = smoker | Constant.SMOKER_YES;				birthDateLow 	= DBUtils.getBirthDate( DBUtils.parseInteger( form.getAgeHigh(), 99).intValue(), locale);		birthDateHigh 	= DBUtils.getBirthDate( DBUtils.parseInteger( form.getAgeLow(), 18).intValue(), locale);		date			= DBUtils.parseDate( form.getDate());		calendar 		= new GregorianCalendar();				calendar.setTime( date);		calendar.add( GregorianCalendar.HOUR_OF_DAY, DBUtils.parseInteger( form.getHour(), 0).intValue());				isOnline 		= form.getIsOnline().booleanValue();		isFreeContact 	= form.getIsFreeContact().booleanValue();		hasAlbum 		= form.getHasAlbum().booleanValue();				sellerIterator = calendarDAO.getSeller( 	new Integer( sex), 													form.getCountry(), 													form.getState(), 													birthDateLow, 													birthDateHigh, 													new Integer( origin), 													new Integer( skinColor), 													new Integer( hairColor), 													new Integer( hairLength), 													new Integer( eyeColor), 													new Integer( language), 													new Integer( educationLevel), 													new Integer( smoker), 													new Integer( physique), 													new Integer( height), 													new Integer( orientation), 													new Integer( pennisSize), 													new Integer( breastSize),													calendar.getTime(),													firstPlaceInSearch,													isOnline,													isFreeContact,													hasAlbum,													form.getOrderedBy(),													startIndex, 													offset).iterator();		while( sellerIterator.hasNext()){			seller = (( Calendar) sellerIterator.next()).getSeller();						SellerToSellerViewListTranslator translator = new SellerToSellerViewListTranslator();						translator.setObject( seller);			translator.setLocale( locale);						sellerViewList = ( SellerViewList) translator.translateView();									if( odd)				sellerViewList.setOddOrEven( "odd");			else				sellerViewList.setOddOrEven( "even");						odd = ! odd;						Result.add( sellerViewList);		}				return Result;	}		public int getSellerCount( BuyerSellerAvailabilitySearchForm form, boolean firstPlaceInSearch, Locale locale) throws DataAccessErrorException	{		ArrayList 			Result 				= new ArrayList();		Iterator			sellerIterator 		= null;						CalendarDAO 		calendarDAO			= new CalendarDAO();		Seller				seller				= null;		SellerView			sellerView			= null;		boolean				odd					= true;		Date 				birthDateLow;		Date				birthDateHigh;		Date				date;		GregorianCalendar 	calendar; 		int 				ageHight 			= 99;		int 				ageLow 				= 18;		int					breastSize 			= 0;		int					educationLevel		= 0;		int					eyeColor			= 0;		int					hairColor			= 0;		int					hairLength			= 0;		int					height				= 0;		int					language			= 0;		int					orientation			= 0;		int					origin				= 0;		int					pennisSize			= 0;		int					physique			= 0;		int					sex					= 0;		int					skinColor			= 0;		int					smoker				= 0;		boolean				isFreeContact		= false;		boolean				isOnline			= false;		boolean				hasAlbum			= false;				if( form.getBreastSize_big().booleanValue())			breastSize = breastSize | Constant.BREAST_SIZE_BIG;		if( form.getBreastSize_normal().booleanValue())			breastSize = breastSize | Constant.BREAST_SIZE_NORMAL;		if( form.getBreastSize_small().booleanValue())			breastSize = breastSize | Constant.BREAST_SIZE_SMALL;		if( form.getBreastSize_veryBig().booleanValue())			breastSize = breastSize | Constant.BREAST_SIZE_VERY_BIG;				if( form.getEducationLevel_postDegree().booleanValue())			educationLevel = educationLevel | Constant.EDUCATION_LEVEL_POSTDEGREE;		if( form.getEducationLevel_primary().booleanValue())			educationLevel = educationLevel | Constant.EDUCATION_LEVEL_PRIMARY;		if( form.getEducationLevel_seconday().booleanValue())			educationLevel = educationLevel | Constant.EDUCATION_LEVEL_SECONDARY;		if( form.getEducationLevel_universitary().booleanValue())			educationLevel = educationLevel | Constant.EDUCATION_LEVEL_UNIVERSITARY;				if( form.getEyeColor_black().booleanValue())			eyeColor = eyeColor | Constant.EYE_COLOR_BLACK;		if( form.getEyeColor_blue().booleanValue())			eyeColor = eyeColor | Constant.EYE_COLOR_BLUE;		if( form.getEyeColor_brown().booleanValue())			eyeColor = eyeColor | Constant.EYE_COLOR_BROWN;		if( form.getEyeColor_green().booleanValue())			eyeColor = eyeColor | Constant.EYE_COLOR_GREEN;				if( form.getHairColor_blonde().booleanValue())			hairColor = hairColor | Constant.HAIR_COLOR_BLONDE;		if( form.getHairColor_brown().booleanValue())			hairColor = hairColor | Constant.HAIR_COLOR_BROWN;		if( form.getHairColor_dark().booleanValue())			hairColor = hairColor | Constant.HAIR_COLOR_DARK;		if( form.getHairColor_red().booleanValue())			hairColor = hairColor | Constant.HAIR_COLOR_RED;		if( form.getHairColor_white().booleanValue())			hairColor = hairColor | Constant.HAIR_COLOR_WHITE;				if( form.getHairLength_beyondShoulders().booleanValue())			hairLength = hairLength | Constant.HAIR_LENGTH_BEYOND_SHOULDERS;		if( form.getHairLength_noHair().booleanValue())			hairLength = hairLength | Constant.HAIR_LENGTH_NOT_HAIR;		if( form.getHairLength_short().booleanValue())			hairLength = hairLength | Constant.HAIR_LENGTH_SHORT;		if( form.getHairLength_untilNeck().booleanValue())			hairLength = hairLength | Constant.HAIR_LENGTH_UNTIL_NECK;		if( form.getHairLength_untilShoulders().booleanValue())			hairLength = hairLength | Constant.HAIR_LENGTH_UNTIL_SHOULDERS;		if( form.getHeight_between150And159().booleanValue())			height = height | Constant.HEIGHT_BETWEEN_150_AND_159;		if( form.getHeight_between160And169().booleanValue())			height = height | Constant.HEIGHT_BETWEEN_160_AND_169;		if( form.getHeight_between170And179().booleanValue())			height = height | Constant.HEIGHT_BETWEEN_170_AND_179;		if( form.getHeight_between180And189().booleanValue())			height = height | Constant.HEIGHT_BETWEEN_180_AND_189;		if( form.getHeight_between190And200().booleanValue())			height = height | Constant.HEIGHT_BETWEEN_190_AND_200;		if( form.getHeight_lessThan150().booleanValue())			height = height | Constant.HEIGHT_LESS_THAN_150;		if( form.getHeight_moreThan200().booleanValue())			height = height | Constant.HEIGHT_MORE_THAN_200;				if( form.getLanguage_arabian().booleanValue())			language = language | Constant.LANGUAGE_ARABIAN;		if( form.getLanguage_chinesse().booleanValue())			language = language | Constant.LANGUAGE_CHINESSE;		if( form.getLanguage_english().booleanValue())			language = language | Constant.LANGUAGE_ENGLISH;		if( form.getLanguage_french().booleanValue())			language = language | Constant.LANGUAGE_FRENCH;		if( form.getLanguage_german().booleanValue())			language = language | Constant.LANGUAGE_GERMAN;		if( form.getLanguage_italian().booleanValue())			language = language | Constant.LANGUAGE_ITALIAN;		if( form.getLanguage_japanesse().booleanValue())			language = language | Constant.LANGUAGE_JAPANESSE;		if( form.getLanguage_portuguese().booleanValue())			language = language | Constant.LANGUAGE_PORTUGUESE;		if( form.getLanguage_spanish().booleanValue())			language = language | Constant.LANGUAGE_SPANISH;				if( form.getOrientation_bicurious().booleanValue())			orientation = orientation | Constant.ORIENTATION_BICURIOUS;		if( form.getOrientation_bisexual().booleanValue())			orientation = orientation | Constant.ORIENTATION_BISEXUAL;		if( form.getOrientation_heterosexual().booleanValue())			orientation = orientation | Constant.ORIENTATION_HETEROSEXUAL;		if( form.getOrientation_homosexual().booleanValue())			orientation = orientation | Constant.ORIENTATION_HOMOSEXUAL;				if( form.getOrigin_african().booleanValue())			origin = origin | Constant.ORIGIN_AFRICAN;		if( form.getOrigin_arabian().booleanValue())			origin = origin | Constant.ORIGIN_ARABIAN;		if( form.getOrigin_caucasic().booleanValue())			origin = origin | Constant.ORIGIN_CAUCASIC;		if( form.getOrigin_hispanic().booleanValue())			origin = origin | Constant.ORIGIN_HISPANIC;		if( form.getOrigin_indian().booleanValue())			origin = origin | Constant.ORIGIN_INDIAN;		if( form.getOrigin_mediterranean().booleanValue())			origin = origin | Constant.ORIGIN_MEDITERRANEAN;		if( form.getOrigin_oriental().booleanValue())			origin = origin | Constant.ORIGIN_ORIENTAL;				if( form.getPennisSize_big().booleanValue())			pennisSize = pennisSize | Constant.PENNIS_SIZE_BIG;		if( form.getPennisSize_biggerThanNormal().booleanValue())			pennisSize = pennisSize | Constant.PENNIS_SIZE_BIGGER_THAN_NORMAL;		if( form.getPennisSize_normal().booleanValue())			pennisSize = pennisSize | Constant.PENNIS_SIZE_NORMAL;		if( form.getPennisSize_veryBig().booleanValue())			pennisSize = pennisSize | Constant.PENNIS_SIZE_VERY_BIG;				if( form.getPhysique_atlethic().booleanValue())			physique = physique | Constant.PHYSIQUE_ATLETHIC;		if( form.getPhysique_fat().booleanValue())			physique = physique | Constant.PHYSIQUE_FAT;		if( form.getPhysique_muscled().booleanValue())			physique = physique | Constant.PHYSIQUE_MUSCLED;		if( form.getPhysique_normal().booleanValue())			physique = physique | Constant.PHYSIQUE_NORMAL;		if( form.getPhysique_thin().booleanValue())			physique = physique | Constant.PHYSIQUE_THIN;		if( form.getSex_boy().booleanValue())			sex = sex | Constant.SEX_BOY;		if( form.getSex_boyAndGirlCouple().booleanValue())			sex = sex | Constant.SEX_BOY_AND_GIRL_COUPLE;		if( form.getSex_boysCouple().booleanValue())			sex = sex | Constant.SEX_BOYS_COUPLE;		if( form.getSex_girl().booleanValue())			sex = sex | Constant.SEX_GIRL;		if( form.getSex_girlsCouple().booleanValue())			sex = sex | Constant.SEX_GIRLS_COUPLE;		if( form.getSex_group().booleanValue())			sex = sex | Constant.SEX_GROUP;		if( form.getSex_shemale().booleanValue())			sex = sex | Constant.SEX_SHEMALE;				if( form.getSkinColor_black().booleanValue())			skinColor = skinColor | Constant.SKIN_COLOR_BLACK;		if( form.getSkinColor_dark().booleanValue())			skinColor = skinColor | Constant.SKIN_COLOR_DARK;		if( form.getSkinColor_veryDark().booleanValue())			skinColor = skinColor | Constant.SKIN_COLOR_VERY_DARK;		if( form.getSkinColor_white().booleanValue())			skinColor = skinColor | Constant.SKIN_COLOR_WHITE;				if( form.getSmoker_eventuallySmoker().booleanValue())			smoker = smoker | Constant.SMOKER_EVENTUALLY;		if( form.getSmoker_noSmoker().booleanValue())			smoker = smoker | Constant.SMOKER_NO;		if( form.getSmoker_smoker().booleanValue())			smoker = smoker | Constant.SMOKER_YES;				birthDateLow 	= DBUtils.getBirthDate( DBUtils.parseInteger( form.getAgeHigh(), 99).intValue(), locale);		birthDateHigh 	= DBUtils.getBirthDate( DBUtils.parseInteger( form.getAgeLow(), 18).intValue(), locale);		date			= DBUtils.parseDate( form.getDate());		calendar 		= new GregorianCalendar();				calendar.setTime( date);		calendar.add( GregorianCalendar.HOUR_OF_DAY, DBUtils.parseInteger( form.getHour(), 0).intValue());				isOnline 		= form.getIsOnline().booleanValue();		isFreeContact 	= form.getIsFreeContact().booleanValue();		hasAlbum 		= form.getHasAlbum().booleanValue();				return calendarDAO.getSellerCount( 	new Integer( sex), 											form.getCountry(), 											form.getState(), 											birthDateLow, 											birthDateHigh, 											new Integer( origin), 											new Integer( skinColor), 											new Integer( hairColor), 											new Integer( hairLength), 											new Integer( eyeColor), 											new Integer( language), 											new Integer( educationLevel), 											new Integer( smoker), 											new Integer( physique), 											new Integer( height), 											new Integer( orientation), 											new Integer( pennisSize), 											new Integer( breastSize),											calendar.getTime(),											firstPlaceInSearch,											isOnline,											isFreeContact,											hasAlbum);			}									}