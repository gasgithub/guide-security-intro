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
package io.openliberty.guides.ui;

import java.io.IOException;
import jakarta.inject.Inject;
import jakarta.security.enterprise.SecurityContext;
import jakarta.security.enterprise.authentication.mechanism.http.FormAuthenticationMechanismDefinition;
import jakarta.security.enterprise.authentication.mechanism.http.LoginToContinue;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/home")
// tag::AuthenticationMechanism[]
@FormAuthenticationMechanismDefinition(
    // tag::loginToContinue[]
    // tag::errorPage[]
    loginToContinue = @LoginToContinue(errorPage = "/error.html",
    // end::errorPage[]
                                        // tag::loginPage[]
                                       loginPage = "/welcome.html"))
                                        // end::loginPage[]
    // end::loginToContinue[]
// end::AuthenticationMechanism[]
// tag::ServletSecurity[]
// tag::HttpConstraint[]
@ServletSecurity(value = @HttpConstraint(rolesAllowed = { "user", "admin" },
// end::HttpConstraint[]
  // tag::TransportGuarantee[]
  transportGuarantee = ServletSecurity.TransportGuarantee.CONFIDENTIAL))
  // end::TransportGuarantee[]
// end::ServletSecurity[]
// tag::HomeServlet[]
public class HomeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Inject
    private SecurityContext securityContext;

    // tag::javaDoc1[]
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    // end::javaDoc1[]
    // tag::doGet[]
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        // tag::CallerInRole[]
        String roles = "";
        if (securityContext.isCallerInRole(Utils.ADMIN)) {
            roles = Utils.ADMIN;
        }
        if (securityContext.isCallerInRole(Utils.USER)) {
            if (!roles.isEmpty()) {
                roles += ", ";
            }
            roles += Utils.USER;
        }
        String username = securityContext.getCallerPrincipal().getName();

        UserBean ubean = new UserBean(username, roles);
        HttpSession session= request.getSession();
        session.setAttribute("userBean", ubean);
        System.out.println("adding user bean: " + ubean.getUsername());

        if (securityContext.isCallerInRole(Utils.ADMIN)) {
            response.sendRedirect("/admin.jsp");
        // end::CallerInRole[]
        } else if  (securityContext.isCallerInRole(Utils.USER)) {
            response.sendRedirect("/user.jsp");
        }
    }
    // end::doGet[]

    // tag::javaDoc2[]
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    // end::javaDoc2[]
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        doGet(request, response);
    }
}
// end::HomeServlet[]
