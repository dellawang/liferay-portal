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

package com.liferay.account.model.impl;

import com.liferay.account.model.AccountEntry;
import com.liferay.account.model.AccountEntryModel;
import com.liferay.account.model.AccountEntrySoap;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
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
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;

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
 * The base model implementation for the AccountEntry service. Represents a row in the &quot;AccountEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>AccountEntryModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link AccountEntryImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountEntryImpl
 * @generated
 */
@JSON(strict = true)
public class AccountEntryModelImpl
	extends BaseModelImpl<AccountEntry> implements AccountEntryModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a account entry model instance should use the <code>AccountEntry</code> interface instead.
	 */
	public static final String TABLE_NAME = "AccountEntry";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"externalReferenceCode", Types.VARCHAR},
		{"accountEntryId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"parentAccountEntryId", Types.BIGINT}, {"name", Types.VARCHAR},
		{"description", Types.VARCHAR}, {"domains", Types.VARCHAR},
		{"logoId", Types.BIGINT}, {"taxIdNumber", Types.VARCHAR},
		{"type_", Types.VARCHAR}, {"status", Types.INTEGER}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("externalReferenceCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("accountEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("parentAccountEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("description", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("domains", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("logoId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("taxIdNumber", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("type_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("status", Types.INTEGER);
	}

	public static final String TABLE_SQL_CREATE =
		"create table AccountEntry (mvccVersion LONG default 0 not null,externalReferenceCode VARCHAR(75) null,accountEntryId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,parentAccountEntryId LONG,name VARCHAR(100) null,description STRING null,domains STRING null,logoId LONG,taxIdNumber VARCHAR(75) null,type_ VARCHAR(75) null,status INTEGER)";

	public static final String TABLE_SQL_DROP = "drop table AccountEntry";

	public static final String ORDER_BY_JPQL =
		" ORDER BY accountEntry.name ASC";

	public static final String ORDER_BY_SQL = " ORDER BY AccountEntry.name ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)
	 */
	@Deprecated
	public static final long COMPANYID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)
	 */
	@Deprecated
	public static final long EXTERNALREFERENCECODE_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)
	 */
	@Deprecated
	public static final long STATUS_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)
	 */
	@Deprecated
	public static final long NAME_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static void setEntityCacheEnabled(boolean entityCacheEnabled) {
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static void setFinderCacheEnabled(boolean finderCacheEnabled) {
	}

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static AccountEntry toModel(AccountEntrySoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		AccountEntry model = new AccountEntryImpl();

		model.setMvccVersion(soapModel.getMvccVersion());
		model.setExternalReferenceCode(soapModel.getExternalReferenceCode());
		model.setAccountEntryId(soapModel.getAccountEntryId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setParentAccountEntryId(soapModel.getParentAccountEntryId());
		model.setName(soapModel.getName());
		model.setDescription(soapModel.getDescription());
		model.setDomains(soapModel.getDomains());
		model.setLogoId(soapModel.getLogoId());
		model.setTaxIdNumber(soapModel.getTaxIdNumber());
		model.setType(soapModel.getType());
		model.setStatus(soapModel.getStatus());

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
	public static List<AccountEntry> toModels(AccountEntrySoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<AccountEntry> models = new ArrayList<AccountEntry>(
			soapModels.length);

		for (AccountEntrySoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public AccountEntryModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _accountEntryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setAccountEntryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _accountEntryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return AccountEntry.class;
	}

	@Override
	public String getModelClassName() {
		return AccountEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<AccountEntry, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<AccountEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<AccountEntry, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((AccountEntry)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<AccountEntry, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<AccountEntry, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(AccountEntry)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<AccountEntry, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<AccountEntry, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, AccountEntry>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			AccountEntry.class.getClassLoader(), AccountEntry.class,
			ModelWrapper.class);

		try {
			Constructor<AccountEntry> constructor =
				(Constructor<AccountEntry>)proxyClass.getConstructor(
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

	private static final Map<String, Function<AccountEntry, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<AccountEntry, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<AccountEntry, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<AccountEntry, Object>>();
		Map<String, BiConsumer<AccountEntry, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<AccountEntry, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", AccountEntry::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<AccountEntry, Long>)AccountEntry::setMvccVersion);
		attributeGetterFunctions.put(
			"externalReferenceCode", AccountEntry::getExternalReferenceCode);
		attributeSetterBiConsumers.put(
			"externalReferenceCode",
			(BiConsumer<AccountEntry, String>)
				AccountEntry::setExternalReferenceCode);
		attributeGetterFunctions.put(
			"accountEntryId", AccountEntry::getAccountEntryId);
		attributeSetterBiConsumers.put(
			"accountEntryId",
			(BiConsumer<AccountEntry, Long>)AccountEntry::setAccountEntryId);
		attributeGetterFunctions.put("companyId", AccountEntry::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<AccountEntry, Long>)AccountEntry::setCompanyId);
		attributeGetterFunctions.put("userId", AccountEntry::getUserId);
		attributeSetterBiConsumers.put(
			"userId", (BiConsumer<AccountEntry, Long>)AccountEntry::setUserId);
		attributeGetterFunctions.put("userName", AccountEntry::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<AccountEntry, String>)AccountEntry::setUserName);
		attributeGetterFunctions.put("createDate", AccountEntry::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<AccountEntry, Date>)AccountEntry::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", AccountEntry::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<AccountEntry, Date>)AccountEntry::setModifiedDate);
		attributeGetterFunctions.put(
			"parentAccountEntryId", AccountEntry::getParentAccountEntryId);
		attributeSetterBiConsumers.put(
			"parentAccountEntryId",
			(BiConsumer<AccountEntry, Long>)
				AccountEntry::setParentAccountEntryId);
		attributeGetterFunctions.put("name", AccountEntry::getName);
		attributeSetterBiConsumers.put(
			"name", (BiConsumer<AccountEntry, String>)AccountEntry::setName);
		attributeGetterFunctions.put(
			"description", AccountEntry::getDescription);
		attributeSetterBiConsumers.put(
			"description",
			(BiConsumer<AccountEntry, String>)AccountEntry::setDescription);
		attributeGetterFunctions.put("domains", AccountEntry::getDomains);
		attributeSetterBiConsumers.put(
			"domains",
			(BiConsumer<AccountEntry, String>)AccountEntry::setDomains);
		attributeGetterFunctions.put("logoId", AccountEntry::getLogoId);
		attributeSetterBiConsumers.put(
			"logoId", (BiConsumer<AccountEntry, Long>)AccountEntry::setLogoId);
		attributeGetterFunctions.put(
			"taxIdNumber", AccountEntry::getTaxIdNumber);
		attributeSetterBiConsumers.put(
			"taxIdNumber",
			(BiConsumer<AccountEntry, String>)AccountEntry::setTaxIdNumber);
		attributeGetterFunctions.put("type", AccountEntry::getType);
		attributeSetterBiConsumers.put(
			"type", (BiConsumer<AccountEntry, String>)AccountEntry::setType);
		attributeGetterFunctions.put("status", AccountEntry::getStatus);
		attributeSetterBiConsumers.put(
			"status",
			(BiConsumer<AccountEntry, Integer>)AccountEntry::setStatus);

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
	public String getExternalReferenceCode() {
		if (_externalReferenceCode == null) {
			return "";
		}
		else {
			return _externalReferenceCode;
		}
	}

	@Override
	public void setExternalReferenceCode(String externalReferenceCode) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_externalReferenceCode = externalReferenceCode;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalExternalReferenceCode() {
		return getColumnOriginalValue("externalReferenceCode");
	}

	@JSON
	@Override
	public long getAccountEntryId() {
		return _accountEntryId;
	}

	@Override
	public void setAccountEntryId(long accountEntryId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_accountEntryId = accountEntryId;
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

	@JSON
	@Override
	public long getParentAccountEntryId() {
		return _parentAccountEntryId;
	}

	@Override
	public void setParentAccountEntryId(long parentAccountEntryId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_parentAccountEntryId = parentAccountEntryId;
	}

	@JSON
	@Override
	public String getName() {
		if (_name == null) {
			return "";
		}
		else {
			return _name;
		}
	}

	@Override
	public void setName(String name) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_name = name;
	}

	@JSON
	@Override
	public String getDescription() {
		if (_description == null) {
			return "";
		}
		else {
			return _description;
		}
	}

	@Override
	public void setDescription(String description) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_description = description;
	}

	@JSON
	@Override
	public String getDomains() {
		if (_domains == null) {
			return "";
		}
		else {
			return _domains;
		}
	}

	@Override
	public void setDomains(String domains) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_domains = domains;
	}

	@JSON
	@Override
	public long getLogoId() {
		return _logoId;
	}

	@Override
	public void setLogoId(long logoId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_logoId = logoId;
	}

	@JSON
	@Override
	public String getTaxIdNumber() {
		if (_taxIdNumber == null) {
			return "";
		}
		else {
			return _taxIdNumber;
		}
	}

	@Override
	public void setTaxIdNumber(String taxIdNumber) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_taxIdNumber = taxIdNumber;
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
	public int getStatus() {
		return _status;
	}

	@Override
	public void setStatus(int status) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_status = status;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public int getOriginalStatus() {
		return GetterUtil.getInteger(
			this.<Integer>getColumnOriginalValue("status"));
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
			getCompanyId(), AccountEntry.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public AccountEntry toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, AccountEntry>
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
		AccountEntryImpl accountEntryImpl = new AccountEntryImpl();

		accountEntryImpl.setMvccVersion(getMvccVersion());
		accountEntryImpl.setExternalReferenceCode(getExternalReferenceCode());
		accountEntryImpl.setAccountEntryId(getAccountEntryId());
		accountEntryImpl.setCompanyId(getCompanyId());
		accountEntryImpl.setUserId(getUserId());
		accountEntryImpl.setUserName(getUserName());
		accountEntryImpl.setCreateDate(getCreateDate());
		accountEntryImpl.setModifiedDate(getModifiedDate());
		accountEntryImpl.setParentAccountEntryId(getParentAccountEntryId());
		accountEntryImpl.setName(getName());
		accountEntryImpl.setDescription(getDescription());
		accountEntryImpl.setDomains(getDomains());
		accountEntryImpl.setLogoId(getLogoId());
		accountEntryImpl.setTaxIdNumber(getTaxIdNumber());
		accountEntryImpl.setType(getType());
		accountEntryImpl.setStatus(getStatus());

		accountEntryImpl.resetOriginalValues();

		return accountEntryImpl;
	}

	@Override
	public int compareTo(AccountEntry accountEntry) {
		int value = 0;

		value = getName().compareTo(accountEntry.getName());

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

		if (!(object instanceof AccountEntry)) {
			return false;
		}

		AccountEntry accountEntry = (AccountEntry)object;

		long primaryKey = accountEntry.getPrimaryKey();

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
		return true;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isFinderCacheEnabled() {
		return true;
	}

	@Override
	public void resetOriginalValues() {
		_columnOriginalValues = Collections.emptyMap();

		_setModifiedDate = false;

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<AccountEntry> toCacheModel() {
		AccountEntryCacheModel accountEntryCacheModel =
			new AccountEntryCacheModel();

		accountEntryCacheModel.mvccVersion = getMvccVersion();

		accountEntryCacheModel.externalReferenceCode =
			getExternalReferenceCode();

		String externalReferenceCode =
			accountEntryCacheModel.externalReferenceCode;

		if ((externalReferenceCode != null) &&
			(externalReferenceCode.length() == 0)) {

			accountEntryCacheModel.externalReferenceCode = null;
		}

		accountEntryCacheModel.accountEntryId = getAccountEntryId();

		accountEntryCacheModel.companyId = getCompanyId();

		accountEntryCacheModel.userId = getUserId();

		accountEntryCacheModel.userName = getUserName();

		String userName = accountEntryCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			accountEntryCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			accountEntryCacheModel.createDate = createDate.getTime();
		}
		else {
			accountEntryCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			accountEntryCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			accountEntryCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		accountEntryCacheModel.parentAccountEntryId = getParentAccountEntryId();

		accountEntryCacheModel.name = getName();

		String name = accountEntryCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			accountEntryCacheModel.name = null;
		}

		accountEntryCacheModel.description = getDescription();

		String description = accountEntryCacheModel.description;

		if ((description != null) && (description.length() == 0)) {
			accountEntryCacheModel.description = null;
		}

		accountEntryCacheModel.domains = getDomains();

		String domains = accountEntryCacheModel.domains;

		if ((domains != null) && (domains.length() == 0)) {
			accountEntryCacheModel.domains = null;
		}

		accountEntryCacheModel.logoId = getLogoId();

		accountEntryCacheModel.taxIdNumber = getTaxIdNumber();

		String taxIdNumber = accountEntryCacheModel.taxIdNumber;

		if ((taxIdNumber != null) && (taxIdNumber.length() == 0)) {
			accountEntryCacheModel.taxIdNumber = null;
		}

		accountEntryCacheModel.type = getType();

		String type = accountEntryCacheModel.type;

		if ((type != null) && (type.length() == 0)) {
			accountEntryCacheModel.type = null;
		}

		accountEntryCacheModel.status = getStatus();

		return accountEntryCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<AccountEntry, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<AccountEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<AccountEntry, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((AccountEntry)this));
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
		Map<String, Function<AccountEntry, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<AccountEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<AccountEntry, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((AccountEntry)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, AccountEntry>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _mvccVersion;
	private String _externalReferenceCode;
	private long _accountEntryId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _parentAccountEntryId;
	private String _name;
	private String _description;
	private String _domains;
	private long _logoId;
	private String _taxIdNumber;
	private String _type;
	private int _status;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<AccountEntry, Object> function = _attributeGetterFunctions.get(
			columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((AccountEntry)this);
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
		_columnOriginalValues.put(
			"externalReferenceCode", _externalReferenceCode);
		_columnOriginalValues.put("accountEntryId", _accountEntryId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put(
			"parentAccountEntryId", _parentAccountEntryId);
		_columnOriginalValues.put("name", _name);
		_columnOriginalValues.put("description", _description);
		_columnOriginalValues.put("domains", _domains);
		_columnOriginalValues.put("logoId", _logoId);
		_columnOriginalValues.put("taxIdNumber", _taxIdNumber);
		_columnOriginalValues.put("type_", _type);
		_columnOriginalValues.put("status", _status);
	}

	private static final Map<String, String> _attributeNames;

	static {
		Map<String, String> attributeNames = new HashMap<>();

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

		columnBitmasks.put("externalReferenceCode", 2L);

		columnBitmasks.put("accountEntryId", 4L);

		columnBitmasks.put("companyId", 8L);

		columnBitmasks.put("userId", 16L);

		columnBitmasks.put("userName", 32L);

		columnBitmasks.put("createDate", 64L);

		columnBitmasks.put("modifiedDate", 128L);

		columnBitmasks.put("parentAccountEntryId", 256L);

		columnBitmasks.put("name", 512L);

		columnBitmasks.put("description", 1024L);

		columnBitmasks.put("domains", 2048L);

		columnBitmasks.put("logoId", 4096L);

		columnBitmasks.put("taxIdNumber", 8192L);

		columnBitmasks.put("type_", 16384L);

		columnBitmasks.put("status", 32768L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private AccountEntry _escapedModel;

}