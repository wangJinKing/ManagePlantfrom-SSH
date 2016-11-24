package com.haifeiWu.daoImple;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.haifeiWu.base.DaoSupportImpl;
import com.haifeiWu.dao.BandInforDao;
import com.haifeiWu.entity.PHCSMP_Band;


@Repository("bandInforDao")
public class BandInforDaoImple extends DaoSupportImpl<PHCSMP_Band> implements BandInforDao {

	private String hql = "";
	private Transaction tx = null;
	private Session session = null;
	
	@Override
	public List<PHCSMP_Band> findAllBundInfor() {
		session = this.getSession();
		tx = session.beginTransaction();//开启事务
		
		hql = "from PHCSMP_Band";
		@SuppressWarnings("unchecked")
		List<PHCSMP_Band> list = session.createQuery(hql).list();
		tx.commit();//提交事务
		
		return list;
	}

	@Override
	public int findBandIdByWristId(String wristId) {
		session = this.getSession();
		tx = session.beginTransaction();//开启事务
		hql = "select p.band_ID from PHCSMP_Band p where remark=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, wristId);
		int bundId =  (Integer) query.uniqueResult();
		tx.commit();//提交事务
		return bundId;
	}

}