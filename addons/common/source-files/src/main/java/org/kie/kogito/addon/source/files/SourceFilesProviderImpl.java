/*
 * Copyright 2022 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kie.kogito.addon.source.files;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class SourceFilesProviderImpl implements SourceFilesProvider {

    private final Map<String, Collection<SourceFile>> sourceFiles = new HashMap<>();

    public void addSourceFile(String id, SourceFile sourceFile) {
        sourceFiles.computeIfAbsent(id, k -> new HashSet<>()).add(sourceFile);
    }

    @Override
    public Collection<SourceFile> getSourceFiles(String id) {
        return sourceFiles.getOrDefault(id, Set.of());
    }

    public void clear() {
        sourceFiles.clear();
    }
}
