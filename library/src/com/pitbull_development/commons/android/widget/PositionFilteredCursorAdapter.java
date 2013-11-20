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

import android.content.Context;
import android.database.Cursor;
import android.widget.CursorAdapter;

/**
 * <code>PositionFilteredCursorAdapter</code> is abstract implementation around
 * the PositionFiltered Cursor.
 */
public abstract class PositionFilteredCursorAdapter extends CursorAdapter
{
	/**
	 * Invokes super constructor with given Cursor wrapped in a
	 * PositionFilteredCursor.
	 * 
	 * @see CursorAdapter#CursorAdapter(Context, Cursor, boolean)
	 * 
	 * @param context
	 * @param c
	 * @param autoRequery
	 */
	public PositionFilteredCursorAdapter(Context context, Cursor c, boolean autoRequery)
	{
		super(context, new PositionFilteredCursor(c), autoRequery);
	}

	/**
	 * Adds this position to the filter. The row on this position will be from
	 * now on ignored by the cursor.
	 */
	public void filter(int position)
	{
		getCursor().filter(position);
		notifyDataSetChanged();
	}

	/**
	 * Clears the filter and notifyDataSetChanged()
	 */
	public void resetFilter()
	{
		getCursor().resetFilter();
		notifyDataSetChanged();
	}

	public PositionFilteredCursor getCursor()
	{
		return (PositionFilteredCursor)super.getCursor();
	}
}
