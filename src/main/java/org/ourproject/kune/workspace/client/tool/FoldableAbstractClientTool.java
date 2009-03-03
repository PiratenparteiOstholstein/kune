/*
 *
 * Copyright (C) 2007-2009 The kune development team (see CREDITS for details)
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
package org.ourproject.kune.workspace.client.tool;

import org.ourproject.kune.platf.client.dto.BasicMimeTypeDTO;
import org.ourproject.kune.platf.client.registry.ContentCapabilitiesRegistry;
import org.ourproject.kune.workspace.client.skel.WorkspaceSkeleton;
import org.ourproject.kune.workspace.client.themes.WsThemePresenter;

public abstract class FoldableAbstractClientTool extends AbstractClientTool {
    public static final String UPLOADEDFILE_SUFFIX = "uploaded";

    protected final ContentCapabilitiesRegistry contentCapabilitiesRegistry;

    public FoldableAbstractClientTool(String shortName, String longName, ToolSelector toolSelector,
            WsThemePresenter wsThemePresenter, WorkspaceSkeleton ws,
            ContentCapabilitiesRegistry contentCapabilitiesRegistry) {
        super(shortName, longName, toolSelector, wsThemePresenter, ws);
        this.contentCapabilitiesRegistry = contentCapabilitiesRegistry;
    }

    public void registerContentTypeIcon(final String typeId, final BasicMimeTypeDTO mimeType, final String iconUrl) {
        contentCapabilitiesRegistry.getIconsRegistry().registerContentTypeIcon(typeId, mimeType, iconUrl);
    }

    public void registerContentTypeIcon(final String contentTypeId, final String iconUrl) {
        contentCapabilitiesRegistry.getIconsRegistry().registerContentTypeIcon(contentTypeId, iconUrl);
    }

    protected void registerAclEditableTypes(String... typeIds) {
        contentCapabilitiesRegistry.getAclEditable().register(typeIds);
    }

    protected void registerAuthorableTypes(String... typeIds) {
        contentCapabilitiesRegistry.getAuthorable().register(typeIds);
    }

    protected void registerComentableTypes(String... typeIds) {
        contentCapabilitiesRegistry.getComentable().register(typeIds);
    }

    protected void registerDragableTypes(String... typeIds) {
        contentCapabilitiesRegistry.getDragable().register(typeIds);
    }

    protected void registerDropableTypes(String... typeIds) {
        contentCapabilitiesRegistry.getDropable().register(typeIds);
    }

    protected void registerEmailSubscribeAbleTypes(String... typeIds) {
        contentCapabilitiesRegistry.getEmailSubscribeAble().register(typeIds);
    }

    protected void registerLicensableTypes(String... typeIds) {
        contentCapabilitiesRegistry.getLicensable().register(typeIds);
    }

    protected void registerPublishModerableTypes(String... typeIds) {
        contentCapabilitiesRegistry.getPublishModerable().register(typeIds);
    }

    protected void registerRateableTypes(String... typeIds) {
        contentCapabilitiesRegistry.getRateable().register(typeIds);
    }

    protected void registerRenamableTypes(String... typeIds) {
        contentCapabilitiesRegistry.getRenamable().register(typeIds);
    }

    protected void registerTageableTypes(String... typeIds) {
        contentCapabilitiesRegistry.getTageable().register(typeIds);
    }

    protected void registerTranslatableTypes(String... typeIds) {
        contentCapabilitiesRegistry.getTranslatable().register(typeIds);
    }

    protected void registerUploadTypesAndMimes(String typeUploadedfile) {
        registerContentTypeIcon(typeUploadedfile, new BasicMimeTypeDTO("image"), "images/nav/picture.png");
        registerContentTypeIcon(typeUploadedfile, new BasicMimeTypeDTO("video"), "images/nav/film.png");
        registerContentTypeIcon(typeUploadedfile, new BasicMimeTypeDTO("application", "pdf"), "images/nav/page_pdf.png");
        registerContentTypeIcon(typeUploadedfile, new BasicMimeTypeDTO("application", "zip"), "images/nav/page_zip.png");
        registerContentTypeIcon(typeUploadedfile, new BasicMimeTypeDTO("application", "zip"), "images/nav/page_zip.png");
        registerContentTypeIcon(typeUploadedfile, new BasicMimeTypeDTO("text"), "images/nav/page_text.png");
        registerContentTypeIcon(typeUploadedfile, new BasicMimeTypeDTO("application", "msword"),
                "images/nav/page_word.png");
        registerContentTypeIcon(typeUploadedfile, new BasicMimeTypeDTO("application", "excel"),
                "images/nav/page_excel.png");
        registerContentTypeIcon(typeUploadedfile, new BasicMimeTypeDTO("application", "mspowerpoint"),
                "images/nav/page_pps.png");
        registerContentTypeIcon(typeUploadedfile, "images/nav/page.png");
    }

    protected void registerVersionableTypes(String... typeIds) {
        contentCapabilitiesRegistry.getVersionable().register(typeIds);
    }

    protected void registerXmppComentableTypes(String... typeIds) {
        contentCapabilitiesRegistry.getXmppComentable().register(typeIds);
    }

    protected void registerXmppNotifyCapableTypes(String... typeIds) {
        contentCapabilitiesRegistry.getXmppNotificyCapable().register(typeIds);
    }
}