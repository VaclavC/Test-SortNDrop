package com.disnel.test;

import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.googlecode.wicket.jquery.core.IJQueryWidget.JQueryWidget;
import com.googlecode.wicket.jquery.ui.interaction.droppable.DroppableAdapter;
import com.googlecode.wicket.jquery.ui.interaction.droppable.DroppableBehavior;
import com.googlecode.wicket.jquery.ui.interaction.sortable.Sortable;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.model.IModel;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;

public class SortNDrop extends WebPage
{
	private static final long serialVersionUID = 1L;

	private static final List<String> items = new LinkedList<>(Arrays.asList(
			"One", "Two", "Three", "Four", "Five", "Six", "Seven")); 
	
	public SortNDrop(final PageParameters parameters)
	{
		super(parameters);

		add(new Sortable<String>("sortable", items)
		{
			@Override
			protected HashListView<String> newListView(IModel<List<String>> model)
			{
				return new HashListView<String>("item", model)
				{
					@Override
					protected void populateItem(ListItem<String> item)
					{
						item.add(new Label("content", item.getModelObject()));
					}
				};
			}
		});
		
		Component dropCont;
		add(dropCont = new WebMarkupContainer("droptarget"));
		
		dropCont.add(new DroppableBehavior(JQueryWidget.getSelector(dropCont),
				new DroppableAdapter()
				{
					@Override
					public void onDrop(AjaxRequestTarget target, Component component)
					{
						System.out.println("component: " + component);
					}
				}));
	}
}
