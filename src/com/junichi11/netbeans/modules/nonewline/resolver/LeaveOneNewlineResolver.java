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
package com.junichi11.netbeans.modules.nonewline.resolver;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import org.netbeans.editor.BaseDocument;

/**
 * Leave one newline if there are over two newlines before EOF.
 *
 * @author junichi11
 */
class LeaveOneNewlineResolver implements Resolver {

    private final Document document;
    private static final Logger LOGGER = Logger.getLogger(LeaveOneNewlineResolver.class.getName());

    public LeaveOneNewlineResolver(Document document) {
        this.document = document;
    }

    @Override
    public void resolve() {
        if (document == null) {
            return;
        }

        String ls = (String) document.getProperty(BaseDocument.READ_LINE_SEPARATOR_PROP);
        if (ls == null || ls.isEmpty()) {
            return;
        }

        try {
            final String originalText = document.getText(0, document.getLength());
            String text = document.getText(0, document.getLength());
            while (text.endsWith(ls)) {
                text = text.substring(0, text.length() - ls.length());
            }
            text += ls;
            if (originalText.length() > text.length()) {
                document.remove(text.length(), originalText.length() - text.length());
            }
        } catch (BadLocationException ex) {
            LOGGER.log(Level.WARNING, null, ex);
        }
    }

}
