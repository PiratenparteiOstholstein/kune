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
package cc.kune.gspace.client.actions;

import cc.kune.common.client.actions.ui.descrip.MenuDescriptor;
import cc.kune.core.client.events.AccessRightsChangedEvent;
import cc.kune.core.client.events.AccessRightsChangedEvent.AccessRightsChangedHandler;
import cc.kune.core.client.state.AccessRightsClientManager;

public class AbstractEditorsMenu extends MenuDescriptor {

  public AbstractEditorsMenu(final AccessRightsClientManager rightsManager) {
    super();
    this.withStyles("k-button, k-btn, k-5corners, k-def-docbtn, k-fl");
    rightsManager.onRightsChanged(true, new AccessRightsChangedHandler() {
      @Override
      public void onAccessRightsChanged(final AccessRightsChangedEvent event) {
        AbstractEditorsMenu.this.setVisible(event.getCurrentRights().isEditable());
      }
    });
  }
}
