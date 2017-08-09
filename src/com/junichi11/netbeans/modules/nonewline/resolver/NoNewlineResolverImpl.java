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
 *
 * @author junichi11
 */
class NoNewlineResolverImpl implements NoNewlineResolver {

    private final Document document;

    public NoNewlineResolverImpl(Document document) {
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

        int lsLength = ls.length();
        int documentLength = document.getLength();
        try {
            if (documentLength == 0) {
                document.insertString(documentLength, ls, null);
            } else if (documentLength >= lsLength) {
                String lastText = document.getText(documentLength - lsLength, lsLength);
                if (!ls.equals(lastText)) {
                    document.insertString(documentLength, ls, null);
                }
            }
        } catch (BadLocationException ex) {
            Logger.getLogger(NoNewlineResolverImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
