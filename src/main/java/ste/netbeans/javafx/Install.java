/**
 * Copyright 2026 the original author or authors from the JavaFX Toolkit for
 * NetBeans project (Jeddict project (https://nb-javafx-toolkit.github.io/).
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package ste.netbeans.javafx;

import atlantafx.base.theme.PrimerLight;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import org.openide.modules.ModuleInstall;
import org.openide.modules.OnStart;


@OnStart
public class Install extends ModuleInstall implements Runnable {

    private final Logger LOG = Logger.getLogger(getClass().getName());

    @Override
    public void run() {
        LOG.info(() -> "Starting NetBeans JavaFX Toolkit");

        try {
            Platform.setImplicitExit(false);
            Platform.startup(() -> {
                Application.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());
            });
        } catch (Throwable t) {
            LOG.log(Level.SEVERE, "Error in NetBeans JavaFX Toolkit module restoration", t);
        }
    }

}
