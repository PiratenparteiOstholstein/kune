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
package cc.kune.chat.client;

import cc.kune.common.shared.i18n.I18nTranslationService;
import cc.kune.core.client.avatar.SmallAvatarDecorator;
import cc.kune.core.client.resources.CoreResources;

import com.calclab.emite.core.client.xmpp.session.XmppSession;
import com.calclab.emite.im.client.presence.PresenceManager;
import com.calclab.emite.im.client.roster.XmppRoster;
import com.google.inject.Inject;

// TODO: Auto-generated Javadoc
/**
 * The Class SmallAvatarDecoratorImpl.
 *
 * @author vjrj@ourproject.org (Vicente J. Ruiz Jurado)
 */
public class SmallAvatarDecoratorImpl extends AvatarDecoratorImpl implements SmallAvatarDecorator {

  /**
   * Instantiates a new small avatar decorator impl.
   *
   * @param i18n
   *          the i18n
   * @param res
   *          the res
   * @param session
   *          the session
   * @param presenceManager
   *          the presence manager
   * @param roster
   *          the roster
   * @param chatClient
   *          the chat client
   */
  @Inject
  public SmallAvatarDecoratorImpl(final I18nTranslationService i18n, final CoreResources res,
      final XmppSession session, final PresenceManager presenceManager, final XmppRoster roster,
      final ChatClient chatClient) {
    super(i18n, session, presenceManager, roster, chatClient, res.chatDotBusySmall(),
        res.chatDotAwaySmall(), res.chatDotAwaySmall(), res.chatDotExtendedAwaySmall(),
        res.chatDotAvailableSmall());
    // setImagePosition(23, -9, -8);
    super.addStyleNameToImage("k-avatar-small-decorator");
  }
}
