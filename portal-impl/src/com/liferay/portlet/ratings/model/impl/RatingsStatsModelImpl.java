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

package com.liferay.portlet.ratings.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.ratings.kernel.model.RatingsStats;
import com.liferay.ratings.kernel.model.RatingsStatsModel;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the RatingsStats service. Represents a row in the &quot;RatingsStats&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>RatingsStatsModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link RatingsStatsImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RatingsStatsImpl
 * @generated
 */
public class RatingsStatsModelImpl
	extends BaseModelImpl<RatingsStats> implements RatingsStatsModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a ratings stats model instance should use the <code>RatingsStats</code> interface instead.
	 */
	public static final String TABLE_NAME = "RatingsStats";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"ctCollectionId", Types.BIGINT},
		{"statsId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"classNameId", Types.BIGINT}, {"classPK", Types.BIGINT},
		{"totalEntries", Types.INTEGER}, {"totalScore", Types.DOUBLE},
		{"averageScore", Types.DOUBLE}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("ctCollectionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("statsId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("classNameId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classPK", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("totalEntries", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("totalScore", Types.DOUBLE);
		TABLE_COLUMNS_MAP.put("averageScore", Types.DOUBLE);
	}

	public static final String TABLE_SQL_CREATE =
		"create table RatingsStats (mvccVersion LONG default 0 not null,ctCollectionId LONG default 0 not null,statsId LONG not null,companyId LONG,createDate DATE null,modifiedDate DATE null,classNameId LONG,classPK LONG,totalEntries INTEGER,totalScore DOUBLE,averageScore DOUBLE,primary key (statsId, ctCollectionId))";

	public static final String TABLE_SQL_DROP = "drop table RatingsStats";

	public static final String ORDER_BY_JPQL =
		" ORDER BY ratingsStats.statsId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY RatingsStats.statsId ASC";

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
	public static final long CLASSNAMEID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)
	 */
	@Deprecated
	public static final long CLASSPK_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)
	 */
	@Deprecated
	public static final long STATSID_COLUMN_BITMASK = 4L;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.portal.util.PropsUtil.get(
			"lock.expiration.time.com.liferay.ratings.kernel.model.RatingsStats"));

	public RatingsStatsModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _statsId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setStatsId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _statsId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return RatingsStats.class;
	}

	@Override
	public String getModelClassName() {
		return RatingsStats.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<RatingsStats, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<RatingsStats, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<RatingsStats, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((RatingsStats)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<RatingsStats, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<RatingsStats, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(RatingsStats)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<RatingsStats, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<RatingsStats, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, RatingsStats>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			RatingsStats.class.getClassLoader(), RatingsStats.class,
			ModelWrapper.class);

		try {
			Constructor<RatingsStats> constructor =
				(Constructor<RatingsStats>)proxyClass.getConstructor(
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

	private static final Map<String, Function<RatingsStats, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<RatingsStats, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<RatingsStats, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<RatingsStats, Object>>();
		Map<String, BiConsumer<RatingsStats, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<RatingsStats, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", RatingsStats::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<RatingsStats, Long>)RatingsStats::setMvccVersion);
		attributeGetterFunctions.put(
			"ctCollectionId", RatingsStats::getCtCollectionId);
		attributeSetterBiConsumers.put(
			"ctCollectionId",
			(BiConsumer<RatingsStats, Long>)RatingsStats::setCtCollectionId);
		attributeGetterFunctions.put("statsId", RatingsStats::getStatsId);
		attributeSetterBiConsumers.put(
			"statsId",
			(BiConsumer<RatingsStats, Long>)RatingsStats::setStatsId);
		attributeGetterFunctions.put("companyId", RatingsStats::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<RatingsStats, Long>)RatingsStats::setCompanyId);
		attributeGetterFunctions.put("createDate", RatingsStats::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<RatingsStats, Date>)RatingsStats::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", RatingsStats::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<RatingsStats, Date>)RatingsStats::setModifiedDate);
		attributeGetterFunctions.put(
			"classNameId", RatingsStats::getClassNameId);
		attributeSetterBiConsumers.put(
			"classNameId",
			(BiConsumer<RatingsStats, Long>)RatingsStats::setClassNameId);
		attributeGetterFunctions.put("classPK", RatingsStats::getClassPK);
		attributeSetterBiConsumers.put(
			"classPK",
			(BiConsumer<RatingsStats, Long>)RatingsStats::setClassPK);
		attributeGetterFunctions.put(
			"totalEntries", RatingsStats::getTotalEntries);
		attributeSetterBiConsumers.put(
			"totalEntries",
			(BiConsumer<RatingsStats, Integer>)RatingsStats::setTotalEntries);
		attributeGetterFunctions.put("totalScore", RatingsStats::getTotalScore);
		attributeSetterBiConsumers.put(
			"totalScore",
			(BiConsumer<RatingsStats, Double>)RatingsStats::setTotalScore);
		attributeGetterFunctions.put(
			"averageScore", RatingsStats::getAverageScore);
		attributeSetterBiConsumers.put(
			"averageScore",
			(BiConsumer<RatingsStats, Double>)RatingsStats::setAverageScore);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

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

	@Override
	public long getCtCollectionId() {
		return _ctCollectionId;
	}

	@Override
	public void setCtCollectionId(long ctCollectionId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_ctCollectionId = ctCollectionId;
	}

	@Override
	public long getStatsId() {
		return _statsId;
	}

	@Override
	public void setStatsId(long statsId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_statsId = statsId;
	}

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

	@Override
	public int getTotalEntries() {
		return _totalEntries;
	}

	@Override
	public void setTotalEntries(int totalEntries) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_totalEntries = totalEntries;
	}

	@Override
	public double getTotalScore() {
		return _totalScore;
	}

	@Override
	public void setTotalScore(double totalScore) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_totalScore = totalScore;
	}

	@Override
	public double getAverageScore() {
		return _averageScore;
	}

	@Override
	public void setAverageScore(double averageScore) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_averageScore = averageScore;
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
			getCompanyId(), RatingsStats.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public RatingsStats toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, RatingsStats>
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
		RatingsStatsImpl ratingsStatsImpl = new RatingsStatsImpl();

		ratingsStatsImpl.setMvccVersion(getMvccVersion());
		ratingsStatsImpl.setCtCollectionId(getCtCollectionId());
		ratingsStatsImpl.setStatsId(getStatsId());
		ratingsStatsImpl.setCompanyId(getCompanyId());
		ratingsStatsImpl.setCreateDate(getCreateDate());
		ratingsStatsImpl.setModifiedDate(getModifiedDate());
		ratingsStatsImpl.setClassNameId(getClassNameId());
		ratingsStatsImpl.setClassPK(getClassPK());
		ratingsStatsImpl.setTotalEntries(getTotalEntries());
		ratingsStatsImpl.setTotalScore(getTotalScore());
		ratingsStatsImpl.setAverageScore(getAverageScore());

		ratingsStatsImpl.resetOriginalValues();

		return ratingsStatsImpl;
	}

	@Override
	public int compareTo(RatingsStats ratingsStats) {
		long primaryKey = ratingsStats.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof RatingsStats)) {
			return false;
		}

		RatingsStats ratingsStats = (RatingsStats)object;

		long primaryKey = ratingsStats.getPrimaryKey();

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
	public CacheModel<RatingsStats> toCacheModel() {
		RatingsStatsCacheModel ratingsStatsCacheModel =
			new RatingsStatsCacheModel();

		ratingsStatsCacheModel.mvccVersion = getMvccVersion();

		ratingsStatsCacheModel.ctCollectionId = getCtCollectionId();

		ratingsStatsCacheModel.statsId = getStatsId();

		ratingsStatsCacheModel.companyId = getCompanyId();

		Date createDate = getCreateDate();

		if (createDate != null) {
			ratingsStatsCacheModel.createDate = createDate.getTime();
		}
		else {
			ratingsStatsCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			ratingsStatsCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			ratingsStatsCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		ratingsStatsCacheModel.classNameId = getClassNameId();

		ratingsStatsCacheModel.classPK = getClassPK();

		ratingsStatsCacheModel.totalEntries = getTotalEntries();

		ratingsStatsCacheModel.totalScore = getTotalScore();

		ratingsStatsCacheModel.averageScore = getAverageScore();

		return ratingsStatsCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<RatingsStats, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<RatingsStats, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<RatingsStats, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((RatingsStats)this));
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
		Map<String, Function<RatingsStats, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<RatingsStats, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<RatingsStats, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((RatingsStats)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, RatingsStats>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _mvccVersion;
	private long _ctCollectionId;
	private long _statsId;
	private long _companyId;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _classNameId;
	private long _classPK;
	private int _totalEntries;
	private double _totalScore;
	private double _averageScore;

	public <T> T getColumnValue(String columnName) {
		Function<RatingsStats, Object> function = _attributeGetterFunctions.get(
			columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((RatingsStats)this);
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
		_columnOriginalValues.put("ctCollectionId", _ctCollectionId);
		_columnOriginalValues.put("statsId", _statsId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put("classNameId", _classNameId);
		_columnOriginalValues.put("classPK", _classPK);
		_columnOriginalValues.put("totalEntries", _totalEntries);
		_columnOriginalValues.put("totalScore", _totalScore);
		_columnOriginalValues.put("averageScore", _averageScore);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("mvccVersion", 1L);

		columnBitmasks.put("ctCollectionId", 2L);

		columnBitmasks.put("statsId", 4L);

		columnBitmasks.put("companyId", 8L);

		columnBitmasks.put("createDate", 16L);

		columnBitmasks.put("modifiedDate", 32L);

		columnBitmasks.put("classNameId", 64L);

		columnBitmasks.put("classPK", 128L);

		columnBitmasks.put("totalEntries", 256L);

		columnBitmasks.put("totalScore", 512L);

		columnBitmasks.put("averageScore", 1024L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private RatingsStats _escapedModel;

}