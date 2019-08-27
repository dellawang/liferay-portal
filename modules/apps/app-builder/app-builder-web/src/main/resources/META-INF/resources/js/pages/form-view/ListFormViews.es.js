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

import moment from 'moment';
import React, {useContext} from 'react';
import {AppContext} from '../../AppContext.es';
import Button from '../../components/button/Button.es';
import ListView from '../../components/list-view/ListView.es';
import {confirmDelete} from '../../utils/client.es';

const EMPTY_STATE = {
	description: Liferay.Language.get(
		'create-one-or-more-forms-to-display-the-data-held-in-your-data-object'
	),
	title: Liferay.Language.get('there-are-no-form-views-yet')
};

export default ({
	match: {
		params: {dataDefinitionId}
	}
}) => {
	const {basePortletURL} = useContext(AppContext);

	const handleEditItem = item => {
		const editUrl = Liferay.Util.PortletURL.createRenderURL(
			basePortletURL,
			{
				dataDefinitionId,
				dataLayoutId: item.id,
				mvcRenderCommandName: '/edit_form_view'
			}
		);

		Liferay.Util.navigate(editUrl);
	};

	const ACTIONS = [
		{
			callback: item => Promise.resolve(handleEditItem(item)),
			name: Liferay.Language.get('edit')
		},
		{
			callback: confirmDelete('/o/data-engine/v1.0/data-layouts/'),
			name: Liferay.Language.get('delete')
		}
	];

	const COLUMNS = [
		{
			key: 'name',
			link: () => 'javascript:;',
			onClick: handleEditItem,
			sortable: true,
			value: Liferay.Language.get('name')
		},
		{
			key: 'dateCreated',
			sortable: true,
			value: Liferay.Language.get('create-date')
		},
		{
			asc: false,
			key: 'dateModified',
			sortable: true,
			value: Liferay.Language.get('modified-date')
		}
	];

	const addURL = Liferay.Util.PortletURL.createRenderURL(basePortletURL, {
		dataDefinitionId,
		mvcRenderCommandName: '/edit_form_view'
	});

	return (
		<ListView
			actions={ACTIONS}
			addButton={() => (
				<Button
					className="nav-btn nav-btn-monospaced navbar-breakpoint-down-d-none"
					onClick={() => Liferay.Util.navigate(addURL)}
					symbol="plus"
					tooltip={Liferay.Language.get('new-form-view')}
				/>
			)}
			columns={COLUMNS}
			emptyState={EMPTY_STATE}
			endpoint={`/o/data-engine/v1.0/data-definitions/${dataDefinitionId}/data-layouts`}
			formatter={items =>
				items.map(item => ({
					dataDefinitionId,
					dateCreated: moment(item.dateCreated).fromNow(),
					dateModified: moment(item.dateModified).fromNow(),
					id: item.id,
					name: item.name.en_US
				}))
			}
		/>
	);
};
