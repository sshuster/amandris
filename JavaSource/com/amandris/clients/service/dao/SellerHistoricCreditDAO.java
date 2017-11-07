package com.amandris.clients.service.dao;import org.apache.ojb.broker.PersistenceBroker;import org.apache.ojb.broker.PersistenceBrokerException;import org.apache.ojb.broker.PersistenceBrokerFactory;import org.apache.ojb.broker.query.Criteria;import org.apache.ojb.broker.query.QueryByCriteria;import com.amandris.clients.service.vo.SellerHistoricCredit;import com.amandris.clients.service.vo.SellerMessage;import com.amandris.clients.util.exception.DataDeleteErrorException;import com.amandris.clients.util.exception.DataUpdateErrorException;public class SellerHistoricCreditDAO{			public void setSellerHistoricCredit( SellerHistoricCredit sellerHistoricCredit)  throws DataUpdateErrorException{		PersistenceBroker broker = null;				try{			broker = PersistenceBrokerFactory.defaultPersistenceBroker();			broker.beginTransaction();			broker.store( sellerHistoricCredit);			broker.commitTransaction();		}catch (PersistenceBrokerException ex){			broker.abortTransaction();			throw new DataUpdateErrorException( SellerHistoricCredit.class.getName(), this.getClass().getName() + ".setSellerHistoricCredit()", ex);		}finally {			if (broker != null) {				broker.close();			}		}	}			public void deleteSellerHistoricCreditBySellerId( Integer sellerId) throws DataDeleteErrorException	{		Criteria 			criteria	= new Criteria();		PersistenceBroker 	broker 		= null;				try {			criteria.addNotEqualTo	( "id", new Integer( 0));			criteria.addEqualTo		( "sellerId", sellerId);									QueryByCriteria query = new QueryByCriteria( SellerHistoricCredit.class, criteria);									broker = PersistenceBrokerFactory.defaultPersistenceBroker();				broker.deleteByQuery( query);					}catch ( PersistenceBrokerException ex) {			throw new DataDeleteErrorException( SellerHistoricCredit.class.getName(), this.getClass().getName() + ".deleteSellerHistoricCreditBySellerId()", ex);		}finally {			if ( broker != null) {				broker.close();			}		}	}	}