/*
 * Copyright 2017 junichi11.
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
package com.junichi11.netbeans.modules.nonewline.resolver.options;

import java.util.prefs.Preferences;
import org.openide.util.NbPreferences;

/**
 *
 * @author junichi11
 */
public final class NoNewlineResolverOptions {

    private static final String NO_NEWLINE_RESOLVER = "no.newline.resolver"; // NOI18N
    private static final String ENABLED = "enabled"; // NOI18N

    private static final NoNewlineResolverOptions INSTANCE = new NoNewlineResolverOptions();

    private NoNewlineResolverOptions() {
    }

    public static NoNewlineResolverOptions getInstance() {
        return INSTANCE;
    }

    public void setEnabled(boolean isEnabled) {
        getPreferences().putBoolean(ENABLED, isEnabled);
    }

    public boolean isEnabled() {
        return getPreferences().getBoolean(ENABLED, true);
    }

    private Preferences getPreferences() {
        return NbPreferences.forModule(NoNewlineResolverOptions.class).node(NO_NEWLINE_RESOLVER);
    }

}
