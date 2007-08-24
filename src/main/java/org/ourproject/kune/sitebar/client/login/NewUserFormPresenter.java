/*
 *
 * Copyright (C) 2007 The kune development team (see CREDITS for details)
 * This file is part of kune.
 *
 * Kune is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * Kune is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package org.ourproject.kune.sitebar.client.login;

import org.ourproject.kune.platf.client.View;
import org.ourproject.kune.sitebar.client.Site;
import org.ourproject.kune.sitebar.client.rpc.SiteBarService;
import org.ourproject.kune.sitebar.client.rpc.SiteBarServiceAsync;

import com.google.gwt.user.client.rpc.AsyncCallback;

public class NewUserFormPresenter implements NewUserForm {
    NewUserFormView view;
    final LoginListener listener;

    // private boolean loginButtonEnabled;

    public NewUserFormPresenter(final LoginListener listener) {
	this.listener = listener;
	// //this.loginButtonEnabled = false;
    }

    public void init(final NewUserFormView loginview) {
	this.view = loginview;
	reset();
    }

    public void doCancel() {
	reset();
	listener.onLoginCancelled();
    }

    public void doRegister() {
	final String shortName = view.getShortName();
	final String name = view.getName();
	final String email = view.getEmail();
	final String passwd = view.getPassword();

	SiteBarServiceAsync siteBarService = SiteBarService.App.getInstance();
	siteBarService.createUser(shortName, name, email, passwd, new AsyncCallback() {

	    public void onFailure(final Throwable arg0) {
		// i18n: Error in user registration
		Site.important("Error in registration");
	    }

	    public void onSuccess(final Object response) {
		String hash = (String) response;
		listener.userLoggedIn(name, hash);
		// TODO: Establecer sesión de este usuario
	    }
	});
    }

    public View getView() {
	return view;
    }

    private void reset() {
	view.clearData();
    }

}
