/*
* Licensed to the Apache Software Foundation (ASF) under one or more
* contributor license agreements. See the NOTICE file distributed with
* this work for additional information regarding copyright ownership.
* The ASF licenses this file to You under the Apache License, Version 2.0
* (the "License"); you may not use this file except in compliance with
* the License. You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.pitbull_development.commons.android.widget;

import java.util.ArrayList;
import android.database.Cursor;
import android.database.CursorWrapper;

/**
 * PositionFilteredCursor is a CursorWrapper that performs position filtering on
 * the wrapped cursor.
 * 
 * @author pblomsma
 * 
 */
public class PositionFilteredCursor extends CursorWrapper
{
	/**
	 * Projections is an Arraylist containing the cursorpositions that are not
	 * filtered.
	 */
	private final ArrayList<Integer> _projections;

	/**
	 * Index pointing to an element in the _projections.
	 */
	private int _projectionIndex;

	/**
	 * @param cursor
	 */
	public PositionFilteredCursor(Cursor cursor)
	{
		super(cursor);
		_projections = new ArrayList<Integer>();
		_projectionIndex = cursor.getPosition();
		createFreshIndex();
	}

	/**
	 * Adds a filtered position to the cursor. The row, to which the given
	 * position is pointing, will be from now on ignored by this cursor.
	 * 
	 * @param position
	 */
	public void filter(int position)
	{
		_projections.remove(position);
	}

	/**
	 * Removes all filters.
	 */
	public void resetFilter()
	{
		createFreshIndex();
	}

	@Override
	public int getCount()
	{
		return _projections.size();
	}

	@Override
	public boolean isAfterLast()
	{
		return (getPosition() > getCount());
	}

	@Override
	public boolean isBeforeFirst()
	{
		return (getPosition() < 0);
	}

	@Override
	public boolean isFirst()
	{
		return (getPosition() == 0);
	}

	@Override
	public boolean isLast()
	{
		return (getPosition() == getCount());
	}

	@Override
	public boolean move(int offset)
	{
		return moveToPosition(getPosition() + offset);
	}

	@Override
	public int getPosition()
	{
		return _projectionIndex;
	}

	@Override
	public boolean moveToFirst()
	{
		return moveToPosition(0);
	}

	@Override
	public boolean moveToLast()
	{
		return moveToPosition(getCount());
	}

	@Override
	public boolean moveToNext()
	{
		return moveToPosition(getPosition() + 1);
	}

	@Override
	public boolean moveToPosition(int position)
	{
		if ((position > -1) && (position < _projections.size()))
		{
			_projectionIndex = position;
			return super.moveToPosition(_projections.get(_projectionIndex));
		}
		return false;
	}

	@Override
	public boolean moveToPrevious()
	{
		return moveToPosition(getPosition() - 1);
	}

	private void createFreshIndex()
	{
		_projections.clear();
		for (int i = 0; i < super.getCount(); i++)
		{
			_projections.add(i);
		}
	}
}
