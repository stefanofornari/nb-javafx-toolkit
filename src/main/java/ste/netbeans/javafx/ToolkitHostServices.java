/*
 * Copyright 2026 ste.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ste.netbeans.javafx;

import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

/**
 *
 */
public class ToolkitHostServices {
    private final static Logger LOG = Logger.getLogger(ToolkitHostServices.class.getName());

    private final CompletableFuture<javafx.application.HostServices> hostServices = new CompletableFuture<>();

    public ToolkitHostServices() {
        LOG.info("setting up toolkit host services");
        // Start the background app thread, pointing to our internal static launcher
        Thread launchThread = new Thread(() -> {
            try {
                Application.launch(FXLauncherStub.class);
            } catch (IllegalStateException e) {
                // JavaFX runtime was already initialized elsewhere
            }
        }, "FX-HostServices-Bootstrap");
        launchThread.setDaemon(true);
        launchThread.start();

        // Connect the callback directly back to our local future container
        FXLauncherStub.registerCallback(hostServices);
    }

    public void openURL(final String url) {
        javafx.application.HostServices services = hostServices.getNow(null);
        if (services == null) {
            throw new IllegalStateException("ToolkitHostServices not ready yet");
        }
        Platform.runLater(() -> services.showDocument(url));
    }

    // --- INNER LAUNCHER STUB ---
    // Must be public and static so the JavaFX reflection engine can construct it
    public static class FXLauncherStub extends Application {
        private static CompletableFuture<javafx.application.HostServices> future;

        public static void registerCallback(final CompletableFuture<javafx.application.HostServices> f) {
            future = f;
        }

        @Override
        public void start(Stage primaryStage) {
            if (future != null) {
                javafx.application.HostServices hs = getHostServices();
                LOG.info("tolkit host services ready " + hs);
                future.complete(hs);
            }
            primaryStage.close(); // Clean up immediately without flashing a blank window
        }
    }
}
