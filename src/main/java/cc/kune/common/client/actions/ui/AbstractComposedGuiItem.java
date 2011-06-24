/*
 *
 * Copyright (C) 2007-2011 The kune development team (see CREDITS for details)
 * This file is part of kune.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package cc.kune.common.client.actions.ui;

import java.util.List;

import cc.kune.common.client.actions.ui.bind.GuiBinding;
import cc.kune.common.client.actions.ui.bind.GuiProvider;
import cc.kune.common.client.actions.ui.descrip.GuiActionDescCollection;
import cc.kune.common.client.actions.ui.descrip.GuiActionDescrip;
import cc.kune.common.client.errors.UIException;

import com.google.gwt.user.client.ui.Composite;

public abstract class AbstractComposedGuiItem extends Composite implements IsActionExtensible {
  private final GuiProvider bindings;
  private GuiActionDescCollection guiItems;

  public AbstractComposedGuiItem(final GuiProvider bindings) {
    super();
    this.bindings = bindings;
  }

  public void add(final GuiActionDescCollection descriptors) {
    for (final GuiActionDescrip descriptor : descriptors) {
      add(descriptor);
    }
  }

  @Override
  public void add(final GuiActionDescrip... descriptors) {
    for (final GuiActionDescrip descriptor : descriptors) {
      add(descriptor);
    }
  }

  @Override
  public void add(final GuiActionDescrip descriptor) {
    getGuiItems().add(descriptor);
    beforeAddWidget(descriptor);
  }

  public void addActions(final List<GuiActionDescrip> descriptors) {
    for (final GuiActionDescrip descriptor : descriptors) {
      add(descriptor);
    }
  }

  @Override
  public void addAll(final GuiActionDescCollection descriptors) {
    for (final GuiActionDescrip descriptor : descriptors) {
      add(descriptor);
    }
  }

  protected abstract void addWidget(AbstractGuiItem item);

  protected void beforeAddWidget(final GuiActionDescrip descrip) {
    if (descrip.mustBeAdded()) {
      final GuiBinding binding = bindings.get(descrip.getType());
      if (binding == null) {
        throw new UIException("Unknown binding for: " + descrip);
      } else {
        final AbstractGuiItem item = binding.create(descrip);
        if (binding.shouldBeAdded()) {
          if (descrip.getPosition() == GuiActionDescrip.NO_POSITION) {
            addWidget(item);
          } else {
            insertWidget(item, descrip.getPosition());
          }
        }
      }
    }
  }

  @Override
  public void clear() {
    getGuiItems().clear();
  }

  public GuiActionDescCollection getGuiItems() {
    if (guiItems == null) {
      guiItems = new GuiActionDescCollection();
    }
    return guiItems;
  }

  protected abstract void insertWidget(AbstractGuiItem item, int position);
}
