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

package com.liferay.commerce.term.model.impl;

import com.liferay.commerce.term.model.CommerceTerm;
import com.liferay.commerce.term.service.CommerceTermLocalServiceUtil;

/**
 * The extended model base implementation for the CommerceTerm service. Represents a row in the &quot;CommerceTerm&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommerceTermImpl}.
 * </p>
 *
 * @author Luca Pellizzon
 * @see CommerceTermImpl
 * @see CommerceTerm
 * @generated
 */
public abstract class CommerceTermBaseImpl
	extends CommerceTermModelImpl implements CommerceTerm {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce term model instance should use the <code>CommerceTerm</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			CommerceTermLocalServiceUtil.addCommerceTerm(this);
		}
		else {
			CommerceTermLocalServiceUtil.updateCommerceTerm(this);
		}
	}

}