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

package com.liferay.asset.taglib.internal.display.context;

import com.liferay.asset.display.page.constants.AssetDisplayPageConstants;
import com.liferay.asset.display.page.item.selector.criterion.AssetDisplayPageSelectorCriterion;
import com.liferay.asset.display.page.model.AssetDisplayPageEntry;
import com.liferay.asset.display.page.service.AssetDisplayPageEntryLocalServiceUtil;
import com.liferay.asset.kernel.AssetRendererFactoryRegistryUtil;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.ClassType;
import com.liferay.asset.kernel.model.ClassTypeReader;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.item.selector.ItemSelector;
import com.liferay.item.selector.ItemSelectorReturnType;
import com.liferay.item.selector.criteria.UUIDItemSelectorReturnType;
import com.liferay.layout.item.selector.criterion.LayoutItemSelectorCriterion;
import com.liferay.layout.page.template.model.LayoutPageTemplateEntry;
import com.liferay.layout.page.template.service.LayoutPageTemplateEntryLocalServiceUtil;
import com.liferay.layout.page.template.service.LayoutPageTemplateEntryServiceUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.bean.BeanParamUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactoryUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Pavel Savinov
 */
public class SelectAssetDisplayPageDisplayContext {

	public SelectAssetDisplayPageDisplayContext(
		HttpServletRequest request, LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse) {

		_request = request;
		_liferayPortletRequest = liferayPortletRequest;
		_liferayPortletResponse = liferayPortletResponse;
	}

	public AssetDisplayPageEntry getAssetDisplayPageEntry()
		throws PortalException {

		if (_assetDisplayPageEntry != null) {
			return _assetDisplayPageEntry;
		}

		if (journalArticle == null) {
			return _assetDisplayPageEntry;
		}

		AssetEntry assetEntry = AssetEntryLocalServiceUtil.fetchEntry(
			journalArticle.getGroupId(),
			journalArticle.getArticleResourceUuid());

		if (assetEntry == null) {
			return _assetDisplayPageEntry;
		}

		AssetDisplayPageEntry assetDisplayPageEntry =
			AssetDisplayPageEntryLocalServiceUtil.fetchAssetDisplayPageEntry(
				assetEntry.getGroupId(), assetEntry.getClassNameId(),
				assetEntry.getClassPK());

		if (assetDisplayPageEntry != null) {
			_assetDisplayPageEntry = assetDisplayPageEntry;
		}

		return _assetDisplayPageEntry;
	}

	public long getAssetDisplayPageId() throws PortalException {
		if (_assetDisplayPageId != null) {
			return _assetDisplayPageId;
		}

		long assetDisplayPageId = 0;

		AssetDisplayPageEntry assetDisplayPageEntry =
			getAssetDisplayPageEntry();

		if (assetDisplayPageEntry != null) {
			assetDisplayPageId =
				assetDisplayPageEntry.getLayoutPageTemplateEntryId();
		}

		_assetDisplayPageId = assetDisplayPageId;

		return _assetDisplayPageId;
	}

	public int getAssetDisplayPageType() throws PortalException {
		if (_displayPageType != null) {
			return _displayPageType;
		}

		String articleId = ParamUtil.getString(_request, "articleId");

		if (Validator.isNull(articleId)) {
			_displayPageType = AssetDisplayPageConstants.TYPE_DEFAULT;

			return _displayPageType;
		}

		AssetDisplayPageEntry assetDisplayPageEntry =
			getAssetDisplayPageEntry();

		if (assetDisplayPageEntry == null) {
			_displayPageType = AssetDisplayPageConstants.TYPE_NONE;

			return _displayPageType;
		}

		_displayPageType = assetDisplayPageEntry.getType();

		return _displayPageType;
	}

	public String getAssetTypeName() throws PortalException {
		if (Validator.isNotNull(_assetTypeName)) {
			return _assetTypeName;
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)_request.getAttribute(
			WebKeys.THEME_DISPLAY);

		AssetRendererFactory assetRendererFactory =
			AssetRendererFactoryRegistryUtil.
				getAssetRendererFactoryByClassNameId(getClassNameId());

		_assetTypeName = assetRendererFactory.getTypeName(
			themeDisplay.getLocale());

		if (getClassTypeId() > 0) {
			ClassTypeReader classTypeReader =
				assetRendererFactory.getClassTypeReader();

			ClassType classType = classTypeReader.getClassType(
				getClassTypeId(), themeDisplay.getLocale());

			_assetTypeName = classType.getName();
		}

		return _assetTypeName;
	}

	public long getClassNameId() {
		if (_classNameId != null) {
			return _classNameId;
		}

		_classNameId = GetterUtil.getLong(
			_request.getAttribute(
				"liferay-asset:select-asset-display-page:classNameId"));

		return _classNameId;
	}

	public long getClassTypeId() {
		if (_classTypeId != null) {
			return _classTypeId;
		}

		_classTypeId = GetterUtil.getLong(
			_request.getAttribute(
				"liferay-asset:select-asset-display-page:classTypeId"));

		return _classTypeId;
	}

	public String getDefaultAssetDisplayPageName() throws PortalException {
		if (_defaultAssetDisplayPageName != null) {
			return _defaultAssetDisplayPageName;
		}

		LayoutPageTemplateEntry layoutPageTemplateEntry = null;

		layoutPageTemplateEntry =
			LayoutPageTemplateEntryServiceUtil.
				fetchDefaultLayoutPageTemplateEntry(
					_getGroupId(), getClassNameId(), getClassTypeId());

		if (layoutPageTemplateEntry != null) {
			_defaultAssetDisplayPageName = layoutPageTemplateEntry.getName();
		}

		return _defaultAssetDisplayPageName;
	}

	public String getDisplayPageItemSelectorURL() throws PortalException {
		ItemSelector itemSelector = (ItemSelector)_request.getAttribute(
			JournalWebKeys.ITEM_SELECTOR);

		DDMStructure ddmStructure = (DDMStructure)_request.getAttribute(
			"edit_article.jsp-structure");

		long displayPageClassNameId = PortalUtil.getClassNameId(
			JournalArticle.class.getName());

		AssetDisplayPageSelectorCriterion assetDisplayPageSelectorCriterion =
			new AssetDisplayPageSelectorCriterion();

		assetDisplayPageSelectorCriterion.setClassNameId(
			displayPageClassNameId);
		assetDisplayPageSelectorCriterion.setClassTypeId(
			ddmStructure.getStructureId());

		List<ItemSelectorReturnType>
			desiredAssetDisplayPageItemSelectorReturnTypes = new ArrayList<>();

		desiredAssetDisplayPageItemSelectorReturnTypes.add(
			new UUIDItemSelectorReturnType());

		assetDisplayPageSelectorCriterion.setDesiredItemSelectorReturnTypes(
			desiredAssetDisplayPageItemSelectorReturnTypes);

		LayoutItemSelectorCriterion layoutItemSelectorCriterion =
			new LayoutItemSelectorCriterion();

		layoutItemSelectorCriterion.setCheckDisplayPage(true);

		List<ItemSelectorReturnType> desiredItemSelectorReturnTypes =
			new ArrayList<>();

		desiredItemSelectorReturnTypes.add(new UUIDItemSelectorReturnType());

		layoutItemSelectorCriterion.setDesiredItemSelectorReturnTypes(
			desiredItemSelectorReturnTypes);

		String eventName =
			_liferayPortletResponse.getNamespace() + "selectDisplayPage";

		PortletURL itemSelectorURL = itemSelector.getItemSelectorURL(
			RequestBackedPortletURLFactoryUtil.create(_liferayPortletRequest),
			eventName, assetDisplayPageSelectorCriterion,
			layoutItemSelectorCriterion);

		itemSelectorURL.setParameter("layoutUuid", getLayoutUuid());

		return itemSelectorURL.toString();
	}

	public String getDisplayPageName() throws Exception {
		String assetDisplayPageName = _getAssetDisplayPageName();

		if (Validator.isNotNull(assetDisplayPageName)) {
			return assetDisplayPageName;
		}

		String layoutUuid = getLayoutUuid();

		if (Validator.isNull(layoutUuid)) {
			return StringPool.BLANK;
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)_request.getAttribute(
			WebKeys.THEME_DISPLAY);

		Layout selLayout = LayoutLocalServiceUtil.fetchLayoutByUuidAndGroupId(
			layoutUuid, themeDisplay.getSiteGroupId(), false);

		if (selLayout == null) {
			selLayout = LayoutLocalServiceUtil.fetchLayoutByUuidAndGroupId(
				layoutUuid, themeDisplay.getSiteGroupId(), true);
		}

		if (selLayout != null) {
			return _getLayoutBreadcrumb(selLayout);
		}

		return StringPool.BLANK;
	}

	public Group getGroup() throws PortalException {
		if (_group != null) {
			return _group;
		}

		_group = GroupLocalServiceUtil.getGroup(_getGroupId());

		return _group;
	}

	public String getLayoutUuid() throws PortalException {
		JournalArticle article = getArticle();

		String layoutUuid = BeanParamUtil.getString(
			getArticle(), _request, "layoutUuid");

		boolean changeStructure = GetterUtil.getBoolean(
			_request.getAttribute("edit_article.jsp-changeStructure"));

		if (changeStructure && (article != null)) {
			layoutUuid = article.getLayoutUuid();
		}

		return layoutUuid;
	}

	public String getURLViewInContext() throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay)_request.getAttribute(
			WebKeys.THEME_DISPLAY);

		AssetRendererFactory<JournalArticle> assetRendererFactory =
			AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClass(
				JournalArticle.class);

		JournalArticle article = getArticle();

		AssetRenderer<JournalArticle> assetRenderer =
			assetRendererFactory.getAssetRenderer(article.getResourcePrimKey());

		return assetRenderer.getURLViewInContext(
			_liferayPortletRequest, _liferayPortletResponse,
			themeDisplay.getURLCurrent());
	}

	public boolean isAssetDisplayPageTypeDefault() throws PortalException {
		if (getAssetDisplayPageType() ==
				AssetDisplayPageConstants.TYPE_DEFAULT) {

			return true;
		}

		return false;
	}

	public boolean isAssetDisplayPageTypeNone() throws PortalException {
		if (getAssetDisplayPageType() == AssetDisplayPageConstants.TYPE_NONE) {
			return true;
		}

		return false;
	}

	public boolean isAssetDisplayPageTypeSpecific() throws PortalException {
		if (getAssetDisplayPageType() ==
				AssetDisplayPageConstants.TYPE_SPECIFIC) {

			return true;
		}

		return false;
	}

	public boolean isURLViewInContext() throws Exception {
		if (getArticle() == null) {
			return false;
		}

		if (Validator.isNull(getLayoutUuid())) {
			return false;
		}

		JournalArticle article = getArticle();

		if (article.isNew()) {
			return false;
		}

		long classNameId = ParamUtil.getLong(_request, "classNameId");

		if (classNameId != JournalArticleConstants.CLASSNAME_ID_DEFAULT) {
			return false;
		}

		if (Validator.isNull(getURLViewInContext())) {
			return false;
		}

		return true;
	}

	private String _getAssetDisplayPageName() throws PortalException {
		long assetDisplayPageId = getAssetDisplayPageId();

		if (assetDisplayPageId == 0) {
			return StringPool.BLANK;
		}

		LayoutPageTemplateEntry layoutPageTemplateEntry =
			LayoutPageTemplateEntryLocalServiceUtil.
				fetchLayoutPageTemplateEntry(assetDisplayPageId);

		if (layoutPageTemplateEntry == null) {
			return StringPool.BLANK;
		}

		return layoutPageTemplateEntry.getName();
	}

	private long _getGroupId() {
		return GetterUtil.getLong(
			_request.getAttribute(
				"liferay-asset:select-asset-display-page:groupId"));
	}

	private String _getLayoutBreadcrumb(Layout layout) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay)_request.getAttribute(
			WebKeys.THEME_DISPLAY);

		Locale locale = themeDisplay.getLocale();

		List<Layout> ancestors = layout.getAncestors();

		StringBundler sb = new StringBundler(4 * ancestors.size() + 5);

		if (layout.isPrivateLayout()) {
			sb.append(LanguageUtil.get(_request, "private-pages"));
		}
		else {
			sb.append(LanguageUtil.get(_request, "public-pages"));
		}

		sb.append(StringPool.SPACE);
		sb.append(StringPool.GREATER_THAN);
		sb.append(StringPool.SPACE);

		Collections.reverse(ancestors);

		for (Layout ancestor : ancestors) {
			sb.append(HtmlUtil.escape(ancestor.getName(locale)));
			sb.append(StringPool.SPACE);
			sb.append(StringPool.GREATER_THAN);
			sb.append(StringPool.SPACE);
		}

		sb.append(HtmlUtil.escape(layout.getName(locale)));

		return sb.toString();
	}

	private AssetDisplayPageEntry _assetDisplayPageEntry;
	private Long _assetDisplayPageId;
	private String _assetTypeName;
	private Long _classNameId;
	private Long _classTypeId;
	private String _defaultAssetDisplayPageName;
	private Integer _displayPageType;
	private Group _group;
	private final LiferayPortletRequest _liferayPortletRequest;
	private final LiferayPortletResponse _liferayPortletResponse;
	private final HttpServletRequest _request;

}