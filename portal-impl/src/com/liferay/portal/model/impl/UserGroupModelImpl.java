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

package com.liferay.portal.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserGroup;
import com.liferay.portal.model.UserGroupModel;
import com.liferay.portal.model.UserGroupSoap;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;
import com.liferay.portlet.exportimport.lar.StagedModelType;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The base model implementation for the UserGroup service. Represents a row in the &quot;UserGroup&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link UserGroupModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link UserGroupImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserGroupImpl
 * @see UserGroup
 * @see UserGroupModel
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class UserGroupModelImpl extends BaseModelImpl<UserGroup>
	implements UserGroupModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a user group model instance should use the {@link UserGroup} interface instead.
	 */
	public static final String TABLE_NAME = "UserGroup";
	public static final Object[][] TABLE_COLUMNS = {
			{ "mvccVersion", Types.BIGINT },
			{ "uuid_", Types.VARCHAR },
			{ "userGroupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "parentUserGroupId", Types.BIGINT },
			{ "name", Types.VARCHAR },
			{ "description", Types.VARCHAR },
			{ "addedByLDAPImport", Types.BOOLEAN }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("userGroupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("parentUserGroupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("description", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("addedByLDAPImport", Types.BOOLEAN);
	}

	public static final String TABLE_SQL_CREATE = "create table UserGroup (mvccVersion LONG default 0,uuid_ VARCHAR(75) null,userGroupId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,parentUserGroupId LONG,name VARCHAR(75) null,description STRING null,addedByLDAPImport BOOLEAN)";
	public static final String TABLE_SQL_DROP = "drop table UserGroup";
	public static final String ORDER_BY_JPQL = " ORDER BY userGroup.name ASC";
	public static final String ORDER_BY_SQL = " ORDER BY UserGroup.name ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.entity.cache.enabled.com.liferay.portal.model.UserGroup"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.finder.cache.enabled.com.liferay.portal.model.UserGroup"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.column.bitmask.enabled.com.liferay.portal.model.UserGroup"),
			true);
	public static final long COMPANYID_COLUMN_BITMASK = 1L;
	public static final long NAME_COLUMN_BITMASK = 2L;
	public static final long PARENTUSERGROUPID_COLUMN_BITMASK = 4L;
	public static final long UUID_COLUMN_BITMASK = 8L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static UserGroup toModel(UserGroupSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		UserGroup model = new UserGroupImpl();

		model.setMvccVersion(soapModel.getMvccVersion());
		model.setUuid(soapModel.getUuid());
		model.setUserGroupId(soapModel.getUserGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setParentUserGroupId(soapModel.getParentUserGroupId());
		model.setName(soapModel.getName());
		model.setDescription(soapModel.getDescription());
		model.setAddedByLDAPImport(soapModel.getAddedByLDAPImport());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<UserGroup> toModels(UserGroupSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<UserGroup> models = new ArrayList<UserGroup>(soapModels.length);

		for (UserGroupSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final String MAPPING_TABLE_GROUPS_USERGROUPS_NAME = "Groups_UserGroups";
	public static final Object[][] MAPPING_TABLE_GROUPS_USERGROUPS_COLUMNS = {
			{ "groupId", Types.BIGINT },
			{ "userGroupId", Types.BIGINT }
		};
	public static final String MAPPING_TABLE_GROUPS_USERGROUPS_SQL_CREATE = "create table Groups_UserGroups (groupId LONG not null,userGroupId LONG not null,primary key (groupId, userGroupId))";
	public static final boolean FINDER_CACHE_ENABLED_GROUPS_USERGROUPS = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.finder.cache.enabled.Groups_UserGroups"), true);
	public static final String MAPPING_TABLE_USERGROUPS_TEAMS_NAME = "UserGroups_Teams";
	public static final Object[][] MAPPING_TABLE_USERGROUPS_TEAMS_COLUMNS = {
			{ "teamId", Types.BIGINT },
			{ "userGroupId", Types.BIGINT }
		};
	public static final String MAPPING_TABLE_USERGROUPS_TEAMS_SQL_CREATE = "create table UserGroups_Teams (teamId LONG not null,userGroupId LONG not null,primary key (teamId, userGroupId))";
	public static final boolean FINDER_CACHE_ENABLED_USERGROUPS_TEAMS = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.finder.cache.enabled.UserGroups_Teams"), true);
	public static final String MAPPING_TABLE_USERS_USERGROUPS_NAME = "Users_UserGroups";
	public static final Object[][] MAPPING_TABLE_USERS_USERGROUPS_COLUMNS = {
			{ "userId", Types.BIGINT },
			{ "userGroupId", Types.BIGINT }
		};
	public static final String MAPPING_TABLE_USERS_USERGROUPS_SQL_CREATE = "create table Users_UserGroups (userId LONG not null,userGroupId LONG not null,primary key (userId, userGroupId))";
	public static final boolean FINDER_CACHE_ENABLED_USERS_USERGROUPS = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.finder.cache.enabled.Users_UserGroups"), true);
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
				"lock.expiration.time.com.liferay.portal.model.UserGroup"));

	public UserGroupModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _userGroupId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setUserGroupId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _userGroupId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return UserGroup.class;
	}

	@Override
	public String getModelClassName() {
		return UserGroup.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mvccVersion", getMvccVersion());
		attributes.put("uuid", getUuid());
		attributes.put("userGroupId", getUserGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("parentUserGroupId", getParentUserGroupId());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("addedByLDAPImport", getAddedByLDAPImport());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long mvccVersion = (Long)attributes.get("mvccVersion");

		if (mvccVersion != null) {
			setMvccVersion(mvccVersion);
		}

		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long userGroupId = (Long)attributes.get("userGroupId");

		if (userGroupId != null) {
			setUserGroupId(userGroupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long parentUserGroupId = (Long)attributes.get("parentUserGroupId");

		if (parentUserGroupId != null) {
			setParentUserGroupId(parentUserGroupId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		Boolean addedByLDAPImport = (Boolean)attributes.get("addedByLDAPImport");

		if (addedByLDAPImport != null) {
			setAddedByLDAPImport(addedByLDAPImport);
		}
	}

	@JSON
	@Override
	public long getMvccVersion() {
		return _mvccVersion;
	}

	@Override
	public void setMvccVersion(long mvccVersion) {
		_mvccVersion = mvccVersion;
	}

	@JSON
	@Override
	public String getUuid() {
		if (_uuid == null) {
			return StringPool.BLANK;
		}
		else {
			return _uuid;
		}
	}

	@Override
	public void setUuid(String uuid) {
		if (_originalUuid == null) {
			_originalUuid = _uuid;
		}

		_uuid = uuid;
	}

	public String getOriginalUuid() {
		return GetterUtil.getString(_originalUuid);
	}

	@JSON
	@Override
	public long getUserGroupId() {
		return _userGroupId;
	}

	@Override
	public void setUserGroupId(long userGroupId) {
		_userGroupId = userGroupId;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_columnBitmask |= COMPANYID_COLUMN_BITMASK;

		if (!_setOriginalCompanyId) {
			_setOriginalCompanyId = true;

			_originalCompanyId = _companyId;
		}

		_companyId = companyId;
	}

	public long getOriginalCompanyId() {
		return _originalCompanyId;
	}

	@JSON
	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return StringPool.BLANK;
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@JSON
	@Override
	public String getUserName() {
		if (_userName == null) {
			return StringPool.BLANK;
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;
	}

	@JSON
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
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

		_modifiedDate = modifiedDate;
	}

	@JSON
	@Override
	public long getParentUserGroupId() {
		return _parentUserGroupId;
	}

	@Override
	public void setParentUserGroupId(long parentUserGroupId) {
		_columnBitmask |= PARENTUSERGROUPID_COLUMN_BITMASK;

		if (!_setOriginalParentUserGroupId) {
			_setOriginalParentUserGroupId = true;

			_originalParentUserGroupId = _parentUserGroupId;
		}

		_parentUserGroupId = parentUserGroupId;
	}

	public long getOriginalParentUserGroupId() {
		return _originalParentUserGroupId;
	}

	@JSON
	@Override
	public String getName() {
		if (_name == null) {
			return StringPool.BLANK;
		}
		else {
			return _name;
		}
	}

	@Override
	public void setName(String name) {
		_columnBitmask = -1L;

		if (_originalName == null) {
			_originalName = _name;
		}

		_name = name;
	}

	public String getOriginalName() {
		return GetterUtil.getString(_originalName);
	}

	@JSON
	@Override
	public String getDescription() {
		if (_description == null) {
			return StringPool.BLANK;
		}
		else {
			return _description;
		}
	}

	@Override
	public void setDescription(String description) {
		_description = description;
	}

	@JSON
	@Override
	public boolean getAddedByLDAPImport() {
		return _addedByLDAPImport;
	}

	@Override
	public boolean isAddedByLDAPImport() {
		return _addedByLDAPImport;
	}

	@Override
	public void setAddedByLDAPImport(boolean addedByLDAPImport) {
		_addedByLDAPImport = addedByLDAPImport;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(PortalUtil.getClassNameId(
				UserGroup.class.getName()));
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			UserGroup.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public UserGroup toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (UserGroup)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		UserGroupImpl userGroupImpl = new UserGroupImpl();

		userGroupImpl.setMvccVersion(getMvccVersion());
		userGroupImpl.setUuid(getUuid());
		userGroupImpl.setUserGroupId(getUserGroupId());
		userGroupImpl.setCompanyId(getCompanyId());
		userGroupImpl.setUserId(getUserId());
		userGroupImpl.setUserName(getUserName());
		userGroupImpl.setCreateDate(getCreateDate());
		userGroupImpl.setModifiedDate(getModifiedDate());
		userGroupImpl.setParentUserGroupId(getParentUserGroupId());
		userGroupImpl.setName(getName());
		userGroupImpl.setDescription(getDescription());
		userGroupImpl.setAddedByLDAPImport(getAddedByLDAPImport());

		userGroupImpl.resetOriginalValues();

		return userGroupImpl;
	}

	@Override
	public int compareTo(UserGroup userGroup) {
		int value = 0;

		value = getName().compareTo(userGroup.getName());

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof UserGroup)) {
			return false;
		}

		UserGroup userGroup = (UserGroup)obj;

		long primaryKey = userGroup.getPrimaryKey();

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

	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		UserGroupModelImpl userGroupModelImpl = this;

		userGroupModelImpl._originalUuid = userGroupModelImpl._uuid;

		userGroupModelImpl._originalCompanyId = userGroupModelImpl._companyId;

		userGroupModelImpl._setOriginalCompanyId = false;

		userGroupModelImpl._setModifiedDate = false;

		userGroupModelImpl._originalParentUserGroupId = userGroupModelImpl._parentUserGroupId;

		userGroupModelImpl._setOriginalParentUserGroupId = false;

		userGroupModelImpl._originalName = userGroupModelImpl._name;

		userGroupModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<UserGroup> toCacheModel() {
		UserGroupCacheModel userGroupCacheModel = new UserGroupCacheModel();

		userGroupCacheModel.mvccVersion = getMvccVersion();

		userGroupCacheModel.uuid = getUuid();

		String uuid = userGroupCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			userGroupCacheModel.uuid = null;
		}

		userGroupCacheModel.userGroupId = getUserGroupId();

		userGroupCacheModel.companyId = getCompanyId();

		userGroupCacheModel.userId = getUserId();

		userGroupCacheModel.userName = getUserName();

		String userName = userGroupCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			userGroupCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			userGroupCacheModel.createDate = createDate.getTime();
		}
		else {
			userGroupCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			userGroupCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			userGroupCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		userGroupCacheModel.parentUserGroupId = getParentUserGroupId();

		userGroupCacheModel.name = getName();

		String name = userGroupCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			userGroupCacheModel.name = null;
		}

		userGroupCacheModel.description = getDescription();

		String description = userGroupCacheModel.description;

		if ((description != null) && (description.length() == 0)) {
			userGroupCacheModel.description = null;
		}

		userGroupCacheModel.addedByLDAPImport = getAddedByLDAPImport();

		return userGroupCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{mvccVersion=");
		sb.append(getMvccVersion());
		sb.append(", uuid=");
		sb.append(getUuid());
		sb.append(", userGroupId=");
		sb.append(getUserGroupId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", parentUserGroupId=");
		sb.append(getParentUserGroupId());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", description=");
		sb.append(getDescription());
		sb.append(", addedByLDAPImport=");
		sb.append(getAddedByLDAPImport());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(40);

		sb.append("<model><model-name>");
		sb.append("com.liferay.portal.model.UserGroup");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>mvccVersion</column-name><column-value><![CDATA[");
		sb.append(getMvccVersion());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userGroupId</column-name><column-value><![CDATA[");
		sb.append(getUserGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(getUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>parentUserGroupId</column-name><column-value><![CDATA[");
		sb.append(getParentUserGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>description</column-name><column-value><![CDATA[");
		sb.append(getDescription());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>addedByLDAPImport</column-name><column-value><![CDATA[");
		sb.append(getAddedByLDAPImport());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = UserGroup.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			UserGroup.class
		};
	private long _mvccVersion;
	private String _uuid;
	private String _originalUuid;
	private long _userGroupId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _parentUserGroupId;
	private long _originalParentUserGroupId;
	private boolean _setOriginalParentUserGroupId;
	private String _name;
	private String _originalName;
	private String _description;
	private boolean _addedByLDAPImport;
	private long _columnBitmask;
	private UserGroup _escapedModel;
}