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

package com.liferay.app.builder.service.base;

import com.liferay.app.builder.model.AppBuilderAppDataRecordLink;
import com.liferay.app.builder.service.AppBuilderAppDataRecordLinkLocalService;
import com.liferay.app.builder.service.persistence.AppBuilderAppDataRecordLinkPersistence;
import com.liferay.petra.sql.dsl.query.DSLQuery;
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
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the app builder app data record link local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.app.builder.service.impl.AppBuilderAppDataRecordLinkLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.app.builder.service.impl.AppBuilderAppDataRecordLinkLocalServiceImpl
 * @generated
 */
public abstract class AppBuilderAppDataRecordLinkLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AopService, AppBuilderAppDataRecordLinkLocalService,
			   IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>AppBuilderAppDataRecordLinkLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.app.builder.service.AppBuilderAppDataRecordLinkLocalServiceUtil</code>.
	 */

	/**
	 * Adds the app builder app data record link to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AppBuilderAppDataRecordLinkLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param appBuilderAppDataRecordLink the app builder app data record link
	 * @return the app builder app data record link that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public AppBuilderAppDataRecordLink addAppBuilderAppDataRecordLink(
		AppBuilderAppDataRecordLink appBuilderAppDataRecordLink) {

		appBuilderAppDataRecordLink.setNew(true);

		return appBuilderAppDataRecordLinkPersistence.update(
			appBuilderAppDataRecordLink);
	}

	/**
	 * Creates a new app builder app data record link with the primary key. Does not add the app builder app data record link to the database.
	 *
	 * @param appBuilderAppDataRecordLinkId the primary key for the new app builder app data record link
	 * @return the new app builder app data record link
	 */
	@Override
	@Transactional(enabled = false)
	public AppBuilderAppDataRecordLink createAppBuilderAppDataRecordLink(
		long appBuilderAppDataRecordLinkId) {

		return appBuilderAppDataRecordLinkPersistence.create(
			appBuilderAppDataRecordLinkId);
	}

	/**
	 * Deletes the app builder app data record link with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AppBuilderAppDataRecordLinkLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param appBuilderAppDataRecordLinkId the primary key of the app builder app data record link
	 * @return the app builder app data record link that was removed
	 * @throws PortalException if a app builder app data record link with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public AppBuilderAppDataRecordLink deleteAppBuilderAppDataRecordLink(
			long appBuilderAppDataRecordLinkId)
		throws PortalException {

		return appBuilderAppDataRecordLinkPersistence.remove(
			appBuilderAppDataRecordLinkId);
	}

	/**
	 * Deletes the app builder app data record link from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AppBuilderAppDataRecordLinkLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param appBuilderAppDataRecordLink the app builder app data record link
	 * @return the app builder app data record link that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public AppBuilderAppDataRecordLink deleteAppBuilderAppDataRecordLink(
		AppBuilderAppDataRecordLink appBuilderAppDataRecordLink) {

		return appBuilderAppDataRecordLinkPersistence.remove(
			appBuilderAppDataRecordLink);
	}

	@Override
	public <T> T dslQuery(DSLQuery dslQuery) {
		return appBuilderAppDataRecordLinkPersistence.dslQuery(dslQuery);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			AppBuilderAppDataRecordLink.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return appBuilderAppDataRecordLinkPersistence.findWithDynamicQuery(
			dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.app.builder.model.impl.AppBuilderAppDataRecordLinkModelImpl</code>.
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

		return appBuilderAppDataRecordLinkPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.app.builder.model.impl.AppBuilderAppDataRecordLinkModelImpl</code>.
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

		return appBuilderAppDataRecordLinkPersistence.findWithDynamicQuery(
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
		return appBuilderAppDataRecordLinkPersistence.countWithDynamicQuery(
			dynamicQuery);
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

		return appBuilderAppDataRecordLinkPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public AppBuilderAppDataRecordLink fetchAppBuilderAppDataRecordLink(
		long appBuilderAppDataRecordLinkId) {

		return appBuilderAppDataRecordLinkPersistence.fetchByPrimaryKey(
			appBuilderAppDataRecordLinkId);
	}

	/**
	 * Returns the app builder app data record link with the primary key.
	 *
	 * @param appBuilderAppDataRecordLinkId the primary key of the app builder app data record link
	 * @return the app builder app data record link
	 * @throws PortalException if a app builder app data record link with the primary key could not be found
	 */
	@Override
	public AppBuilderAppDataRecordLink getAppBuilderAppDataRecordLink(
			long appBuilderAppDataRecordLinkId)
		throws PortalException {

		return appBuilderAppDataRecordLinkPersistence.findByPrimaryKey(
			appBuilderAppDataRecordLinkId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(
			appBuilderAppDataRecordLinkLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(AppBuilderAppDataRecordLink.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"appBuilderAppDataRecordLinkId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			appBuilderAppDataRecordLinkLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(
			AppBuilderAppDataRecordLink.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"appBuilderAppDataRecordLinkId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(
			appBuilderAppDataRecordLinkLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(AppBuilderAppDataRecordLink.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"appBuilderAppDataRecordLinkId");
	}

	/**
	 * @throws PortalException
	 */
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return appBuilderAppDataRecordLinkPersistence.create(
			((Long)primaryKeyObj).longValue());
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return appBuilderAppDataRecordLinkLocalService.
			deleteAppBuilderAppDataRecordLink(
				(AppBuilderAppDataRecordLink)persistedModel);
	}

	public BasePersistence<AppBuilderAppDataRecordLink> getBasePersistence() {
		return appBuilderAppDataRecordLinkPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return appBuilderAppDataRecordLinkPersistence.findByPrimaryKey(
			primaryKeyObj);
	}

	/**
	 * Returns a range of all the app builder app data record links.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.app.builder.model.impl.AppBuilderAppDataRecordLinkModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of app builder app data record links
	 * @param end the upper bound of the range of app builder app data record links (not inclusive)
	 * @return the range of app builder app data record links
	 */
	@Override
	public List<AppBuilderAppDataRecordLink> getAppBuilderAppDataRecordLinks(
		int start, int end) {

		return appBuilderAppDataRecordLinkPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of app builder app data record links.
	 *
	 * @return the number of app builder app data record links
	 */
	@Override
	public int getAppBuilderAppDataRecordLinksCount() {
		return appBuilderAppDataRecordLinkPersistence.countAll();
	}

	/**
	 * Updates the app builder app data record link in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AppBuilderAppDataRecordLinkLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param appBuilderAppDataRecordLink the app builder app data record link
	 * @return the app builder app data record link that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public AppBuilderAppDataRecordLink updateAppBuilderAppDataRecordLink(
		AppBuilderAppDataRecordLink appBuilderAppDataRecordLink) {

		return appBuilderAppDataRecordLinkPersistence.update(
			appBuilderAppDataRecordLink);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			AppBuilderAppDataRecordLinkLocalService.class,
			IdentifiableOSGiService.class, PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		appBuilderAppDataRecordLinkLocalService =
			(AppBuilderAppDataRecordLinkLocalService)aopProxy;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return AppBuilderAppDataRecordLinkLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return AppBuilderAppDataRecordLink.class;
	}

	protected String getModelClassName() {
		return AppBuilderAppDataRecordLink.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				appBuilderAppDataRecordLinkPersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(
				dataSource, sql);

			sqlUpdate.update();
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
	}

	protected AppBuilderAppDataRecordLinkLocalService
		appBuilderAppDataRecordLinkLocalService;

	@Reference
	protected AppBuilderAppDataRecordLinkPersistence
		appBuilderAppDataRecordLinkPersistence;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

}