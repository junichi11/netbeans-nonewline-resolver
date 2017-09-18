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

import com.junichi11.netbeans.modules.nonewline.resolver.ResolverFactory.Type;
import javax.swing.text.BadLocationException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.netbeans.editor.BaseDocument;
import org.openide.util.Utilities;

/**
 *
 * @author junichi11
 */
public class NoNewlineResolverTest {

    private static final String LF = getLF();

    public NoNewlineResolverTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testResolveEmptyFile() throws BadLocationException {
        testResolve("", LF);
    }

    @Test
    public void testResolveNoNewline_01() throws BadLocationException {
        testResolve("test", "test" + LF);
    }

    @Test
    public void testResolveNoNewline_02() throws BadLocationException {
        testResolve(LF + "test", LF + "test" + LF);
    }

    @Test
    public void testResolveHasNewline_01() throws BadLocationException {
        testResolve(LF, LF);
    }

    @Test
    public void testResolveHasNewline_02() throws BadLocationException {
        testResolve("test" + LF, "test" + LF);
    }

    // LeaveOneNewlineResolver
    @Test
    public void testResolveLastMultipleNewlines_01() throws BadLocationException {
        testResolve("test", "test", Type.LeaveOneNewline);
    }

    @Test
    public void testResolveLastMultipleNewlines_02() throws BadLocationException {
        testResolve("test" + LF, "test" + LF, Type.LeaveOneNewline);
    }

    @Test
    public void testResolveLastMultipleNewlines_03() throws BadLocationException {
        testResolve("test" + LF + LF, "test" + LF, Type.LeaveOneNewline);
    }

    @Test
    public void testResolveLastMultipleNewlines_04() throws BadLocationException {
        testResolve("", "", Type.LeaveOneNewline);
    }

    @Test
    public void testResolveLastMultipleNewlines_05() throws BadLocationException {
        testResolve("" + LF, "" + LF, Type.LeaveOneNewline);
    }

    @Test
    public void testResolveLastMultipleNewlines_06() throws BadLocationException {
        testResolve("" + LF + LF, "" + LF, Type.LeaveOneNewline);
    }

    @Test
    public void testResolveLastMultipleNewlines_07() throws BadLocationException {
        testResolve("" + LF + LF + LF, "" + LF, Type.LeaveOneNewline);
    }

    private void testResolve(String text, String expectedResult) throws BadLocationException {
        testResolve(text, expectedResult, Type.AddNewline);
    }

    private void testResolve(String text, String expectedResult, Type type) throws BadLocationException {
        BaseDocument document = new BaseDocument(false, "text/plain");
        document.insertString(0, text, null);
        Resolver instance = ResolverFactory.createResolver(document, type);
        instance.resolve();
        assertEquals(expectedResult, document.getText(0, document.getLength()));
    }

    private static String getLF() {
        return Utilities.isWindows() ? "\r\n" : "\n";
    }

}
