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

package com.liferay.opensocial.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.opensocial.model.Gadget;
import com.liferay.opensocial.model.GadgetModel;
import com.liferay.opensocial.model.GadgetSoap;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
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
 * The base model implementation for the Gadget service. Represents a row in the &quot;OpenSocial_Gadget&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>GadgetModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link GadgetImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see GadgetImpl
 * @generated
 */
@JSON(strict = true)
public class GadgetModelImpl
	extends BaseModelImpl<Gadget> implements GadgetModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a gadget model instance should use the <code>Gadget</code> interface instead.
	 */
	public static final String TABLE_NAME = "OpenSocial_Gadget";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR}, {"gadgetId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP}, {"name", Types.VARCHAR},
		{"url", Types.VARCHAR}, {"portletCategoryNames", Types.VARCHAR},
		{"lastPublishDate", Types.TIMESTAMP}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("gadgetId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("url", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("portletCategoryNames", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("lastPublishDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE =
		"create table OpenSocial_Gadget (uuid_ VARCHAR(75) null,gadgetId LONG not null primary key,companyId LONG,createDate DATE null,modifiedDate DATE null,name VARCHAR(75) null,url STRING null,portletCategoryNames STRING null,lastPublishDate DATE null)";

	public static final String TABLE_SQL_DROP = "drop table OpenSocial_Gadget";

	public static final String ORDER_BY_JPQL = " ORDER BY gadget.name ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY OpenSocial_Gadget.name ASC";

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
	public static final long COMPANYID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)
	 */
	@Deprecated
	public static final long URL_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)
	 */
	@Deprecated
	public static final long UUID_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)
	 */
	@Deprecated
	public static final long NAME_COLUMN_BITMASK = 8L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static Gadget toModel(GadgetSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		Gadget model = new GadgetImpl();

		model.setUuid(soapModel.getUuid());
		model.setGadgetId(soapModel.getGadgetId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setName(soapModel.getName());
		model.setUrl(soapModel.getUrl());
		model.setPortletCategoryNames(soapModel.getPortletCategoryNames());
		model.setLastPublishDate(soapModel.getLastPublishDate());

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
	public static List<Gadget> toModels(GadgetSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<Gadget> models = new ArrayList<Gadget>(soapModels.length);

		for (GadgetSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.util.service.ServiceProps.get(
			"lock.expiration.time.com.liferay.opensocial.model.Gadget"));

	public GadgetModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _gadgetId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setGadgetId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _gadgetId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Gadget.class;
	}

	@Override
	public String getModelClassName() {
		return Gadget.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<Gadget, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<Gadget, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Gadget, Object> attributeGetterFunction = entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((Gadget)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<Gadget, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<Gadget, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(Gadget)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<Gadget, Object>> getAttributeGetterFunctions() {
		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<Gadget, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, Gadget>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			Gadget.class.getClassLoader(), Gadget.class, ModelWrapper.class);

		try {
			Constructor<Gadget> constructor =
				(Constructor<Gadget>)proxyClass.getConstructor(
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

	private static final Map<String, Function<Gadget, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<Gadget, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<Gadget, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<Gadget, Object>>();
		Map<String, BiConsumer<Gadget, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<Gadget, ?>>();

		attributeGetterFunctions.put("uuid", Gadget::getUuid);
		attributeSetterBiConsumers.put(
			"uuid", (BiConsumer<Gadget, String>)Gadget::setUuid);
		attributeGetterFunctions.put("gadgetId", Gadget::getGadgetId);
		attributeSetterBiConsumers.put(
			"gadgetId", (BiConsumer<Gadget, Long>)Gadget::setGadgetId);
		attributeGetterFunctions.put("companyId", Gadget::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId", (BiConsumer<Gadget, Long>)Gadget::setCompanyId);
		attributeGetterFunctions.put("createDate", Gadget::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate", (BiConsumer<Gadget, Date>)Gadget::setCreateDate);
		attributeGetterFunctions.put("modifiedDate", Gadget::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate", (BiConsumer<Gadget, Date>)Gadget::setModifiedDate);
		attributeGetterFunctions.put("name", Gadget::getName);
		attributeSetterBiConsumers.put(
			"name", (BiConsumer<Gadget, String>)Gadget::setName);
		attributeGetterFunctions.put("url", Gadget::getUrl);
		attributeSetterBiConsumers.put(
			"url", (BiConsumer<Gadget, String>)Gadget::setUrl);
		attributeGetterFunctions.put(
			"portletCategoryNames", Gadget::getPortletCategoryNames);
		attributeSetterBiConsumers.put(
			"portletCategoryNames",
			(BiConsumer<Gadget, String>)Gadget::setPortletCategoryNames);
		attributeGetterFunctions.put(
			"lastPublishDate", Gadget::getLastPublishDate);
		attributeSetterBiConsumers.put(
			"lastPublishDate",
			(BiConsumer<Gadget, Date>)Gadget::setLastPublishDate);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
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
	public long getGadgetId() {
		return _gadgetId;
	}

	@Override
	public void setGadgetId(long gadgetId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_gadgetId = gadgetId;
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

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalUrl() {
		return getColumnOriginalValue("url");
	}

	@JSON
	@Override
	public String getPortletCategoryNames() {
		if (_portletCategoryNames == null) {
			return "";
		}
		else {
			return _portletCategoryNames;
		}
	}

	@Override
	public void setPortletCategoryNames(String portletCategoryNames) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_portletCategoryNames = portletCategoryNames;
	}

	@JSON
	@Override
	public Date getLastPublishDate() {
		return _lastPublishDate;
	}

	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_lastPublishDate = lastPublishDate;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(Gadget.class.getName()));
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
			getCompanyId(), Gadget.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Gadget toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, Gadget>
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
		GadgetImpl gadgetImpl = new GadgetImpl();

		gadgetImpl.setUuid(getUuid());
		gadgetImpl.setGadgetId(getGadgetId());
		gadgetImpl.setCompanyId(getCompanyId());
		gadgetImpl.setCreateDate(getCreateDate());
		gadgetImpl.setModifiedDate(getModifiedDate());
		gadgetImpl.setName(getName());
		gadgetImpl.setUrl(getUrl());
		gadgetImpl.setPortletCategoryNames(getPortletCategoryNames());
		gadgetImpl.setLastPublishDate(getLastPublishDate());

		gadgetImpl.resetOriginalValues();

		return gadgetImpl;
	}

	@Override
	public int compareTo(Gadget gadget) {
		int value = 0;

		value = getName().compareTo(gadget.getName());

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

		if (!(object instanceof Gadget)) {
			return false;
		}

		Gadget gadget = (Gadget)object;

		long primaryKey = gadget.getPrimaryKey();

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
	public CacheModel<Gadget> toCacheModel() {
		GadgetCacheModel gadgetCacheModel = new GadgetCacheModel();

		gadgetCacheModel.uuid = getUuid();

		String uuid = gadgetCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			gadgetCacheModel.uuid = null;
		}

		gadgetCacheModel.gadgetId = getGadgetId();

		gadgetCacheModel.companyId = getCompanyId();

		Date createDate = getCreateDate();

		if (createDate != null) {
			gadgetCacheModel.createDate = createDate.getTime();
		}
		else {
			gadgetCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			gadgetCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			gadgetCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		gadgetCacheModel.name = getName();

		String name = gadgetCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			gadgetCacheModel.name = null;
		}

		gadgetCacheModel.url = getUrl();

		String url = gadgetCacheModel.url;

		if ((url != null) && (url.length() == 0)) {
			gadgetCacheModel.url = null;
		}

		gadgetCacheModel.portletCategoryNames = getPortletCategoryNames();

		String portletCategoryNames = gadgetCacheModel.portletCategoryNames;

		if ((portletCategoryNames != null) &&
			(portletCategoryNames.length() == 0)) {

			gadgetCacheModel.portletCategoryNames = null;
		}

		Date lastPublishDate = getLastPublishDate();

		if (lastPublishDate != null) {
			gadgetCacheModel.lastPublishDate = lastPublishDate.getTime();
		}
		else {
			gadgetCacheModel.lastPublishDate = Long.MIN_VALUE;
		}

		return gadgetCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<Gadget, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<Gadget, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Gadget, Object> attributeGetterFunction = entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((Gadget)this));
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
		Map<String, Function<Gadget, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<Gadget, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Gadget, Object> attributeGetterFunction = entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((Gadget)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, Gadget>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private String _uuid;
	private long _gadgetId;
	private long _companyId;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private String _name;
	private String _url;
	private String _portletCategoryNames;
	private Date _lastPublishDate;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<Gadget, Object> function = _attributeGetterFunctions.get(
			columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((Gadget)this);
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

		_columnOriginalValues.put("uuid_", _uuid);
		_columnOriginalValues.put("gadgetId", _gadgetId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put("name", _name);
		_columnOriginalValues.put("url", _url);
		_columnOriginalValues.put(
			"portletCategoryNames", _portletCategoryNames);
		_columnOriginalValues.put("lastPublishDate", _lastPublishDate);
	}

	private static final Map<String, String> _attributeNames;

	static {
		Map<String, String> attributeNames = new HashMap<>();

		attributeNames.put("uuid_", "uuid");

		_attributeNames = Collections.unmodifiableMap(attributeNames);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("uuid_", 1L);

		columnBitmasks.put("gadgetId", 2L);

		columnBitmasks.put("companyId", 4L);

		columnBitmasks.put("createDate", 8L);

		columnBitmasks.put("modifiedDate", 16L);

		columnBitmasks.put("name", 32L);

		columnBitmasks.put("url", 64L);

		columnBitmasks.put("portletCategoryNames", 128L);

		columnBitmasks.put("lastPublishDate", 256L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private Gadget _escapedModel;

}