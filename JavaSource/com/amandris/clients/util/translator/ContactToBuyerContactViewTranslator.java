package com.amandris.clients.util.translator;import com.amandris.clients.service.vo.Contact;import com.amandris.clients.util.ConfigurationParameterManager;import com.amandris.clients.util.DBUtils;import com.amandris.clients.util.exception.TranslateValueObjectErrorException;import com.amandris.clients.web.view.BuyerContactView;public class ContactToBuyerContactViewTranslator extends Translator {	public Object translateView() throws TranslateValueObjectErrorException	{		BuyerContactView 	buyerContactView 	= new BuyerContactView();		Contact				contact				= ( Contact) object;		try{			buyerContactView.setId						( DBUtils.controlNull( contact.getId()));			buyerContactView.setSellerId				( DBUtils.controlNull( contact.getSellerId()));			buyerContactView.setBuyerId					( DBUtils.controlNull( contact.getBuyerId()));			buyerContactView.setSellerAge				( Integer.toString( DBUtils.getAge( contact.getSeller().getBirthdate(), locale)));			buyerContactView.setSellerHasPicture		( DBUtils.controlNull( contact.getSeller().getHasPicture()));			buyerContactView.setSellerMainPicture		( DBUtils.controlNull( contact.getSeller().getMainPicture()));			buyerContactView.setSellerMainPictureCode	( DBUtils.controlNull( contact.getSeller().getMainPictureCode()));			buyerContactView.setSellerSex				( DBUtils.controlNull( contact.getSeller().getSexConst()));			buyerContactView.setLength					( DBUtils.controlNull( contact.getLength()));			buyerContactView.setMessageBriefDate		( DBUtils.getDate( contact.getContactDate()));			buyerContactView.setMessageDate				( DBUtils.getDate( contact.getContactDate(), 4));			buyerContactView.setMessageStatusConst		( DBUtils.controlNull( contact.getMessageStatusConst()));			buyerContactView.setMessageStatusText		( ConfigurationParameterManager.getMessages().getMessage( locale, "messageStatus." + DBUtils.controlNull( contact.getMessageStatusConst())));			buyerContactView.setMessageTime				( DBUtils.getTime( contact.getContactDate()));			buyerContactView.setMessageTimeFrom			( ConfigurationParameterManager.getMessages().getMessage( locale, "timeTypeFrom." + DBUtils.getTimeFromType( contact.getContactDate(), locale, true), Integer.toString( DBUtils.getTimeFrom( contact.getContactDate(), locale, true))));			buyerContactView.setMessageAnswer			( DBUtils.removeCR( contact.getMessageAnswer()));			buyerContactView.setDateBriefDate			( DBUtils.getDate( contact.getDateDate()));			buyerContactView.setDateDate				( DBUtils.getDate( contact.getDateDate(), 4));						buyerContactView.setDateStatusConst			( DBUtils.controlNull( contact.getDateStatusConst()));			buyerContactView.setDateStatusText			( ConfigurationParameterManager.getMessages().getMessage( locale, "dateStatus." + DBUtils.controlNull( contact.getDateStatusConst())));			buyerContactView.setDateTime				( DBUtils.getTime( contact.getDateDate()));			buyerContactView.setDateTimeTo				( ConfigurationParameterManager.getMessages().getMessage( locale, "timeTypeTo." + DBUtils.getTimeFromType( contact.getDateDate(), locale, false), Integer.toString( DBUtils.getTimeFrom( contact.getDateDate(), locale, false))));			buyerContactView.setDateTimeToValue			( Integer.toString( DBUtils.getTimeFrom( contact.getDateDate(), locale, false)));			buyerContactView.setSellerCountry			( ConfigurationParameterManager.getMessages().getMessage( locale, "country." + DBUtils.controlNull( contact.getSeller().getState().getCountry().getText())));			buyerContactView.setSellerLogin				( DBUtils.controlNull( contact.getSeller().getLogin(), 15));			buyerContactView.setSellerFullLogin			( DBUtils.controlNull( contact.getSeller().getLogin()));			buyerContactView.setSellerNegativeVotes		( DBUtils.controlNull( contact.getSeller().getNegativeVotes()));			buyerContactView.setSellerPositiveVotes		( DBUtils.controlNull( contact.getSeller().getPositiveVotes()));			buyerContactView.setSellerState				( ConfigurationParameterManager.getMessages().getMessage( locale, "state." + DBUtils.controlNull( contact.getSeller().getState().getText())));			buyerContactView.setSellerCity				( DBUtils.controlNull( contact.getSeller().getCity(), 15));						buyerContactView.setPaymentStatusConst		( DBUtils.controlNull( contact.getPaymentStatusConst()));			buyerContactView.setPaymentStatusText		( ConfigurationParameterManager.getMessages().getMessage( locale, "paymentStatus." + DBUtils.controlNull( contact.getPaymentStatusConst())));			buyerContactView.setVoteStatusConst			( DBUtils.controlNull( contact.getVoteStatusConst()));			buyerContactView.setVoteStatusText			( ConfigurationParameterManager.getMessages().getMessage( locale, "voteStatus." + DBUtils.controlNull( contact.getVoteStatusConst())));			buyerContactView.setSellerVoteStatusConst	( DBUtils.controlNull( contact.getSellerVoteStatusConst()));			buyerContactView.setSellerVoteStatusText	( ConfigurationParameterManager.getMessages().getMessage( locale, "voteStatus." + DBUtils.controlNull( contact.getSellerVoteStatusConst())));						buyerContactView.setBuyerMadePayment		( DBUtils.controlNull( contact.getBuyerMadePayment()));			buyerContactView.setPrice					( DBUtils.controlNull( contact.getPrice()));			buyerContactView.setCurrency				( ( ConfigurationParameterManager.getMessages().getMessage( locale, "currency." + contact.getCurrencyConst())));			buyerContactView.setState					( DBUtils.controlNull( contact.getState()));			buyerContactView.setCity					( DBUtils.controlNull( contact.getCity()));			buyerContactView.setAddress1				( DBUtils.controlNull( contact.getAddress1()));			buyerContactView.setAddress2				( DBUtils.controlNull( contact.getAddress2()));			buyerContactView.setPostalCode				( DBUtils.controlNull( contact.getPostalCode()));			buyerContactView.setCountry					( DBUtils.controlNull( contact.getCountry()));						buyerContactView.setServiceTypeConst		( DBUtils.controlNull( contact.getServiceTypeConst()));			buyerContactView.setServiceTypeText			( ConfigurationParameterManager.getMessages().getMessage( locale, "service." + DBUtils.controlNull( contact.getServiceTypeConst())));			buyerContactView.setCancelDateText			( DBUtils.removeCR( contact.getCancelDateText()));									buyerContactView.setVote					( DBUtils.controlNull( contact.getVote()));			buyerContactView.setSellerVote				( DBUtils.controlNull( contact.getSellerVote()));						if( contact.getPrice().doubleValue() == 0)				buyerContactView.setIsFree( "1");			else				buyerContactView.setIsFree( "0");						switch( contact.getMessageStatusConst().intValue()){				case 1: buyerContactView.setMessageStatusColor( ConfigurationParameterManager.getParameterString( "status.waiting"));	break;				case 2: buyerContactView.setMessageStatusColor( ConfigurationParameterManager.getParameterString( "status.ok"));		break;				case 3: buyerContactView.setMessageStatusColor( ConfigurationParameterManager.getParameterString( "status.fail"));		break;				case 4: buyerContactView.setMessageStatusColor( ConfigurationParameterManager.getParameterString( "status.passed"));	break;				default: buyerContactView.setMessageStatusColor( ConfigurationParameterManager.getParameterString( "status.default"));	break;			}						switch( contact.getDateStatusConst().intValue()){				case 1: buyerContactView.setDateStatusColor( ConfigurationParameterManager.getParameterString( "status.waiting"));	break;				case 2: buyerContactView.setDateStatusColor( ConfigurationParameterManager.getParameterString( "status.fail"));		break;				case 3: buyerContactView.setDateStatusColor( ConfigurationParameterManager.getParameterString( "status.ok"));		break;				case 4: buyerContactView.setDateStatusColor( ConfigurationParameterManager.getParameterString( "status.passed"));	break;				default: buyerContactView.setDateStatusColor( ConfigurationParameterManager.getParameterString( "status.default"));	break;			}						switch( contact.getPaymentStatusConst().intValue()){				case 1: buyerContactView.setPaymentStatusColor( ConfigurationParameterManager.getParameterString( "status.waiting"));	break;				case 2: buyerContactView.setPaymentStatusColor( ConfigurationParameterManager.getParameterString( "status.ok"));		break;				case 3: buyerContactView.setPaymentStatusColor( ConfigurationParameterManager.getParameterString( "status.passed"));	break;				case 4: buyerContactView.setPaymentStatusColor( ConfigurationParameterManager.getParameterString( "status.notNeeded"));	break;				default: buyerContactView.setPaymentStatusColor( ConfigurationParameterManager.getParameterString( "status.default"));	break;			}						switch( contact.getVoteStatusConst().intValue()){				case 1: buyerContactView.setVoteStatusColor( ConfigurationParameterManager.getParameterString( "status.waiting"));	break;				case 2: buyerContactView.setVoteStatusColor( ConfigurationParameterManager.getParameterString( "status.ok"));		break;				case 3: buyerContactView.setVoteStatusColor( ConfigurationParameterManager.getParameterString( "status.passed"));	break;				default: buyerContactView.setVoteStatusColor( ConfigurationParameterManager.getParameterString( "status.default"));	break;			}						switch( contact.getSellerVoteStatusConst().intValue()){			case 1: buyerContactView.setSellerVoteStatusColor( ConfigurationParameterManager.getParameterString( "status.waiting"));	break;			case 2: buyerContactView.setSellerVoteStatusColor( ConfigurationParameterManager.getParameterString( "status.ok"));		break;			case 3: buyerContactView.setSellerVoteStatusColor( ConfigurationParameterManager.getParameterString( "status.passed"));	break;			default: buyerContactView.setSellerVoteStatusColor( ConfigurationParameterManager.getParameterString( "status.default"));	break;		}					}catch( Exception e) {			e.printStackTrace();			throw new TranslateValueObjectErrorException( object.getClass().getName(), buyerContactView.getClass().getName(), e);		}				return buyerContactView;	}}