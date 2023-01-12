// tag::copyright[]
/*******************************************************************************
 * Copyright (c) 2018, 2022 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - Initial implementation
 *******************************************************************************/
// end::copyright[]
// tag::userbean[]
package io.openliberty.guides.ui;

import java.io.Serializable;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.security.enterprise.SecurityContext;


public class UserBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;
    private String roles = "";

    public UserBean(String u, String r){
        this.username = u;
        this.roles = r;
    }

    public String getUsername() {
        return username;
    }

    public String getRoles() {
        return roles;
    }
}
// end::userbean[]
