<%@ page language="java" contentType="text/html; charset=UTF-8" 
import="io.openliberty.guides.ui.*"%>
<!DOCTYPE html>
<!--
  Copyright (c) 2018 IBM Corp.

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
-->
<html>
<head>
    <title>Administrator</title>
</head>
<body>
    <h2>Administrator</h2>
        <FORM METHOD="POST" ACTION="ibm_security_logout" NAME="logout">
          <input type="submit" name="logout" value="Log Out" />
          <input type="HIDDEN" name="logoutExitPage" value="/home" />
        </FORM>
        Username: ${userBean.username} 
        Roles: ${userBean.roles}
</body>
</html>
