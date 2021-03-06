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
package cc.kune.core.client.sn.actions;

import cc.kune.common.client.actions.ui.descrip.MenuDescriptor;
import cc.kune.common.client.actions.ui.descrip.MenuRadioItemDescriptor;
import cc.kune.core.shared.domain.AdmissionType;

import com.google.inject.Inject;

// TODO: Auto-generated Javadoc
/**
 * The Class MembersModerationMenuItem.
 * 
 * @author vjrj@ourproject.org (Vicente J. Ruiz Jurado)
 */
public class MembersModerationMenuItem extends MenuRadioItemDescriptor {

  /** The Constant GROUP_MEMBERS_MODERATION. */
  private static final String GROUP_MEMBERS_MODERATION = "k-sn-gmembers-vis";

  /**
   * Instantiates a new members moderation menu item.
   * 
   * @param parent
   *          the parent
   * @param action
   *          the action
   */
  @Inject
  public MembersModerationMenuItem(final MenuDescriptor parent, final MembersModerationAction action) {
    super(parent, action, GROUP_MEMBERS_MODERATION);
  }

  /**
   * With moderation.
   * 
   * @param admissionType
   *          the admission type
   * @return the menu radio item descriptor
   */
  public MenuRadioItemDescriptor withModeration(final AdmissionType admissionType) {
    ((MembersModerationAction) getAction()).setAdmissionType(admissionType);
    return this;
  }

}
