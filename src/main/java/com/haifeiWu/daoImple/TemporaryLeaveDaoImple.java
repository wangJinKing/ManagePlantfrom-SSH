package com.haifeiWu.daoImple;

import org.hibernate.Query;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.haifeiWu.base.DaoSupportImpl;
import com.haifeiWu.dao.TemporaryLeaveDao;
import com.haifeiWu.entity.Temporary_Leave;

@Repository("temporaryLeaveDao")
public class TemporaryLeaveDaoImple extends DaoSupportImpl<Temporary_Leave>
		implements TemporaryLeaveDao {
	private Transaction tx;

	// 查找当前嫌疑人出区返回时间为空的信息
	@Override
	public Temporary_Leave findTemporaryLeaveInfoById(String suspectId) {
		String hql = "from Temporary_Leave t where t.suspect_ID=? and t.return_Time=''";
		System.out.println(hql + "=---------------");
		tx = getSession().beginTransaction();// 开启事务

		Query query = getSession().createQuery(hql);
		query.setParameter(0, suspectId);
		@SuppressWarnings("unchecked")
		Temporary_Leave entity = (Temporary_Leave) query.uniqueResult();

		tx.commit();// 提交事务
		return entity;
	}
}
