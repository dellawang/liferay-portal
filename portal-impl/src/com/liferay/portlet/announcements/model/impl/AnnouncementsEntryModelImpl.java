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

package com.liferay.portlet.announcements.model.impl;

import com.liferay.announcements.kernel.model.AnnouncementsEntry;
import com.liferay.announcements.kernel.model.AnnouncementsEntryModel;
import com.liferay.announcements.kernel.model.AnnouncementsEntrySoap;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the AnnouncementsEntry service. Represents a row in the &quot;AnnouncementsEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>AnnouncementsEntryModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link AnnouncementsEntryImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AnnouncementsEntryImpl
 * @generated
 */
@JSON(strict = true)
public class AnnouncementsEntryModelImpl
	extends BaseModelImpl<AnnouncementsEntry>
	implements AnnouncementsEntryModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a announcements entry model instance should use the <code>AnnouncementsEntry</code> interface instead.
	 */
	public static final String TABLE_NAME = "AnnouncementsEntry";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"uuid_", Types.VARCHAR},
		{"entryId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"classNameId", Types.BIGINT}, {"classPK", Types.BIGINT},
		{"title", Types.VARCHAR}, {"content", Types.CLOB},
		{"url", Types.VARCHAR}, {"type_", Types.VARCHAR},
		{"displayDate", Types.TIMESTAMP}, {"expirationDate", Types.TIMESTAMP},
		{"priority", Types.INTEGER}, {"alert", Types.BOOLEAN}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("entryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("classNameId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classPK", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("title", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("content", Types.CLOB);
		TABLE_COLUMNS_MAP.put("url", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("type_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("displayDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("expirationDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("priority", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("alert", Types.BOOLEAN);
	}

	public static final String TABLE_SQL_CREATE =
		"create table AnnouncementsEntry (mvccVersion LONG default 0 not null,uuid_ VARCHAR(75) null,entryId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,classNameId LONG,classPK LONG,title VARCHAR(75) null,content TEXT null,url STRING null,type_ VARCHAR(75) null,displayDate DATE null,expirationDate DATE null,priority INTEGER,alert BOOLEAN)";

	public static final String TABLE_SQL_DROP = "drop table AnnouncementsEntry";

	public static final String ORDER_BY_JPQL =
		" ORDER BY announcementsEntry.priority ASC, announcementsEntry.modifiedDate ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY AnnouncementsEntry.priority ASC, AnnouncementsEntry.modifiedDate ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static final boolean ENTITY_CACHE_ENABLED = true;

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static final boolean FINDER_CACHE_ENABLED = true;

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static final boolean COLUMN_BITMASK_ENABLED = true;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)
	 */
	@Deprecated
	public static final long ALERT_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)
	 */
	@Deprecated
	public static final long CLASSNAMEID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)
	 */
	@Deprecated
	public static final long CLASSPK_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)
	 */
	@Deprecated
	public static final long COMPANYID_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)
	 */
	@Deprecated
	public static final long USERID_COLUMN_BITMASK = 16L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)
	 */
	@Deprecated
	public static final long UUID_COLUMN_BITMASK = 32L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)
	 */
	@Deprecated
	public static final long PRIORITY_COLUMN_BITMASK = 64L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)
	 */
	@Deprecated
	public static final long MODIFIEDDATE_COLUMN_BITMASK = 128L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static AnnouncementsEntry toModel(AnnouncementsEntrySoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		AnnouncementsEntry model = new AnnouncementsEntryImpl();

		model.setMvccVersion(soapModel.getMvccVersion());
		model.setUuid(soapModel.getUuid());
		model.setEntryId(soapModel.getEntryId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setClassNameId(soapModel.getClassNameId());
		model.setClassPK(soapModel.getClassPK());
		model.setTitle(soapModel.getTitle());
		model.setContent(soapModel.getContent());
		model.setUrl(soapModel.getUrl());
		model.setType(soapModel.getType());
		model.setDisplayDate(soapModel.getDisplayDate());
		model.setExpirationDate(soapModel.getExpirationDate());
		model.setPriority(soapModel.getPriority());
		model.setAlert(soapModel.isAlert());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static List<AnnouncementsEntry> toModels(
		AnnouncementsEntrySoap[] soapModels) {

		if (soapModels == null) {
			return null;
		}

		List<AnnouncementsEntry> models = new ArrayList<AnnouncementsEntry>(
			soapModels.length);

		for (AnnouncementsEntrySoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.portal.util.PropsUtil.get(
			"lock.expiration.time.com.liferay.announcements.kernel.model.AnnouncementsEntry"));

	public AnnouncementsEntryModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _entryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setEntryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _entryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return AnnouncementsEntry.class;
	}

	@Override
	public String getModelClassName() {
		return AnnouncementsEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<AnnouncementsEntry, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<AnnouncementsEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<AnnouncementsEntry, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((AnnouncementsEntry)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<AnnouncementsEntry, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<AnnouncementsEntry, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(AnnouncementsEntry)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<AnnouncementsEntry, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<AnnouncementsEntry, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, AnnouncementsEntry>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			AnnouncementsEntry.class.getClassLoader(), AnnouncementsEntry.class,
			ModelWrapper.class);

		try {
			Constructor<AnnouncementsEntry> constructor =
				(Constructor<AnnouncementsEntry>)proxyClass.getConstructor(
					InvocationHandler.class);

			return invocationHandler -> {
				try {
					return constructor.newInstance(invocationHandler);
				}
				catch (ReflectiveOperationException
							reflectiveOperationException) {

					throw new InternalError(reflectiveOperationException);
				}
			};
		}
		catch (NoSuchMethodException noSuchMethodException) {
			throw new InternalError(noSuchMethodException);
		}
	}

	private static final Map<String, Function<AnnouncementsEntry, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<AnnouncementsEntry, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<AnnouncementsEntry, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<AnnouncementsEntry, Object>>();
		Map<String, BiConsumer<AnnouncementsEntry, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap<String, BiConsumer<AnnouncementsEntry, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", AnnouncementsEntry::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<AnnouncementsEntry, Long>)
				AnnouncementsEntry::setMvccVersion);
		attributeGetterFunctions.put("uuid", AnnouncementsEntry::getUuid);
		attributeSetterBiConsumers.put(
			"uuid",
			(BiConsumer<AnnouncementsEntry, String>)
				AnnouncementsEntry::setUuid);
		attributeGetterFunctions.put("entryId", AnnouncementsEntry::getEntryId);
		attributeSetterBiConsumers.put(
			"entryId",
			(BiConsumer<AnnouncementsEntry, Long>)
				AnnouncementsEntry::setEntryId);
		attributeGetterFunctions.put(
			"companyId", AnnouncementsEntry::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<AnnouncementsEntry, Long>)
				AnnouncementsEntry::setCompanyId);
		attributeGetterFunctions.put("userId", AnnouncementsEntry::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<AnnouncementsEntry, Long>)
				AnnouncementsEntry::setUserId);
		attributeGetterFunctions.put(
			"userName", AnnouncementsEntry::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<AnnouncementsEntry, String>)
				AnnouncementsEntry::setUserName);
		attributeGetterFunctions.put(
			"createDate", AnnouncementsEntry::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<AnnouncementsEntry, Date>)
				AnnouncementsEntry::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", AnnouncementsEntry::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<AnnouncementsEntry, Date>)
				AnnouncementsEntry::setModifiedDate);
		attributeGetterFunctions.put(
			"classNameId", AnnouncementsEntry::getClassNameId);
		attributeSetterBiConsumers.put(
			"classNameId",
			(BiConsumer<AnnouncementsEntry, Long>)
				AnnouncementsEntry::setClassNameId);
		attributeGetterFunctions.put("classPK", AnnouncementsEntry::getClassPK);
		attributeSetterBiConsumers.put(
			"classPK",
			(BiConsumer<AnnouncementsEntry, Long>)
				AnnouncementsEntry::setClassPK);
		attributeGetterFunctions.put("title", AnnouncementsEntry::getTitle);
		attributeSetterBiConsumers.put(
			"title",
			(BiConsumer<AnnouncementsEntry, String>)
				AnnouncementsEntry::setTitle);
		attributeGetterFunctions.put("content", AnnouncementsEntry::getContent);
		attributeSetterBiConsumers.put(
			"content",
			(BiConsumer<AnnouncementsEntry, String>)
				AnnouncementsEntry::setContent);
		attributeGetterFunctions.put("url", AnnouncementsEntry::getUrl);
		attributeSetterBiConsumers.put(
			"url",
			(BiConsumer<AnnouncementsEntry, String>)AnnouncementsEntry::setUrl);
		attributeGetterFunctions.put("type", AnnouncementsEntry::getType);
		attributeSetterBiConsumers.put(
			"type",
			(BiConsumer<AnnouncementsEntry, String>)
				AnnouncementsEntry::setType);
		attributeGetterFunctions.put(
			"displayDate", AnnouncementsEntry::getDisplayDate);
		attributeSetterBiConsumers.put(
			"displayDate",
			(BiConsumer<AnnouncementsEntry, Date>)
				AnnouncementsEntry::setDisplayDate);
		attributeGetterFunctions.put(
			"expirationDate", AnnouncementsEntry::getExpirationDate);
		attributeSetterBiConsumers.put(
			"expirationDate",
			(BiConsumer<AnnouncementsEntry, Date>)
				AnnouncementsEntry::setExpirationDate);
		attributeGetterFunctions.put(
			"priority", AnnouncementsEntry::getPriority);
		attributeSetterBiConsumers.put(
			"priority",
			(BiConsumer<AnnouncementsEntry, Integer>)
				AnnouncementsEntry::setPriority);
		attributeGetterFunctions.put("alert", AnnouncementsEntry::getAlert);
		attributeSetterBiConsumers.put(
			"alert",
			(BiConsumer<AnnouncementsEntry, Boolean>)
				AnnouncementsEntry::setAlert);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public long getMvccVersion() {
		return _mvccVersion;
	}

	@Override
	public void setMvccVersion(long mvccVersion) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_mvccVersion = mvccVersion;
	}

	@JSON
	@Override
	public String getUuid() {
		if (_uuid == null) {
			return "";
		}
		else {
			return _uuid;
		}
	}

	@Override
	public void setUuid(String uuid) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_uuid = uuid;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalUuid() {
		return getColumnOriginalValue("uuid_");
	}

	@JSON
	@Override
	public long getEntryId() {
		return _entryId;
	}

	@Override
	public void setEntryId(long entryId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_entryId = entryId;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_companyId = companyId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalCompanyId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("companyId"));
	}

	@JSON
	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException portalException) {
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalUserId() {
		return GetterUtil.getLong(this.<Long>getColumnOriginalValue("userId"));
	}

	@JSON
	@Override
	public String getUserName() {
		if (_userName == null) {
			return "";
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_userName = userName;
	}

	@JSON
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_createDate = createDate;
	}

	@JSON
	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_modifiedDate = modifiedDate;
	}

	@Override
	public String getClassName() {
		if (getClassNameId() <= 0) {
			return "";
		}

		return PortalUtil.getClassName(getClassNameId());
	}

	@Override
	public void setClassName(String className) {
		long classNameId = 0;

		if (Validator.isNotNull(className)) {
			classNameId = PortalUtil.getClassNameId(className);
		}

		setClassNameId(classNameId);
	}

	@JSON
	@Override
	public long getClassNameId() {
		return _classNameId;
	}

	@Override
	public void setClassNameId(long classNameId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_classNameId = classNameId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalClassNameId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("classNameId"));
	}

	@JSON
	@Override
	public long getClassPK() {
		return _classPK;
	}

	@Override
	public void setClassPK(long classPK) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_classPK = classPK;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalClassPK() {
		return GetterUtil.getLong(this.<Long>getColumnOriginalValue("classPK"));
	}

	@JSON
	@Override
	public String getTitle() {
		if (_title == null) {
			return "";
		}
		else {
			return _title;
		}
	}

	@Override
	public void setTitle(String title) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_title = title;
	}

	@JSON
	@Override
	public String getContent() {
		if (_content == null) {
			return "";
		}
		else {
			return _content;
		}
	}

	@Override
	public void setContent(String content) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_content = content;
	}

	@JSON
	@Override
	public String getUrl() {
		if (_url == null) {
			return "";
		}
		else {
			return _url;
		}
	}

	@Override
	public void setUrl(String url) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_url = url;
	}

	@JSON
	@Override
	public String getType() {
		if (_type == null) {
			return "";
		}
		else {
			return _type;
		}
	}

	@Override
	public void setType(String type) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_type = type;
	}

	@JSON
	@Override
	public Date getDisplayDate() {
		return _displayDate;
	}

	@Override
	public void setDisplayDate(Date displayDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_displayDate = displayDate;
	}

	@JSON
	@Override
	public Date getExpirationDate() {
		return _expirationDate;
	}

	@Override
	public void setExpirationDate(Date expirationDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_expirationDate = expirationDate;
	}

	@JSON
	@Override
	public int getPriority() {
		return _priority;
	}

	@Override
	public void setPriority(int priority) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_priority = priority;
	}

	@JSON
	@Override
	public boolean getAlert() {
		return _alert;
	}

	@JSON
	@Override
	public boolean isAlert() {
		return _alert;
	}

	@Override
	public void setAlert(boolean alert) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_alert = alert;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public boolean getOriginalAlert() {
		return GetterUtil.getBoolean(
			this.<Boolean>getColumnOriginalValue("alert"));
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(AnnouncementsEntry.class.getName()),
			getClassNameId());
	}

	public long getColumnBitmask() {
		if (_columnBitmask > 0) {
			return _columnBitmask;
		}

		if ((_columnOriginalValues == null) ||
			(_columnOriginalValues == Collections.EMPTY_MAP)) {

			return 0;
		}

		for (Map.Entry<String, Object> entry :
				_columnOriginalValues.entrySet()) {

			if (entry.getValue() != getColumnValue(entry.getKey())) {
				_columnBitmask |= _columnBitmasks.get(entry.getKey());
			}
		}

		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), AnnouncementsEntry.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public AnnouncementsEntry toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, AnnouncementsEntry>
				escapedModelProxyProviderFunction =
					EscapedModelProxyProviderFunctionHolder.
						_escapedModelProxyProviderFunction;

			_escapedModel = escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		AnnouncementsEntryImpl announcementsEntryImpl =
			new AnnouncementsEntryImpl();

		announcementsEntryImpl.setMvccVersion(getMvccVersion());
		announcementsEntryImpl.setUuid(getUuid());
		announcementsEntryImpl.setEntryId(getEntryId());
		announcementsEntryImpl.setCompanyId(getCompanyId());
		announcementsEntryImpl.setUserId(getUserId());
		announcementsEntryImpl.setUserName(getUserName());
		announcementsEntryImpl.setCreateDate(getCreateDate());
		announcementsEntryImpl.setModifiedDate(getModifiedDate());
		announcementsEntryImpl.setClassNameId(getClassNameId());
		announcementsEntryImpl.setClassPK(getClassPK());
		announcementsEntryImpl.setTitle(getTitle());
		announcementsEntryImpl.setContent(getContent());
		announcementsEntryImpl.setUrl(getUrl());
		announcementsEntryImpl.setType(getType());
		announcementsEntryImpl.setDisplayDate(getDisplayDate());
		announcementsEntryImpl.setExpirationDate(getExpirationDate());
		announcementsEntryImpl.setPriority(getPriority());
		announcementsEntryImpl.setAlert(isAlert());

		announcementsEntryImpl.resetOriginalValues();

		return announcementsEntryImpl;
	}

	@Override
	public int compareTo(AnnouncementsEntry announcementsEntry) {
		int value = 0;

		if (getPriority() < announcementsEntry.getPriority()) {
			value = -1;
		}
		else if (getPriority() > announcementsEntry.getPriority()) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		value = DateUtil.compareTo(
			getModifiedDate(), announcementsEntry.getModifiedDate());

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof AnnouncementsEntry)) {
			return false;
		}

		AnnouncementsEntry announcementsEntry = (AnnouncementsEntry)object;

		long primaryKey = announcementsEntry.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		_columnOriginalValues = Collections.emptyMap();

		_setModifiedDate = false;

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<AnnouncementsEntry> toCacheModel() {
		AnnouncementsEntryCacheModel announcementsEntryCacheModel =
			new AnnouncementsEntryCacheModel();

		announcementsEntryCacheModel.mvccVersion = getMvccVersion();

		announcementsEntryCacheModel.uuid = getUuid();

		String uuid = announcementsEntryCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			announcementsEntryCacheModel.uuid = null;
		}

		announcementsEntryCacheModel.entryId = getEntryId();

		announcementsEntryCacheModel.companyId = getCompanyId();

		announcementsEntryCacheModel.userId = getUserId();

		announcementsEntryCacheModel.userName = getUserName();

		String userName = announcementsEntryCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			announcementsEntryCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			announcementsEntryCacheModel.createDate = createDate.getTime();
		}
		else {
			announcementsEntryCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			announcementsEntryCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			announcementsEntryCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		announcementsEntryCacheModel.classNameId = getClassNameId();

		announcementsEntryCacheModel.classPK = getClassPK();

		announcementsEntryCacheModel.title = getTitle();

		String title = announcementsEntryCacheModel.title;

		if ((title != null) && (title.length() == 0)) {
			announcementsEntryCacheModel.title = null;
		}

		announcementsEntryCacheModel.content = getContent();

		String content = announcementsEntryCacheModel.content;

		if ((content != null) && (content.length() == 0)) {
			announcementsEntryCacheModel.content = null;
		}

		announcementsEntryCacheModel.url = getUrl();

		String url = announcementsEntryCacheModel.url;

		if ((url != null) && (url.length() == 0)) {
			announcementsEntryCacheModel.url = null;
		}

		announcementsEntryCacheModel.type = getType();

		String type = announcementsEntryCacheModel.type;

		if ((type != null) && (type.length() == 0)) {
			announcementsEntryCacheModel.type = null;
		}

		Date displayDate = getDisplayDate();

		if (displayDate != null) {
			announcementsEntryCacheModel.displayDate = displayDate.getTime();
		}
		else {
			announcementsEntryCacheModel.displayDate = Long.MIN_VALUE;
		}

		Date expirationDate = getExpirationDate();

		if (expirationDate != null) {
			announcementsEntryCacheModel.expirationDate =
				expirationDate.getTime();
		}
		else {
			announcementsEntryCacheModel.expirationDate = Long.MIN_VALUE;
		}

		announcementsEntryCacheModel.priority = getPriority();

		announcementsEntryCacheModel.alert = isAlert();

		return announcementsEntryCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<AnnouncementsEntry, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<AnnouncementsEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<AnnouncementsEntry, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((AnnouncementsEntry)this));
			sb.append(", ");
		}

		if (sb.index() > 1) {
			sb.setIndex(sb.index() - 1);
		}

		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		Map<String, Function<AnnouncementsEntry, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<AnnouncementsEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<AnnouncementsEntry, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((AnnouncementsEntry)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, AnnouncementsEntry>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _mvccVersion;
	private String _uuid;
	private long _entryId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _classNameId;
	private long _classPK;
	private String _title;
	private String _content;
	private String _url;
	private String _type;
	private Date _displayDate;
	private Date _expirationDate;
	private int _priority;
	private boolean _alert;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<AnnouncementsEntry, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((AnnouncementsEntry)this);
	}

	public <T> T getColumnOriginalValue(String columnName) {
		if (_columnOriginalValues == null) {
			return null;
		}

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		return (T)_columnOriginalValues.get(columnName);
	}

	private void _setColumnOriginalValues() {
		_columnOriginalValues = new HashMap<String, Object>();

		_columnOriginalValues.put("mvccVersion", _mvccVersion);
		_columnOriginalValues.put("uuid_", _uuid);
		_columnOriginalValues.put("entryId", _entryId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put("classNameId", _classNameId);
		_columnOriginalValues.put("classPK", _classPK);
		_columnOriginalValues.put("title", _title);
		_columnOriginalValues.put("content", _content);
		_columnOriginalValues.put("url", _url);
		_columnOriginalValues.put("type_", _type);
		_columnOriginalValues.put("displayDate", _displayDate);
		_columnOriginalValues.put("expirationDate", _expirationDate);
		_columnOriginalValues.put("priority", _priority);
		_columnOriginalValues.put("alert", _alert);
	}

	private static final Map<String, String> _attributeNames;

	static {
		Map<String, String> attributeNames = new HashMap<>();

		attributeNames.put("uuid_", "uuid");
		attributeNames.put("type_", "type");

		_attributeNames = Collections.unmodifiableMap(attributeNames);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("mvccVersion", 1L);

		columnBitmasks.put("uuid_", 2L);

		columnBitmasks.put("entryId", 4L);

		columnBitmasks.put("companyId", 8L);

		columnBitmasks.put("userId", 16L);

		columnBitmasks.put("userName", 32L);

		columnBitmasks.put("createDate", 64L);

		columnBitmasks.put("modifiedDate", 128L);

		columnBitmasks.put("classNameId", 256L);

		columnBitmasks.put("classPK", 512L);

		columnBitmasks.put("title", 1024L);

		columnBitmasks.put("content", 2048L);

		columnBitmasks.put("url", 4096L);

		columnBitmasks.put("type_", 8192L);

		columnBitmasks.put("displayDate", 16384L);

		columnBitmasks.put("expirationDate", 32768L);

		columnBitmasks.put("priority", 65536L);

		columnBitmasks.put("alert", 131072L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private AnnouncementsEntry _escapedModel;

}