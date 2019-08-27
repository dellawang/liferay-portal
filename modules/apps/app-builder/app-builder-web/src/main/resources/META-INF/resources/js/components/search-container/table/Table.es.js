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

import ClayTable from '@clayui/table';
import React from 'react';
import {Link} from 'react-router-dom';
import DropDown from './DropDown.es';

const {Body, Cell, Head, Row} = ClayTable;

export default ({actions, columns, items}) => {
	return (
		<ClayTable hover={false}>
			<Head>
				<Row>
					{columns.map((column, index) => (
						<Cell
							className={index > 0 && 'table-cell-expand-smaller'}
							expanded={index === 0}
							headingCell
							key={index}
						>
							{column.value}
						</Cell>
					))}
					{actions.length > 0 && <Cell>{''}</Cell>}
				</Row>
			</Head>
			<Body>
				{items.map(item => (
					<Row data-testid="item" key={item.id}>
						{columns.map((column, index) => {
							let cell = item[column.key];

							if (Object.hasOwnProperty.call(column, 'link')) {
								cell = (
									<Link to={column.link(item)}>{cell}</Link>
								);
							}

							const {onClick} = column;

							return (
								<Cell
									className={
										index > 0 && 'table-cell-expand-smaller'
									}
									expanded={index === 0}
									headingTitle={index === 0}
									key={index}
									onClick={() => onClick && onClick(item)}
								>
									{cell}
								</Cell>
							);
						})}
						<Cell>
							<DropDown actions={actions} item={item} />
						</Cell>
					</Row>
				))}
			</Body>
		</ClayTable>
	);
};
