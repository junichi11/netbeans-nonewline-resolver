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

import com.junichi11.netbeans.modules.nonewline.resolver.options.NoNewlineResolverOptions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.text.Document;

/**
 *
 * @author junichi11
 */
public final class ResolverFactory {

    public enum Type {
        AddNewline, LeaveOneNewline
    }

    private ResolverFactory() {
    }

    /**
     * Create a resolver.
     *
     * @param document the document
     * @param type the resolver type
     * @return the resolver for the type
     */
    public static Resolver createResolver(Document document, Type type) {
        switch (type) {
            case AddNewline:
                return new NoNewlineResolver(document);
            case LeaveOneNewline:
                return new LeaveOneNewlineResolver(document);
            default:
                throw new AssertionError();
        }
    }

    /**
     * Create all resolvers.
     *
     * @param document the document
     * @return all resolvers
     */
    public static List<Resolver> createResolvers(Document document) {
        ArrayList<Resolver> resolvers = new ArrayList<>();
        if (document.getLength() == 0
                && NoNewlineResolverOptions.getInstance().doNotAddNewlineInEmptyFiles()) {
            return Collections.emptyList();
        }
        if (NoNewlineResolverOptions.getInstance().addNewline()) {
            resolvers.add(new NoNewlineResolver(document));
        }
        if (NoNewlineResolverOptions.getInstance().leaveOneNewline()) {
            resolvers.add(new LeaveOneNewlineResolver(document));
        }
        return resolvers;
    }

}
