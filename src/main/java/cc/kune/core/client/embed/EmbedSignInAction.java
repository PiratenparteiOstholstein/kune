/*
 *
 * Copyright (C) 2007-2013 Licensed to the Comunes Association (CA) under
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

package cc.kune.core.client.embed;

import cc.kune.common.client.actions.Action;
import cc.kune.common.client.actions.ActionEvent;
import cc.kune.core.client.auth.SignIn;
import cc.kune.core.client.i18n.I18nUITranslationService;
import cc.kune.core.client.sitebar.AbstractSignInAction;
import cc.kune.core.client.state.Session;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * The Class SitebarSignInAction.
 * 
 * @author vjrj@ourproject.org (Vicente J. Ruiz Jurado)
 */
public class EmbedSignInAction extends AbstractSignInAction {

  private final Session session;
  private final Provider<SignIn> signIn;

  /**
   * Instantiates a new sitebar sign in action.
   * 
   * @param stateManager
   *          the state manager
   * @param i18n
   *          the i18n
   * @param session
   *          the session
   */
  @Inject
  public EmbedSignInAction(final I18nUITranslationService i18n, final Session session,
      final Provider<SignIn> signIn) {
    super();
    this.session = session;
    this.signIn = signIn;
    putValue(Action.NAME, i18n.t("Sign in"));
    putValue(
        Action.TOOLTIP,
        i18n.t("Please sign in or register to get full access to [%s] tools and contents",
            i18n.getSiteCommonName()));
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * cc.kune.common.client.actions.ActionListener#actionPerformed(cc.kune.common
   * .client.actions.ActionEvent)
   */
  @Override
  public void actionPerformed(final ActionEvent event) {
    final String siteOnOverLogo = session.getInitData().getSiteLogoUrlOnOver();
    signIn.get().setHeaderLogo(siteOnOverLogo);
    signIn.get().showSignInDialog();
  }

}