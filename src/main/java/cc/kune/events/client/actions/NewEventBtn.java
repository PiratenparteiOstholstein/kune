/*
 *
 * Copyright (C) 2007-2015 Licensed to the Comunes Association (CA) under
 * one or more contributor license agreements (see COPYRIGHT for details).
 * The CA licenses this file to you under the GNU Affero General Public
 * License version 3, (the "License"); you may not use this file except in
 * compliance with the License. This file is part of kune.
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
package cc.kune.events.client.actions;

import cc.kune.common.client.actions.ui.descrip.ButtonDescriptor;
import cc.kune.common.client.shortcuts.GlobalShortcutRegister;
import cc.kune.common.shared.i18n.I18nTranslationService;
import cc.kune.core.client.resources.iconic.IconicResources;
import cc.kune.events.client.actions.EventAddMenuItem.EventAddAction;

import com.google.inject.Inject;

// TODO: Auto-generated Javadoc
/**
 * The Class NewEventBtn.
 * 
 * @author vjrj@ourproject.org (Vicente J. Ruiz Jurado)
 */
public class NewEventBtn extends ButtonDescriptor {

  /**
   * Instantiates a new new event btn.
   * 
   * @param i18n
   *          the i18n
   * @param action
   *          the action
   * @param res
   *          the res
   * @param shorcutReg
   *          the shorcut reg
   */
  @Inject
  public NewEventBtn(final I18nTranslationService i18n, final EventAddAction action,
      final IconicResources res, final GlobalShortcutRegister shorcutReg) {
    super(i18n.t("New event"), action);
    withIcon(res.eventAdd()).withToolTip(i18n.t("Create a New Event"));
    action.setOpenAfterCreation(true);
  }

}
