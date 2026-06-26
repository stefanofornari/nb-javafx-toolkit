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

import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.openide.modules.ModuleInstall;
import org.openide.modules.OnStart;


@OnStart
public class Install extends ModuleInstall implements Runnable {

    private final static Logger LOG = Logger.getLogger(Install.class.getName());

    @Override
    public void run() {
        LOG.info(() -> "Starting NetBeans JavaFX Toolkit");

        LOG.info("Setting up toolkit host services");
        FXLauncherStub.registerCallback(JFXPanel.hostServices);
        Thread launchThread = new Thread(() -> {
            try {
                Application.launch(FXLauncherStub.class);
            } catch (IllegalStateException e) {
                LOG.log(Level.INFO, "JavaFX alrady initialized", e);
            }
        }, "FX-HostServices-Bootstrap");

        launchThread.setDaemon(true);

        try {
            new JFXPanel();  // initialize JavaFX
            Platform.setImplicitExit(false);
            launchThread.start();
            LOG.info("NetBeans JavaFX Toolkit initialized");
        } catch (Throwable t) {
            t.printStackTrace();
            LOG.log(Level.SEVERE, "Error in NetBeans JavaFX Toolkit module restoration", t);
        }
    }

    public static class FXLauncherStub extends Application {
        private static CompletableFuture<HostServices> future;

        public static void registerCallback(final CompletableFuture<HostServices> f) {
            future = f;
        }

        @Override
        public void start(Stage primaryStage) {
            if (future != null) {
                HostServices hs = getHostServices();
                LOG.info("NetBeans JavaFX Toolkit host services ready " + hs);
                future.complete(hs);
            }
            primaryStage.close(); // Clean up immediately without flashing a blank window
        }
    }

}
