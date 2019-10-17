/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.info.service.base;

import com.liferay.info.model.InfoItemUsage;
import com.liferay.info.service.InfoItemUsageLocalService;
import com.liferay.info.service.persistence.InfoItemUsagePersistence;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the info item usage local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.info.service.impl.InfoItemUsageLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.info.service.impl.InfoItemUsageLocalServiceImpl
 * @generated
 */
public abstract class InfoItemUsageLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AopService, IdentifiableOSGiService, InfoItemUsageLocalService {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>InfoItemUsageLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.info.service.InfoItemUsageLocalServiceUtil</code>.
	 */

	/**
	 * Adds the info item usage to the database. Also notifies the appropriate model listeners.
	 *
	 * @param infoItemUsage the info item usage
	 * @return the info item usage that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public InfoItemUsage addInfoItemUsage(InfoItemUsage infoItemUsage) {
		infoItemUsage.setNew(true);

		return infoItemUsagePersistence.update(infoItemUsage);
	}

	/**
	 * Creates a new info item usage with the primary key. Does not add the info item usage to the database.
	 *
	 * @param infoItemUsageId the primary key for the new info item usage
	 * @return the new info item usage
	 */
	@Override
	@Transactional(enabled = false)
	public InfoItemUsage createInfoItemUsage(long infoItemUsageId) {
		return infoItemUsagePersistence.create(infoItemUsageId);
	}

	/**
	 * Deletes the info item usage with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param infoItemUsageId the primary key of the info item usage
	 * @return the info item usage that was removed
	 * @throws PortalException if a info item usage with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public InfoItemUsage deleteInfoItemUsage(long infoItemUsageId)
		throws PortalException {

		return infoItemUsagePersistence.remove(infoItemUsageId);
	}

	/**
	 * Deletes the info item usage from the database. Also notifies the appropriate model listeners.
	 *
	 * @param infoItemUsage the info item usage
	 * @return the info item usage that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public InfoItemUsage deleteInfoItemUsage(InfoItemUsage infoItemUsage) {
		return infoItemUsagePersistence.remove(infoItemUsage);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			InfoItemUsage.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return infoItemUsagePersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.info.model.impl.InfoItemUsageModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return infoItemUsagePersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.info.model.impl.InfoItemUsageModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return infoItemUsagePersistence.findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return infoItemUsagePersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		DynamicQuery dynamicQuery, Projection projection) {

		return infoItemUsagePersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public InfoItemUsage fetchInfoItemUsage(long infoItemUsageId) {
		return infoItemUsagePersistence.fetchByPrimaryKey(infoItemUsageId);
	}

	/**
	 * Returns the info item usage matching the UUID and group.
	 *
	 * @param uuid the info item usage's UUID
	 * @param groupId the primary key of the group
	 * @return the matching info item usage, or <code>null</code> if a matching info item usage could not be found
	 */
	@Override
	public InfoItemUsage fetchInfoItemUsageByUuidAndGroupId(
		String uuid, long groupId) {

		return infoItemUsagePersistence.fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the info item usage with the primary key.
	 *
	 * @param infoItemUsageId the primary key of the info item usage
	 * @return the info item usage
	 * @throws PortalException if a info item usage with the primary key could not be found
	 */
	@Override
	public InfoItemUsage getInfoItemUsage(long infoItemUsageId)
		throws PortalException {

		return infoItemUsagePersistence.findByPrimaryKey(infoItemUsageId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(infoItemUsageLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(InfoItemUsage.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("infoItemUsageId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			infoItemUsageLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(InfoItemUsage.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"infoItemUsageId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(infoItemUsageLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(InfoItemUsage.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("infoItemUsageId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return infoItemUsageLocalService.deleteInfoItemUsage(
			(InfoItemUsage)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return infoItemUsagePersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns the info item usage matching the UUID and group.
	 *
	 * @param uuid the info item usage's UUID
	 * @param groupId the primary key of the group
	 * @return the matching info item usage
	 * @throws PortalException if a matching info item usage could not be found
	 */
	@Override
	public InfoItemUsage getInfoItemUsageByUuidAndGroupId(
			String uuid, long groupId)
		throws PortalException {

		return infoItemUsagePersistence.findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns a range of all the info item usages.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.info.model.impl.InfoItemUsageModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of info item usages
	 * @param end the upper bound of the range of info item usages (not inclusive)
	 * @return the range of info item usages
	 */
	@Override
	public List<InfoItemUsage> getInfoItemUsages(int start, int end) {
		return infoItemUsagePersistence.findAll(start, end);
	}

	/**
	 * Returns the number of info item usages.
	 *
	 * @return the number of info item usages
	 */
	@Override
	public int getInfoItemUsagesCount() {
		return infoItemUsagePersistence.countAll();
	}

	/**
	 * Updates the info item usage in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param infoItemUsage the info item usage
	 * @return the info item usage that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public InfoItemUsage updateInfoItemUsage(InfoItemUsage infoItemUsage) {
		return infoItemUsagePersistence.update(infoItemUsage);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			InfoItemUsageLocalService.class, IdentifiableOSGiService.class,
			PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		infoItemUsageLocalService = (InfoItemUsageLocalService)aopProxy;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return InfoItemUsageLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return InfoItemUsage.class;
	}

	protected String getModelClassName() {
		return InfoItemUsage.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = infoItemUsagePersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(
				dataSource, sql);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	protected InfoItemUsageLocalService infoItemUsageLocalService;

	@Reference
	protected InfoItemUsagePersistence infoItemUsagePersistence;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.LayoutLocalService
		layoutLocalService;

}