package com.amandris.clients.util.translator;import com.amandris.clients.service.vo.SellerPromotion;import com.amandris.clients.util.DBUtils;import com.amandris.clients.util.exception.TranslateValueObjectErrorException;import com.amandris.clients.web.view.PromotionView;public class SellerPromotionToPromotionViewTranslator extends Translator {	public Object translateView() throws TranslateValueObjectErrorException	{		PromotionView 	promotionView 	= new PromotionView();		SellerPromotion	sellerPromotion	= ( SellerPromotion) object;				try{			promotionView.setId					( DBUtils.controlNull( sellerPromotion.getId()));			promotionView.setSellerId			( DBUtils.controlNull( sellerPromotion.getSellerId()));			promotionView.setStartDate			( DBUtils.getDate( sellerPromotion.getStartDate(), 3));			promotionView.setEndDate			( DBUtils.getDate( sellerPromotion.getEndDate(), 3));			promotionView.setStartJavaDate		( sellerPromotion.getStartDate());			promotionView.setEndJavaDate		( sellerPromotion.getEndDate());			promotionView.setPromotionConst		( DBUtils.controlNull( sellerPromotion.getPromotionConst()));		}catch( Exception e) {			throw new TranslateValueObjectErrorException( object.getClass().getName(), promotionView.getClass().getName(), e);		}				return promotionView;	}}