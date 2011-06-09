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
package cc.kune.client;

import cc.kune.barters.client.BartersClientTool;
import cc.kune.blogs.client.BlogsClientTool;
import cc.kune.chat.client.ChatClientTool;
import cc.kune.core.client.init.AppStartEvent;
import cc.kune.core.client.init.AppStartEvent.AppStartHandler;
import cc.kune.core.client.state.Session;
import cc.kune.docs.client.DocsClientTool;
import cc.kune.meets.client.MeetingsClientTool;
import cc.kune.wiki.client.WikiClientTool;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class OnAppStartFactory {

  @Inject
  public OnAppStartFactory(final Session session, final Provider<DocsClientTool> docsClientTool,
      final Provider<BlogsClientTool> blogsClientTool,
      final Provider<BartersClientTool> bartersClientTool,
      final Provider<MeetingsClientTool> meetsClientTool,
      final Provider<ChatClientTool> chatsClientTool, final Provider<WikiClientTool> wikiClientTool) {
    session.onAppStart(true, new AppStartHandler() {
      @Override
      public void onAppStart(final AppStartEvent event) {
      }
    });
  }
}
