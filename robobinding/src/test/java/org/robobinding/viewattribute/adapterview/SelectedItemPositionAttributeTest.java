/**
 * Copyright 2011 Cheng Wei, Robert Taylor
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions
 * and limitations under the License.
 */
package org.robobinding.viewattribute.adapterview;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.robobinding.property.ValueModel;
import org.robobinding.viewattribute.AbstractPropertyViewAttributeTest;
import org.robobinding.viewattribute.RandomValues;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 *
 * @since 1.0
 * @version $Revision: 1.0 $
 * @author Robert Taylor
 */
public class SelectedItemPositionAttributeTest extends AbstractPropertyViewAttributeTest<ListView, SelectedItemPositionAttribute>
{
	private ArrayAdapter<String> arrayAdapter;
	private ValueModel<Integer> valueModel;
	
	@Before
	public void setUp()
	{
		valueModel = twoWayBindToProperty(Integer.class);
		arrayAdapter = new MockArrayAdapter();
		view.setAdapter(arrayAdapter);
	}
	
	@Test
	public void whenUpdatingValueModel_ThenSelectedItemShouldBeUpdated()
	{
		int index = RandomValues.anyIndex(arrayAdapter);
		valueModel.setValue(index);
		
		assertThat(view.getSelectedItemPosition(), is(index));
	}
	
	@Test
	public void whenUpdatingSelectedItem_ThenPresentationModelShouldBeUpdated()
	{
		int index = RandomValues.anyIndex(arrayAdapter);
		view.setSelection(index);
		
		assertThat(valueModel.getValue(), is(index));
	}
	
	@Test
	@Ignore
	//TODO Enable this test when the appropriate support has been added to Robolectric
	public void whenAllItemsAreRemovedFromAdapter_ThenSelectedItemPositionShouldEqualInvalidPosition()
	{
		arrayAdapter.clear();
		arrayAdapter.notifyDataSetChanged();
		
		assertThat(valueModel.getValue(), is(AdapterView.INVALID_POSITION));
	}
}
