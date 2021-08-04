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

import java.util.prefs.PreferenceChangeEvent;
import java.util.prefs.PreferenceChangeListener;
import java.util.prefs.Preferences;
import org.openide.util.NbPreferences;

/**
 *
 * @author junichi11
 */
public final class NoNewlineResolverOptions implements PreferenceChangeListener {

    private static final String NO_NEWLINE_RESOLVER = "no.newline.resolver"; // NOI18N
    private static final String ENABLED = "enabled"; // NOI18N
    private static final String ADD_NEWLINE = "add.newline"; // NOI18N
    private static final String LEAVE_ONE_NEWLINE = "leave.one.newline"; // NOI18N
    private static final String DO_NOT_ADD_NEWLINE_IN_EMPTY_FILES = "do.not.add.in.empty.files"; // NOI18N

    private static final NoNewlineResolverOptions INSTANCE = new NoNewlineResolverOptions();

    private final Cache cache = new Cache();

    static {
        INSTANCE.getPreferences().addPreferenceChangeListener(INSTANCE);
        INSTANCE.refreshCache(null);
    }

    private NoNewlineResolverOptions() {
    }

    public static NoNewlineResolverOptions getInstance() {
        return INSTANCE;
    }

    public void setEnabled(boolean isEnabled) {
        getPreferences().putBoolean(ENABLED, isEnabled);
    }

    public boolean isEnabled() {
        return cache.isEnabled();
    }

    public void setAddNewLine(boolean add) {
        getPreferences().putBoolean(ADD_NEWLINE, add);
    }

    public boolean addNewline() {
        return cache.addNewline();
    }

    public void setLeaveOneNewline(boolean remove) {
        getPreferences().putBoolean(LEAVE_ONE_NEWLINE, remove);
    }

    public boolean leaveOneNewline() {
        return cache.leaveOneNewline();
    }

    public void setDoNotAddNewLineInEmptyFiles(boolean doNotAdd) {
        getPreferences().putBoolean(DO_NOT_ADD_NEWLINE_IN_EMPTY_FILES, doNotAdd);
    }

    public boolean doNotAddNewlineInEmptyFiles() {
        return cache.doNotAddNewlineInEmptyFiles();
    }

    private void refreshCache(String key) {
        if (key == null || ENABLED.equals(key)) {
            cache.setEnabled(getPreferences().getBoolean(ENABLED, true));
        }
        if (key == null || ADD_NEWLINE.equals(key)) {
            cache.setAddNewline(getPreferences().getBoolean(ADD_NEWLINE, true));
        }
        if (key == null || LEAVE_ONE_NEWLINE.equals(key)) {
            cache.setLeaveOneNewline(getPreferences().getBoolean(LEAVE_ONE_NEWLINE, false));
        }
        if (key == null || DO_NOT_ADD_NEWLINE_IN_EMPTY_FILES.equals(key)) {
            cache.setDoNotAddNewlineInEmptyFiles(getPreferences().getBoolean(DO_NOT_ADD_NEWLINE_IN_EMPTY_FILES, false));
        }
    }

    private Preferences getPreferences() {
        return NbPreferences.forModule(NoNewlineResolverOptions.class).node(NO_NEWLINE_RESOLVER);
    }

    @Override
    public void preferenceChange(PreferenceChangeEvent evt) {
        refreshCache(evt.getKey());
    }

    private static final class Cache {

        private volatile boolean enabled;
        private volatile boolean addNewline;
        private volatile boolean leaveOneNewline;
        private volatile boolean doNotAddNewlineInEmptyFiles;

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public boolean addNewline() {
            return addNewline;
        }

        public void setAddNewline(boolean addNewline) {
            this.addNewline = addNewline;
        }

        public boolean leaveOneNewline() {
            return leaveOneNewline;
        }

        public void setLeaveOneNewline(boolean leaveOneNewline) {
            this.leaveOneNewline = leaveOneNewline;
        }

        public boolean doNotAddNewlineInEmptyFiles() {
            return doNotAddNewlineInEmptyFiles;
        }

        public void setDoNotAddNewlineInEmptyFiles(boolean doNotAddNewlineInEmptyFiles) {
            this.doNotAddNewlineInEmptyFiles = doNotAddNewlineInEmptyFiles;
        }

        @Override
        public String toString() {
            return "Cache{" // NOI18N
                    + "enabled=" + enabled // NOI18N
                    + ", addNewline=" + addNewline // NOI18N
                    + ", leaveOneNewline=" + leaveOneNewline // NOI18N
                    + ", doNotAddNewlineInEmptyFiles=" + doNotAddNewlineInEmptyFiles // NOI18N
                    + '}';
        }

    }
}
